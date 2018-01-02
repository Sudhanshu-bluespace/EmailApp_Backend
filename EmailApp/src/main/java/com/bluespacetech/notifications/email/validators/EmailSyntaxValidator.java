package com.bluespacetech.notifications.email.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailSyntaxValidator
{
  private static final String valid_regex = "[^@]+@([-\\p{Alnum}]+\\.)*\\p{Alnum}+";
  private static final String valid_regex_alt = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
  private static final Logger LOGGER = LogManager.getLogger(EmailSyntaxValidator.class.getName());
  
  public static boolean validateEmailSyntax(String email)
  {
    boolean status = false;
    if (!email.matches("[^@]+@([-\\p{Alnum}]+\\.)*\\p{Alnum}+")) {
      LOGGER.error("Email failed to match the expected pattern [^@]+@([-\\p{Alnum}]+\\.)*\\p{Alnum}+");
    } else if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
      LOGGER.error("Email failed to match the expected pattern ^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
    } else {
      status = true;
    }
    return status;
  }
}
