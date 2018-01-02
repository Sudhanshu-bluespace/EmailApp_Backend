package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.repository.UserRoleRepository;
import com.bluespacetech.security.repository.UserRoleRepositoryCustom;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class UserRoleServiceImpl
  implements UserRoleService
{
  @Autowired
  UserRoleRepository userRoleRepository;
  @Autowired
  UserRoleRepositoryCustom userRoleRepositoryCustom;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public UserRole findUserRoleByRoleName(String roleName)
  {
    return this.userRoleRepository.findUserRoleByRoleName(roleName);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public List<UserRole> findByDescriptionLike(String description)
  {
    return this.userRoleRepository.findByDescriptionLike(description);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public List<UserRole> getAllUserRoles()
  {
    return this.userRoleRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public UserRole getUserRoleById(Long userRoleId)
  {
    return (UserRole)this.userRoleRepository.findOne(userRoleId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_USER_ROLES'))")
  public UserRole createUserRole(UserRole userRole)
    throws BusinessException
  {
    for (UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities()) {
      userRoleAuthority.setUserRole(userRole);
    }
    return (UserRole)this.userRoleRepository.save(userRole);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_USER_ROLES'))")
  public UserRole updateUserRole(UserRole userRole)
    throws BusinessException
  {
    for (UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities()) {
      userRoleAuthority.setUserRole(userRole);
    }
    return (UserRole)this.userRoleRepository.save(userRole);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_USER_ROLES'))")
  public void deleteUserRole(Long userRoleId)
  {
    this.userRoleRepository.delete(userRoleId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public List<UserRole> findByRoleNameLike(String roleName)
  {
    return this.userRoleRepository.findByRoleNameLike(roleName);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public List<UserRole> findUserRolesBySearchCriteria(UserRoleSearchCriteria sectionSearchCriteria)
  {
    return this.userRoleRepositoryCustom.findUserRolesBySearchCriteria(sectionSearchCriteria);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
  public List<UserRole> getUserRoleByIds(List<Long> userRoleIds)
  {
    return this.userRoleRepository.findAll(userRoleIds);
  }
}
