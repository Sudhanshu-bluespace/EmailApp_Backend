package com.bluespacetech.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.repository.UserRoleRepository;
import com.bluespacetech.security.repository.UserRoleRepositoryCustom;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;

/**
 * The Class UserRoleServiceImpl.
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class UserRoleServiceImpl implements UserRoleService
{

    /** The user role repository. */
    @Autowired
    UserRoleRepository userRoleRepository;

    /** The user role repository custom. */
    @Autowired
    UserRoleRepositoryCustom userRoleRepositoryCustom;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#findUserRoleByRoleName(java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public UserRole findUserRoleByRoleName(final String roleName)
    {
        return userRoleRepository.findUserRoleByRoleName(roleName);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#findByDescriptionLike(java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public List<UserRole> findByDescriptionLike(final String description)
    {
        return userRoleRepository.findByDescriptionLike(description);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#getAllUserRoles()
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public List<UserRole> getAllUserRoles()
    {
        return userRoleRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#getUserRoleById(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public UserRole getUserRoleById(final Long userRoleId)
    {
        return userRoleRepository.findOne(userRoleId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#createUserRole(com.bluespacetech.security.model.UserRole)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_USER_ROLES'))")
    public UserRole createUserRole(final UserRole userRole) throws BusinessException
    {
        for (final UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities())
        {
            userRoleAuthority.setUserRole(userRole);
        }
        return userRoleRepository.save(userRole);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#updateUserRole(com.bluespacetech.security.model.UserRole)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_USER_ROLES'))")
    public UserRole updateUserRole(final UserRole userRole) throws BusinessException
    {
        for (final UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities())
        {
            userRoleAuthority.setUserRole(userRole);
        }
        return userRoleRepository.save(userRole);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#deleteUserRole(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_USER_ROLES'))")
    public void deleteUserRole(final Long userRoleId)
    {
        userRoleRepository.delete(userRoleId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#findByRoleNameLike(java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public List<UserRole> findByRoleNameLike(final String roleName)
    {
        return userRoleRepository.findByRoleNameLike(roleName);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#findUserRolesBySearchCriteria(com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public List<UserRole> findUserRolesBySearchCriteria(final UserRoleSearchCriteria sectionSearchCriteria)
    {
        return userRoleRepositoryCustom.findUserRolesBySearchCriteria(sectionSearchCriteria);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserRoleService#getUserRoleByIds(java.util.List)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_USER_ROLES'))")
    public List<UserRole> getUserRoleByIds(final List<Long> userRoleIds)
    {
        return userRoleRepository.findAll(userRoleIds);
    }

}