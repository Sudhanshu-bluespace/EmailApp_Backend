package com.bluespacetech.server.analytics.repository;

/**
 * The Class ReadReceiptDTO.
 */
public class ReadReceiptDTO
{

    /** The read receipt id. */
    private Long readReceiptId;

    /** The email id. */
    private Long emailId;

    /** The contact id. */
    private Long contactId;

    /** The group id. */
    private Long groupId;

    /** The contact email. */
    private String contactEmail;

    /** The last read time. */
    private String lastReadTime;

    /** The read count. */
    private int readCount;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ReadReceiptDTO [readReceiptId=" + readReceiptId + ", emailId=" + emailId + ", contactId=" + contactId
                + ", groupId=" + groupId + ", contactEmail=" + contactEmail + ", lastReadTime=" + lastReadTime
                + ", readCount=" + readCount + "]";
    }

    /**
     * Gets the read receipt id.
     *
     * @return the read receipt id
     */
    public Long getReadReceiptId()
    {
        return readReceiptId;
    }

    /**
     * Sets the read receipt id.
     *
     * @param readReceiptId the new read receipt id
     */
    public void setReadReceiptId(Long readReceiptId)
    {
        this.readReceiptId = readReceiptId;
    }

    /**
     * Gets the email id.
     *
     * @return the email id
     */
    public Long getEmailId()
    {
        return emailId;
    }

    /**
     * Sets the email id.
     *
     * @param emailId the new email id
     */
    public void setEmailId(Long emailId)
    {
        this.emailId = emailId;
    }

    /**
     * Gets the contact id.
     *
     * @return the contact id
     */
    public Long getContactId()
    {
        return contactId;
    }

    /**
     * Sets the contact id.
     *
     * @param contactId the new contact id
     */
    public void setContactId(Long contactId)
    {
        this.contactId = contactId;
    }

    /**
     * Gets the group id.
     *
     * @return the group id
     */
    public Long getGroupId()
    {
        return groupId;
    }

    /**
     * Sets the group id.
     *
     * @param groupId the new group id
     */
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    /**
     * Gets the contact email.
     *
     * @return the contact email
     */
    public String getContactEmail()
    {
        return contactEmail;
    }

    /**
     * Sets the contact email.
     *
     * @param contactEmail the new contact email
     */
    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    /**
     * Gets the last read time.
     *
     * @return the last read time
     */
    public String getLastReadTime()
    {
        return lastReadTime;
    }

    /**
     * Sets the last read time.
     *
     * @param lastReadTime the new last read time
     */
    public void setLastReadTime(String lastReadTime)
    {
        this.lastReadTime = lastReadTime;
    }

    /**
     * Gets the read count.
     *
     * @return the read count
     */
    public int getReadCount()
    {
        return readCount;
    }

    /**
     * Sets the read count.
     *
     * @param readCount the new read count
     */
    public void setReadCount(int readCount)
    {
        this.readCount = readCount;
    }

}
