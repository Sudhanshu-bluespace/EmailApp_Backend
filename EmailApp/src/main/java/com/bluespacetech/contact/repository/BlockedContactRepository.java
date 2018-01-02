package com.bluespacetech.contact.repository;

import com.bluespacetech.contact.entity.BlockedContacts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface BlockedContactRepository
  extends JpaRepository<BlockedContacts, Long>
{
  public abstract List<BlockedContacts> findAll();
  
  public abstract List<BlockedContacts> findByEmailAndReasonAllIgnoreCase(String paramString1, String paramString2);
  
  public abstract List<BlockedContacts> findByEmailAndReason(String paramString1, String paramString2);
  
  public abstract List<BlockedContacts> findByEmailIgnoreCase(String paramString);
  
  public abstract List<BlockedContacts> findByFirstNameIgnoreCase(String paramString);
  
  public abstract BlockedContacts findById(Long paramLong);
}
