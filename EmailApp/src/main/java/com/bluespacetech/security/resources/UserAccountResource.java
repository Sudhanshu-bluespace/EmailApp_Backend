/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.resources;

/**
 * Entity class for user accounts
 *
 * @author Pradeep
 */
import java.util.Collection;

import org.springframework.hateoas.ResourceSupport;

import com.bluespacetech.security.constants.UserAccountTypeConstant;

/**
 * The Class UserAccountResource.
 */
public class UserAccountResource extends ResourceSupport
{

    /** The object id. */
    private Long objectId;

    /** The version. */
    private Long version;

    /** The user account user groups. */
    private Collection<UserAccountUserGroupResource> userAccountUserGroups;

    /** The username. */
    private String username;

    /** The active. */
    private boolean active;

    /** The account expired. */
    private boolean accountExpired;

    /** The credentials expired. */
    private boolean credentialsExpired;

    /** The account locked. */
    private boolean accountLocked;

    /** The user account type. */
    private UserAccountTypeConstant userAccountType;

    /** The employee number. */
    private String employeeNumber;

    /** The password. */
    private String password;

    /** The email. */
    private String email;

    /** The company name. */
    private String companyName;

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the new company name
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * Gets the object id.
     *
     * @return the objectId
     */
    public Long getObjectId()
    {
        return objectId;
    }

    /**
     * Sets the object id.
     *
     * @param objectId the objectId to set
     */
    public void setObjectId(final Long objectId)
    {
        this.objectId = objectId;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the version to set
     */
    public void setVersion(final Long version)
    {
        this.version = version;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(final String username)
    {
        this.username = username;
    }

    /**
     * Checks if is active.
     *
     * @return the active
     */
    public boolean isActive()
    {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the active to set
     */
    public void setActive(final boolean active)
    {
        this.active = active;
    }

    /**
     * Checks if is account expired.
     *
     * @return the accountExpired
     */
    public boolean isAccountExpired()
    {
        return accountExpired;
    }

    /**
     * Sets the account expired.
     *
     * @param accountExpired the accountExpired to set
     */
    public void setAccountExpired(final boolean accountExpired)
    {
        this.accountExpired = accountExpired;
    }

    /**
     * Checks if is credentials expired.
     *
     * @return the credentialsExpired
     */
    public boolean isCredentialsExpired()
    {
        return credentialsExpired;
    }

    /**
     * Sets the credentials expired.
     *
     * @param credentialsExpired the credentialsExpired to set
     */
    public void setCredentialsExpired(final boolean credentialsExpired)
    {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * Checks if is account locked.
     *
     * @return the accountLocked
     */
    public boolean isAccountLocked()
    {
        return accountLocked;
    }

    /**
     * Sets the account locked.
     *
     * @param accountLocked the accountLocked to set
     */
    public void setAccountLocked(final boolean accountLocked)
    {
        this.accountLocked = accountLocked;
    }

    /**
     * Gets the user account type.
     *
     * @return the userAccountType
     */
    public UserAccountTypeConstant getUserAccountType()
    {
        return userAccountType;
    }

    /**
     * Sets the user account type.
     *
     * @param userAccountType the userAccountType to set
     */
    public void setUserAccountType(final UserAccountTypeConstant userAccountType)
    {
        this.userAccountType = userAccountType;
    }

    /**
     * Gets the user account user groups.
     *
     * @return the userAccountUserGroups
     */
    public Collection<UserAccountUserGroupResource> getUserAccountUserGroups()
    {
        return userAccountUserGroups;
    }

    /**
     * Sets the user account user groups.
     *
     * @param userAccountUserGroups the userAccountUserGroups to set
     */
    public void setUserAccountUserGroups(final Collection<UserAccountUserGroupResource> userAccountUserGroups)
    {
        this.userAccountUserGroups = userAccountUserGroups;
    }

    /**
     * Gets the employee number.
     *
     * @return the employeeNumber
     */
    public String getEmployeeNumber()
    {
        return employeeNumber;
    }

    /**
     * Sets the employee number.
     *
     * @param employeeNumber the employeeNumber to set
     */
    public void setEmployeeNumber(final String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(final String password)
    {
        this.password = password;
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
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

}
