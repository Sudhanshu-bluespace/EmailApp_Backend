/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contactgroup.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;
import com.bluespacetech.contactgroup.repository.ContactGroupRepository;
import com.bluespacetech.contactgroup.repository.ContactGroupRepositoryCustom;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.exceptions.ContactAlreadySubscribedException;
import com.bluespacetech.core.exceptions.ContactAlreadyUnsubscribedException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.repository.GroupRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class for ContactGroupService
 *
 * @author Sandeep created date 25-June-2015
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
//@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class ContactGroupServiceImpl implements ContactGroupService
{

    @Autowired
    private ContactGroupRepository contactGroupRepository;
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ContactGroupRepositoryCustom contactGroupRepositoryCustom;

    private static final Logger LOGGER = LogManager.getLogger(ContactGroupServiceImpl.class);

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public ContactGroup getContactGroupById(ContactGroupPK contactGroupPK)
    {
        return contactGroupRepository.findOne(contactGroupPK);
    }

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_CONTACTS'))")
    public ContactGroup createContactGroup(ContactGroup contactGroup) throws BusinessException
    {
        return contactGroupRepository.save(contactGroup);
    }

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_CONTACTS'))")
    public void deleteContactGroup(ContactGroupPK contactGroupPK) throws BusinessException
    {
        contactGroupRepository.delete(contactGroupPK);
    }

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public List<ContactGroup> findAll()
    {
        return contactGroupRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_CONTACTS'))")
    public ContactGroup updateContactGroup(ContactGroup contactGroup) throws BusinessException
    {
        return contactGroupRepository.save(contactGroup);
    }

    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public ContactGroup unsubscribeContactGroup(Long contactId, Long groupId) throws BusinessException,ContactAlreadyUnsubscribedException
    {
        final ContactGroup contactGroup = contactGroupRepositoryCustom.getContactGroupByContactIdAndGroupId(contactId,
                groupId);
        if (!contactGroup.isUnSubscribed())
        {
            contactGroup.setUnSubscribed(true);
            Date now = new Date();
            Timestamp time = new Timestamp(now.getTime()); 
            contactGroup.setUnsubscribedDate(time);
            return updateContactGroup(contactGroup);
        }
        else
        {
            LOGGER.warn("Contact is already unsubscribed. Wont be unsubscribed again.");
            throw new ContactAlreadyUnsubscribedException("You have already unsubscribed from the mailing list");
        }
    }

    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public int fullUnsubscribeContactGroup(Long contactId, Long groupId, String createdUser)
            throws BusinessException, ContactAlreadyUnsubscribedException
    {
        List<ContactGroup> contactGroups = contactGroupRepositoryCustom.getContactGroupsByContactId(contactId);
        List<Group> groups = groupRepository.findByCreatedUser(createdUser);
        List<Long> listIds = getGroupToIds(groups);
        
        int numberOfRecords = 0;
        for(ContactGroup cg:contactGroups)
        {
            if(listIds.contains(cg.getGroup().getId()))
            {
                if(!cg.isUnSubscribed())
                {
                    cg.setUnSubscribed(true);
                    Date now = new Date();
                    Timestamp time = new Timestamp(now.getTime()); 
                    cg.setUnsubscribedDate(time);
                    contactGroupRepository.save(cg);
                    numberOfRecords++;
                }
            }               
        }
        if(numberOfRecords==0)
        {
            LOGGER.warn("Contact is already fully unsubscribed. Wont be unsubscribed again.");
            throw new ContactAlreadyUnsubscribedException("You have already full unsubscribed from the mailing list");
        }
        return numberOfRecords;
    }
    
    private List<Long> getGroupToIds(List<Group> groups)
    {
        List<Long> list = new ArrayList<>();
        for(Group g : groups)
        {
            list.add(g.getId());
        }
        return list;
    }
    
    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
    public int subscribeContactGroup(Long contactId, Long groupId, String createdUser)
            throws BusinessException, ContactAlreadySubscribedException
    {
        List<ContactGroup> contactGroups = contactGroupRepositoryCustom.getContactGroupsByContactId(contactId);
        List<Group> groups = groupRepository.findByCreatedUser(createdUser);
        List<Long> listIds = getGroupToIds(groups);
        int numberOfRecords = 0;
        for (ContactGroup cg : contactGroups)
        {
            if (listIds.contains(cg.getGroup().getId()))
            {
                if (cg.isUnSubscribed())
                {
                    cg.setUnSubscribed(false);
                    cg.setUnsubscribedDate(null);
                    contactGroupRepository.save(cg);
                    numberOfRecords++;
                }
            }
        }
        
        if(numberOfRecords == 0)
        {
            throw new ContactAlreadySubscribedException("Your subscription request has already been processed");
        }

        return numberOfRecords;
    }
}
