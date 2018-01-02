package com.bluespacetech.contactgroup.repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ContactGroupRepository
  extends JpaRepository<ContactGroup, ContactGroupPK>
{}
