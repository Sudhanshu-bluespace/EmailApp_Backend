package com.bluespacetech.contact.service;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.BusinessException;
import java.util.List;

public abstract interface ContactService
{
  public abstract Contact createContact(Contact paramContact)
    throws BusinessException;
  
  public abstract void deleteContact(Long paramLong)
    throws BusinessException;
  
  public abstract List<Contact> findByFirstNameOrLastName(String paramString1, String paramString2);
  
  public abstract List<Contact> findByEmail(String paramString);
  
  public abstract List<Contact> findAll();
  
  public abstract Contact getContactById(Long paramLong);
  
  public abstract Contact updateContact(Contact paramContact)
    throws BusinessException;
  
  public abstract List<Contact> findBySearchCriteria(ContactSearchCriteria paramContactSearchCriteria);
  
  public abstract List<BlockedContacts> getBlockedContacts();
  
  public abstract List<Contact> findByCreatedUser(String paramString);
  
  public abstract Contact findById(Long paramLong);
}
