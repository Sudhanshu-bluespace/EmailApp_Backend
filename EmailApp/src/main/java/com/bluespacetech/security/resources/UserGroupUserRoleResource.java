package com.bluespacetech.security.resources;

import org.springframework.hateoas.ResourceSupport;

public class UserGroupUserRoleResource
  extends ResourceSupport
{
  private Long objectId;
  private Long version;
  private UserRoleResource userRole;
  
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
  
  public UserRoleResource getUserRole()
  {
    return this.userRole;
  }
  
  public void setUserRole(UserRoleResource userRole)
  {
    this.userRole = userRole;
  }
}
