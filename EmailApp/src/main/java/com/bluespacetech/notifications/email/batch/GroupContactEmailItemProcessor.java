package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.common.util.Base64ToImageDecodeHelper;
import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.service.BlockedContactService;
import com.bluespacetech.core.crypto.Decryptor;
import com.bluespacetech.core.crypto.Encryptor;
import com.bluespacetech.notifications.email.entity.BlueSpaceMimeMessage;
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.repository.EmailRepository;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.util.EmailUtils;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.notifications.email.validators.EmailMXRecordDNSValidator;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.security.model.CompanyRegistration;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.repository.UserAccountRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * The Class GroupContactEmailItemProcessor.
 */
public class GroupContactEmailItemProcessor implements ItemProcessor<EmailContactGroupVO, ContactGroupMailMessage>
{

    /** The email request URL. */
    private String emailRequestURL;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(GroupContactEmailItemProcessor.class);

    /** The mail sender. */
    private JavaMailSender mailSender;

    /** The request batch id. */
    private String requestBatchId;

    /** The velocity engine. */
    @Autowired
    private VelocityEngine velocityEngine;

    /** The blocked contact service. */
    @Autowired
    private BlockedContactService blockedContactService;

    /** The email repository. */
    @Autowired
    private EmailRepository emailRepository;

    /** The user account repo. */
    @Autowired
    private UserAccountRepository userAccountRepo;

    /** The template configuration. */
    @Autowired
    private MailTemplateConfiguration templateConfiguration;

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    public ContactGroupMailMessage process(EmailContactGroupVO emailContactGroupVO) throws Exception
    {
        if (CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().containsKey(emailContactGroupVO.getEmailId()))
        {
            if ((CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().get(emailContactGroupVO.getEmailId()))
                    .contains(emailContactGroupVO.getContactEmail()))
            {
                LOGGER.info("Email " + emailContactGroupVO.getContactEmail()
                        + " has already been selected once for this campaign. Avoiding duplicate emails to be sent to the same contact");

                return null;
            }
        }
        LOGGER.debug("Processing ContactGroupMailMesaage for - " + emailContactGroupVO);

        String email = emailContactGroupVO.getContactEmail();
        if ((email == null) || (email.trim().isEmpty()))
        {
            return null;
        }
        if (CommonUtilCache.getIgnoreList().contains(email.substring(0, email.indexOf("@") + 1)))
        {
            LOGGER.warn("Email " + email + " falls under IGNORED LIST, will be skipped");
            addEmailToBlockedList(email, emailContactGroupVO);
            return null;
        }
        List<BlockedContacts> blocked = this.blockedContactService.findByEmail(email);
        if ((blocked != null) && (!blocked.isEmpty()))
        {
            String reason = ((BlockedContacts) blocked.get(0)).getReason();
            if (reason.contains("BOUNCE"))
            {
                LOGGER.warn("Blocking send emails to Previously Bounced Contact " + email
                        + " as it is blacklisted (found from DB), reason : [" + reason + "]");

                return null;
            }
        }
        String mxRecord = EmailMXRecordDNSValidator.validateMxRecord(email.trim());
        if ((mxRecord == null) || (mxRecord.trim().isEmpty()))
        {
            LOGGER.warn("No MX records found for email " + email + ", Potential candidate for blacklist");
            boolean recordFound = false;
            for (BlockedContacts contacts : blocked)
            {
                if (contacts.getEmail().equalsIgnoreCase(email))
                {
                    recordFound = true;
                }
            }
            if (!recordFound)
            {
                addEmailToBlockedList(email, emailContactGroupVO);
                LOGGER.info("Blacklisted " + email + " successfully");
            }
            return null;
        }
        LOGGER.info("MX Records validated for " + email + " : " + mxRecord);
        List<BlockedContacts> contact = this.blockedContactService.findBlockedContactByEmailAndReason(email,
                "INVALID_MX_RECORDS");
        if ((contact != null) && (!contact.isEmpty()))
        {
            for (BlockedContacts c : contact)
            {
                this.blockedContactService.remove(c);
                CommonUtilCache.getBlacklistedContactEmails().remove(c.getEmail());
                LOGGER.info("Removed re-validated contact " + c + " successfully from blacklist");
            }
        }
        String[] splitEmail = email.split("@");
        if ((splitEmail.length == 2) && (CommonUtilCache.getBlacklistedDomainList().contains(splitEmail[1].trim())))
        {
            LOGGER.warn("Contact domain (DNS) matches a  SPAM / Blacklisted entry in cache. Email sending is blocked");

            return null;
        }
        Random randomno = new Random();
        long value = randomno.nextLong();

        String splitRef = this.emailRequestURL.substring(0, this.emailRequestURL.indexOf("/emails"));
        String unsubscribeLink = EmailUtils.generateUnsubscribeLink(emailContactGroupVO, splitRef);
        String fullUnsubscribeLink = EmailUtils.generateFullUnsubscribeLink(emailContactGroupVO, splitRef);

        String subscribeLink = EmailUtils.generateSubscribeLink(emailContactGroupVO, splitRef);
        String readMailImageSRC = EmailUtils.generateReadMailImageSRC(emailContactGroupVO, splitRef,
                Long.valueOf(value));

        LOGGER.debug("footerLightText : " + this.templateConfiguration.getFooterLightText());
        LOGGER.debug("footerDarkText : " + this.templateConfiguration.getFooterDarkText());

        VelocityContext context = new VelocityContext();
        String message = emailContactGroupVO.getMessage();

        ContactGroupMailMessage contactGroupMailMessage = new ContactGroupMailMessage();
        MimeMessage msg = mailSender.createMimeMessage();

        BlueSpaceMimeMessage mimeMessage = new BlueSpaceMimeMessage(msg.getSession());
        if ((this.requestBatchId != null) && (!this.requestBatchId.trim().isEmpty()))
        {
            if (this.requestBatchId.contains("|"))
            {
                String[] splitJobId = this.requestBatchId.split("\\|");
                mimeMessage.addHeader("X-JOB_ID", splitJobId[0]);
            }
            else
            {
                mimeMessage.addHeader("X-JOB_ID", this.requestBatchId);
            }
        }
        mimeMessage.setCampaignId(emailContactGroupVO.getEmailId().longValue());
        mimeMessage.setContactId(emailContactGroupVO.getContactId().longValue());
        mimeMessage.setEncryptedEmail(Encryptor.Encrypt(emailContactGroupVO.getContactEmail()));
        mimeMessage.setDomainName("hireswing.net");
        mimeMessage.saveChanges();

        MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
        FileSystemResource resource = null;
        List<Base64ToImageDecodeHelper> objList = null;
        if (message.contains("<img src="))
        {
            LOGGER.info("Found image.. resolving and setting as inline in mime message");
            objList = getFormattedEmailForImages(message, emailContactGroupVO.getEmailId());
            if (objList != null)
            {
                for (Base64ToImageDecodeHelper obj : objList)
                {
                    LOGGER.info("Object successfully populated after image formatting.. : " + obj);
                    if (obj.getReplacedText().startsWith("<img"))
                    {
                        message = message.replace(obj.getReplacedText(),
                                "<img src=\"cid:" + obj.getImageIdentifierKey() + "\">");
                    }
                    else
                    {
                        message = message.replace(obj.getReplacedText(),
                                "src=\"cid:" + obj.getImageIdentifierKey() + "\"");
                    }
                    if (!CommonUtilCache.getTempFileCleanupMap().containsKey(emailContactGroupVO.getEmailId()))
                    {
                        CommonUtilCache.getTempFileCleanupMap().put(emailContactGroupVO.getEmailId(), new ArrayList<>());
                    }
                    CommonUtilCache.getTempFileCleanupMap().get(emailContactGroupVO.getEmailId())
                            .add(obj.getClasspathToImage());
                    LOGGER.info("Temp Image path " + obj.getClasspathToImage() + " saved to cleanup cache..");
                }
            }
        }
        LOGGER.debug("Re-Formatted email text : " + message);

        Long emailId = emailContactGroupVO.getEmailId();
        Email emailObj = this.emailRepository.findById(emailId);
        String sender = emailObj.getCreatedUser();
        UserAccount senderAccount = this.userAccountRepo.findUserAccountByUsername(sender);
        String senderEmail = senderAccount.getEmail();
        String senderAddress = senderAccount.getAddressLine1() + " " + senderAccount.getAddressLine2();
        String companyName = "";

        CompanyRegistration company = senderAccount.getCompanyRegistration();
        if (company != null)
        {
            companyName = company.getCompanyName();
        }
        String senderName = senderAccount.getFirstName() + " " + senderAccount.getLastName();

        context.put("userName", emailContactGroupVO.getContactFirstName());
        context.put("emailText", message);
        context.put("unsubscribe", unsubscribeLink);
        context.put("fullUnsubscribe", fullUnsubscribeLink);
        context.put("subscribe", subscribeLink);
        context.put("readMailImageSRC", readMailImageSRC);
        context.put("footerLightText", this.templateConfiguration.getFooterLightText());
        context.put("footerDarkText", this.templateConfiguration.getFooterDarkText());

        context.put("domainName", senderAccount.getEmail());
        context.put("senderName", senderName);
        context.put("companyName", companyName);
        context.put("senderAddress", senderAddress);
        context.put("senderEmail", senderEmail);
        context.put("addressAndName", senderAddress + " - " + companyName);
        context.put("contextRoot", "http://www.hireswing.com");

        StringWriter writer = new StringWriter();
        this.velocityEngine.mergeTemplate("velocityTemplates/SimpleEmail.vm", "UTF-8", context, writer);
        String text = writer.toString();

        simpleMailMessage.setTo(emailContactGroupVO.getContactEmail());

        InternetAddress address = new InternetAddress(senderAccount.getEmail());
        address.setPersonal(senderName);
        simpleMailMessage.getMimeMessage().setFrom(address);
        simpleMailMessage.getMimeMessage().setReplyTo(new Address[] { address });

        simpleMailMessage.setSubject(emailContactGroupVO.getSubject());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText(text, true);
        if (objList != null)
        {
            for (Base64ToImageDecodeHelper obj : objList)
            {
                resource = new FileSystemResource(obj.getClasspathToImage().toString());
                LOGGER.debug(resource.getPath() + " | " + resource.exists() + " | " + resource.getFilename());
                simpleMailMessage.addInline(obj.getImageIdentifierKey(), resource);
            }
        }
        FileSystemResource logo = new FileSystemResource("/opt/packages/Oracle/BluespaceMailer/data/newlogo.png");

        LOGGER.debug("Logo : " + logo.getFilename() + " | Exists ? " + logo.exists());
        simpleMailMessage.addInline("logo", logo);

        EmailContactGroup emailContactGroup = new EmailContactGroup();
        emailContactGroup.setContactId(emailContactGroupVO.getContactId());
        emailContactGroup.setGroupId(emailContactGroupVO.getGroupId());
        emailContactGroup.setRandomNumber(String.valueOf(value));
        emailContactGroup.setReadCount(Integer.valueOf(0));
        if (emailContactGroupVO.getEmailId() != null)
        {
            emailContactGroup.setEmailId(emailContactGroupVO.getEmailId());
            emailContactGroup.setMessage(text.getBytes());
            emailContactGroup.setSubject(emailContactGroupVO.getSubject());
        }
        else
        {
            LOGGER.warn("Missing information 'email id', mandatory to persist email_contact_group row into database. ");

            LOGGER.warn("Instance " + emailContactGroupVO + " will not be saved in database");
        }
        LOGGER.debug("Setting emailcontactgroup : " + emailContactGroup);
        contactGroupMailMessage.setEmailContactGroup(emailContactGroup);
        contactGroupMailMessage.setMimeMessage(mimeMessage);

        LOGGER.info("Finished processing email message for " + emailContactGroupVO);
        if (!CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().containsKey(emailContactGroupVO.getEmailId()))
        {
            LOGGER.info("Creating new entry for campaign id : " + emailContactGroupVO.getEmailId());
            CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().put(emailContactGroupVO.getEmailId(),
                    new HashSet<>());
        }
        CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().get(emailContactGroupVO.getEmailId())
                .add(emailContactGroupVO.getContactEmail());
        LOGGER.info("Added entry for " + emailContactGroupVO.getContactEmail() + " to campaign id : "
                + emailContactGroupVO.getEmailId());

        contactGroupMailMessage.setRequestBatchId(this.requestBatchId);
        return contactGroupMailMessage;
    }

    /**
     * Prints the headers.
     *
     * @param mimeMessage the mime message
     * @throws MessagingException the messaging exception
     */
    private void printHeaders(BlueSpaceMimeMessage mimeMessage) throws MessagingException
    {
        Enumeration<Header> headerEnum = mimeMessage.getAllHeaders();
        while (headerEnum.hasMoreElements())
        {
            Header header = (Header) headerEnum.nextElement();
            LOGGER.info("Header: " + header.getName() + " - " + header.getValue());
        }
    }

    /**
     * Gets the formatted email for images.
     *
     * @param message the message
     * @param emailId the email id
     * @return the formatted email for images
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<Base64ToImageDecodeHelper> getFormattedEmailForImages(String message, Long emailId) throws IOException
    {
        LOGGER.debug("Message : " + message);
        String regex = "<img([\\w\\W]+?)>";
        Pattern pattern = Pattern.compile(regex);
        List<Base64ToImageDecodeHelper> objList = new ArrayList<>();
        Matcher matcher = pattern.matcher(message);
        int i = 0;
        while (matcher.find())
        {
            i++;
            Base64ToImageDecodeHelper obj = new Base64ToImageDecodeHelper();
            String res = matcher.group();
            LOGGER.debug(res);

            String[] splitEncoded = res.split(",");
            if (splitEncoded.length == 1)
            {
                LOGGER.info("Found an image with actual url, not Base64 encoded : " + res);
                String newRegex = "src=\".*?\"";
                Pattern patternNew = Pattern.compile(newRegex);
                Matcher matcherNew = patternNew.matcher(res);
                if (matcherNew.find())
                {
                    String match = matcherNew.group();

                    String[] split = match.split("path=");

                    LOGGER.debug("Split text : " + split[0] + " | " + split[1]);
                    String path = Decryptor.Decrypt(split[1].substring(0, split[1].length() - 1));

                    obj.setImageIdentifierKey("image_" + i);
                    obj.setClasspathToImage(Paths.get(path, new String[0]));
                    obj.setReplacedText(match);
                    objList.add(obj);
                }
            }
            else
            {
                String encodedString = splitEncoded[1];
                if (encodedString.endsWith("\">"))
                {
                    encodedString = encodedString.substring(0, encodedString.length() - 2);
                }
                else
                {
                    encodedString = encodedString.substring(0, encodedString.length() - 1);
                }
                LOGGER.debug("Encoded : " + encodedString);
                byte[] decodedImg = Base64.getDecoder().decode(encodedString.getBytes(StandardCharsets.UTF_8));
                Resource resource = new FileSystemResource("/opt/packages/Oracle/BluespaceMailer/temp");
                LOGGER.debug("File System resource exists : " + resource.exists());
                File file = new File(resource.getFile(), "/tempImage_" + emailId + "_" + i + ".jpg");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(decodedImg);
                fos.close();
                obj.setImageIdentifierKey("image_" + i);
                obj.setClasspathToImage(file.toPath());

                obj.setReplacedText(res);
                objList.add(obj);
            }
        }
        return objList;
    }

    /**
     * Adds the email to blocked list.
     *
     * @param email the email
     * @param emailContactGroupVO the email contact group VO
     */
    private void addEmailToBlockedList(String email, EmailContactGroupVO emailContactGroupVO)
    {
        String reason = "INVALID_MX_RECORDS";
        List<BlockedContacts> contacts = this.blockedContactService.findBlockedContactByEmailAndReason(email, reason);
        if ((contacts == null) || (contacts.isEmpty()))
        {
            BlockedContacts contactToBlock = new BlockedContacts();
            contactToBlock.setEmail(email);
            contactToBlock.setFirstName(emailContactGroupVO.getContactFirstName());
            contactToBlock.setLastName(emailContactGroupVO.getContactLastName());
            contactToBlock.setReason(reason);
            this.blockedContactService.addBlockedContact(contactToBlock);
        }
        else
        {
            LOGGER.warn("Contact is already in blocked list");
        }
    }

    /**
     * Sets the email request URL.
     *
     * @param emailRequestURL the new email request URL
     */
    public void setEmailRequestURL(String emailRequestURL)
    {
        this.emailRequestURL = emailRequestURL;
    }

    /**
     * Sets the mail sender.
     *
     * @param mailSender the new mail sender
     */
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    /**
     * Sets the velocity engine.
     *
     * @param velocityEngine the new velocity engine
     */
    public void setVelocityEngine(VelocityEngine velocityEngine)
    {
        this.velocityEngine = velocityEngine;
    }

    /**
     * Gets the request batch id.
     *
     * @return the request batch id
     */
    public String getRequestBatchId()
    {
        return this.requestBatchId;
    }

    /**
     * Sets the request batch id.
     *
     * @param requestBatchId the new request batch id
     */
    public void setRequestBatchId(String requestBatchId)
    {
        this.requestBatchId = requestBatchId;
    }
}
