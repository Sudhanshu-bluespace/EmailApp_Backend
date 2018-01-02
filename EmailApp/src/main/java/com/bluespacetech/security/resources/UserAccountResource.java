package com.bluespacetech.security.resources;

import com.bluespacetech.security.constants.UserAccountTypeConstant;
import java.util.Collection;
import org.springframework.hateoas.ResourceSupport;

public class UserAccountResource
  extends ResourceSupport
{
  private Long objectId;
  private Long version;
  private Collection<UserAccountUserGroupResource> userAccountUserGroups;
  private String username;
  private boolean active;
  private boolean accountExpired;
  private boolean credentialsExpired;
  private boolean accountLocked;
  private UserAccountTypeConstant userAccountType;
  private String employeeNumber;
  private String password;
  private String email;
  private String companyName;
  
  public String getCompanyName()
  {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }
  
  public Long getObjectId()
  {
    return this.objectId;
  }
  
  public void setObjectId(Long objectId)
  {
    this.objectId = objectId;
  }
  
  public Long getVersion()
  {
    return this.version;
  }
  
  public void setVersion(Long version)
  {
    this.version = version;
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
  
  public Collection<UserAccountUserGroupResource> getUserAccountUserGroups()
  {
    return this.userAccountUserGroups;
  }
  
  public void setUserAccountUserGroups(Collection<UserAccountUserGroupResource> userAccountUserGroups)
  {
    this.userAccountUserGroups = userAccountUserGroups;
  }
  
  public String getEmployeeNumber()
  {
    return this.employeeNumber;
  }
  
  public void setEmployeeNumber(String employeeNumber)
  {
    this.employeeNumber = employeeNumber;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
}
