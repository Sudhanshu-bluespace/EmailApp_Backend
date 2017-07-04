/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.repository.ContactRepositoryCustom;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// TODO: Auto-generated Javadoc
/**
 * class for ContactService.
 *
 * @author pradeep created date 25-Aug-2016
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class ContactServiceImpl implements ContactService
{

    /** The contact repository. */
    @Autowired
    private ContactRepository contactRepository;

    /** The contact repository custom. */
    @Autowired
    private ContactRepositoryCustom contactRepositoryCustom;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactServiceImpl.class);

    /**
     * Validate contact.
     *
     * @param contact the contact
     * @throws BusinessException the business exception
     */
    public static void validateContact(final Contact contact) throws BusinessException
    {
        if (contact.getEmail() == null || contact.getEmail().trim().length() == 0)
        {
            throw new BusinessException("Contact Email is Mandatory.");
        }
        else
        {
            String[] splitEmail = contact.getEmail().split("@");
            if (splitEmail.length == 2)
            {
                if (CommonUtilCache.getBlacklistedDomainList().contains(splitEmail[1].trim()))
                {
                    LOGGER.error(
                            "The email is either SPAM or blacklisted and cannot be added. Scanned through a repository of "
                                    + CommonUtilCache.getBlacklistedDomainList().size() + " blacklisted entries");
                    throw new BusinessException("The email is either SPAM or blacklisted and cannot be added");
                }

                if (CommonUtilCache.getIgnoreList().contains(splitEmail[0] + "@"))
                {
                    LOGGER.error(
                            "The email is a marketing/sales based contact and cannot be added. Scanned through a repository of "
                                    + CommonUtilCache.getIgnoreList().size() + " marketing/sales ignore list entries");
                    throw new BusinessException("The email is a marketing/sales based contact and cannot be added");
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#createContact(com.bluespacetech.contact.entity.Contact)
     */
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_CONTACTS'))")
    @Override
    public Contact createContact(final Contact contact) throws BusinessException
    {
        ContactServiceImpl.validateContact(contact);
        final Contact newContact = contactRepository.save(contact);
        return newContact;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#deleteContact(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or  (hasAuthority('DELETE_CONTACTS'))")
    public void deleteContact(final Long contactId) throws BusinessException
    {
        contactRepository.delete(contactId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#findByFirstNameOrLastName(java.lang.String, java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public List<Contact> findByFirstNameOrLastName(final String firstName, final String lastName)
    {
        return contactRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#getContactById(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public Contact getContactById(final Long contactId)
    {
        return contactRepository.findOne(contactId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#updateContact(com.bluespacetech.contact.entity.Contact)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_CONTACTS'))")
    public Contact updateContact(final Contact contact) throws BusinessException
    {
        ContactServiceImpl.validateContact(contact);
        final Contact updatedContact = contactRepository.save(contact);
        return updatedContact;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#findByEmail(java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public List<Contact> findByEmail(final String email)
    {
        return contactRepository.findByEmailLike(email);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#findAll()
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public List<Contact> findAll()
    {
        return contactRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.contact.service.ContactService#findBySearchCriteria(com.bluespacetech.contact.searchcriteria.ContactSearchCriteria)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public List<Contact> findBySearchCriteria(ContactSearchCriteria contactSearchCriteria)
    {
        return contactRepositoryCustom.findContactsBySearchCriteria(contactSearchCriteria);
    }
}
