package com.bluespacetech.core.utility;

import com.bluespacetech.security.constants.UserAccountTypeConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class ViewUtil
{
  private static final Logger LOGGER = LogManager.getLogger(ViewUtil.class);
  
  public static SecurityContext gtecurityContext()
  {
    return SecurityContextHolder.getContext();
  }
  
  public static String getPrincipal()
  {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = null;
    if ((principal instanceof UserDetails))
    {
      username = ((UserDetails)principal).getUsername();
    }
    else
    {
      LOGGER.debug("User making changes for this transaction is an Auto Administrator recognized by " + principal
        .toString() + ", Setting user to auto_admin");
      username = "auto_admin";
    }
    return username;
  }
  
  public static Authentication getAuthentication()
  {
    return SecurityContextHolder.getContext().getAuthentication();
  }
  
  public static UserAccountTypeConstant getUserAccountType()
  {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserAccountTypeConstant userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;
    if ((authentication != null) && (authentication.getAuthorities() != null)) {
      for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
        if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
          userAccountType = UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN;
        } else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
          userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
        } else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType().equals(grantedAuthority.getAuthority())) {
          userAccountType = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
        } else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
          userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
        }
      }
    }
    return userAccountType;
  }
}
