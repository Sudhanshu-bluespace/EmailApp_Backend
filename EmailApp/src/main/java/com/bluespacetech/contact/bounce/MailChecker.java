package com.bluespacetech.contact.bounce;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.batch.EmailListener;
import com.bluespacetech.contact.repository.BlockedContactRepository;
import com.bluespacetech.contact.repository.ContactRepository;

/**
 * The Class MailChecker.
 */
public class MailChecker
{

    /** The contact repository. */
    private ContactRepository contactRepository;

    /** The blocked contacts repository. */
    private BlockedContactRepository blockedContactsRepository;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(MailChecker.class);

    /**
     * Instantiates a new mail checker.
     *
     * @param contactRepository the contact repository
     * @param blockedContactRepository the blocked contact repository
     */
    public MailChecker(ContactRepository contactRepository, BlockedContactRepository blockedContactRepository)
    {
        this.contactRepository = contactRepository;
        this.blockedContactsRepository = blockedContactRepository;
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
     */
    public void check(String host, String storeType, String user, String password, String port, String storeProtocol,
            String starttlsEnabled)
    {
        try
        {
            String emailRegexWrap = "Final-Recipient.*+";
            String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
            Pattern pattern = Pattern.compile(emailRegex);
            Pattern patternWrap = Pattern.compile(emailRegexWrap);

            // create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", port);
            properties.put("mail.pop3.starttls.enable", starttlsEnabled);
            properties.put("mail.pop3.socketFactory.port", port);
            properties.put("mail.pop3.socketFactory.fallback", "false");
            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.pop3.connectiontimeout", "10000");

            properties.put("mail.store.protocol", storeProtocol);
            ;
            Session emailSession = Session.getDefaultInstance(properties);

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3");

            store.connect(host, user, password);

            LOGGER.info("Connected to " + host + " successfully, Opening INBOX to scan for unread emails");

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.addMessageChangedListener(new EmailListener());
            emailFolder.open(Folder.READ_WRITE);

            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

            // retrieve the messages from the folder in an array and print it

            Message[] messages = emailFolder.search(unseenFlagTerm);

            // Sort messages from recent to oldest
            Arrays.sort(messages, (m1, m2) -> {
                try
                {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                }
                catch (MessagingException e)
                {
                    throw new RuntimeException(e);
                }
            });

            // Message[] messages = emailFolder.getMessages();
            LOGGER.info("Number of unread emails : " + messages.length);

            SmtpScanner scanner = SmtpScanner.getInstance();
            for (int i = 0, n = messages.length; i < n; i++)
            {
                String body = null;
                String subject = messages[i].getSubject();
                // String from = InternetAddress.toString(messages[i].getFrom());

                if (subject.toLowerCase().equalsIgnoreCase("successful mail delivery report"))
                {
                    LOGGER.info("Delivery Status Notification (Success), Skip Scan for Bounce..");
                    continue;
                }

                if (messages[i].isMimeType("text/plain"))
                {
                    body = messages[i].getContent().toString();
                }
                else if (messages[i].isMimeType("multipart/*"))
                {

                    MimeMultipart mimeMultipart = (MimeMultipart) messages[i].getContent();
                    int count = mimeMultipart.getCount();
                    for (int m = 0; m < count; m++)
                    {
                        Part bodyPart = mimeMultipart.getBodyPart(m);
                        LOGGER.debug("[" + m + "] Part Disposition Type : " + bodyPart.getDisposition());
                        LOGGER.debug("[" + m + "] Part Content Type : " + bodyPart.getContentType());

                        if ("message/delivery-status".equalsIgnoreCase(bodyPart.getContentType()))
                        {
                            body = dumpPart(bodyPart);
                            LOGGER.debug(body);
                            String res = scanner.scanBody(body);
                            if (res != null && res.toUpperCase().contains("BOUNCE"))
                            {
                                matchBodyPart(patternWrap, pattern, body, res);
                            }
                            else
                            {
                                LOGGER.info("Email Delivery Status : " + res);
                            }
                        }
                        else
                        {
                            continue;
                        }
                    }
                }
                else
                {
                    LOGGER.warn(messages[i] + " not supported for processing");
                }
            }

            // close the store and folder objects
            emailFolder.close(true);
            store.close();

        }
        catch (NoSuchProviderException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Match body part.
     *
     * @param patternWrap the pattern wrap
     * @param pattern the pattern
     * @param body the body
     * @param res the res
     */
    private void matchBodyPart(Pattern patternWrap, Pattern pattern, String body, String res)
    {
        Matcher wrap = patternWrap.matcher(body);
        if (wrap.find())
        {
            String scanString = wrap.group();
            Matcher match = pattern.matcher(scanString);
            if (match.find())
            {
                LOGGER.info("Recovered bounced recipient email " + match.group());
                String email = match.group();
                BlockedContacts blockedEmail = blockedContactsRepository.findByEmailIgnoreCase(email);
                if (blockedEmail == null)
                {
                    Contact contact = contactRepository.findByEmailIgnoreCase(email);
                    // System.out.println("Res : "+res);
                    String[] response = res.split("\\|");
                    String reason = "";
                    String resCode = "";
                    if (response.length == 2)
                    {
                        reason = response[0];
                        resCode = response[1];
                    }
                    if (contact != null)
                    {
                        LOGGER.info("Contact retrieved from DB. Adding it to Blocked list");
                        BlockedContacts blocked = new BlockedContacts();
                        blocked.setEmail(email);
                        blocked.setAutoRunEnabled(true);
                        blocked.setReason(reason);
                        blocked.setFirstName(contact.getFirstName());
                        blocked.setLastName(contact.getLastName());
                        blocked.setResponseCode(resCode);
                        blockedContactsRepository.save(blocked);
                        LOGGER.info("Email blacklisted successfully");
                    }
                }
                else
                {
                    LOGGER.warn("Skip processing already blacklisted email : " + email);
                }
            }
        }
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
        // If "is" is not already buffered, wrap a BufferedInputStream
        // around it.
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
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try
        {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /*
     * public static void main(String[] args) { String emailRegex = "^Final\\-Recipient:\\s+[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"; String emailRegex2 = "Final-Recipient.*+"; Pattern pattern
     * = Pattern.compile(emailRegex2); String body = "X-PowerMTA-VirtualMTA: vmta1" + "Received-From-MTA: dns;ip-172-31-11-8.us-west-2.compute.internal (35.163.189.155)" +
     * "Arrival-Date: Thu, 29 Jun 2017 12:00:46 -0400" + "Original-Recipient: rfc822;bounces@hireswing.net" + "Final-Recipient: rfc822;sudhanshu_8_41@gmail.com" + "Action: failed" +
     * "Status: 5.1.1 (bad destination mailbox address)" + "Remote-MTA: dns;gmail-smtp-in.l.google.com (74.125.28.26)" +
     * "Diagnostic-Code: smtp;550 5.1.1 The email account that you tried to reach does not exist. Please try double-checking the recipient's email address for typos or unnecessary spaces. Learn more at https://support.google.com/mail/?p=NoSuchUser e26si4398979plj.541 - gsmtp"
     * + "X-PowerMTA-BounceCategory: bad-mailbox"; Matcher match = pattern.matcher(body); if (match.find()) { System.out.println("match : " + match.group()); } }
     */
}
