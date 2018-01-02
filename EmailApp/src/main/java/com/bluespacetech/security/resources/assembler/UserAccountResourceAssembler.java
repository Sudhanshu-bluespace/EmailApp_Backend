package com.bluespacetech.security.resources.assembler;

import com.bluespacetech.security.controller.UserAccountController;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.resources.UserAccountResource;
import com.bluespacetech.security.resources.UserAccountUserGroupResource;
import com.bluespacetech.security.resources.UserGroupResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserAccountResourceAssembler
  extends ResourceAssemblerSupport<UserAccount, UserAccountResource>
{
  public UserAccountResourceAssembler()
  {
    super(UserAccountController.class, UserAccountResource.class);
  }
  
  public UserAccountResource toResource(UserAccount userAccount)
  {
    UserAccountResource userAccountResource = new UserAccountResource();
    userAccountResource.setObjectId(userAccount.getId());
    userAccountResource.setVersion(userAccount.getVersion());
    userAccountResource.setUsername(userAccount.getUsername());
    userAccountResource.setEmail(userAccount.getEmail());
    userAccountResource.setAccountExpired(userAccount.isAccountExpired());
    userAccountResource.setAccountLocked(userAccount.isAccountLocked());
    userAccountResource.setActive(userAccount.isActive());
    userAccountResource.setCredentialsExpired(userAccount.isCredentialsExpired());
    userAccountResource.setUserAccountType(userAccount.getUserAccountType());
    Link link = ((ControllerLinkBuilder)ControllerLinkBuilder.linkTo(UserAccountController.class).slash(userAccount.getId())).withSelfRel();
    userAccountResource.add(link.withSelfRel());
    return userAccountResource;
  }
  
  public UserAccountResource toCompleteResource(UserAccount userAccount, Map<Long, UserGroup> userGroupsMap)
  {
    UserAccountResource userAccountResource = toResource(userAccount);
    List<UserAccountUserGroupResource> userAccountUserGroupResources = new ArrayList();
    
    UserGroupResourceAssembler userAccountResourceAssembler = new UserGroupResourceAssembler();
    if (userAccount.getUserAccountUserGroups() != null) {
      for (UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups())
      {
        UserAccountUserGroupResource userAccountUserGroupResource = new UserAccountUserGroupResource();
        
        UserGroupResource userGroupResource = userAccountResourceAssembler.toResource((UserGroup)userGroupsMap.get(userAccountUserGroup.getUserGroupId()));
        userAccountUserGroupResource.setUserGroup(userGroupResource);
        userAccountUserGroupResource.setObjectId(userAccountUserGroup.getId());
        userAccountUserGroupResources.add(userAccountUserGroupResource);
      }
    }
    userAccountResource.setUserAccountUserGroups(userAccountUserGroupResources);
    return userAccountResource;
  }
  
  public static void copyUserAccountInto(UserAccount sourceUserAccount, UserAccount destinationUserAccount)
  {
    destinationUserAccount.setAccountExpired(sourceUserAccount.isAccountExpired());
    destinationUserAccount.setAccountLocked(sourceUserAccount.isAccountLocked());
    destinationUserAccount.setActive(sourceUserAccount.isActive());
    destinationUserAccount.setCredentialsExpired(sourceUserAccount.isCredentialsExpired());
    destinationUserAccount.setUserAccountType(sourceUserAccount.getUserAccountType());
    destinationUserAccount.setUsername(sourceUserAccount.getUsername());
    destinationUserAccount.setEmail(sourceUserAccount.getEmail());
    Collection<UserAccountUserGroup> userAccountUserRoleToPersist = new ArrayList();
    Map<Long, UserAccountUserGroup> existingUserAccountUserGroups = new HashMap();
    if (destinationUserAccount.getUserAccountUserGroups() != null)
    {
      for (UserAccountUserGroup userAccountUserGroup : destinationUserAccount.getUserAccountUserGroups()) {
        existingUserAccountUserGroups.put(userAccountUserGroup.getId(), userAccountUserGroup);
      }
      for (UserAccountUserGroup newUserAccountUserGroup : sourceUserAccount.getUserAccountUserGroups()) {
        if ((newUserAccountUserGroup.getResourceObjectId() != null) && 
          (existingUserAccountUserGroups.containsKey(newUserAccountUserGroup.getResourceObjectId())))
        {
          userAccountUserRoleToPersist.add(existingUserAccountUserGroups.get(newUserAccountUserGroup.getResourceObjectId()));
        }
        else
        {
          UserAccountUserGroup userAccountUserGroup = new UserAccountUserGroup();
          userAccountUserGroup.setUserGroupId(newUserAccountUserGroup.getUserGroupId());
          userAccountUserRoleToPersist.add(userAccountUserGroup);
        }
      }
    }
    destinationUserAccount.getUserAccountUserGroups().clear();
    destinationUserAccount.getUserAccountUserGroups().addAll(userAccountUserRoleToPersist);
  }
  
  public static UserAccount getUserAccountFromResource(UserAccountResource userAccountResource)
  {
    UserAccount destinationUserAccount = new UserAccount();
    destinationUserAccount.setAccountExpired(userAccountResource.isAccountExpired());
    destinationUserAccount.setAccountLocked(userAccountResource.isAccountLocked());
    destinationUserAccount.setActive(userAccountResource.isActive());
    destinationUserAccount.setCredentialsExpired(userAccountResource.isCredentialsExpired());
    destinationUserAccount.setUserAccountType(userAccountResource.getUserAccountType());
    destinationUserAccount.setUsername(userAccountResource.getUsername());
    destinationUserAccount.setEmail(userAccountResource.getEmail());
    Collection<UserAccountUserGroup> userAccountUserGroups = new ArrayList();
    if (userAccountResource.getUserAccountUserGroups() != null) {
      for (UserAccountUserGroupResource userAccountUserGroupResource : userAccountResource
        .getUserAccountUserGroups())
      {
        UserAccountUserGroup userAccountUserGroup = new UserAccountUserGroup();
        userAccountUserGroup.setUserGroupId(userAccountUserGroupResource.getUserGroup().getObjectId());
        userAccountUserGroup.setResourceObjectId(userAccountUserGroupResource.getObjectId());
        userAccountUserGroups.add(userAccountUserGroup);
      }
    }
    destinationUserAccount.setUserAccountUserGroups(userAccountUserGroups);
    return destinationUserAccount;
  }
}
