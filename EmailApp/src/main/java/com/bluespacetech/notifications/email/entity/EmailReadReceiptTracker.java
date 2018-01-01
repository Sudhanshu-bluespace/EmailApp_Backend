/**
 * 
 */
package com.bluespacetech.notifications.email.entity;

/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * The Class EmailReadReceiptTracker.
 *
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "EMAIL_READ_RECEIPT_TRACKER")
public class EmailReadReceiptTracker extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6118718181276955321L;

    /** The emailt id. */
    @Column(name = "EMAIL_ID", nullable = false)
    private Long emailId;

    /** The contact id. */
    @Column(name = "CONTACT_ID", nullable = false)
    private Long contactId;

    /** The contact email. */
    @Column(name = "CONTACT_EMAIL", nullable = false)
    private String contactEmail;

    /** The group id. */
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

    /** The read count. */
    @Column(name = "READ_COUNT")
    private Integer readCount;

    /**
     * Gets the contact id.
     *
     * @return the contactId
     */
    public Long getContactId()
    {
        return contactId;
    }

    /**
     * Sets the contact id.
     *
     * @param contactId the contactId to set
     */
    public void setContactId(Long contactId)
    {
        this.contactId = contactId;
    }

    /**
     * Gets the group id.
     *
     * @return the groupId
     */
    public Long getGroupId()
    {
        return groupId;
    }

    /**
     * Sets the group id.
     *
     * @param groupId the groupId to set
     */
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    /**
     * Gets the emailt id.
     *
     * @return the emailt id
     */
    public Long getEmailId()
    {
        return emailId;
    }

    /**
     * Sets the emailt id.
     *
     * @param emailtId the new emailt id
     */
    public void setEmailId(Long emailId)
    {
        this.emailId = emailId;
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
     * Gets the read count.
     *
     * @return the readCount
     */
    public Integer getReadCount()
    {
        return readCount;
    }

    /**
     * Sets the read count.
     *
     * @param readCount the readCount to set
     */
    public void setReadCount(Integer readCount)
    {
        this.readCount = readCount;
    }

    @Override
    public String toString()
    {
        return "EmailReadReceiptTracker [emailtId=" + emailId + ", contactId="
                + contactId + ", contactEmail=" + contactEmail + ", groupId=" + groupId + ", readCount=" + readCount
                + "]";
    }
}
