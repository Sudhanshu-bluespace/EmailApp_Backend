package com.bluespacetech.contactgroup.service;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.exceptions.ContactAlreadySubscribedException;
import com.bluespacetech.core.exceptions.ContactAlreadyUnsubscribedException;
import java.util.List;

public abstract interface ContactGroupService
{
  public abstract ContactGroup getContactGroupById(ContactGroupPK paramContactGroupPK);
  
  public abstract ContactGroup createContactGroup(ContactGroup paramContactGroup)
    throws BusinessException;
  
  public abstract void deleteContactGroup(ContactGroupPK paramContactGroupPK)
    throws BusinessException;
  
  public abstract List<ContactGroup> findAll();
  
  public abstract ContactGroup updateContactGroup(ContactGroup paramContactGroup)
    throws BusinessException;
  
  public abstract ContactGroup unsubscribeContactGroup(Long paramLong1, Long paramLong2)
    throws BusinessException, ContactAlreadyUnsubscribedException;
  
  public abstract int fullUnsubscribeContactGroup(Long paramLong1, Long paramLong2, String paramString)
    throws BusinessException, ContactAlreadyUnsubscribedException;
  
  public abstract int subscribeContactGroup(Long paramLong1, Long paramLong2, String paramString)
    throws BusinessException, ContactAlreadyUnsubscribedException, ContactAlreadySubscribedException;
}
