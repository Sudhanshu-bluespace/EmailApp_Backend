package com.bluespacetech.group.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import java.util.List;

public abstract interface GroupService
{
  public abstract Group createGroup(Group paramGroup)
    throws BusinessException;
  
  public abstract void deleteGroup(Long paramLong)
    throws BusinessException;
  
  public abstract List<Group> findByName(String paramString);
  
  public abstract List<Group> findAll();
  
  public abstract Group getGroupById(Long paramLong);
  
  public abstract Group updateGroup(Group paramGroup)
    throws BusinessException;
  
  public abstract List<Group> findBySearchCriteria(GroupSearchCriteria paramGroupSearchCriteria);
  
  public abstract List<Group> findByCreatedUser(String paramString);
}
