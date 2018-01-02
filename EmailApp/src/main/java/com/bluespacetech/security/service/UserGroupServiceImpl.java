package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.repository.UserGroupRepository;
import com.bluespacetech.security.repository.UserGroupRepositoryCustom;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class UserGroupServiceImpl
  implements UserGroupService
{
  @Autowired
  UserGroupRepository userGroupRepository;
  @Autowired
  UserGroupRepositoryCustom userGroupRepositoryCustom;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public UserGroup findUserGroupByGroupName(String groupName)
  {
    return this.userGroupRepository.findUserGroupByGroupName(groupName);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public List<UserGroup> findByDescriptionLike(String description)
  {
    return this.userGroupRepository.findByDescriptionLike(description);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public List<UserGroup> getAllUserGroups()
  {
    return this.userGroupRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public UserGroup getUserGroupById(Long userGroupId)
  {
    return (UserGroup)this.userGroupRepository.findOne(userGroupId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_USER_GROUPS'))")
  public UserGroup createUserGroup(UserGroup userGroup)
    throws BusinessException
  {
    for (UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles()) {
      userGroupUserRole.setUserGroup(userGroup);
    }
    return (UserGroup)this.userGroupRepository.save(userGroup);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_USER_GROUPS'))")
  public UserGroup updateUserGroup(UserGroup userGroup)
    throws BusinessException
  {
    for (UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles()) {
      userGroupUserRole.setUserGroup(userGroup);
    }
    return (UserGroup)this.userGroupRepository.save(userGroup);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_USER_GROUPS'))")
  public void deleteUserGroup(Long userGroupId)
  {
    this.userGroupRepository.delete(userGroupId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public List<UserGroup> findByGroupNameLike(String groupName)
  {
    return this.userGroupRepository.findByGroupNameLike(groupName);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public List<UserGroup> findUserGroupsBySearchCriteria(UserGroupSearchCriteria sectionSearchCriteria)
  {
    return this.userGroupRepositoryCustom.findUserGroupsBySearchCriteria(sectionSearchCriteria);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_GROUPS'))")
  public List<UserGroup> getUserGroupByIds(List<Long> userGroupIds)
  {
    return this.userGroupRepository.findAll(userGroupIds);
  }
}
