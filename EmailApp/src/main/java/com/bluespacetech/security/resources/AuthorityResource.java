package com.bluespacetech.security.resources;

import com.bluespacetech.security.constants.AuthorityConstant;
import com.bluespacetech.security.constants.GrantConstant;
import org.springframework.hateoas.ResourceSupport;

public class AuthorityResource
  extends ResourceSupport
{
  private AuthorityConstant authorityConstant;
  private GrantConstant viewGrant;
  private GrantConstant createGrant;
  private GrantConstant updateGrant;
  private GrantConstant deleteGrant;
  private String moduleName;
  
  public AuthorityConstant getAuthorityConstant()
  {
    return this.authorityConstant;
  }
  
  public void setAuthorityConstant(AuthorityConstant authorityConstant)
  {
    this.authorityConstant = authorityConstant;
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
  
  public static AuthorityResource getAuthorityResource(AuthorityConstant authorityConstant)
  {
    AuthorityResource authorityResource = new AuthorityResource();
    authorityResource.setAuthorityConstant(authorityConstant);
    authorityResource.setViewGrant(authorityConstant.getViewGrant());
    authorityResource.setCreateGrant(authorityConstant.getCreateGrant());
    authorityResource.setUpdateGrant(authorityConstant.getUpdateGrant());
    authorityResource.setDeleteGrant(authorityConstant.getDeleteGrant());
    authorityResource.setModuleName(authorityConstant.getModuleName());
    return authorityResource;
  }
}
