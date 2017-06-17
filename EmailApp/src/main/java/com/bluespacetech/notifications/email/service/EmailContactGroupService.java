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
import com.bluespacetech.notifications.email.entity.EmailContactGroup;

/**
 * class for EmailContactGroupService.
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailContactGroupService
{

    /**
     * Creates the email contact group.
     *
     * @param emailContactGroup
     *            the email contact group
     * @return the email contact group
     * @throws BusinessException
     *             the business exception
     */
    EmailContactGroup createEmailContactGroup(final EmailContactGroup emailContactGroup) throws BusinessException;

    /**
     * Delete email contact group.
     *
     * @param emailContactGroups
     *            the email contact groups
     * @throws BusinessException
     *             the business exception
     */
    void deleteEmailContactGroup(final List<EmailContactGroup> emailContactGroups) throws BusinessException;

    /**
     * Creates the email contact groups.
     *
     * @param emailContactGroups
     *            the email contact groups
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<EmailContactGroup> createEmailContactGroups(final List<EmailContactGroup> emailContactGroups)
            throws BusinessException;

    /**
     * Find all.
     *
     * @return the list
     */
    List<EmailContactGroup> findAll();

    /**
     * Find by contact id and group id and random number.
     *
     * @param contactId
     *            the contact id
     * @param groupId
     *            the group id
     * @param randomNumber
     *            the random number
     * @return the email contact group
     */
    EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(final Long contactId, final Long groupId,
            final Long randomNumber);

    /**
     * Update email contact group.
     *
     * @param emailContactGroup
     *            the email contact group
     * @return the email contact group
     * @throws BusinessException
     *             the business exception
     */
    EmailContactGroup updateEmailContactGroup(final EmailContactGroup emailContactGroup) throws BusinessException;

}
