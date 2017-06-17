package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;
import com.bluespacetech.security.constants.UserAccountTypeConstant;

/**
 * Singleton class for branch expense search criteria..
 *
 * @author Pradeep
 */
public class UserAccountSearchCriteria implements SearchCriteria
{

    /** Universal serial version id for this class. */
    private static final long serialVersionUID = -7524669904323526192L;

    /**
     * Branch.
     */
    private Long branchId;

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

    /**
     * Gets the branch id.
     *
     * @return the branchId
     */
    @Override
    public Long getBranchId()
    {
        return branchId;
    }

    /**
     * Sets the branch id.
     *
     * @param branchId the branchId to set
     */
    @Override
    public void setBranchId(final Long branchId)
    {
        this.branchId = branchId;
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

}
