package com.bluespacetech.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.BlockedContacts;

@Repository
public interface BlockedContactRepository extends JpaRepository<BlockedContacts,Long>
{
    List<BlockedContacts> findAll();
    BlockedContacts findByEmailIgnoreCase(final String email);
    BlockedContacts findByFirstNameIgnoreCase(final String firstName);
    BlockedContacts findById(final Long id);

}
