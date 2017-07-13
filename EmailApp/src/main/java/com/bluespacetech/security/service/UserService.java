package com.bluespacetech.security.service;

/**
 * Service implementation interface for user.
 *
 * @author pradeep
 *
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.repository.UserGroupRepository;
import com.bluespacetech.security.repository.UserRoleRepository;

/**
 * The Class UserService.
 * 
 * @author Sudhanshu Tripathy
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements UserDetailsService
{

    /** The user account service. */
    @Autowired
    UserAccountService userAccountService;

    // @Autowired
    // PageLinksService pageLinksService;

    /** The user group repository. */
    @Autowired
    UserGroupRepository userGroupRepository;

    /** The user role repository. */
    @Autowired
    UserRoleRepository userRoleRepository;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        UserAccount userAccount = null;
        LOGGER.debug("inside user details service");

        try
        {
            userAccount = userAccountService.findUserAccountByUsername(username);
        }
        catch (final RuntimeException exception)
        {
            throw new UsernameNotFoundException(exception.getMessage());
        }

        if (userAccount == null)
        {
            LOGGER.warn("User not found by username");
            throw new UsernameNotFoundException("User not found by username");
        }

        final List<Long> userGroupIds = new ArrayList<Long>();
        final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups())
        {
            userGroupIds.add(userAccountUserGroup.getUserGroupId());
        }
        if (userGroupIds.size() > 0)
        {
            final List<Long> userRoleIds = new ArrayList<Long>();
            final List<UserGroup> userGroups = userGroupRepository.findAll(userGroupIds);
            for (final UserGroup userGroup : userGroups)
            {
                for (final UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles())
                {
                    userRoleIds.add(userGroupUserRole.getUserRoleId());
                }
            }
            if (userRoleIds.size() > 0)
            {
                final List<UserRole> userRoles = userRoleRepository.findAll(userRoleIds);
                for (final UserRole userRole : userRoles)
                {
                    for (final UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities())
                    {
                        grantedAuthorities.add(new SimpleGrantedAuthority(
                                userRoleAuthority.getAuthorityGrant().getLabel().toUpperCase()));
                    }
                }
            }
        }

        if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(userAccount.getUserAccountType()))
        {
            grantedAuthorities
                    .add(new SimpleGrantedAuthority(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType()));
        }
        else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.equals(userAccount.getUserAccountType()))
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()));
        }
        else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.equals(userAccount.getUserAccountType()))
        {
            grantedAuthorities
                    .add(new SimpleGrantedAuthority(UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()));
        }
        else if (UserAccountTypeConstant.ACC_TYPE_USER.equals(userAccount.getUserAccountType()))
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(UserAccountTypeConstant.ACC_TYPE_USER.getAccountType()));
        }
        // System.out.println("inside loadUserByUsername() :grantedAuthorities "
        // + grantedAuthorities);
        if (grantedAuthorities.isEmpty())
        {
            LOGGER.warn("User does not have granted authorities");
            throw new UsernameNotFoundException("User does not have granted authorities");
        }

        //System.out.println("User Account : "+userAccount);
        final String password = userAccount.getPassword();

        final boolean isActive = userAccount.isActive();

        final boolean isAccountNotExpried = !userAccount.isAccountExpired();

        final boolean isCredentialsNotExpired = !userAccount.isCredentialsExpired();

        final boolean isAccountNotLocked = !userAccount.isAccountLocked() && userAccount.isVerifiedByAdmin();

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password,
                isActive, isAccountNotExpried, isCredentialsNotExpired, isAccountNotLocked, grantedAuthorities);
        //System.out.println("User returned : " + userDetails.getUsername() + "|" + userDetails.getPassword());
        // pageLinksService.getPageLinksAllowedForUser();
        LOGGER.info("User retrieved successfully");
        System.out.println("User : "+userDetails.isAccountNonExpired()+ " | "+userDetails.isAccountNonLocked()+" | "+userDetails.isEnabled()+" | "+userDetails.isCredentialsNonExpired());
        return userDetails;
    }

    /**
     * Update password for user account.
     *
     * @param username the username
     * @param password the password
     */
    public void updatePasswordForUserAccount(final String username, final String password)
    {

        /*
         * final UserAccount userAccount = findUserAccountByUsername(username); final PasswordEncoder encoder = new PasswordEncoder(); final String encodedPassword = encoder.encodePassword(password,
         * null); userAccount.setPassword(encodedPassword); updateUserAccount(userAccount);
         */

    }

}
