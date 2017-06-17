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
import com.bluespacetech.notifications.email.entity.EmailServerProperties;

/**
 * class for EmailServerService.
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailServerPropertiesService
{

    /**
     * Creates the email server property.
     *
     * @param emailServerProperties
     *            the email server properties
     * @return the email server properties
     * @throws BusinessException
     *             the business exception
     */
    EmailServerProperties createEmailServerProperty(final EmailServerProperties emailServerProperties)
            throws BusinessException;

    /**
     * Delete email server property.
     *
     * @param emailServerPropertiesId
     *            the email server properties id
     * @throws BusinessException
     *             the business exception
     */
    void deleteEmailServerProperty(final Long emailServerPropertiesId) throws BusinessException;

    /**
     * Update email server property.
     *
     * @param emailServerProperties
     *            the email server properties
     * @return the email server properties
     * @throws BusinessException
     *             the business exception
     */
    EmailServerProperties updateEmailServerProperty(final EmailServerProperties emailServerProperties)
            throws BusinessException;

    /**
     * Find by email server.
     *
     * @param emailServer
     *            the email server
     * @return the list
     */
    List<EmailServerProperties> findByEmailServer(final EmailServer emailServer);

    /**
     * Gets the email server properties by id.
     *
     * @param id
     *            the id
     * @return the email server properties by id
     * @throws BusinessException
     *             the business exception
     */
    EmailServerProperties getEmailServerPropertiesById(final Long id) throws BusinessException;

    /**
     * Find by email servers.
     *
     * @param emailServers
     *            the email servers
     * @return the list
     */
    List<EmailServerProperties> findByEmailServers(final List<EmailServer> emailServers);

}
