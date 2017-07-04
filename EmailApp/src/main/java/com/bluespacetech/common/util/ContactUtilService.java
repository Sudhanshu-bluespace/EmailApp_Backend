package com.bluespacetech.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contact.repository.BlockedContactRepository;

@Service
public class ContactUtilService
{
    @Autowired
    private BlockedContactRepository blockedContactRepository;
    
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
        BlockedContacts contacts = blockedContactRepository.findByEmailIgnoreCase(email);
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
