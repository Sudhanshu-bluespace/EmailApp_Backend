package com.bluespacetech.contactgroup.controller;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.repository.ContactGroupRepositoryCustom;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.exceptions.BusinessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contactgroups"})
@CrossOrigin
public class ContactGroupController
{
  @Autowired
  private ContactService contactService;
  @Autowired
  private ContactGroupService contactGroupService;
  @Autowired
  private ContactGroupRepositoryCustom contactGroupRepositoryCustom;
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<ContactGroup>> getContactGroups()
  {
    List<ContactGroup> contactGroups = this.contactGroupService.findAll();
    return new ResponseEntity(contactGroups, HttpStatus.OK);
  }
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, produces={"application/json"})
  public ResponseEntity<Void> update(@RequestBody ContactGroup contactGroup)
    throws BusinessException
  {
    Contact contact = contactGroup.getContact();
    this.contactService.updateContact(contact);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value={"/{contactId}/{groupId}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  public ResponseEntity<Void> delete(@PathVariable Long contactId, @PathVariable Long groupId)
    throws BusinessException
  {
    ContactGroup contactGroup = this.contactGroupRepositoryCustom.getContactGroupByContactIdAndGroupId(contactId, groupId);
    
    this.contactGroupService.deleteContactGroup(contactGroup.getContactGroupPK());
    return new ResponseEntity(HttpStatus.OK);
  }
}
