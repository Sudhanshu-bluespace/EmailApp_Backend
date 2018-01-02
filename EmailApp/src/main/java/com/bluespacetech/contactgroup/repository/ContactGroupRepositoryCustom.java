package com.bluespacetech.contactgroup.repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import java.util.List;

public abstract interface ContactGroupRepositoryCustom
{
  public abstract ContactGroup getContactGroupByContactIdAndGroupId(Long paramLong1, Long paramLong2);
  
  public abstract List<ContactGroup> getContactGroupsByContactId(Long paramLong);
}
