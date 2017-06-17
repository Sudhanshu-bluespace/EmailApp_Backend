package com.bluespacetech.security.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class AccountCreationEmail.
 * @author Sudhanshu Tripathy
 */
public class AccountCreationEmail
{

    /** The mail from. */
    private String mailFrom;

    /** The mail to. */
    private String mailTo;

    /** The mail cc. */
    private String mailCc;

    /** The mail bcc. */
    private String mailBcc;

    /** The mail subject. */
    private String mailSubject;

    /** The mail content. */
    private String mailContent;

    /** The content type. */
    private String contentType;

    /** The verification url. */
    private String verificationUrl;

    /** The unsubscribe url. */
    private String unsubscribeUrl;

    /**
     * Gets the unsubscribe url.
     *
     * @return the unsubscribe url
     */
    public String getUnsubscribeUrl()
    {
        return unsubscribeUrl;
    }

    /**
     * Sets the unsubscribe url.
     *
     * @param unsubscribeUrl the new unsubscribe url
     */
    public void setUnsubscribeUrl(String unsubscribeUrl)
    {
        this.unsubscribeUrl = unsubscribeUrl;
    }

    /**
     * Gets the verification url.
     *
     * @return the verification url
     */
    public String getVerificationUrl()
    {
        return verificationUrl;
    }

    /**
     * Sets the verification url.
     *
     * @param verificationUrl the new verification url
     */
    public void setVerificationUrl(String verificationUrl)
    {
        this.verificationUrl = verificationUrl;
    }

    /** The attachments. */
    private List<Object> attachments;

    /** The model. */
    private Map<String, Object> model;

    /**
     * Instantiates a new account creation email.
     */
    public AccountCreationEmail()
    {
        contentType = "text/plain";
    }

    /**
     * Gets the content type.
     *
     * @return the content type
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the new content type
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * Gets the mail bcc.
     *
     * @return the mail bcc
     */
    public String getMailBcc()
    {
        return mailBcc;
    }

    /**
     * Sets the mail bcc.
     *
     * @param mailBcc the new mail bcc
     */
    public void setMailBcc(String mailBcc)
    {
        this.mailBcc = mailBcc;
    }

    /**
     * Gets the mail cc.
     *
     * @return the mail cc
     */
    public String getMailCc()
    {
        return mailCc;
    }

    /**
     * Sets the mail cc.
     *
     * @param mailCc the new mail cc
     */
    public void setMailCc(String mailCc)
    {
        this.mailCc = mailCc;
    }

    /**
     * Gets the mail from.
     *
     * @return the mail from
     */
    public String getMailFrom()
    {
        return mailFrom;
    }

    /**
     * Sets the mail from.
     *
     * @param mailFrom the new mail from
     */
    public void setMailFrom(String mailFrom)
    {
        this.mailFrom = mailFrom;
    }

    /**
     * Gets the mail subject.
     *
     * @return the mail subject
     */
    public String getMailSubject()
    {
        return mailSubject;
    }

    /**
     * Sets the mail subject.
     *
     * @param mailSubject the new mail subject
     */
    public void setMailSubject(String mailSubject)
    {
        this.mailSubject = mailSubject;
    }

    /**
     * Gets the mail to.
     *
     * @return the mail to
     */
    public String getMailTo()
    {
        return mailTo;
    }

    /**
     * Sets the mail to.
     *
     * @param mailTo the new mail to
     */
    public void setMailTo(String mailTo)
    {
        this.mailTo = mailTo;
    }

    /**
     * Gets the mail send date.
     *
     * @return the mail send date
     */
    public Date getMailSendDate()
    {
        return new Date();
    }

    /**
     * Gets the mail content.
     *
     * @return the mail content
     */
    public String getMailContent()
    {
        return mailContent;
    }

    /**
     * Sets the mail content.
     *
     * @param mailContent the new mail content
     */
    public void setMailContent(String mailContent)
    {
        this.mailContent = mailContent;
    }

    /**
     * Gets the attachments.
     *
     * @return the attachments
     */
    public List<Object> getAttachments()
    {
        return attachments;
    }

    /**
     * Sets the attachments.
     *
     * @param attachments the new attachments
     */
    public void setAttachments(List<Object> attachments)
    {
        this.attachments = attachments;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public Map<String, Object> getModel()
    {
        return model;
    }

    /**
     * Sets the model.
     *
     * @param model the model
     */
    public void setModel(Map<String, Object> model)
    {
        this.model = model;
    }

}
