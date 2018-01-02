package com.bluespacetech.security.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class UIModuleRolesResource
  extends ResourceSupport
{
  private Collection<String> uiUserRoles;
  private String moduleName;
  private final List<String> moduleNames = new ArrayList();
  
  public Collection<String> getUiUserRoles()
  {
    return this.uiUserRoles;
  }
  
  public void setUiUserRoles(Collection<String> uiUserRoles)
  {
    this.uiUserRoles = uiUserRoles;
  }
  
  public String getModuleName()
  {
    return this.moduleName;
  }
  
  public void setModuleName(String moduleName)
  {
    this.moduleName = moduleName;
  }
  
  public List<String> getModuleNames()
  {
    return this.moduleNames;
  }
}
