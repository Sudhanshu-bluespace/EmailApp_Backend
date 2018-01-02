package com.bluespacetech.contact.bounce;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.common.util.ExceptionUtil;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.batch.EmailListener;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.service.BlockedContactService;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class MailChecker.
 */
public class MailChecker
{

    /** The contact repository. */
    private ContactRepository contactRepository;

    /** The blocked contact service. */
    private BlockedContactService blockedContactService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(MailChecker.class);

    /**
     * Instantiates a new mail checker.
     *
     * @param contactRepository the contact repository
     * @param blockedContactService the blocked contact service
     */
    public MailChecker(ContactRepository contactRepository, BlockedContactService blockedContactService)
    {
        this.contactRepository = contactRepository;
        this.blockedContactService = blockedContactService;
    }

    /**
     * Check.
     *
     * @param host the host
     * @param storeType the store type
     * @param user the user
     * @param password the password
     * @param port the port
     * @param storeProtocol the store protocol
     * @param starttlsEnabled the starttls enabled
     * @return the int
     */
    public int check(String host, String storeType, String user, String password, String port, String storeProtocol,
            String starttlsEnabled)
    {
        int numberOfSuccessfulBlackListings = 0;
        try
        {
            String emailRegexWrap = "Final-Recipient.*+";
            String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
            Pattern pattern = Pattern.compile(emailRegex);
            Pattern patternWrap = Pattern.compile(emailRegexWrap);

            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", port);
            properties.put("mail.pop3.starttls.enable", starttlsEnabled);
            properties.put("mail.pop3.socketFactory.port", port);
            properties.put("mail.pop3.socketFactory.fallback", "false");
            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.pop3.connectiontimeout", "10000");

            properties.put("mail.store.protocol", storeProtocol);

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3");

            store.connect(host, user, password);

            LOGGER.info("Successfully Connected to " + host + ", Reading INBOX...");

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.addMessageChangedListener(new EmailListener());
            emailFolder.open(2);

            Message[] messages = emailFolder.getMessages();
            if (messages == null)
            {
                LOGGER.info("No new messages fetched during current run.. Nothing to scan..");
                return 0;
            }
            LOGGER.info("Number of messages to scan: " + messages.length);

            SmtpScanner scanner = SmtpScanner.getInstance();
            int i = 0;
            for (int n = messages.length; i < n; i++)
            {
                String body = null;
                String subject = messages[i].getSubject();
                if (subject == null)
                {
                    LOGGER.info("Ignoring message with NULL subject... " + messages[i].toString());
                }
                else if ((subject != null)
                        && (subject.toLowerCase().equalsIgnoreCase("successful mail delivery report")))
                {
                    LOGGER.debug("Delivery Status Notification (Success), Skip Scan for Bounce..");
                    setDeleteFlagOnMessage(messages[i]);
                }
                else if (messages[i].isMimeType("text/plain"))
                {
                    body = messages[i].getContent().toString();
                    LOGGER.debug("[ Subject=" + messages[i].getSubject()
                            + "] Body is a plain text message.. Scanning for hard bounce error codes..");

                    String res = scanner.scanBody(body);
                    if ((res != null)
                            && ((res.toUpperCase().contains("BOUNCE")) || (res.toUpperCase().contains("|5."))))
                    {
                        LOGGER.info("Bounce detected " + res + " | email " + messages[i].getSubject() + ",recipients: "
                                + messages[i].getAllRecipients());
                        if (matchBodyPart(patternWrap, pattern, body, res))
                        {
                            numberOfSuccessfulBlackListings++;
                        }
                        setDeleteFlagOnMessage(messages[i]);
                        LOGGER.debug("Sent email [subject= " + messages[i].getSubject() + "] for deletion..");
                    }
                    else
                    {
                        LOGGER.debug("No Hard bounce detected for email subject[ : " + messages[i].getSubject() + "]");
                    }
                }
                else if (messages[i].isMimeType("multipart/*"))
                {
                    MimeMultipart mimeMultipart = (MimeMultipart) messages[i].getContent();
                    int count = mimeMultipart.getCount();
                    LOGGER.debug("Multipart mixed mode detected.. iterating through all types and scanning..");
                    for (int m = 0; m < count; m++)
                    {
                        Part bodyPart = mimeMultipart.getBodyPart(m);
                        LOGGER.debug("[" + m + "] Part Disposition Type : " + bodyPart.getDisposition());
                        LOGGER.debug("[" + m + "] Part Content Type : " + bodyPart.getContentType());
                        if ("message/delivery-status".equalsIgnoreCase(bodyPart.getContentType()))
                        {
                            body = dumpPart(bodyPart);

                            String res = scanner.scanBody(body);
                            LOGGER.debug("Scan Response: " + res);
                            if ((res != null)
                                    && ((res.toUpperCase().contains("BOUNCE")) || (res.toUpperCase().contains("|5."))))
                            {
                                LOGGER.info("Bounce detected " + res + " | email " + messages[i].getSubject()
                                        + ",recipients: " + messages[i].getAllRecipients());
                                if (matchBodyPart(patternWrap, pattern, body, res))
                                {
                                    numberOfSuccessfulBlackListings++;
                                }
                                setDeleteFlagOnMessage(messages[i]);
                                LOGGER.debug("Sent email [subject= " + messages[i].getSubject() + "] for deletion..");
                            }
                            else
                            {
                                LOGGER.debug("No Hard bounce detected for email subject[ : " + messages[i].getSubject()
                                        + " ]");
                            }
                        }
                        else if (("text/rfc822-headers".equalsIgnoreCase(bodyPart.getContentType()))
                                || ("text/rfc822".equalsIgnoreCase(bodyPart.getContentType())))
                        {
                            LOGGER.info("Skipping header info processing...");
                        }
                        else
                        {
                            String res = scanner.scanBody(body);
                            if ((res != null)
                                    && ((res.toUpperCase().contains("BOUNCE")) || (res.toUpperCase().contains("|5."))))
                            {
                                LOGGER.info("Bounce detected " + res + " | email " + messages[i].getSubject()
                                        + ",recipients: " + messages[i].getAllRecipients());
                                if (matchBodyPart(patternWrap, pattern, body, res))
                                {
                                    numberOfSuccessfulBlackListings++;
                                }
                                setDeleteFlagOnMessage(messages[i]);
                                LOGGER.debug("Sent email [subject= " + messages[i].getSubject() + "] for deletion..");
                            }
                            else
                            {
                                LOGGER.info("Unsupported content type : " + bodyPart.getContentType()
                                        + " | Bounce error Scan Resonse: " + res);
                            }
                        }
                    }
                    LOGGER.debug("Finished processing multipart message..");
                }
                else
                {
                    LOGGER.warn(messages[i] + " not supported for processing");
                }
            }
            emailFolder.close(true);
            store.close();
        }
        catch (MessagingException e)
        {
            LOGGER.error("Failed to read inbox, reason: [" + ExceptionUtil.getErrorRootCause(e) + "]");
        }
        catch (Exception e)
        {
            LOGGER.error("Scanning of bounced emails Failed, reason : [" + ExceptionUtil.getErrorRootCause(e) + "]");
        }
        return numberOfSuccessfulBlackListings;
    }

    /**
     * Sets the delete flag on message.
     *
     * @param message the new delete flag on message
     * @throws MessagingException the messaging exception
     */
    private void setDeleteFlagOnMessage(Message message) throws MessagingException
    {
        message.setFlag(Flags.Flag.DELETED, true);
    }

    /**
     * Match body part.
     *
     * @param patternWrap the pattern wrap
     * @param pattern the pattern
     * @param body the body
     * @param res the res
     * @return true, if successful
     */
    private boolean matchBodyPart(Pattern patternWrap, Pattern pattern, String body, String res)
    {
        Matcher wrap = patternWrap.matcher(body);
        boolean isBlacklistingProcessed = false;
        if (wrap.find())
        {
            String scanString = wrap.group();
            Matcher match = pattern.matcher(scanString);
            if (match.find())
            {
                String email = match.group();
                LOGGER.debug("Recovered new bounced recipient email " + email);

                String[] response = res.split("\\|");
                String reason = "";
                String resCode = "";
                if (response.length == 2)
                {
                    reason = response[0];
                    resCode = response[1];
                }
                LOGGER.debug("Split result : " + reason + " | " + resCode);
                if (!CommonUtilCache.getBouncedEmailCache().containsKey(reason))
                {
                    CommonUtilCache.getBouncedEmailCache().put(reason, new ArrayList<>());
                }
                if (!( CommonUtilCache.getBouncedEmailCache().get(reason)).contains(email))
                {
                    List<Contact> contacts = this.contactRepository.findByEmailIgnoreCase(email);
                    if ((contacts != null) && (!contacts.isEmpty()))
                    {
                        Contact contact = (Contact) contacts.get(0);
                        LOGGER.debug("Contact retrieved from DB. Adding it to Blocked list");
                        BlockedContacts blocked = new BlockedContacts();
                        blocked.setEmail(email);
                        blocked.setAutoRunEnabled(true);
                        blocked.setReason(reason);
                        blocked.setFirstName(contact.getFirstName());
                        blocked.setLastName(contact.getLastName());
                        blocked.setResponseCode(resCode);

                        List<BlockedContacts> blockedEmails = this.blockedContactService
                                .findBlockedContactByEmailAndReason(email, reason);
                        if ((blockedEmails == null) || (blockedEmails.isEmpty()))
                        {
                            LOGGER.info("Processing Blacklisting of Email : " + contact + "");
                            this.blockedContactService.addBlockedContact(blocked);
                            if (CommonUtilCache.getBouncedEmailCache().containsKey(reason))
                            {
                                CommonUtilCache.getBouncedEmailCache().get(reason).add(email);
                            }
                            LOGGER.info("Email : " + contact
                                    + " blacklisted and added to Bounced Email cache successfully");

                            isBlacklistingProcessed = true;
                        }
                        else
                        {
                            LOGGER.debug("Email : " + contact + " is already blacklisted.");
                        }
                    }
                }
                else
                {
                    LOGGER.debug("Skip processing blacklisted email already in cache : " + email);
                }
            }
        }
        return isBlacklistingProcessed;
    }

    /**
     * Dump part.
     *
     * @param p the p
     * @return the string
     * @throws Exception the exception
     */
    private static String dumpPart(Part p) throws Exception
    {
        InputStream is = p.getInputStream();
        if (!(is instanceof BufferedInputStream))
        {
            is = new BufferedInputStream(is);
        }
        return getStringFromInputStream(is);
    }

    /**
     * Gets the string from input stream.
     *
     * @param is the is
     * @return the string from input stream
     */
    private static String getStringFromInputStream(InputStream is)
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));)
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException e)
        {
            LOGGER.error(
                    "Failed to parse String from input stream, reason: [ " + ExceptionUtil.getErrorRootCause(e) + " ]");
        }
        return sb.toString();
    }
}
