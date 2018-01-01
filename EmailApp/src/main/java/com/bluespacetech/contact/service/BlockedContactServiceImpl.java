package com.bluespacetech.contact.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.repository.BlockedContactRepository;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;

@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
public class BlockedContactServiceImpl implements BlockedContactService
{
    private static final Logger LOGGER = LogManager.getLogger(BlockedContactServiceImpl.class);

    @Autowired
    private BlockedContactRepository blockedContactRepository;

    @Override
    public void addBlockedContact(BlockedContacts blockedContact)
    {
        blockedContactRepository.save(blockedContact);
    }

    @Override
    public List<BlockedContacts> findBlockedContactByEmailAndReason(final String email,final String reason)
    {
        LOGGER.debug("Searching for "+email+" in blocked contacts repository");
        return blockedContactRepository.findByEmailIgnoreCase(email);
    }
    
    @Override
    public List<BlockedContacts> findAll()
    {
        return blockedContactRepository.findAll();
    }
    
    @Override
    public List<BlockedContacts> findByEmail(final String email)
    {
        return blockedContactRepository.findByEmailIgnoreCase(email);
    }
    
    @Override
    public void remove(final BlockedContacts blockedContact)
    {
        blockedContactRepository.delete(blockedContact);
    }
}
