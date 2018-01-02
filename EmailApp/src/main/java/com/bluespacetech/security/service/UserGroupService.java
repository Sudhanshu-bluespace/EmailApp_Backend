package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;
import java.util.List;

public abstract interface UserGroupService
{
  public abstract UserGroup findUserGroupByGroupName(String paramString);
  
  public abstract List<UserGroup> findByGroupNameLike(String paramString);
  
  public abstract List<UserGroup> findByDescriptionLike(String paramString);
  
  public abstract List<UserGroup> getAllUserGroups();
  
  public abstract UserGroup getUserGroupById(Long paramLong);
  
  public abstract List<UserGroup> getUserGroupByIds(List<Long> paramList);
  
  public abstract UserGroup createUserGroup(UserGroup paramUserGroup)
    throws BusinessException;
  
  public abstract UserGroup updateUserGroup(UserGroup paramUserGroup)
    throws BusinessException;
  
  public abstract void deleteUserGroup(Long paramLong);
  
  public abstract List<UserGroup> findUserGroupsBySearchCriteria(UserGroupSearchCriteria paramUserGroupSearchCriteria);
}
