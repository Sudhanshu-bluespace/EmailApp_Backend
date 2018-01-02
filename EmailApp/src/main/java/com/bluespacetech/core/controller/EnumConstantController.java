package com.bluespacetech.core.controller;

import com.bluespacetech.security.constants.AuthorityConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.resources.AuthoritiesByModuleResource;
import com.bluespacetech.security.resources.AuthorityResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/enumConstants"})
public class EnumConstantController
  extends AbstractBaseController
{
  @RequestMapping(value={"/authorities"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public AuthorityConstant[] getAuthorities()
  {
    return AuthorityConstant.values();
  }
  
  @RequestMapping(value={"/authorityConstants"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public AuthoritiesByModuleResource[] getAuthorityConstants()
  {
    Map<String, List<AuthorityResource>> authorityConstantMap = new HashMap();
    for (AuthorityConstant authorityConstant : AuthorityConstant.values()) {
      if (authorityConstantMap.get(authorityConstant.getModuleName()) == null)
      {
        List<AuthorityResource> authorityConstants = new ArrayList();
        authorityConstants.add(AuthorityResource.getAuthorityResource(authorityConstant));
        authorityConstantMap.put(authorityConstant.getModuleName(), authorityConstants);
      }
      else
      {
        ((List)authorityConstantMap.get(authorityConstant.getModuleName())).add(AuthorityResource.getAuthorityResource(authorityConstant));
      }
    }
    AuthoritiesByModuleResource[] authoritiesByModuleResources = new AuthoritiesByModuleResource[authorityConstantMap.keySet().size()];
    int i = 0;
    for (Map.Entry<String, List<AuthorityResource>> entry : authorityConstantMap.entrySet())
    {
      AuthoritiesByModuleResource authoritiesByModuleResource = new AuthoritiesByModuleResource();
      authoritiesByModuleResource.setModuleName((String)entry.getKey());
      authoritiesByModuleResource.setAuthorityResources((List)entry.getValue());
      authoritiesByModuleResources[i] = authoritiesByModuleResource;
      i++;
    }
    return authoritiesByModuleResources;
  }
  
  @RequestMapping(value={"/userAccountTypeConstants"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  @ResponseBody
  public UserAccountTypeConstant[] getUserAccountTypeConstants()
  {
    return UserAccountTypeConstant.getUserAccountTypesForNewUser();
  }
}
