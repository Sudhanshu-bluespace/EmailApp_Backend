/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Enums for Authority constants.
 *
 * @author Pradeep
 */
public enum AuthorityConstant
{

    /** The dashboard. */
    DASHBOARD(GrantConstant.ACCESS_DASHBOARD, null, null, null, "Dash Board"),

    /** The reports. */
    REPORTS(GrantConstant.ACCESS_REPORTS, null, null, null, "Reports"),

    /** The send email. */
    SEND_EMAIL(GrantConstant.ACCESS_SEND_EMAIL, null, null, null, "Email"),

    /** The groups. */
    GROUPS(GrantConstant.ACCESS_GROUPS, GrantConstant.CREATE_GROUPS, GrantConstant.UPDATE_GROUPS, GrantConstant.DELETE_GROUPS, "Email"),

    /** The contacts. */
    CONTACTS(GrantConstant.ACCESS_CONTACTS, GrantConstant.CREATE_CONTACTS, GrantConstant.UPDATE_CONTACTS, GrantConstant.DELETE_CONTACTS, "Contacts"),

    /** The users. */
    USERS(GrantConstant.ACCESS_USERS, GrantConstant.CREATE_USERS, GrantConstant.UPDATE_USERS, GrantConstant.DELETE_USERS, "User Management"),

    /** The user roles. */
    USER_ROLES(GrantConstant.ACCESS_USER_ROLES, GrantConstant.CREATE_USER_ROLES, GrantConstant.UPDATE_USER_ROLES, GrantConstant.DELETE_USER_ROLES, "User Management"),

    /** The user groups. */
    USER_GROUPS(GrantConstant.ACCESS_USER_GROUPS, GrantConstant.CREATE_USER_GROUPS, GrantConstant.UPDATE_USER_GROUPS, GrantConstant.DELETE_USER_GROUPS, "User Management"),

    /** The account approval. */
    ACCOUNT_APPROVAL(GrantConstant.ACCOUNT_APPROVAL, null, null, null, "Account Approvals"),

    /** The analytics. */
    ANALYTICS(GrantConstant.ANALYTICS, null, null, null, "Analytics"),

    /** The bulk upload. */
    BULK_UPLOAD(GrantConstant.BULK_UPLOAD, null, null, null, "Bulk Upload Contacts"),

    /** The servers. */
    SERVERS(GrantConstant.ACCESS_SERVERS, GrantConstant.CREATE_SERVERS, GrantConstant.UPDATE_SERVERS, GrantConstant.DELETE_SERVERS, "Servers");

    /** The view grant. */
    // User Authorities
    private GrantConstant viewGrant;

    /** The create grant. */
    private GrantConstant createGrant;

    /** The update grant. */
    private GrantConstant updateGrant;

    /** The delete grant. */
    private GrantConstant deleteGrant;

    /** The module name. */
    private String moduleName;

    /**
     * Instantiates a new authority constant.
     *
     * @param viewGrant the view grant
     * @param createGrant the create grant
     * @param updateGrant the update grant
     * @param deleteGrant the delete grant
     * @param moduleName the module name
     */
    AuthorityConstant(final GrantConstant viewGrant, final GrantConstant createGrant, final GrantConstant updateGrant,
            final GrantConstant deleteGrant, final String moduleName)
    {
        this.viewGrant = viewGrant;
        this.createGrant = createGrant;
        this.updateGrant = updateGrant;
        this.deleteGrant = deleteGrant;
        this.moduleName = moduleName;
    }

    /**
     * Gets the view grant.
     *
     * @return the viewGrant
     */
    public GrantConstant getViewGrant()
    {
        return viewGrant;
    }

    /**
     * Sets the view grant.
     *
     * @param viewGrant the viewGrant to set
     */
    public void setViewGrant(final GrantConstant viewGrant)
    {
        this.viewGrant = viewGrant;
    }

    /**
     * Gets the creates the grant.
     *
     * @return the createGrant
     */
    public GrantConstant getCreateGrant()
    {
        return createGrant;
    }

    /**
     * Sets the creates the grant.
     *
     * @param createGrant the createGrant to set
     */
    public void setCreateGrant(final GrantConstant createGrant)
    {
        this.createGrant = createGrant;
    }

    /**
     * Gets the update grant.
     *
     * @return the updateGrant
     */
    public GrantConstant getUpdateGrant()
    {
        return updateGrant;
    }

    /**
     * Sets the update grant.
     *
     * @param updateGrant the updateGrant to set
     */
    public void setUpdateGrant(final GrantConstant updateGrant)
    {
        this.updateGrant = updateGrant;
    }

    /**
     * Gets the delete grant.
     *
     * @return the deleteGrant
     */
    public GrantConstant getDeleteGrant()
    {
        return deleteGrant;
    }

    /**
     * Sets the delete grant.
     *
     * @param deleteGrant the deleteGrant to set
     */
    public void setDeleteGrant(final GrantConstant deleteGrant)
    {
        this.deleteGrant = deleteGrant;
    }

    /**
     * Gets the module name.
     *
     * @return the moduleName
     */
    public String getModuleName()
    {
        return moduleName;
    }

    /**
     * Sets the module name.
     *
     * @param moduleName the moduleName to set
     */
    public void setModuleName(final String moduleName)
    {
        this.moduleName = moduleName;
    }

    /**
     * Gets the grants for module.
     *
     * @param module the module
     * @return the grants for module
     */
    public static List<GrantConstant> getGrantsForModule(final String module)
    {
        final List<GrantConstant> grantsForModule = new ArrayList<GrantConstant>();
        if (module != null && module.trim().length() > 0)
        {
            for (final AuthorityConstant authorityConstant : AuthorityConstant.values())
            {
                if (authorityConstant.getModuleName().equals(module))
                {
                    if (authorityConstant.getViewGrant() != null)
                    {
                        grantsForModule.add(authorityConstant.getViewGrant());
                    }
                    if (authorityConstant.getDeleteGrant() != null)
                    {
                        grantsForModule.add(authorityConstant.getDeleteGrant());
                    }
                    if (authorityConstant.getUpdateGrant() != null)
                    {
                        grantsForModule.add(authorityConstant.getUpdateGrant());
                    }
                    if (authorityConstant.getCreateGrant() != null)
                    {
                        grantsForModule.add(authorityConstant.getCreateGrant());
                    }
                }
            }
        }
        return grantsForModule;
    }

}
