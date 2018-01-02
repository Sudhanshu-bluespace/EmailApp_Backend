package com.bluespacetech.notifications.email.util;

import com.bluespacetech.core.crypto.Encryptor;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailUtils
{
  public static final String EMAIL_SECRET_KEY = "ThisIsKeyForEmailEncryptDecrypt";
  private static final Logger LOGGER = LogManager.getLogger(EmailUtils.class);
  
  public static String generateFullUnsubscribeLink(EmailContactGroupVO emailContactGroupVO, String emailRequestURL)
  {
    LOGGER.debug("Generating Full Unsubscribe Link");
    StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/unsubscribe");
    StringBuilder token = new StringBuilder();
    unscribeLink.append("?token=");
    token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
      .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
      .append(emailContactGroupVO.getGroupId().toString())
      .append("&emailId=").append(emailContactGroupVO.getEmailId())
      .append("&full=true");
    String tokenEnc = Encryptor.Encrypt(token.toString());
    unscribeLink.append(tokenEnc);
    
    LOGGER.debug("Full Unsubscribe Link : " + unscribeLink.toString());
    return unscribeLink.toString();
  }
  
  public static String generateSubscribeLink(EmailContactGroupVO emailContactGroupVO, String emailRequestURL)
  {
    LOGGER.debug("Generating Resubscribe Link");
    StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/subscribe");
    StringBuilder token = new StringBuilder();
    unscribeLink.append("?token=");
    token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
      .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
      .append(emailContactGroupVO.getGroupId().toString())
      .append("&emailId=").append(emailContactGroupVO.getEmailId());
    String tokenEnc = Encryptor.Encrypt(token.toString());
    unscribeLink.append(tokenEnc);
    
    LOGGER.debug("Unsubscribe Link : " + unscribeLink.toString());
    return unscribeLink.toString();
  }
  
  public static String generateUnsubscribeLink(EmailContactGroupVO emailContactGroupVO, String emailRequestURL)
  {
    LOGGER.debug("Generating Unsubscribe Link");
    StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/unsubscribe");
    StringBuilder token = new StringBuilder();
    unscribeLink.append("?token=");
    token.append("contactEmail=").append(emailContactGroupVO.getContactEmail()).append("&contactId=")
      .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
      .append(emailContactGroupVO.getGroupId().toString());
    String tokenEnc = Encryptor.Encrypt(token.toString());
    unscribeLink.append(tokenEnc);
    
    LOGGER.debug("Unsubscribe Link : " + unscribeLink.toString());
    return unscribeLink.toString();
  }
  
  public static String generateReadMailImageSRC(EmailContactGroupVO emailContactGroupVO, String emailRequestURL, Long emailRandomNumber)
  {
    LOGGER.debug("Generating read link");
    StringBuffer unscribeLink = new StringBuffer(emailRequestURL + "/track/readMail");
    unscribeLink.append("?emailRandomNumber=").append(emailRandomNumber).append("&contactId=")
      .append(emailContactGroupVO.getContactId().toString()).append("&groupId=")
      .append(emailContactGroupVO.getGroupId().toString());
    
    LOGGER.debug("Read Mail Link : " + unscribeLink.toString());
    return unscribeLink.toString();
  }
  
  public static boolean isEmailValid(String email)
  {
    boolean isValid = false;
    if ((email != null) && (!email.trim().isEmpty()))
    {
      String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(email);
      if (matcher.find()) {
        isValid = true;
      }
    }
    return isValid;
  }
}
