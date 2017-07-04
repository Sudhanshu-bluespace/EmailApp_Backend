package com.bluespacetech.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.BlockedContacts;

@Repository
public interface BlockedContactRepository extends JpaRepository<BlockedContacts,Long>
{
    List<BlockedContacts> findAll();
    BlockedContacts findByEmailIgnoreCase(String email);
    BlockedContacts findByFirstNameIgnoreCase(String firstName);
    BlockedContacts findById(long id);

}
