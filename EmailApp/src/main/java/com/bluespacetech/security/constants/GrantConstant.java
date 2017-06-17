package com.bluespacetech.security.constants;

import java.util.HashMap;
import java.util.Map;

import com.bluespacetech.core.constants.Labeled;

/**
 * Enums for Authority constants.
 *
 * @author Pradeep
 */
public enum GrantConstant implements Labeled
{

    /** The none. */
    NONE("none", "NONE"),

    /** The access dashboard. */
    ACCESS_DASHBOARD("access_dashboard", "UI_ACCESS_DASHBOARD"),

    /** The access reports. */
    ACCESS_REPORTS("access_reports", "UI_ACCESS_REPORTS"),

    /** The access send email. */
    ACCESS_SEND_EMAIL("access_send_email", "UI_ACCESS_SEND_EMAIL"),

    /** The access contacts. */
    ACCESS_CONTACTS("access_contacts", "UI_ACCESS_CONTACTS"),

    /** The create contacts. */
    CREATE_CONTACTS("create_contacts", "UI_CREATE_CONTACTS"),

    /** The update contacts. */
    UPDATE_CONTACTS("update_contacts", "UI_UPDATE_CONTACTS"),

    /** The delete contacts. */
    DELETE_CONTACTS("delete_contacts", "UI_DELETE_CONTACTS"),

    /** The access groups. */
    ACCESS_GROUPS("access_groups", "UI_ACCESS_GROUPS"),

    /** The create groups. */
    CREATE_GROUPS("create_groups", "UI_CREATE_GROUPS"),

    /** The update groups. */
    UPDATE_GROUPS("update_groups", "UI_UPDATE_GROUPS"),

    /** The delete groups. */
    DELETE_GROUPS("delete_groups", "UI_DELETE_GROUPS"),

    /** The access servers. */
    ACCESS_SERVERS("access_servers", "UI_ACCESS_SERVERS"),

    /** The create servers. */
    CREATE_SERVERS("create_servers", "UI_CREATE_SERVERS"),

    /** The update servers. */
    UPDATE_SERVERS("update_servers", "UI_UPDATE_SERVERS"),

    /** The delete servers. */
    DELETE_SERVERS("delete_servers", "UI_DELETE_SERVERS"),

    /** The access user roles. */
    ACCESS_USER_ROLES("access_user_roles", "UI_USER_ROLES_ACCESS"),

    /** The create user roles. */
    CREATE_USER_ROLES("create_user_roles", "UI_USER_ROLES_CREATE"),

    /** The update user roles. */
    UPDATE_USER_ROLES("update_user_roles", "UI_USER_ROLES_UPDATE"),

    /** The delete user roles. */
    DELETE_USER_ROLES("delete_user_roles", "UI_USER_ROLES_DELETE"),

    /** The access user groups. */
    ACCESS_USER_GROUPS("access_user_groups", "UI_USER_GROUPS_ACCESS"),

    /** The create user groups. */
    CREATE_USER_GROUPS("create_user_groups", "UI_USER_GROUPS_CREATE"),

    /** The update user groups. */
    UPDATE_USER_GROUPS("update_user_groups", "UI_USER_GROUPS_UPDATE"),

    /** The delete user groups. */
    DELETE_USER_GROUPS("delete_user_groups", "UI_USER_GROUPS_DELETE"),

    /** The access users. */
    ACCESS_USERS("access_users", "UI_USERS_ACCESS"),

    /** The create users. */
    CREATE_USERS("create_users", "UI_USERS_CREATE"),

    /** The update users. */
    UPDATE_USERS("update_users", "UI_USERS_UPDATE"),

    /** The delete users. */
    DELETE_USERS("delete_users", "UI_USERS_DELETE"),

    /** The account approval. */
    ACCOUNT_APPROVAL("account_approval", "UI_ACCESS_ACCOUNT_APPROVAL"),

    /** The analytics. */
    ANALYTICS("analytics", "UI_ACCESS_ANAlYTICS"),

    /** The bulk upload. */
    BULK_UPLOAD("bulk_upload", "UI_ACCESS_BULK_UPLOAD");

    /** The label. */
    // User Authorities
    private String label;

    /** The grant UI. */
    private String grantUI;

    /**
     * Instantiates a new grant constant.
     *
     * @param label the label
     * @param grantUI the grant UI
     */
    GrantConstant(final String label, final String grantUI)
    {
        this.label = label;
        this.grantUI = grantUI;
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
     * Gets the grant UI.
     *
     * @return the grantUI
     */
    public String getGrantUI()
    {
        return grantUI;
    }

    /**
     * Sets the grant UI.
     *
     * @param grantUI the grantUI to set
     */
    public void setGrantUI(final String grantUI)
    {
        this.grantUI = grantUI;
    }

    /**
     * Gets the UI grants by grants.
     *
     * @return the UI grants by grants
     */
    public static Map<String, String> getUIGrantsByGrants()
    {
        final Map<String, String> uiGrantsByGrantsMap = new HashMap<String, String>();
        for (final GrantConstant grantConstant : GrantConstant.values())
        {
            uiGrantsByGrantsMap.put(grantConstant.getLabel().toUpperCase(), grantConstant.getGrantUI().toUpperCase());
        }
        return uiGrantsByGrantsMap;
    }
}
