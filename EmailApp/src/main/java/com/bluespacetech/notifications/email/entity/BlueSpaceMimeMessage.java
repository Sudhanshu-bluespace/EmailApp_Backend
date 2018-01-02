package com.bluespacetech.notifications.email.entity;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class BlueSpaceMimeMessage.
 */
public class BlueSpaceMimeMessage extends MimeMessage
{

    /** The domain name. */
    private String domainName;

    /** The campaign id. */
    private long campaignId;

    /** The encrypted email. */
    private String encryptedEmail;

    /** The contact id. */
    private long contactId;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(BlueSpaceMimeMessage.class);

    /**
     * Instantiates a new blue space mime message.
     *
     * @param session the session
     */
    public BlueSpaceMimeMessage(Session session)
    {
        super(session);
    }

    /*
     * (non-Javadoc)
     * @see javax.mail.internet.MimeMessage#updateMessageID()
     */
    protected void updateMessageID() throws MessagingException
    {
        setHeader("Message-ID", getGeneratedMessageId());
        LOGGER.info("Message-ID Header updated, new value : " + getHeader("Message-ID")[0]);
    }

    /**
     * Gets the generated message id.
     *
     * @return the generated message id
     */
    private String getGeneratedMessageId()
    {
        StringBuilder sb = new StringBuilder("<");
        sb.append(System.currentTimeMillis());
        sb.append(".");
        sb.append(this.campaignId);
        sb.append(".");
        sb.append(this.encryptedEmail);
        sb.append(".");
        sb.append(this.contactId);
        sb.append("@");
        sb.append(this.domainName);
        sb.append(">");
        return sb.toString();
    }

    /**
     * Gets the domain name.
     *
     * @return the domain name
     */
    public String getDomainName()
    {
        return this.domainName;
    }

    /**
     * Sets the domain name.
     *
     * @param domainName the new domain name
     */
    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }

    /**
     * Gets the campaign id.
     *
     * @return the campaign id
     */
    public long getCampaignId()
    {
        return this.campaignId;
    }

    /**
     * Sets the campaign id.
     *
     * @param campaignId the new campaign id
     */
    public void setCampaignId(long campaignId)
    {
        this.campaignId = campaignId;
    }

    /**
     * Gets the encrypted email.
     *
     * @return the encrypted email
     */
    public String getEncryptedEmail()
    {
        return this.encryptedEmail;
    }

    /**
     * Sets the encrypted email.
     *
     * @param encryptedEmail the new encrypted email
     */
    public void setEncryptedEmail(String encryptedEmail)
    {
        this.encryptedEmail = encryptedEmail;
    }

    /**
     * Gets the contact id.
     *
     * @return the contact id
     */
    public long getContactId()
    {
        return this.contactId;
    }

    /**
     * Sets the contact id.
     *
     * @param contactId the new contact id
     */
    public void setContactId(long contactId)
    {
        this.contactId = contactId;
    }
}
