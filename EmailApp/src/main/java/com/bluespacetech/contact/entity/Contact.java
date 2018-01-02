package com.bluespacetech.contact.entity;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CONTACTS")
public class Contact
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -8030722963619449582L;
  @Column(name="FIRST_NAME")
  private String firstName;
  @Column(name="LAST_NAME")
  private String lastName;
  @NotEmpty(message="Email is mandatory.")
  @Column(name="EMAIL")
  private String email;
  @OneToMany(mappedBy="contactGroupPK.contact", cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true)
  private List<ContactGroup> contactGroups = new ArrayList();
  
  public List<ContactGroup> getContactGroups()
  {
    return this.contactGroups;
  }
  
  public void setContactGroups(List<ContactGroup> contactGroups)
  {
    this.contactGroups = contactGroups;
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
  
  public String toString()
  {
    return "Contact [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + "]";
  }
}
