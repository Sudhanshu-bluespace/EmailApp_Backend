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
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;

/**
 * class for EmailService.
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailService
{

    /**
     * Creates the email.
     *
     * @param email the email
     * @return the email
     * @throws BusinessException the business exception
     */
    Email createEmail(final Email email) throws BusinessException;

    /**
     * Creates the email.
     *
     * @param emailVO the email VO
     * @return the email
     * @throws BusinessException the business exception
     */
    Email createEmail(final EmailVO emailVO) throws BusinessException;

    /**
     * Delete email.
     *
     * @param email the email
     * @throws BusinessException the business exception
     */
    void deleteEmail(final Email email) throws BusinessException;

    /**
     * Find all.
     *
     * @return the list
     */
    List<Email> findAll();
    
    List<EmailContactGroupVO> getEmailContactGroups(String query,EmailVO emailVO,Long emailId);

}
