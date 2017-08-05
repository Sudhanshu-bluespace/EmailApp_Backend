package com.bluespacetech.notifications.email.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

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

import com.bluespacetech.common.util.Base64ToImageDecodeHelper;
import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.service.BlockedContactService;
import com.bluespacetech.core.crypto.Decryptor;
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

    /** The velocity engine. */
    @Autowired
    private VelocityEngine velocityEngine;

    /** The blocked contact repository. */
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
    @Override
    public ContactGroupMailMessage process(final EmailContactGroupVO emailContactGroupVO) throws Exception
    {

        LOGGER.debug("Processing ContactGroupMailMesaage");

        String email = emailContactGroupVO.getContactEmail();
        if (email == null || email.trim().isEmpty())
        {
            return null;
        }
        else
        {
            List<BlockedContacts> blocked = blockedContactService.findByEmail(email);
            if (blocked != null && !blocked.isEmpty())
            {
                String reason = blocked.get(0).getReason();
                LOGGER.warn("Blocking send emails to Contact " + email + " as it is blacklisted, reason : [" + reason
                        + "]");
                return null;
            }
            else if (CommonUtilCache.getIgnoreList().contains(email.substring(0, email.indexOf("@") + 1)))
            {

                LOGGER.warn("Email " + email + " falls under IGNORED LIST, will be skipped");
                addEmailToBlockedList(email, emailContactGroupVO);
                return null;
            }
            else
            {
                String[] splitEmail = email.split("@");
                if (splitEmail.length == 2 && CommonUtilCache.getBlacklistedDomainList().contains(splitEmail[1].trim()))
                {
                    LOGGER.warn(
                            "Contact domain (DNS) matches a  SPAM / Blacklisted entry in cache. Email sending is blocked");
                    return null;
                }

                List<String> mxRecords = EmailMXRecordDNSValidator.validateMxRecord(email.trim());
                if (mxRecords == null || mxRecords.isEmpty())
                {
                    LOGGER.warn("No MX records found for email " + email + ", Potential candidate for blacklist");
                    addEmailToBlockedList(email, emailContactGroupVO);
                    return null;
                }
                else
                {
                    LOGGER.debug("MX Records validated for " + email + " : " + mxRecords);
                }

                final Random randomno = new Random();
                final long value = randomno.nextLong();

                String splitRef = emailRequestURL.substring(0, emailRequestURL.indexOf("/emails"));
                final String unsubscribeLink = EmailUtils.generateUnsubscribeLink(emailContactGroupVO, splitRef);
                final String fullUnsubscribeLink = EmailUtils.generateFullUnsubscribeLink(emailContactGroupVO,
                        splitRef);
                final String subscribeLink = EmailUtils.generateSubscribeLink(emailContactGroupVO, splitRef);
                final String readMailImageSRC = EmailUtils.generateReadMailImageSRC(emailContactGroupVO, splitRef,
                        value);

                LOGGER.debug("footerLightText : " + templateConfiguration.getFooterLightText());
                LOGGER.debug("footerDarkText : " + templateConfiguration.getFooterDarkText());

                VelocityContext context = new VelocityContext();
                String message = emailContactGroupVO.getMessage();

                final ContactGroupMailMessage contactGroupMailMessage = new ContactGroupMailMessage();
                final MimeMessage mimeMessage = mailSender.createMimeMessage();

                final MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
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
                            
                            if(obj.getReplacedText().startsWith("<img"))
                            {
                                message = message.replace(obj.getReplacedText(),
                                    "<img src=\"cid:" + obj.getImageIdentifierKey() + "\">");
                            }
                            else
                            {
                                message = message.replace(obj.getReplacedText(), "src=\"cid:"+obj.getImageIdentifierKey()+"\"");
                            }

                            if (!CommonUtilCache.getTempFileCleanupMap().containsKey(emailContactGroupVO.getEmailId()))
                            {
                                CommonUtilCache.getTempFileCleanupMap().put(emailContactGroupVO.getEmailId(),
                                        new ArrayList<>());
                            }
                            CommonUtilCache.getTempFileCleanupMap().get(emailContactGroupVO.getEmailId())
                                    .add(obj.getClasspathToImage());
                            LOGGER.info("Temp Image path " + obj.getClasspathToImage() + " saved to cleanup cache..");
                        }
                    }
                }

                LOGGER.info("Re-Formatted email text : " + message);

                Long emailId = emailContactGroupVO.getEmailId();
                Email emailObj = emailRepository.findById(emailId);
                String sender = emailObj.getCreatedUser();
                UserAccount senderAccount = userAccountRepo.findUserAccountByUsername(sender);
                String senderEmail = senderAccount.getEmail();
                String senderAddress = senderAccount.getAddressLine1()+" "+senderAccount.getAddressLine2();
                String companyName = "";
                String computedFromId = "";

                CompanyRegistration company = senderAccount.getCompanyRegistration();
                if (company != null)
                {
                    companyName = company.getCompanyName();
                }
                String[] splitEmailAddress = senderEmail.split("@");
                String senderName = senderAccount.getFirstName()+" "+senderAccount.getLastName();//splitEmailAddress[0];

                if (splitEmailAddress.length > 1)
                {
                    computedFromId = splitEmailAddress[1].split("\\.")[0];
                }
                // String senderMailAddress = userAccountRepo.findUserAccountByUsername(sender).getEmail();
                // System.out.println("Sender emall address : "+senderMailAddress);

                context.put("userName", emailContactGroupVO.getContactFirstName());
                context.put("emailText", message);
                context.put("unsubscribe", unsubscribeLink);
                context.put("fullUnsubscribe", fullUnsubscribeLink);
                context.put("subscribe", subscribeLink);
                context.put("readMailImageSRC", readMailImageSRC);
                context.put("footerLightText", templateConfiguration.getFooterLightText());
                context.put("footerDarkText", templateConfiguration.getFooterDarkText());
                context.put("domainName", computedFromId + "@hireswing.net");
                context.put("senderName", senderName);
                context.put("companyName", companyName);
                context.put("senderAddress", senderAddress);
                context.put("senderEmail", senderEmail);
                context.put("addressAndName", senderAddress + " - " + companyName);
                context.put("contextRoot", "http://www.hireswing.com");

                StringWriter writer = new StringWriter();
                velocityEngine.mergeTemplate("velocityTemplates/SimpleEmail.vm", "UTF-8", context, writer);
                final String text = writer.toString();

                simpleMailMessage.setTo(emailContactGroupVO.getContactEmail());

                /*
                 * if (mailSender instanceof JavaMailSenderImpl) { System.out.println(((JavaMailSenderImpl) mailSender).getHost() + " | " + ((JavaMailSenderImpl) mailSender).getJavaMailProperties());
                 * String fromAddress = ((JavaMailSenderImpl) mailSender).getJavaMailProperties().getProperty("mail.from"); simpleMailMessage.setFrom(fromAddress); }
                 */

                simpleMailMessage.getMimeMessage().setFrom(new InternetAddress(computedFromId + "@hireswing.com"));
                // simpleMailMessage.setFrom(senderMailAddress);
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
                LOGGER.info("Logo : "+logo.getFilename()+" | Exists ? "+logo.exists());
                simpleMailMessage.addInline("logo", logo);

                final EmailContactGroup emailContactGroup = new EmailContactGroup();
                emailContactGroup.setContactId(emailContactGroupVO.getContactId());
                emailContactGroup.setGroupId(emailContactGroupVO.getGroupId());
                emailContactGroup.setRandomNumber(value);
                emailContactGroup.setReadCount(0);
                if (emailContactGroupVO.getEmailId() != null)
                {
                    emailContactGroup.setEmailId(emailContactGroupVO.getEmailId());
                    emailContactGroup.setMessage(text.getBytes());
                    emailContactGroup.setSubject(emailContactGroupVO.getSubject());
                }
                else
                {
                    LOGGER.warn(
                            "Missing information 'email id', mandatory to persist email_contact_group row into database. ");
                    LOGGER.warn("Instance " + emailContactGroupVO + " will not be saved in database");
                }

                LOGGER.debug("Setting emailcontactgroup : " + emailContactGroup);
                contactGroupMailMessage.setEmailContactGroup(emailContactGroup);
                contactGroupMailMessage.setMimeMessage(mimeMessage);
                // contactGroupMailMessage.setPreparator(preparator);
                LOGGER.debug("Returning processed email message");

                return contactGroupMailMessage;
            }
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
        java.util.regex.Matcher matcher = pattern.matcher(message);
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
                // actual url.. not byte encoded..
                LOGGER.info("Found an image with actual url, not Base64 encoded : "+res);
                String newRegex = "src=\".*?\"";
                Pattern patternNew = Pattern.compile(newRegex);
                java.util.regex.Matcher matcherNew = patternNew.matcher(res);
                if(matcherNew.find())
                {
                    String match = matcherNew.group();
                    //System.out.println("Match found : "+match);
                    String[] split = match.split("path=");
                    
                    LOGGER.debug("Split text : " + split[0] + " | " + split[1]);
                    String path = Decryptor.Decrypt(split[1].substring(0, split[1].length() - 1));
                    
                    obj.setImageIdentifierKey("image_" + i);
                    obj.setClasspathToImage(Paths.get(path));
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
                // String output = message.replace(res, "<img src=\"cid:image_" + i + "\">");
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
        BlockedContacts contacts = blockedContactService.findBlockedContactByEmailAndReason(email, reason);
        if (contacts == null)
        {
            BlockedContacts contactToBlock = new BlockedContacts();
            contactToBlock.setEmail(email);
            contactToBlock.setFirstName(emailContactGroupVO.getContactFirstName());
            contactToBlock.setLastName(emailContactGroupVO.getContactLastName());
            contactToBlock.setReason(reason);
            blockedContactService.addBlockedContact(contactToBlock);
        }
        else
        {
            LOGGER.warn("Contact is already in blocked list");
        }
    }

    /**
     * Sets the email request URL.
     *
     * @param emailRequestURL the emailRequestURL to set
     */
    public void setEmailRequestURL(String emailRequestURL)
    {
        this.emailRequestURL = emailRequestURL;
    }

    /**
     * Sets the mail sender.
     *
     * @param mailSender the mailSender to set
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
}