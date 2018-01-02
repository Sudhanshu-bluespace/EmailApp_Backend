package com.bluespacetech.security.constants;

import com.bluespacetech.core.constants.Labeled;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum PageLinkConstant
  implements Labeled
{
  DASHBOARD("Dashboard", 0, "dashboard", PageLinkTypeConstant.LINK, false, UserAccountTypeConstant.getAllUserAccountTypes(), EnumSet.of(GrantConstant.NONE)),  REPORTS("Reports", 4, "reports", PageLinkTypeConstant.TOGGLE, true, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_REPORTS)),  SEND_EMAILS("Send Emails", 1, "send_emails", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SEND_EMAIL)),  CONTACTS("Contacts", 2, "contacts", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_CONTACTS)),  SERVERS("Servers", 5, "servers", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_SERVERS)),  USER_ROLES("User Roles", 9, "user_roles", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_ROLES)),  USER_GROUPS("User Groups", 10, "user_groups", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),  USER_ACCOUNTS("User Accounts", 11, "user_accounts", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_USER_GROUPS)),  GROUPS("Groups", 3, "groups", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ACCESS_GROUPS)),  ANALYTICS("Analytics", 6, "analytics", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN, UserAccountTypeConstant.ACC_TYPE_ADMIN, UserAccountTypeConstant.ACC_TYPE_EMPLOYEE), EnumSet.of(GrantConstant.ANALYTICS)),  ACCOUNT_APPROVAL("Account Approval", 7, "account_approval", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN), EnumSet.of(GrantConstant.ACCOUNT_APPROVAL)),  BULK_UPLOAD("Bulk Upload", 8, "bulk_upload", PageLinkTypeConstant.LINK, false, EnumSet.of(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN), EnumSet.of(GrantConstant.BULK_UPLOAD));
  
  private String label;
  private final int displayOrder;
  private Set<PageLinkConstant> childPageLinks;
  private final String pageUrl;
  private final PageLinkTypeConstant urlType;
  private final boolean branchRequired;
  private final Set<UserAccountTypeConstant> userAccountTypes;
  private final Set<GrantConstant> grants;
  private final Set<PageLinkConstant> childPageLinksAllowedForUser = new HashSet();
  private static final Set<PageLinkConstant> togglePages;
  
  private PageLinkConstant(String label, int displayOrder, String pageUrl, PageLinkTypeConstant urlType, boolean branchRequired, Set<UserAccountTypeConstant> userAccountTypes, Set<GrantConstant> grants)
  {
    this.label = label;
    this.pageUrl = pageUrl;
    this.displayOrder = displayOrder;
    this.urlType = urlType;
    this.branchRequired = branchRequired;
    
    this.userAccountTypes = userAccountTypes;
    this.grants = grants;
  }
  
  static
  {
    togglePages = new HashSet();
    
    togglePages.add(SEND_EMAILS);
    togglePages.add(CONTACTS);
    togglePages.add(SERVERS);
    togglePages.add(SERVERS);
    togglePages.add(GROUPS);
    togglePages.add(USER_ROLES);
    togglePages.add(USER_GROUPS);
    togglePages.add(DASHBOARD);
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String label)
  {
    this.label = label;
  }
  
  public Set<PageLinkConstant> getChildPageLinks()
  {
    return this.childPageLinks;
  }
  
  public String getPageUrl()
  {
    return this.pageUrl;
  }
  
  public PageLinkTypeConstant getUrlType()
  {
    return this.urlType;
  }
  
  public boolean isBranchRequired()
  {
    return this.branchRequired;
  }
  
  public Set<UserAccountTypeConstant> getUserAccountTypes()
  {
    return this.userAccountTypes;
  }
  
  public Set<GrantConstant> getGrants()
  {
    return this.grants;
  }
  
  public static Set<PageLinkConstant> getTogglepages()
  {
    return togglePages;
  }
  
  public Set<PageLinkConstant> getChildPageLinksAllowedForUser()
  {
    return this.childPageLinksAllowedForUser;
  }
  
  public int getDisplayOrder()
  {
    return this.displayOrder;
  }
}
