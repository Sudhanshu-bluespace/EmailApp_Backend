/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.bluespacetech.core.constants.Labeled;

// TODO: Auto-generated Javadoc
/**
 * The Enum PageLinkConstant.
 */
public enum PageLinkConstant implements Labeled
{

    /** The dashboard. */
    DASHBOARD("Dashboard", 0, "dashboard", PageLinkTypeConstant.LINK, false, UserAccountTypeConstant.getAllUserAccountTypes(), EnumSet.of(GrantConstant.NONE)),

    /** The reports. */
    // Setup Links
    REPORTS("Reports", 4, "reports", PageLinkTypeConstant.TOGGLE, true, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_REPORTS)),

    /** The send emails. */
    SEND_EMAILS("Send Emails", 1, "send_emails", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SEND_EMAIL)),

    /** The contacts. */
    CONTACTS("Contacts", 2, "contacts", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_CONTACTS)),

    /** The servers. */
    SERVERS("Servers", 5, "servers", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SERVERS)),

    /** The user roles. */
    USER_ROLES("User Roles", 9, "user_roles", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_ROLES)),

    /** The user groups. */
    USER_GROUPS("User Groups", 10, "user_groups", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),

    /** The user accounts. */
    USER_ACCOUNTS("User Accounts", 11, "user_accounts", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),

    /** The groups. */
    GROUPS("Groups", 3, "groups", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_GROUPS)),

    /** The analytics. */
    ANALYTICS("Analytics", 6, "analytics", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ANALYTICS)),

    /** The account approval. */
    ACCOUNT_APPROVAL("Account Approval", 7, "account_approval", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN), EnumSet.of(GrantConstant.ACCOUNT_APPROVAL)),

    /** The bulk upload. */
    BULK_UPLOAD("Bulk Upload", 8, "bulk_upload", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN), EnumSet.of(GrantConstant.BULK_UPLOAD));

    /** The label. */
    private String label;

    /** The display order. */
    private final int displayOrder;

    /** The child page links. */
    private Set<PageLinkConstant> childPageLinks;

    /** The page url. */
    private final String pageUrl;

    /** The url type. */
    private final PageLinkTypeConstant urlType;

    /** The branch required. */
    private final boolean branchRequired;

    /** The user account types. */
    private final Set<UserAccountTypeConstant> userAccountTypes;

    /** The grants. */
    private final Set<GrantConstant> grants;

    /** The child page links allowed for user. */
    private final Set<PageLinkConstant> childPageLinksAllowedForUser = new HashSet<PageLinkConstant>();

    /** The Constant togglePages. */
    private static final Set<PageLinkConstant> togglePages = new HashSet<PageLinkConstant>();

    /**
     * Instantiates a new page link constant.
     *
     * @param label the label
     * @param displayOrder the display order
     * @param pageUrl the page url
     * @param urlType the url type
     * @param branchRequired the branch required
     * @param userAccountTypes the user account types
     * @param grants the grants
     */
    PageLinkConstant(final String label, final int displayOrder, final String pageUrl,
            final PageLinkTypeConstant urlType, final boolean branchRequired,
            // final Set<PageLinkConstant> childPageLinks,
            final Set<UserAccountTypeConstant> userAccountTypes, final Set<GrantConstant> grants)
    {
        this.label = label;
        this.pageUrl = pageUrl;
        this.displayOrder = displayOrder;
        this.urlType = urlType;
        this.branchRequired = branchRequired;
        // this.childPageLinks=childPageLinks;
        this.userAccountTypes = userAccountTypes;
        this.grants = grants;
    }

    static
    {
        togglePages.add(SEND_EMAILS);
        togglePages.add(CONTACTS);
        togglePages.add(SERVERS);
        togglePages.add(SERVERS);
        togglePages.add(GROUPS);
        togglePages.add(USER_ROLES);
        togglePages.add(USER_GROUPS);
        togglePages.add(DASHBOARD);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.constants.Labeled#getLabel()
     */
    @Override
    public String getLabel()
    {
        return label;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.constants.Labeled#setLabel(java.lang.String)
     */
    @Override
    public void setLabel(final String label)
    {
        this.label = label;

    }

    /**
     * Gets the child page links.
     *
     * @return the childPageLinks
     */
    public Set<PageLinkConstant> getChildPageLinks()
    {
        return childPageLinks;
    }

    /**
     * Gets the page url.
     *
     * @return the pageUrl
     */
    public String getPageUrl()
    {
        return pageUrl;
    }

    /**
     * Gets the url type.
     *
     * @return the urlType
     */
    public PageLinkTypeConstant getUrlType()
    {
        return urlType;
    }

    /**
     * Checks if is branch required.
     *
     * @return the branchRequired
     */
    public boolean isBranchRequired()
    {
        return branchRequired;
    }

    /**
     * Gets the user account types.
     *
     * @return the userAccountTypes
     */
    public Set<UserAccountTypeConstant> getUserAccountTypes()
    {
        return userAccountTypes;
    }

    /**
     * Gets the grants.
     *
     * @return the grants
     */
    public Set<GrantConstant> getGrants()
    {
        return grants;
    }

    /**
     * Gets the togglepages.
     *
     * @return the togglepages
     */
    public static Set<PageLinkConstant> getTogglepages()
    {
        return togglePages;
    }

    /**
     * Gets the child page links allowed for user.
     *
     * @return the childPageLinksAllowedForUser
     */
    public Set<PageLinkConstant> getChildPageLinksAllowedForUser()
    {
        return childPageLinksAllowedForUser;
    }

    /**
     * Gets the display order.
     *
     * @return the display order
     */
    public int getDisplayOrder()
    {
        return displayOrder;
    }

}
