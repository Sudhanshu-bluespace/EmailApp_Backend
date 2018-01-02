package com.bluespacetech.contact.repository;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import java.util.List;

public abstract interface ContactRepositoryCustom
{
  public abstract List<Contact> findContactsBySearchCriteria(ContactSearchCriteria paramContactSearchCriteria);
}
