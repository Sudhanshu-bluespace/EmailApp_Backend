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
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.repository.EmailRepository;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;

// TODO: Auto-generated Javadoc
/**
 * class for EmailService.
 *
 * @author pradeep created date 25-June-2015
 * @author Sudhanshu Tripathy
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailServiceImpl implements EmailService
{

    /** The email repository. */
    @Autowired
    private EmailRepository emailRepository;

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailService#createEmail(
     * com.bluespacetech.notifications.email.entity.Email)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public Email createEmail(final Email email) throws BusinessException
    {
        final Email newEmail = emailRepository.save(email);
        return newEmail;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailService#deleteEmail(
     * com.bluespacetech.notifications.email.entity.Email)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public void deleteEmail(final Email email) throws BusinessException
    {
        emailRepository.delete(email);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bluespacetech.notifications.email.service.EmailService#createEmail(
     * com.bluespacetech.notifications.email.valueobjects.EmailVO)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public Email createEmail(final EmailVO emailVO) throws BusinessException
    {
        Email email = new Email();
        email.setMessage(emailVO.getMessage());
        email.setSubject(emailVO.getSubject());
        email = createEmail(email);
        return email;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.notifications.email.service.EmailService#findAll()
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
    public List<Email> findAll()
    {
        return emailRepository.findAll();
    }

}
