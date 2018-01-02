package com.bluespacetech.contactgroup.entity;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ContactGroupPK
  implements Serializable
{
  private static final long serialVersionUID = 5141112485683480973L;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="CONTACT_ID")
  private Contact contact;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="GROUP_ID")
  private Group group;
  
  public Contact getContact()
  {
    return this.contact;
  }
  
  public void setContact(Contact contact)
  {
    this.contact = contact;
  }
  
  public Group getGroup()
  {
    return this.group;
  }
  
  public void setGroup(Group group)
  {
    this.group = group;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.contact == null ? 0 : this.contact.hashCode());
    result = 31 * result + (this.group == null ? 0 : this.group.hashCode());
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ContactGroupPK other = (ContactGroupPK)obj;
    if (this.contact == null)
    {
      if (other.contact != null) {
        return false;
      }
    }
    else if (!this.contact.equals(other.contact)) {
      return false;
    }
    if (this.group == null)
    {
      if (other.group != null) {
        return false;
      }
    }
    else if (!this.group.equals(other.group)) {
      return false;
    }
    return true;
  }
}
