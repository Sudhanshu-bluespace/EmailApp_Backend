/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.notifications.email.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;

/**
 * class for EmailServerService.
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailServerService
{

    /**
     * Creates the email server.
     *
     * @param emailServer
     *            the email server
     * @return the email server
     * @throws BusinessException
     *             the business exception
     */
    EmailServer createEmailServer(final EmailServer emailServer) throws BusinessException;

    /**
     * Delete email server.
     *
     * @param emailServerId
     *            the email server id
     * @throws BusinessException
     *             the business exception
     */
    void deleteEmailServer(final Long emailServerId) throws BusinessException;

    /**
     * Update email server.
     *
     * @param emailServer
     *            the email server
     * @return the email server
     * @throws BusinessException
     *             the business exception
     */
    EmailServer updateEmailServer(final EmailServer emailServer) throws BusinessException;

    /**
     * Find email server by name.
     *
     * @param emailServerName
     *            the email server name
     * @return the email server
     * @throws BusinessException
     *             the business exception
     */
    EmailServer findEmailServerByName(final String emailServerName) throws BusinessException;

    /**
     * Gets the email server by id.
     *
     * @param id
     *            the id
     * @return the email server by id
     * @throws BusinessException
     *             the business exception
     */
    EmailServer getEmailServerById(final Long id) throws BusinessException;

    /**
     * Gets the default email server.
     *
     * @return the default email server
     * @throws BusinessException
     *             the business exception
     */
    EmailServer getDefaultEmailServer() throws BusinessException;

    /**
     * Find all.
     *
     * @return the list
     */
    List<EmailServer> findAll();

}
