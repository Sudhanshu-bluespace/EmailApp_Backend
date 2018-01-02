package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;
import com.bluespacetech.security.constants.UserAccountTypeConstant;

public class UserAccountSearchCriteria
  implements SearchCriteria
{
  private static final long serialVersionUID = -7524669904323526192L;
  private Long branchId;
  private String username;
  private boolean active;
  private boolean accountExpired;
  private boolean credentialsExpired;
  private boolean accountLocked;
  private UserAccountTypeConstant userAccountType;
  
  public Long getBranchId()
  {
    return this.branchId;
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public void setActive(boolean active)
  {
    this.active = active;
  }
  
  public boolean isAccountExpired()
  {
    return this.accountExpired;
  }
  
  public void setAccountExpired(boolean accountExpired)
  {
    this.accountExpired = accountExpired;
  }
  
  public boolean isCredentialsExpired()
  {
    return this.credentialsExpired;
  }
  
  public void setCredentialsExpired(boolean credentialsExpired)
  {
    this.credentialsExpired = credentialsExpired;
  }
  
  public boolean isAccountLocked()
  {
    return this.accountLocked;
  }
  
  public void setAccountLocked(boolean accountLocked)
  {
    this.accountLocked = accountLocked;
  }
  
  public UserAccountTypeConstant getUserAccountType()
  {
    return this.userAccountType;
  }
  
  public void setUserAccountType(UserAccountTypeConstant userAccountType)
  {
    this.userAccountType = userAccountType;
  }
}
