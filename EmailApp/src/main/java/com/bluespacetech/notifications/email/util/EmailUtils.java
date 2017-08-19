package com.bluespacetech.notifications.email.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.bluespacetech.common.util.Base64ToImageDecodeHelper;
import com.bluespacetech.core.crypto.Encryptor;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailUtils.
 * @author Sudhanshu Tripathy
 */
public class EmailUtils
{

    /** The Constant EMAIL_SECRET_KEY. */
    public final static String EMAIL_SECRET_KEY = "ThisIsKeyForEmailEncryptDecrypt";
    
    /** The Constant LOGGER. */
    private final static Logger LOGGER = LogManager.getLogger(EmailUtils.class);
    
    /**
     * Generate full unscribe link.
     *
     * @param emailContactGroupVO the email contact group VO
     * @param emailRequestURL the email request URL
     * @return the string
     */
    public static String generateFullUnsubscribeLink(final EmailContactGroupVO emailContactGroupVO,
            final String emailRequestURL)
    {
        LOGGER.debug("Generating Full Unsubscribe Link");
        final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/unsubscribe");
        final StringBuilder token = new StringBuilder();
        unscribeLink.append("?token=");
        token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
                .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
                .append(emailContactGroupVO.getGroupId().toString())
                .append("&emailId=").append(emailContactGroupVO.getEmailId())
                .append("&full=true");
        String tokenEnc = Encryptor.Encrypt(token.toString());
        unscribeLink.append(tokenEnc);
        
        LOGGER.debug("Full Unsubscribe Link : "+unscribeLink.toString());
        return unscribeLink.toString();
    }
    
    /**
     * Generate subscribe link.
     *
     * @param emailContactGroupVO the email contact group VO
     * @param emailRequestURL the email request URL
     * @return the string
     */
    public static String generateSubscribeLink(final EmailContactGroupVO emailContactGroupVO,
            final String emailRequestURL)
    {
        LOGGER.debug("Generating Resubscribe Link");
        final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/subscribe");
        final StringBuilder token = new StringBuilder();
        unscribeLink.append("?token=");
        token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
                .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
                .append(emailContactGroupVO.getGroupId().toString())
                .append("&emailId=").append(emailContactGroupVO.getEmailId());
        String tokenEnc = Encryptor.Encrypt(token.toString());
        unscribeLink.append(tokenEnc);
        
        LOGGER.debug("Unsubscribe Link : "+unscribeLink.toString());
        return unscribeLink.toString();
    }

    /**
     * Generate unscribe link.
     *
     * @param emailContactGroupVO the email contact group VO
     * @param emailRequestURL the email request URL
     * @return the string
     */
    public static String generateUnsubscribeLink(final EmailContactGroupVO emailContactGroupVO,
            final String emailRequestURL)
    {
        LOGGER.debug("Generating Unsubscribe Link");
        final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/unsubscribe");
        final StringBuilder token = new StringBuilder();
        unscribeLink.append("?token=");
        token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
                .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
                .append(emailContactGroupVO.getGroupId().toString());
        String tokenEnc = Encryptor.Encrypt(token.toString());
        unscribeLink.append(tokenEnc);
        
        LOGGER.debug("Unsubscribe Link : "+unscribeLink.toString());
        return unscribeLink.toString();
    }

    /**
     * Generate read mail image SRC.
     *
     * @param emailContactGroupVO the email contact group VO
     * @param emailRequestURL the email request URL
     * @param emailRandomNumber the email random number
     * @return the string
     */
    public static String generateReadMailImageSRC(final EmailContactGroupVO emailContactGroupVO,
            final String emailRequestURL, final Long emailRandomNumber)
    {
        LOGGER.debug("Generating read link");
        final StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/readMail");
        unscribeLink.append("?emailRandomNumber=").append(emailRandomNumber).append("&contactId=")
                .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
                .append(emailContactGroupVO.getGroupId().toString());

        LOGGER.debug("Read Mail Link : "+unscribeLink.toString());
        return unscribeLink.toString();
    }
    
    /**
     * Checks if is email valid.
     *
     * @param email the email
     * @return true, if is email valid
     */
    public static boolean isEmailValid(String email)
    {
        boolean isValid = false;
        if (email != null && !email.trim().isEmpty())
        {
            String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.find())
            {
                isValid = true;
            }
        }

        return isValid;
    }
}
