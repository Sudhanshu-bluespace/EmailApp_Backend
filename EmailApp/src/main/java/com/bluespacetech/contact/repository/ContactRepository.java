package com.bluespacetech.contact.repository;

import com.bluespacetech.contact.entity.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ContactRepository
  extends JpaRepository<Contact, Long>
{
  public abstract List<Contact> findByFirstNameLikeOrLastNameLike(String paramString1, String paramString2);
  
  public abstract List<Contact> findByEmailLike(String paramString);
  
  public abstract List<Contact> findByEmailIgnoreCase(String paramString);
  
  public abstract List<Contact> findByCreatedUser(String paramString);
  
  public abstract Contact findById(Long paramLong);
}
