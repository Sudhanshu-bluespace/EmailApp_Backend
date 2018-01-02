package com.bluespacetech.security.resources.assembler;

import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.controller.UserRoleController;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.resources.UserRoleResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserRoleResourceAssembler
  extends ResourceAssemblerSupport<UserRole, UserRoleResource>
{
  public UserRoleResourceAssembler()
  {
    super(UserRoleController.class, UserRoleResource.class);
  }
  
  public UserRoleResource toResource(UserRole userRole)
  {
    UserRoleResource userRoleResource = new UserRoleResource();
    userRoleResource.setObjectId(userRole.getId());
    userRoleResource.setVersion(userRole.getVersion());
    userRoleResource.setRoleName(userRole.getRoleName());
    userRoleResource.setDescription(userRole.getDescription());
    
    Link link = ((ControllerLinkBuilder)ControllerLinkBuilder.linkTo(UserRoleController.class).slash(userRole.getId())).withSelfRel();
    userRoleResource.add(link.withSelfRel());
    return userRoleResource;
  }
  
  public UserRoleResource toCompleteResource(UserRole userRole)
  {
    UserRoleResource userRoleResource = toResource(userRole);
    List<String> userRoleAuthorities = new ArrayList();
    for (UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities()) {
      userRoleAuthorities.add(userRoleAuthority.getAuthorityGrant().toString());
    }
    userRoleResource.setUserRoleAuthorities(userRoleAuthorities);
    return userRoleResource;
  }
  
  public static void copyUserRoleInto(UserRole sourceUserRole, UserRole destinationUserRole)
  {
    destinationUserRole.setRoleName(sourceUserRole.getRoleName());
    destinationUserRole.setDescription(sourceUserRole.getDescription());
    Collection<UserRoleAuthority> userRoleAuthoritiesToPersist = new ArrayList();
    Map<String, UserRoleAuthority> existingUserRoleAuthorities = new HashMap();
    for (UserRoleAuthority exitingUserRoleAuthority : destinationUserRole.getUserRoleAuthorities()) {
      existingUserRoleAuthorities.put(exitingUserRoleAuthority.getAuthorityGrant().toString(), exitingUserRoleAuthority);
    }
    for (UserRoleAuthority newUserRoleAuthority : sourceUserRole.getUserRoleAuthorities()) {
      if (existingUserRoleAuthorities.containsKey(newUserRoleAuthority.getAuthorityGrant().toString()))
      {
        userRoleAuthoritiesToPersist.add(existingUserRoleAuthorities.get(newUserRoleAuthority.getAuthorityGrant().toString()));
      }
      else
      {
        UserRoleAuthority userRoleAuthority = new UserRoleAuthority();
        userRoleAuthority.setAuthorityGrant(newUserRoleAuthority.getAuthorityGrant());
        userRoleAuthoritiesToPersist.add(userRoleAuthority);
      }
    }
    destinationUserRole.getUserRoleAuthorities().clear();
    destinationUserRole.getUserRoleAuthorities().addAll(userRoleAuthoritiesToPersist);
  }
  
  public static UserRole getUserRoleFromResource(UserRoleResource userRoleResource)
  {
    UserRole destinationUserRole = new UserRole();
    destinationUserRole.setResourceObjectId(userRoleResource.getObjectId());
    destinationUserRole.setRoleName(userRoleResource.getRoleName());
    destinationUserRole.setDescription(userRoleResource.getDescription());
    Collection<UserRoleAuthority> userRoleAuthorities = new ArrayList();
    for (String userRoleAuthorityResource : userRoleResource.getUserRoleAuthorities())
    {
      UserRoleAuthority userRoleAuthority = new UserRoleAuthority();
      userRoleAuthority.setAuthorityGrant(GrantConstant.valueOf(userRoleAuthorityResource));
      userRoleAuthorities.add(userRoleAuthority);
    }
    destinationUserRole.setUserRoleAuthorities(userRoleAuthorities);
    return destinationUserRole;
  }
}
