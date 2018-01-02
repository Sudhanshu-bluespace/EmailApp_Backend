package com.bluespacetech.security.resources;

import java.util.Collection;
import org.springframework.hateoas.ResourceSupport;

public class UserGroupResource
  extends ResourceSupport
{
  private Long objectId;
  private Long version;
  private String groupName;
  private String description;
  private Collection<UserGroupUserRoleResource> userGroupUserRoles;
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
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
  
  public Collection<UserGroupUserRoleResource> getUserGroupUserRoles()
  {
    return this.userGroupUserRoles;
  }
  
  public void setUserGroupUserRoles(Collection<UserGroupUserRoleResource> userGroupUserRoles)
  {
    this.userGroupUserRoles = userGroupUserRoles;
  }
}
