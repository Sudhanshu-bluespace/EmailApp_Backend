package com.bluespacetech.common.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contact.repository.BlockedContactRepository;

@Component
public class ContactUtilService
{
    @Autowired
    private BlockedContactRepository blockedContactRepository;
    
    @Autowired
    @PersistenceContext
    private EntityManager em;
    
    private static final Logger LOGGER = LogManager.getLogger(ContactUtilService.class);
    /**
     * Adds the email to blocked list.
     *
     * @param email the email
     * @param contactUploadDTO the contact upload DTO
     * @param reason the reason
     */
    public void addEmailToBlockedList(String email, ContactUploadDTO contactUploadDTO, String reason)
    {
        System.out.println("BCR : "+blockedContactRepository);
        BlockedContacts contacts = blockedContactRepository.findByEmailIgnoreCase(email);
       // List<BlockedContacts> contacts = blockedContactRepository.findAll();

        if (contacts == null)
        {
            BlockedContacts contactToBlock = new BlockedContacts();
            contactToBlock.setEmail(email);
            contactToBlock.setFirstName(contactUploadDTO.getFirstName());
            contactToBlock.setLastName(contactUploadDTO.getLastName());
            contactToBlock.setReason(reason);
            blockedContactRepository.save(contactToBlock);
        }
        else
        {
            LOGGER.warn("Contact is already in blocked list");
        }
    }
}
