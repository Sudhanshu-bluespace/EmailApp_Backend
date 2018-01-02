package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="EMAIL_CONTACT_GROUP")
public class EmailContactGroup
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 7245273784297025334L;
  @Column(name="EMAIL_ID", nullable=false)
  private Long emailId;
  @Column(name="CONTACT_ID", nullable=false)
  private Long contactId;
  @Column(name="GROUP_ID", nullable=false)
  private Long groupId;
  @Lob
  @Column(name="TEXT", length=300000)
  private byte[] message;
  @Column(name="SUBJECT")
  private String subject;
  @Column(name="RANDOM_NUMBER", nullable=false)
  private String randomNumber;
  @Column(name="READ_COUNT")
  private Integer readCount;
  
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
  
  public byte[] getMessage()
  {
    return this.message;
  }
  
  public void setMessage(byte[] message)
  {
    this.message = message;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getRandomNumber()
  {
    return this.randomNumber;
  }
  
  public void setRandomNumber(String randomNumber)
  {
    this.randomNumber = randomNumber;
  }
  
  public Integer getReadCount()
  {
    return this.readCount;
  }
  
  public void setReadCount(Integer readCount)
  {
    this.readCount = readCount;
  }
}
