package com.bluespacetech.security.resources.assembler;

import com.bluespacetech.security.controller.UserGroupController;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.resources.UserGroupResource;
import com.bluespacetech.security.resources.UserGroupUserRoleResource;
import com.bluespacetech.security.resources.UserRoleResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserGroupResourceAssembler
  extends ResourceAssemblerSupport<UserGroup, UserGroupResource>
{
  public UserGroupResourceAssembler()
  {
    super(UserGroupController.class, UserGroupResource.class);
  }
  
  public UserGroupResource toResource(UserGroup userGroup)
  {
    UserGroupResource userGroupResource = new UserGroupResource();
    userGroupResource.setObjectId(userGroup.getId());
    userGroupResource.setVersion(userGroup.getVersion());
    userGroupResource.setGroupName(userGroup.getGroupName());
    userGroupResource.setDescription(userGroup.getDescription());
    
    Link link = ((ControllerLinkBuilder)ControllerLinkBuilder.linkTo(UserGroupController.class).slash(userGroup.getId())).withSelfRel();
    userGroupResource.add(link.withSelfRel());
    return userGroupResource;
  }
  
  public UserGroupResource toCompleteResource(UserGroup userGroup, Map<Long, UserRole> userRolesMap)
  {
    UserGroupResource userGroupResource = toResource(userGroup);
    List<UserGroupUserRoleResource> userGroupUserRoleResources = new ArrayList();
    UserRoleResourceAssembler userRoleResourceAssembler = new UserRoleResourceAssembler();
    for (UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles())
    {
      UserGroupUserRoleResource userGroupUserRoleResource = new UserGroupUserRoleResource();
      UserRoleResource userRoleResource = userRoleResourceAssembler.toCompleteResource((UserRole)userRolesMap.get(userGroupUserRole.getUserRoleId()));
      userGroupUserRoleResource.setUserRole(userRoleResource);
      userGroupUserRoleResource.setObjectId(userGroupUserRole.getId());
      userGroupUserRoleResources.add(userGroupUserRoleResource);
    }
    userGroupResource.setUserGroupUserRoles(userGroupUserRoleResources);
    return userGroupResource;
  }
  
  public static void copyUserGroupInto(UserGroup sourceUserGroup, UserGroup destinationUserGroup)
  {
    destinationUserGroup.setGroupName(sourceUserGroup.getGroupName());
    destinationUserGroup.setDescription(sourceUserGroup.getDescription());
    
    Collection<UserGroupUserRole> userGroupUserRoleToPersist = new ArrayList();
    Map<Long, UserGroupUserRole> existingUserGroupUserRole = new HashMap();
    for (UserGroupUserRole userGroupUserRole : destinationUserGroup.getUserGroupUserRoles()) {
      existingUserGroupUserRole.put(userGroupUserRole.getId(), userGroupUserRole);
    }
    for (UserGroupUserRole newUserGroupUserRole : sourceUserGroup.getUserGroupUserRoles()) {
      if ((newUserGroupUserRole.getResourceObjectId() != null) && (existingUserGroupUserRole.containsKey(newUserGroupUserRole.getResourceObjectId())))
      {
        userGroupUserRoleToPersist.add(existingUserGroupUserRole.get(newUserGroupUserRole.getResourceObjectId()));
      }
      else
      {
        UserGroupUserRole userGroupUserRole = new UserGroupUserRole();
        userGroupUserRole.setUserRoleId(newUserGroupUserRole.getUserRoleId());
        userGroupUserRoleToPersist.add(userGroupUserRole);
      }
    }
    destinationUserGroup.getUserGroupUserRoles().clear();
    destinationUserGroup.getUserGroupUserRoles().addAll(userGroupUserRoleToPersist);
  }
  
  public static UserGroup getUserGroupFromResource(UserGroupResource userGroupResource)
  {
    UserGroup destinationUserGroup = new UserGroup();
    destinationUserGroup.setGroupName(userGroupResource.getGroupName());
    destinationUserGroup.setDescription(userGroupResource.getDescription());
    Collection<UserGroupUserRole> userGroupUserRoles = new ArrayList();
    for (UserGroupUserRoleResource userGroupUserRoleResource : userGroupResource.getUserGroupUserRoles())
    {
      UserGroupUserRole userGroupUserRole = new UserGroupUserRole();
      userGroupUserRole.setUserRoleId(userGroupUserRoleResource.getUserRole().getObjectId());
      userGroupUserRole.setResourceObjectId(userGroupUserRoleResource.getObjectId());
      userGroupUserRoles.add(userGroupUserRole);
    }
    destinationUserGroup.setUserGroupUserRole(userGroupUserRoles);
    return destinationUserGroup;
  }
}
