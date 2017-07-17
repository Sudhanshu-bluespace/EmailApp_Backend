package com.bluespacetech.notifications.email.batch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.security.service.UserAccountService;

/**
 * The Class ContactGroupMailMessageItemWriter.
 * 
 * @author Sudhanshu Tripathy
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
     * A {@link JavaMailSender} to be used to send messages in {@link #write(List)}.
     *
     * @param mailSender the new mail sender
     */
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    /**
     * The handler for failed messages. Defaults to a {@link DefaultMailErrorHandler}.
     *
     * @param emailContactGroupService the new email contact group service
     */

    /**
     * @param emailContactGroupService the emailContactGroupService to set
     */
    public void setEmailContactGroupService(EmailContactGroupService emailContactGroupService)
    {
        this.emailContactGroupService = emailContactGroupService;
    }

    /**
     * Check mandatory properties (mailSender).
     * 
     * @throws IllegalStateException if the mandatory properties are not set
     * @see InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws IllegalStateException
    {
        Assert.state(mailSender != null, "A MailSender must be provided.");
    }

    /**
     * Write.
     *
     * @param items the items to send
     * @throws MailSendException the mail send exception
     * @throws MessagingException the messaging exception
     * @throws BusinessException the business exception
     * @see ItemWriter#write(List)
     */
    @Override
    public void write(List<? extends ContactGroupMailMessage> items)
            throws MailSendException, MessagingException, BusinessException
    {
        final List<EmailContactGroup> emailContactGroups = new ArrayList<EmailContactGroup>();
        try
        {
            LOGGER.debug("Processing email through Item Writer");

            final List<EmailServer> emailServers = emailServerService.findAll();
            final Map<Long, Properties> emailServerPropertiesByServers = new HashMap<Long, Properties>();
            Properties oldMailProperties = null;
            if (mailSender instanceof JavaMailSenderImpl)
            {
                oldMailProperties = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
                LOGGER.debug("Old properties : " + oldMailProperties);
                ((JavaMailSenderImpl) mailSender).getSession()
                        .setDebug(Boolean.getBoolean((String) oldMailProperties.get("mail.debug")));
                // final String userName = ViewUtil.getPrincipal();
                // final UserAccount userAccount =
                // userAccountService.findUserAccountByUsername(userName);
                // final String userEmail = userAccount.getEmail();
                if (emailServers != null)
                {
                    final List<EmailServerProperties> emailServerPropertiesOfServers = emailServerPropertiesService
                            .findByEmailServers(emailServers);
                    LOGGER.debug("Email servers found.. Searching for properties..");
                    for (final EmailServerProperties emailServerProperty : emailServerPropertiesOfServers)
                    {
                        if (emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId()) != null)
                        {
                            emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId())
                                    .put(emailServerProperty.getPropertyName(), emailServerProperty.getValue());
                        }
                        else
                        {
                            final Properties properties = new Properties();
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

            int count = 0, toltalMessagesCount = 0;
            int mailServerCount = 0;
            MimeMessage[] messages = null;// new MimeMessage[items.size()];
            // MimeMessagePreparator[] preparator = null;//new MimeMessagePreparator[items.size()];
            for (final ContactGroupMailMessage contactGroupMailMessage : items)
            {
                LOGGER.debug("Adding emailcntactgroup : " + contactGroupMailMessage.getEmailContactGroup() + " to "
                        + contactGroupMailMessage);
                emailContactGroups.add(contactGroupMailMessage.getEmailContactGroup());
                if (emailServers != null && !emailServers.isEmpty())
                {
                    // Loop though all email servers
                    LOGGER.debug("Looping through mail servers..");
                    if (mailServerCount < emailServers.size())
                    {
                        // get each configured email server and send mails
                        final EmailServer emailServer = emailServers.get(mailServerCount);
                        if (mailSender instanceof JavaMailSenderImpl)
                        {
                            LOGGER.debug("Valid mail configurations found..Setting properties..");
                            // contactGroupMailMessage.getPreparator()..getMimeMessage().setFrom(emailServer.getFromAddress());
                            if (emailServerPropertiesByServers.get(emailServer.getId()) != null)
                            {
                                ((JavaMailSenderImpl) mailSender)
                                        .setJavaMailProperties(emailServerPropertiesByServers.get(emailServer.getId()));
                            }
                            else
                            {
                                LOGGER.debug("No server properties found.. using default old properties");
                                ((JavaMailSenderImpl) mailSender).setJavaMailProperties(oldMailProperties);
                            }
                        }
                        // Initialize the messages array to mail server capacity for
                        // sending.
                        if (count == 0)
                        {
                            messages = new MimeMessage[emailServer.getMailsPerSession()];
                            // preparator = new MimeMessagePreparator[emailServer.getMailsPerSession()];
                        }

                        messages[count] = contactGroupMailMessage.getMimeMessage();
                        // preparator[count] = contactGroupMailMessage.getPreparator();
                        // Once number of messages reach mail server capacity or all
                        // the messages in the batch are completed, mail/send the
                        // messages.
                        if (count == emailServer.getMailsPerSession() - 1 || toltalMessagesCount == items.size() - 1)
                        {
                            LOGGER.debug("Sending emails through " + mailSender);
                            for (MimeMessage msg : messages)
                            {
                                if (msg != null)
                                {
                                    mailSender.send(msg);
                                    // mailSender.send(preparator);
                                    mailServerCount++;
                                }
                            }
                            count = 0;
                            LOGGER.debug("emails sent..");
                        }
                        else
                        {
                            count++;
                        }
                        toltalMessagesCount++;
                        if (mailServerCount == emailServers.size() && toltalMessagesCount < items.size())
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
                        // preparator = new MimeMessagePreparator[items.size()];
                    }
                    // preparator[count] = contactGroupMailMessage.getPreparator();
                    messages[count] = contactGroupMailMessage.getMimeMessage();
                    count++;
                }
            }
            LOGGER.debug("Creating email contact groups through service..");
            emailContactGroupService.createEmailContactGroups(emailContactGroups);
            if ((emailServers == null || emailServers.isEmpty()) && messages != null)
            {
                LOGGER.debug("No Servers configured in application. Sending the entire bunch of " + messages.length
                        + " messages through default mailSender : " + mailSender);
                mailSender.send(messages);
            }
            if (oldMailProperties != null && oldMailProperties.size() > 0 && mailSender instanceof JavaMailSenderImpl)
            {
                ((JavaMailSenderImpl) mailSender).setJavaMailProperties(oldMailProperties);
            }

            if (emailContactGroups != null && !emailContactGroups.isEmpty())
            {
                Long emailId = emailContactGroups.get(0).getEmailId();
                if (CommonUtilCache.getTempFileCleanupMap().containsKey(emailId))
                {
                    List<Path> tempFilePathList = CommonUtilCache.getTempFileCleanupMap().get(emailId);
                    for (Path path : tempFilePathList)
                    {
                        try
                        {
                            boolean deleted = Files.deleteIfExists(path);
                            LOGGER.info("Temp file " + path + " deleted successfully ? " + deleted);
                        }
                        catch (IOException e)
                        {
                            LOGGER.warn("Failed to delete temp file from " + path + ", reason: [" + e.getMessage()
                                    + "], Please delete manually");
                        }
                    }
                }
            }

        }
        catch (final MailSendException e)
        {
            LOGGER.error("Mail send exception : " + e.getMessage());
            throw new RuntimeException("Job execution Failed");
        }
        catch (final BusinessException e)
        {
            LOGGER.error("Business Exception : " + e.getMessage());
            throw new RuntimeException("Job execution Failed");
        }
        catch (final Exception e)
        {
            LOGGER.error("Exception : " + e.getMessage());
        }

    }

    /**
     * Sets the email server service.
     *
     * @param emailServerService the emailServerService to set
     */
    public void setEmailServerService(EmailServerService emailServerService)
    {
        this.emailServerService = emailServerService;
    }

    /**
     * Sets the email server properties service.
     *
     * @param emailServerPropertiesService the emailServerPropertiesService to set
     */
    public void setEmailServerPropertiesService(EmailServerPropertiesService emailServerPropertiesService)
    {
        this.emailServerPropertiesService = emailServerPropertiesService;
    }

    /**
     * Sets the user account service.
     *
     * @param userAccountService the userAccountService to set
     */
    public void setUserAccountService(UserAccountService userAccountService)
    {
    }

}
