package com.bluespacetech.security.controller;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.UserDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user"})
public class UserController
  extends AbstractBaseController
{
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<UserDAO> user()
  {
    UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserDAO user = new UserDAO();
    user.setLoggedInUserName(userDetails.getUsername());
    Collection<String> roles = new ArrayList();
    Collection<String> uiRoles = new ArrayList();
    Map<String, String> uiGrantsByGrantsMap = GrantConstant.getUIGrantsByGrants();
    for (GrantedAuthority grantedAuthority : userDetails.getAuthorities())
    {
      if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
        user.setUserType(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType());
      } else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
        user.setUserType(UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType());
      } else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType().equals(grantedAuthority.getAuthority())) {
        user.setUserType(UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType());
      } else if (UserAccountTypeConstant.ACC_TYPE_USER.getAccountType().toString().equals(grantedAuthority.getAuthority())) {
        user.setUserType(UserAccountTypeConstant.ACC_TYPE_USER.getAccountType());
      }
      roles.add(grantedAuthority.getAuthority());
      if (uiGrantsByGrantsMap.get(grantedAuthority.getAuthority().toUpperCase()) != null) {
        uiRoles.add(uiGrantsByGrantsMap.get(grantedAuthority.getAuthority().toUpperCase()));
      }
    }
    user.setUiRoles(uiRoles);
    return new ResponseEntity(user, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ResponseEntity<Void> logoutPage(HttpServletRequest request, HttpServletResponse response)
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return new ResponseEntity(HttpStatus.OK);
  }
}
