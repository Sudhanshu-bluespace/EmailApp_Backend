/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.notifications.email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.repository.EmailContactGroupRepository;

/**
 * class for EmailContactGroupService.
 *
 * @author pradeep created date 25-June-2015
 * @author Sudhanshu Tripathy
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
//@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailContactGroupServiceImpl implements EmailContactGroupService
{

    /** The email contact group repository. */
    @Autowired
    private EmailContactGroupRepository emailContactGroupRepository;

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * createEmailContactGroup(com.bluespacetech.notifications.email.entity.
     * EmailContactGroup)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public EmailContactGroup createEmailContactGroup(final EmailContactGroup emailContactGroup) throws BusinessException
    {
        final EmailContactGroup newEmailContactGroup = emailContactGroupRepository.save(emailContactGroup);
        return newEmailContactGroup;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * createEmailContactGroups(java.util.List)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public List<EmailContactGroup> createEmailContactGroups(final List<EmailContactGroup> emailContactGroups)
            throws BusinessException
    {
        final List<EmailContactGroup> result = emailContactGroupRepository.save(emailContactGroups);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * findAll()
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public List<EmailContactGroup> findAll()
    {
        return emailContactGroupRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * findByContactIdAndGroupIdAndRandomNumber(java.lang.Long, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(Long contactId, Long groupId, Long randomNumber)
    {
        return emailContactGroupRepository.findByContactIdAndGroupIdAndRandomNumber(contactId, groupId, randomNumber);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * updateEmailContactGroup(com.bluespacetech.notifications.email.entity.
     * EmailContactGroup)
     */
    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public EmailContactGroup updateEmailContactGroup(EmailContactGroup emailContactGroup) throws BusinessException
    {
        final EmailContactGroup newEmailContactGroup = emailContactGroupRepository.save(emailContactGroup);
        return newEmailContactGroup;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailContactGroupService#
     * deleteEmailContactGroup(java.util.List)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public void deleteEmailContactGroup(final List<EmailContactGroup> emailContactGroups) throws BusinessException
    {
        emailContactGroupRepository.delete(emailContactGroups);
    }

}
