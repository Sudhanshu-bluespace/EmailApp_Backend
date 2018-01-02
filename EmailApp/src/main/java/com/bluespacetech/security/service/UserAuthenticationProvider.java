package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider
  extends AbstractUserDetailsAuthenticationProvider
{
  @Autowired
  UserService userService;
  @Autowired
  PasswordEncoder passwordEncoder;
  
  protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
    throws AuthenticationException
  {
    if ((authentication.getCredentials() == null) || (userDetails.getPassword() == null)) {
      throw new BadCredentialsException("Credentials may not be null.");
    }
    if (!this.passwordEncoder.matches((String)authentication.getCredentials(), userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid Credentials.");
    }
  }
  
  protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
    throws AuthenticationException
  {
    UserDetails userDetails = this.userService.loadUserByUsername(username);
    
    return userDetails;
  }
}
