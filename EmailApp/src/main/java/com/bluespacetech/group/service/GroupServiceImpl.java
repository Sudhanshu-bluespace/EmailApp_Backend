package com.bluespacetech.group.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.repository.GroupRepository;
import com.bluespacetech.group.repository.GroupRepositoryCustom;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class GroupServiceImpl
  implements GroupService
{
  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private GroupRepositoryCustom groupRepositoryCustom;
  
  public static void validateGroup(Group group)
    throws BusinessException
  {
    if ((group.getName() == null) || (group.getName().trim().length() == 0)) {
      throw new BusinessException("Group Name is Mandatory.");
    }
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_GROUPS'))")
  public Group createGroup(Group group)
    throws BusinessException
  {
    validateGroup(group);
    Group newGroup = (Group)this.groupRepository.save(group);
    return newGroup;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_GROUPS'))")
  public void deleteGroup(Long groupId)
    throws BusinessException
  {
    this.groupRepository.delete(groupId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_GROUPS'))")
  public Group getGroupById(Long groupId)
  {
    Group group = (Group)this.groupRepository.findOne(groupId);
    return group;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_GROUPS'))")
  public Group updateGroup(Group group)
    throws BusinessException
  {
    validateGroup(group);
    Group updatedGroup = (Group)this.groupRepository.save(group);
    return updatedGroup;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_GROUPS'))")
  public List<Group> findByName(String email)
  {
    return this.groupRepository.findByNameLike(email);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_GROUPS'))")
  public List<Group> findAll()
  {
    return this.groupRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_GROUPS'))")
  public List<Group> findBySearchCriteria(GroupSearchCriteria groupSearchCriteria)
  {
    return this.groupRepositoryCustom.findGroupsBySearchCriteria(groupSearchCriteria);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_GROUPS'))")
  public List<Group> findByCreatedUser(String username)
  {
    return this.groupRepository.findByCreatedUser(username);
  }
}
