package com.bluespacetech.contact.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BLOCKED_CONTACTS")
public class BlockedContacts
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -920295196248477456L;
  @Column(name="FIRST_NAME", nullable=false)
  private String firstName;
  @Column(name="LAST_NAME", nullable=false)
  private String lastName;
  @Column(name="EMAIL", nullable=false)
  private String email;
  @Column(name="REASON")
  private String reason;
  @Column(name="RESPONSE_CODE")
  private String responseCode;
  
  public String getResponseCode()
  {
    return this.responseCode;
  }
  
  public void setResponseCode(String responseCode)
  {
    this.responseCode = responseCode;
  }
  
  public String getReason()
  {
    return this.reason;
  }
  
  public void setReason(String reason)
  {
    this.reason = reason;
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
    return "BlockedContacts [id=" + getId() + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", reason=" + this.reason + ", responseCode=" + this.responseCode + "]";
  }
}
