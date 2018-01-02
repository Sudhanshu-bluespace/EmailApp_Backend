package com.bluespacetech.security.dao;

import java.io.Serializable;
import java.util.Collection;

public class UserDAO
  implements Serializable
{
  private static final long serialVersionUID = -8695248104044241700L;
  private String loggedInUserName;
  private String userType;
  private Collection<String> roles;
  private Collection<String> uiRoles;
  
  public String getLoggedInUserName()
  {
    return this.loggedInUserName;
  }
  
  public void setLoggedInUserName(String loggedInUserName)
  {
    this.loggedInUserName = loggedInUserName;
  }
  
  public Collection<String> getRoles()
  {
    return this.roles;
  }
  
  public void setRoles(Collection<String> roles)
  {
    this.roles = roles;
  }
  
  public Collection<String> getUiRoles()
  {
    return this.uiRoles;
  }
  
  public void setUiRoles(Collection<String> uiRoles)
  {
    this.uiRoles = uiRoles;
  }
  
  public String getUserType()
  {
    return this.userType;
  }
  
  public void setUserType(String userType)
  {
    this.userType = userType;
  }
}
