/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author pradeep
 */
@Component
public class AuthenticationFailure extends SimpleUrlAuthenticationFailureHandler
{
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFailure.class);

    /*
     * (non-Javadoc)
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException exception) throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        LOGGER.error("Authentication failed : " + exception.getMessage());
        response.getOutputStream().println("{ \"error\": \"" + exception.getMessage() + "\" }");
    }

}
