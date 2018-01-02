package com.bluespacetech.contact.resources;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;
import org.springframework.hateoas.ResourceSupport;

public class ContactResource
  extends ResourceSupport
{
  private Long objectId;
  private Long version;
  private String firstName;
  private String lastName;
  private String email;
  private Group group;
  
  public Group getGroup()
  {
    return this.group;
  }
  
  public void setContactGroup(Group group)
  {
    this.group = group;
  }
  
  public Long getObjectId()
  {
    return this.objectId;
  }
  
  public void setObjectId(Long objectId)
  {
    this.objectId = objectId;
  }
  
  public Long getVersion()
  {
    return this.version;
  }
  
  public void setVersion(Long version)
  {
    this.version = version;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public static ContactResource convertToPeronResource(Contact contact, boolean completeResource, boolean viewContactInfoPermission, boolean viewAddressPermission)
  {
    ContactResource contactResource = new ContactResource();
    contactResource.setFirstName(contact.getFirstName());
    contactResource.setLastName(contact.getLastName());
    contactResource.setObjectId(contact.getId());
    contactResource.setVersion(contact.getVersion());
    contactResource.setEmail(contact.getEmail());
    return contactResource;
  }
  
  public static Contact getContactFromResource(ContactResource contactResource)
  {
    Contact destinationContact = new Contact();
    destinationContact.setFirstName(contactResource.getFirstName());
    destinationContact.setLastName(contactResource.getLastName());
    destinationContact.setEmail(contactResource.getEmail());
    return destinationContact;
  }
  
  public static void copyContactInto(Contact sourceContact, Contact destinationContact)
  {
    destinationContact.setFirstName(sourceContact.getFirstName());
    destinationContact.setLastName(sourceContact.getLastName());
    destinationContact.setEmail(sourceContact.getEmail());
  }
}
