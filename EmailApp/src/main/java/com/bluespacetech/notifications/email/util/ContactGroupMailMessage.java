package com.bluespacetech.notifications.email.util;

import java.io.Serializable;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;

/**
 * The Class ContactGroupMailMessage.
 * @author Sudhanshu Tripathy
 */
public class ContactGroupMailMessage implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6064992838049760633L;

    /** The email contact group. */
    private EmailContactGroup emailContactGroup;

    /** The mime message. */
    private MimeMessage mimeMessage;
    
    /** The Mime message preparator */
    private MimeMessagePreparator preparator;
    

    public MimeMessagePreparator getPreparator()
    {
        return preparator;
    }

    public void setPreparator(MimeMessagePreparator preparator)
    {
        this.preparator = preparator;
    }

    /**
     * Gets the mime message.
     *
     * @return the mimeMessage
     */
    public MimeMessage getMimeMessage()
    {
        return mimeMessage;
    }

    /**
     * Sets the mime message.
     *
     * @param mimeMessage            the mimeMessage to set
     */
    public void setMimeMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
    }

    /**
     * Gets the email contact group.
     *
     * @return the emailContactGroup
     */
    public EmailContactGroup getEmailContactGroup()
    {
        return emailContactGroup;
    }

    /**
     * Sets the email contact group.
     *
     * @param emailContactGroup            the emailContactGroup to set
     */
    public void setEmailContactGroup(EmailContactGroup emailContactGroup)
    {
        this.emailContactGroup = emailContactGroup;
    }

}
