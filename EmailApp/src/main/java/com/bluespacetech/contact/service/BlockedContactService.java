package com.bluespacetech.contact.service;

import java.util.List;

import com.bluespacetech.contact.entity.BlockedContacts;

// TODO: Auto-generated Javadoc
/**
 * The Interface BlockedContactService.
 */
public interface BlockedContactService
{

    /**
     * Find blocked contact by email and reason.
     *
     * @param email the email
     * @param reason the reason
     * @return the list
     */
    public BlockedContacts findBlockedContactByEmailAndReason(final String email, final String reason);
    
    public List<BlockedContacts> findByEmail(final String email);

    /**
     * Adds the blocked contact.
     *
     * @param blockedContact the blocked contact
     */
    public void addBlockedContact(final BlockedContacts blockedContact);
}
