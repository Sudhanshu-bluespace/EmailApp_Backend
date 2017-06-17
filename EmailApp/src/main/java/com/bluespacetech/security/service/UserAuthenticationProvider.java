package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * The Class UserAuthenticationProvider.
 */
@Component
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

    /** The user service. */
    @Autowired
    UserService userService;

    /** The password encoder. */
    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails,
     * org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        System.out.println("Authentication check additional");

        if (authentication.getCredentials() == null || userDetails.getPassword() == null)
        {
            throw new BadCredentialsException("Credentials may not be null.");
        }
        if (!passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid Credentials.");
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
     * org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException
    {
        final UserDetails userDetails = userService.loadUserByUsername(username);
        System.out.println("Retreived User : " + userDetails);
        return userDetails;
    }

}
