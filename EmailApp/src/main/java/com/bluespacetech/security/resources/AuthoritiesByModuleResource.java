package com.bluespacetech.security.resources;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class AuthoritiesByModuleResource
  extends ResourceSupport
{
  private String moduleName;
  private List<AuthorityResource> authorityResources = new ArrayList();
  
  public String getModuleName()
  {
    return this.moduleName;
  }
  
  public void setModuleName(String moduleName)
  {
    this.moduleName = moduleName;
  }
  
  public List<AuthorityResource> getAuthorityResources()
  {
    return this.authorityResources;
  }
  
  public void setAuthorityResources(List<AuthorityResource> authorityResources)
  {
    this.authorityResources = authorityResources;
  }
}
