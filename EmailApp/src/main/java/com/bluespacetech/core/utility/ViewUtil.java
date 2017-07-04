/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluespacetech.security.constants.UserAccountTypeConstant;

/**
 * The Class ViewUtil.
 */
public final class ViewUtil
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ViewUtil.class);

    /**
     * Gets the principal.
     *
     * @return the principal
     */
    public static String getPrincipal()
    {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails)
        {
            username = ((UserDetails) principal).getUsername();
        }
        else
        {
            // username = principal.toString();
            LOGGER.debug("User making changes for this transaction is an Auto Administrator recognized by "
                    + principal.toString() + ", Setting user to auto_admin");
            username = "auto_admin";
        }
        return username;
    }

    /**
     * Gets the authentication.
     *
     * @return the authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Gets the user account type.
     *
     * @return the user account type
     */
    public static UserAccountTypeConstant getUserAccountType()
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccountTypeConstant userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;
        if (authentication != null && authentication.getAuthorities() != null)
        {
            for (final GrantedAuthority grantedAuthority : authentication.getAuthorities())
            {
                if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
                }
            }
        }
        return userAccountType;
    }

}
