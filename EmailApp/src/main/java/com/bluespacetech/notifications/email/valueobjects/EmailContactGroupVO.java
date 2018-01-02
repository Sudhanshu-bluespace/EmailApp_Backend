package com.bluespacetech.notifications.email.valueobjects;

import java.io.Serializable;

public class EmailContactGroupVO
  implements Serializable
{
  private static final long serialVersionUID = 237003043434123667L;
  private Long emailId;
  private Long contactId;
  private Long groupId;
  private String message;
  private String contactEmail;
  private String contactFirstName;
  private String contactLastName;
  private String subject;
  private String fromAddress;
  
  public Long getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(Long emailId)
  {
    this.emailId = emailId;
  }
  
  public Long getContactId()
  {
    return this.contactId;
  }
  
  public void setContactId(Long contactId)
  {
    this.contactId = contactId;
  }
  
  public Long getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(Long groupId)
  {
    this.groupId = groupId;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getContactEmail()
  {
    return this.contactEmail;
  }
  
  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }
  
  public String getContactFirstName()
  {
    return this.contactFirstName;
  }
  
  public void setContactFirstName(String contactFirstName)
  {
    this.contactFirstName = contactFirstName;
  }
  
  public String getContactLastName()
  {
    return this.contactLastName;
  }
  
  public void setContactLastName(String contactLastName)
  {
    this.contactLastName = contactLastName;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getFromAddress()
  {
    return this.fromAddress;
  }
  
  public void setFromAddress(String fromAddress)
  {
    this.fromAddress = fromAddress;
  }
  
  public String toString()
  {
    return "EmailContactGroupVO [emailId=" + this.emailId + ", contactId=" + this.contactId + ", groupId=" + this.groupId + ", message=" + this.message + ", contactEmail=" + this.contactEmail + ", contactFirstName=" + this.contactFirstName + ", contactLastName=" + this.contactLastName + ", subject=" + this.subject + ", fromAddress=" + this.fromAddress + "]";
  }
}
