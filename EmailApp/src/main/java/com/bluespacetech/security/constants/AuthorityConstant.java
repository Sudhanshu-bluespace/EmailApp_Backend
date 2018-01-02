package com.bluespacetech.security.constants;

import java.util.ArrayList;
import java.util.List;

public enum AuthorityConstant
{
  DASHBOARD(GrantConstant.ACCESS_DASHBOARD, null, null, null, "Dash Board"),  REPORTS(GrantConstant.ACCESS_REPORTS, null, null, null, "Reports"),  SEND_EMAIL(GrantConstant.ACCESS_SEND_EMAIL, null, null, null, "Email"),  GROUPS(GrantConstant.ACCESS_GROUPS, GrantConstant.CREATE_GROUPS, GrantConstant.UPDATE_GROUPS, GrantConstant.DELETE_GROUPS, "Email"),  CONTACTS(GrantConstant.ACCESS_CONTACTS, GrantConstant.CREATE_CONTACTS, GrantConstant.UPDATE_CONTACTS, GrantConstant.DELETE_CONTACTS, "Contacts"),  USERS(GrantConstant.ACCESS_USERS, GrantConstant.CREATE_USERS, GrantConstant.UPDATE_USERS, GrantConstant.DELETE_USERS, "User Management"),  USER_ROLES(GrantConstant.ACCESS_USER_ROLES, GrantConstant.CREATE_USER_ROLES, GrantConstant.UPDATE_USER_ROLES, GrantConstant.DELETE_USER_ROLES, "User Management"),  USER_GROUPS(GrantConstant.ACCESS_USER_GROUPS, GrantConstant.CREATE_USER_GROUPS, GrantConstant.UPDATE_USER_GROUPS, GrantConstant.DELETE_USER_GROUPS, "User Management"),  ACCOUNT_APPROVAL(GrantConstant.ACCOUNT_APPROVAL, null, null, null, "Account Approvals"),  ANALYTICS(GrantConstant.ANALYTICS, null, null, null, "Analytics"),  BULK_UPLOAD(GrantConstant.BULK_UPLOAD, null, null, null, "Bulk Upload Contacts"),  SERVERS(GrantConstant.ACCESS_SERVERS, GrantConstant.CREATE_SERVERS, GrantConstant.UPDATE_SERVERS, GrantConstant.DELETE_SERVERS, "Servers");
  
  private GrantConstant viewGrant;
  private GrantConstant createGrant;
  private GrantConstant updateGrant;
  private GrantConstant deleteGrant;
  private String moduleName;
  
  private AuthorityConstant(GrantConstant viewGrant, GrantConstant createGrant, GrantConstant updateGrant, GrantConstant deleteGrant, String moduleName)
  {
    this.viewGrant = viewGrant;
    this.createGrant = createGrant;
    this.updateGrant = updateGrant;
    this.deleteGrant = deleteGrant;
    this.moduleName = moduleName;
  }
  
  public GrantConstant getViewGrant()
  {
    return this.viewGrant;
  }
  
  public void setViewGrant(GrantConstant viewGrant)
  {
    this.viewGrant = viewGrant;
  }
  
  public GrantConstant getCreateGrant()
  {
    return this.createGrant;
  }
  
  public void setCreateGrant(GrantConstant createGrant)
  {
    this.createGrant = createGrant;
  }
  
  public GrantConstant getUpdateGrant()
  {
    return this.updateGrant;
  }
  
  public void setUpdateGrant(GrantConstant updateGrant)
  {
    this.updateGrant = updateGrant;
  }
  
  public GrantConstant getDeleteGrant()
  {
    return this.deleteGrant;
  }
  
  public void setDeleteGrant(GrantConstant deleteGrant)
  {
    this.deleteGrant = deleteGrant;
  }
  
  public String getModuleName()
  {
    return this.moduleName;
  }
  
  public void setModuleName(String moduleName)
  {
    this.moduleName = moduleName;
  }
  
  public static List<GrantConstant> getGrantsForModule(String module)
  {
    List<GrantConstant> grantsForModule = new ArrayList();
    if ((module != null) && (module.trim().length() > 0)) {
      for (AuthorityConstant authorityConstant : values()) {
        if (authorityConstant.getModuleName().equals(module))
        {
          if (authorityConstant.getViewGrant() != null) {
            grantsForModule.add(authorityConstant.getViewGrant());
          }
          if (authorityConstant.getDeleteGrant() != null) {
            grantsForModule.add(authorityConstant.getDeleteGrant());
          }
          if (authorityConstant.getUpdateGrant() != null) {
            grantsForModule.add(authorityConstant.getUpdateGrant());
          }
          if (authorityConstant.getCreateGrant() != null) {
            grantsForModule.add(authorityConstant.getCreateGrant());
          }
        }
      }
    }
    return grantsForModule;
  }
}
