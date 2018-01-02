package com.bluespacetech.security.resources;

import org.springframework.hateoas.ResourceSupport;

public class UserAccountChangePasswordResource
  extends ResourceSupport
{
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;
  
  public String getOldPassword()
  {
    return this.oldPassword;
  }
  
  public void setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
  }
  
  public String getNewPassword()
  {
    return this.newPassword;
  }
  
  public void setNewPassword(String newPassword)
  {
    this.newPassword = newPassword;
  }
  
  public String getConfirmPassword()
  {
    return this.confirmPassword;
  }
  
  public void setConfirmPassword(String confirmPassword)
  {
    this.confirmPassword = confirmPassword;
  }
}
