package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.common.util.ExceptionUtil;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.security.service.UserAccountService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;

/**
 * The Class ContactGroupMailMessageItemWriter.
 */
public class ContactGroupMailMessageItemWriter implements ItemWriter<ContactGroupMailMessage>, InitializingBean
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactGroupMailMessageItemWriter.class);

    /** The mail sender. */
    private JavaMailSender mailSender;

    /** The email contact group service. */
    private EmailContactGroupService emailContactGroupService;

    /** The email server service. */
    private EmailServerService emailServerService;

    /** The email server properties service. */
    private EmailServerPropertiesService emailServerPropertiesService;

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
     * Sets the email contact group service.
     *
     * @param emailContactGroupService the new email contact group service
     */
    public void setEmailContactGroupService(EmailContactGroupService emailContactGroupService)
    {
        this.emailContactGroupService = emailContactGroupService;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws IllegalStateException
    {
        Assert.state(this.mailSender != null, "A MailSender must be provided.");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    public void write(List<? extends ContactGroupMailMessage> items)
            throws MailSendException, MessagingException, BusinessException
    {
        List<EmailContactGroup> emailContactGroups = new ArrayList<>();
        Map<String, List<String>> errorListMap = new HashMap<>();
        try
        {
            LOGGER.debug("Preparing to send emails through Item Writer");

            List<EmailServer> emailServers = this.emailServerService.findAll();
            Map<Long, Properties> emailServerPropertiesByServers = new HashMap<>();
            Properties oldMailProperties = null;
            if ((this.mailSender instanceof JavaMailSenderImpl))
            {
                oldMailProperties = ((JavaMailSenderImpl) this.mailSender).getJavaMailProperties();
                LOGGER.debug("Old properties : " + oldMailProperties);
                if (emailServers != null)
                {
                    List<EmailServerProperties> emailServerPropertiesOfServers = this.emailServerPropertiesService
                            .findByEmailServers(emailServers);
                    LOGGER.debug("Email servers found.. Searching for properties..");
                    for (EmailServerProperties emailServerProperty : emailServerPropertiesOfServers)
                    {
                        if (emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId()) != null)
                        {
                            (emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId()))
                                    .put(emailServerProperty.getPropertyName(), emailServerProperty.getValue());
                        }
                        else
                        {
                            Properties properties = new Properties();
                            properties.put(emailServerProperty.getPropertyName(), emailServerProperty.getValue());
                            emailServerPropertiesByServers.put(emailServerProperty.getEmailServer().getId(),
                                    properties);
                        }
                    }
                    for (final Map.Entry<Long, Properties> entry : emailServerPropertiesByServers.entrySet())
                    {
                        final Properties properties = entry.getValue();
                        if (oldMailProperties != null && oldMailProperties.size() > 0)
                        {
                            for (final Object key : oldMailProperties.keySet())
                            {
                                if (properties.get(key) == null)
                                {
                                    properties.put(key, oldMailProperties.get(key));
                                }
                            }
                        }
                    }
                }
            }

            int count = 0;
            int toltalMessagesCount = 0;
            int mailServerCount = 0;
            MimeMessage[] messages = null;
            for (final ContactGroupMailMessage contactGroupMailMessage : items)
            {
                LOGGER.debug("Adding emailcntactgroup : " + contactGroupMailMessage.getEmailContactGroup() + " to "
                        + contactGroupMailMessage);

                emailContactGroups.add(contactGroupMailMessage.getEmailContactGroup());
                if ((emailServers != null) && (!emailServers.isEmpty()))
                {
                    LOGGER.debug("Looping through mail servers..");
                    if (mailServerCount < emailServers.size())
                    {
                        // get each configured email server and send mails
                        final EmailServer emailServer = emailServers.get(mailServerCount);
                        if ((this.mailSender instanceof JavaMailSenderImpl))
                        {
                            LOGGER.debug("Valid mail configurations found..Setting properties..");
                            if (emailServerPropertiesByServers.get(emailServer.getId()) != null)
                            {
                                ((JavaMailSenderImpl) this.mailSender)
                                        .setJavaMailProperties(emailServerPropertiesByServers.get(emailServer.getId()));
                            }
                            else
                            {
                                LOGGER.debug("No server properties found.. using default old properties - "
                                        + oldMailProperties);
                                ((JavaMailSenderImpl) this.mailSender).setJavaMailProperties(oldMailProperties);
                            }
                        }
                        if (count == 0)
                        {
                            messages = new MimeMessage[emailServer.getMailsPerSession().intValue()];
                        }
                        messages[count] = contactGroupMailMessage.getMimeMessage();
                        if ((count == emailServer.getMailsPerSession().intValue() - 1)
                                || (toltalMessagesCount == items.size() - 1))
                        {
                            int numberOfSuccessfulEmails = 0;
                            for (MimeMessage message : messages)
                            {
                                if (message != null)
                                {
                                    String recipients = getAllRecipients(message);
                                    try
                                    {
                                        this.mailSender.send(message);
                                        numberOfSuccessfulEmails++;
                                        LOGGER.info("Email triggered for " + recipients + " successfully");
                                    }
                                    catch (MailException ex)
                                    {
                                        LOGGER.error("Failed to trigger email for " + recipients + ", reason: ["
                                                + ExceptionUtil.getErrorRootCause(ex) + " ]");
                                        if (!errorListMap.containsKey(ExceptionUtil.getErrorRootCause(ex)))
                                        {
                                            errorListMap.put(ExceptionUtil.getErrorRootCause(ex), new ArrayList<>());
                                        }
                                        errorListMap.get(ExceptionUtil.getErrorRootCause(ex)).add(recipients);
                                    }
                                }
                            }
                            
                            LOGGER.info("[Via Configured Mail Server] - Sent email to " + numberOfSuccessfulEmails
                                    + " contacts successfully. Check Bounced Watcher logs for details on Bounced emails captured, if any..");

                            count = 0;
                            if (!errorListMap.isEmpty())
                            {
                                pushErrorsToCache(errorListMap, contactGroupMailMessage.getRequestBatchId(),
                                        numberOfSuccessfulEmails);
                            }
                        }
                        else
                        {
                            count++;
                        }
                        toltalMessagesCount++;
                        if ((mailServerCount == emailServers.size()) && (toltalMessagesCount < items.size()))
                        {
                            mailServerCount = 0;
                        }
                    }
                }
                else
                {
                    if (count == 0)
                    {
                        messages = new MimeMessage[items.size()];
                    }
                    messages[count] = contactGroupMailMessage.getMimeMessage();
                    count++;
                }
            }

            LOGGER.debug("Creating email contact groups through service..");
            this.emailContactGroupService.createEmailContactGroups(emailContactGroups);
            if (((emailServers == null) || (emailServers.isEmpty())) && (messages != null))
            {
                LOGGER.debug("No Servers configured in application. Sending the entire bunch of " + messages.length
                        + " messages through default mailSender : " + this.mailSender);

                int numberOfSuccessfulEmails = 0;
                for (MimeMessage message : messages)
                {
                    String recipients = getAllRecipients(message);
                    try
                    {
                        this.mailSender.send(message);
                        numberOfSuccessfulEmails++;
                        LOGGER.info("[Default Mail Sender] - Email triggered for " + getAllRecipients(message)
                                + " successfully");
                    }
                    catch (MailException ex)
                    {
                        LOGGER.error("[Default Mail Sender]: Failed to trigger email for " + getAllRecipients(message)
                                + ", reason: [" + ExceptionUtil.getErrorRootCause(ex) + " ]");
                        if (!errorListMap.containsKey(ExceptionUtil.getErrorRootCause(ex)))
                        {
                            errorListMap.put(ExceptionUtil.getErrorRootCause(ex), new ArrayList<>());
                        }
                        errorListMap.get(ExceptionUtil.getErrorRootCause(ex)).add(recipients);
                    }
                }
                LOGGER.info("[Default Mail Sender] - Sent email to " + numberOfSuccessfulEmails
                        + " contacts successfully. Check Bounced Watcher logs for details on Bounced emails captured, if any..");

            }
            if ((oldMailProperties != null) && (oldMailProperties.size() > 0)
                    && ((this.mailSender instanceof JavaMailSenderImpl)))
            {
                ((JavaMailSenderImpl) this.mailSender).setJavaMailProperties(oldMailProperties);
            }
        }
        catch (MailSendException e)
        {
            LOGGER.error("Mail send exception : " + e.getMessage());
            throw new RuntimeException("Job execution Failed", e);
        }
        catch (BusinessException e)
        {
            LOGGER.error("Business Exception : " + e.getMessage());
            throw new RuntimeException("Job execution Failed", e);
        }
        catch (Exception e)
        {
            LOGGER.error("Exception : " + e.getMessage());
            throw new RuntimeException("Job execution Failed", e);
        }
    }

    /**
     * @param errorListMap
     * @param contactGroupMailMessage
     * @param numberOfSuccessfulEmails
     * @throws MailSendException
     */
    private void pushErrorsToCache(Map<String, List<String>> errorListMap, final String requestBatchId,
            int numberOfSuccessfulEmails) throws MailSendException
    {
        Entry<String, List<String>> error = errorListMap.entrySet().iterator().next();
        if (numberOfSuccessfulEmails == 0)
        {
            throw new MailSendException(error.getKey() + " - [No emails could be sent]");
        }

        StringBuilder capturedErrors = new StringBuilder();
        int failedContactSize = 0;
        for (Map.Entry<String, List<String>> entry : errorListMap.entrySet())
        {
            capturedErrors.append(entry.getKey()).append(" | ");
            failedContactSize = failedContactSize + entry.getValue().size();
        }
        LOGGER.info("Sent email to " + numberOfSuccessfulEmails
                + " contacts successfully. Check Bounced Watcher logs for Bounced notifications");

        CommonUtilCache.getRequestIdVsErrorMap().put(requestBatchId,
                "Job completed but failed to send emails to " + failedContactSize + " contacts. [Error: "
                        + capturedErrors.substring(0, capturedErrors.lastIndexOf(" | ")).toString() + "]");
    }

    /**
     * Gets the all recipients.
     *
     * @param message the message
     * @return the all recipients
     */
    private String getAllRecipients(MimeMessage message)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            for (Address address : message.getAllRecipients())
            {
                sb.append(address.toString()).append(",");
            }
        }
        catch (MessagingException e)
        {
            LOGGER.error("Error while fetching recipient list, reason: [" + ExceptionUtil.getErrorRootCause(e) + " ]");
        }
        return sb.substring(0, sb.lastIndexOf(",")).toString();
    }

    /**
     * Sets the email server service.
     *
     * @param emailServerService the new email server service
     */
    public void setEmailServerService(EmailServerService emailServerService)
    {
        this.emailServerService = emailServerService;
    }

    /**
     * Sets the email server properties service.
     *
     * @param emailServerPropertiesService the new email server properties service
     */
    public void setEmailServerPropertiesService(EmailServerPropertiesService emailServerPropertiesService)
    {
        this.emailServerPropertiesService = emailServerPropertiesService;
    }

    /**
     * Sets the user account service.
     *
     * @param userAccountService the new user account service
     */
    public void setUserAccountService(UserAccountService userAccountService)
    {
    }
}
