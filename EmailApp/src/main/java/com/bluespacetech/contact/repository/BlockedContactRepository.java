package com.bluespacetech.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.BlockedContacts;

/**
 * The Interface BlockedContactRepository.
 */
@Repository
public interface BlockedContactRepository extends JpaRepository<BlockedContacts, Long>
{

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<BlockedContacts> findAll();

    /**
     * Find by email and reason all ignore case.
     *
     * @param email the email
     * @param reason the reason
     * @return the list
     */
    List<BlockedContacts> findByEmailAndReasonAllIgnoreCase(final String email, final String reason);
    
    List<BlockedContacts> findByEmailAndReason(final String email, final String reason);

    /**
     * Find by email ignore case.
     *
     * @param email the email
     * @return the list
     */
    List<BlockedContacts> findByEmailIgnoreCase(final String email);

    /**
     * Find by first name ignore case.
     *
     * @param firstName the first name
     * @return the list
     */
    List<BlockedContacts> findByFirstNameIgnoreCase(final String firstName);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the blocked contacts
     */
    BlockedContacts findById(final Long id);

}
