package com.bluespacetech.notifications.email.batch;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.repository.BlockedContactRepository;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.util.EmailUtils;
import com.bluespacetech.notifications.email.util.MailConfiguration;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.notifications.email.validators.EmailMXRecordDNSValidator;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

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

    /** The mail config. */
    @Autowired
    private MailConfiguration mailConfig;

    /** The blocked contact repository. */
    @Autowired
    private BlockedContactRepository blockedContactRepository;

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
            BlockedContacts blocked = blockedContactRepository.findByEmailIgnoreCase(email);
            if (blocked != null)
            {
                String reason = blocked.getReason();
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
                final String fullUnsubscribeLink = EmailUtils.generateFullUnsubscribeLink(emailContactGroupVO, splitRef);
                final String subscribeLink = EmailUtils.generateSubscribeLink(emailContactGroupVO, splitRef);
                final String readMailImageSRC = EmailUtils.generateReadMailImageSRC(emailContactGroupVO, splitRef,
                        value);

                LOGGER.debug("footerLightText : " + templateConfiguration.getFooterLightText());
                LOGGER.debug("footerDarkText : " + templateConfiguration.getFooterDarkText());

                VelocityContext context = new VelocityContext();
                context.put("userName", emailContactGroupVO.getContactFirstName());
                context.put("emailText", emailContactGroupVO.getMessage());
                context.put("unsubscribe", unsubscribeLink);
                context.put("fullUnsubscribe", fullUnsubscribeLink);
                context.put("subscribe", subscribeLink);
                context.put("readMailImageSRC", readMailImageSRC);
                context.put("footerLightText", templateConfiguration.getFooterLightText());
                context.put("footerDarkText", templateConfiguration.getFooterDarkText());

                StringWriter writer = new StringWriter();
                velocityEngine.mergeTemplate("velocityTemplates/SimpleEmail.vm", "UTF-8", context, writer);
                final String text = writer.toString();

                final ContactGroupMailMessage contactGroupMailMessage = new ContactGroupMailMessage();
                final MimeMessage mimeMessage = mailSender.createMimeMessage();

                final MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
                simpleMailMessage.setTo(emailContactGroupVO.getContactEmail());
                /*
                 * if (mailSender instanceof JavaMailSenderImpl) { System.out.println(((JavaMailSenderImpl) mailSender).getHost() + " | " + ((JavaMailSenderImpl) mailSender).getJavaMailProperties());
                 * String fromAddress = ((JavaMailSenderImpl) mailSender).getJavaMailProperties().getProperty("mail.from"); simpleMailMessage.setFrom(fromAddress); }
                 */

                simpleMailMessage.setFrom(mailConfig.getMailFrom());
                simpleMailMessage.setSubject(emailContactGroupVO.getSubject());
                simpleMailMessage.setSentDate(new Date());
                simpleMailMessage.setText(text, true);

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
     * Adds the email to blocked list.
     *
     * @param email the email
     * @param emailContactGroupVO the email contact group VO
     */
    private void addEmailToBlockedList(String email, EmailContactGroupVO emailContactGroupVO)
    {
        BlockedContacts contacts = blockedContactRepository.findByEmailIgnoreCase(email);
        if (contacts == null)
        {
            BlockedContacts contactToBlock = new BlockedContacts();
            contactToBlock.setEmail(email);
            contactToBlock.setFirstName(emailContactGroupVO.getContactFirstName());
            contactToBlock.setLastName(emailContactGroupVO.getContactLastName());
            contactToBlock.setReason("INVALID_MX_RECORDS");
            blockedContactRepository.save(contactToBlock);
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