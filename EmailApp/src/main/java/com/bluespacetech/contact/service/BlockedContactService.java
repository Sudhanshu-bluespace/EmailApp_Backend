package com.bluespacetech.contact.service;

import com.bluespacetech.contact.entity.BlockedContacts;
import java.util.List;

public abstract interface BlockedContactService
{
  public abstract List<BlockedContacts> findBlockedContactByEmailAndReason(String paramString1, String paramString2);
  
  public abstract List<BlockedContacts> findByEmail(String paramString);
  
  public abstract List<BlockedContacts> findAll();
  
  public abstract void addBlockedContact(BlockedContacts paramBlockedContacts);
  
  public abstract void remove(BlockedContacts paramBlockedContacts);
}
