/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * The Class BlockedContacts.
 *
 * @author sudhanshu tripathy created date 13-Jul-2016
 */
@Entity
@Table(name = "BLOCKED_CONTACTS")
public class BlockedContacts extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -920295196248477456L;

    /** The first name. */
    @Column(name = "FIRST_NAME",nullable=false)
    private String firstName;

    /** The last name. */
    @Column(name = "LAST_NAME",nullable=false)
    private String lastName;

    /** The email. */
    @Column(name = "EMAIL",nullable=false)
    private String email;

    /** The reason. */
    @Column(name = "REASON")
    private String reason;

    /** The response code. */
    @Column(name = "RESPONSE_CODE")
    private String responseCode;

    /**
     * Gets the response code.
     *
     * @return the response code
     */
    public String getResponseCode()
    {
        return responseCode;
    }

    /**
     * Sets the response code.
     *
     * @param responseCode the new response code
     */
    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    /**
     * Gets the reason.
     *
     * @return the reason
     */
    public String getReason()
    {
        return reason;
    }

    /**
     * Sets the reason.
     *
     * @param reason the new reason
     */
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "BlockedContacts [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", reason="
                + reason + ", responseCode=" + responseCode + "]";
    }

}
