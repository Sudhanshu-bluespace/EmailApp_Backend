package com.bluespacetech.notifications.email.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class EmailSyntaxValidator.
 */
public class EmailSyntaxValidator
{
    
    /** The Constant valid_regex. */
    private static final String valid_regex = "[^@]+@([-\\p{Alnum}]+\\.)*\\p{Alnum}+";
    
    /** The Constant valid_regex_alt. */
    private static final String valid_regex_alt ="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"; 
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailSyntaxValidator.class.getName());

    /**
     * Validate email syntax.
     *
     * @param email the email
     * @return true, if successful
     */
    public static boolean validateEmailSyntax(String email)
    {
        boolean status = false;
        if (!email.matches(valid_regex))
        {
            LOGGER.error("Email failed to match the expected pattern "+valid_regex);
        }
        else if(!email.matches(valid_regex_alt))
        {
            LOGGER.error("Email failed to match the expected pattern "+valid_regex_alt);
        }
        else
        {
            status=true;
        }

        return status;
    }
}
