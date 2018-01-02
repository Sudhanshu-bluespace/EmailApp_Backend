package com.bluespacetech.contact.service;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.BlockedContactRepository;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.repository.ContactRepositoryCustom;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
public class ContactServiceImpl
  implements ContactService
{
  @Autowired
  private ContactRepository contactRepository;
  @Autowired
  private ContactRepositoryCustom contactRepositoryCustom;
  @Autowired
  private BlockedContactRepository blockedContactsRepository;
  private static final Logger LOGGER = LogManager.getLogger(ContactServiceImpl.class);
  
  public static void validateContact(Contact contact)
    throws BusinessException
  {
    if ((contact.getEmail() == null) || (contact.getEmail().trim().length() == 0)) {
      throw new BusinessException("Contact Email is Mandatory.");
    }
    String[] splitEmail = contact.getEmail().split("@");
    if (splitEmail.length == 2)
    {
      if (CommonUtilCache.getBlacklistedDomainList().contains(splitEmail[1].trim()))
      {
        LOGGER.error("The email is either SPAM or blacklisted and cannot be added. Scanned through a repository of " + 
        
          CommonUtilCache.getBlacklistedDomainList().size() + " blacklisted entries");
        throw new BusinessException("The email is either SPAM or blacklisted and cannot be added");
      }
      if (CommonUtilCache.getIgnoreList().contains(splitEmail[0] + "@"))
      {
        LOGGER.error("The email is a marketing/sales based contact and cannot be added. Scanned through a repository of " + 
        
          CommonUtilCache.getIgnoreList().size() + " marketing/sales ignore list entries");
        throw new BusinessException("The email is a marketing/sales based contact and cannot be added");
      }
    }
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_CONTACTS'))")
  public Contact createContact(Contact contact)
    throws BusinessException
  {
    validateContact(contact);
    Contact newContact = (Contact)this.contactRepository.save(contact);
    return newContact;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or  (hasAuthority('DELETE_CONTACTS'))")
  public void deleteContact(Long contactId)
    throws BusinessException
  {
    this.contactRepository.delete(contactId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
  public List<Contact> findByFirstNameOrLastName(String firstName, String lastName)
  {
    return this.contactRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
  }
  
  public Contact getContactById(Long contactId)
  {
    return (Contact)this.contactRepository.findOne(contactId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_CONTACTS'))")
  public Contact updateContact(Contact contact)
    throws BusinessException
  {
    validateContact(contact);
    Contact updatedContact = (Contact)this.contactRepository.save(contact);
    return updatedContact;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
  public List<Contact> findByEmail(String email)
  {
    return this.contactRepository.findByEmailLike(email);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
  public List<Contact> findAll()
  {
    return this.contactRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_CONTACTS'))")
  public List<Contact> findBySearchCriteria(ContactSearchCriteria contactSearchCriteria)
  {
    return this.contactRepositoryCustom.findContactsBySearchCriteria(contactSearchCriteria);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACC_TYPE_ADMIN')) or (hasAuthority('ACCESS_CONTACTS'))")
  public List<BlockedContacts> getBlockedContacts()
  {
    return this.blockedContactsRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACC_TYPE_ADMIN')) or (hasAuthority('ACCESS_CONTACTS'))")
  public List<Contact> findByCreatedUser(String username)
  {
    return this.contactRepository.findByCreatedUser(username);
  }
  
  public Contact findById(Long id)
  {
    return this.contactRepository.findById(id);
  }
}
