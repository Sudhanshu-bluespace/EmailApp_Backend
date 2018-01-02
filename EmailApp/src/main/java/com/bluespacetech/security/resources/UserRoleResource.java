package com.bluespacetech.security.resources;

import java.util.Collection;
import org.springframework.hateoas.ResourceSupport;

public class UserRoleResource
  extends ResourceSupport
{
  private Long objectId;
  private Long version;
  private String roleName;
  private String description;
  private Collection<String> userRoleAuthorities;
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public Collection<String> getUserRoleAuthorities()
  {
    return this.userRoleAuthorities;
  }
  
  public void setUserRoleAuthorities(Collection<String> userRoleAuthorities)
  {
    this.userRoleAuthorities = userRoleAuthorities;
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
}
