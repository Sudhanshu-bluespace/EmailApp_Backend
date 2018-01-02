package com.bluespacetech.contact.service;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.repository.BlockedContactRepository;
import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
public class BlockedContactServiceImpl
  implements BlockedContactService
{
  private static final Logger LOGGER = LogManager.getLogger(BlockedContactServiceImpl.class);
  @Autowired
  private BlockedContactRepository blockedContactRepository;
  
  public void addBlockedContact(BlockedContacts blockedContact)
  {
    this.blockedContactRepository.save(blockedContact);
  }
  
  public List<BlockedContacts> findBlockedContactByEmailAndReason(String email, String reason)
  {
    LOGGER.debug("Searching for " + email + " in blocked contacts repository");
    return this.blockedContactRepository.findByEmailIgnoreCase(email);
  }
  
  public List<BlockedContacts> findAll()
  {
    return this.blockedContactRepository.findAll();
  }
  
  public List<BlockedContacts> findByEmail(String email)
  {
    return this.blockedContactRepository.findByEmailIgnoreCase(email);
  }
  
  public void remove(BlockedContacts blockedContact)
  {
    this.blockedContactRepository.delete(blockedContact);
  }
}
