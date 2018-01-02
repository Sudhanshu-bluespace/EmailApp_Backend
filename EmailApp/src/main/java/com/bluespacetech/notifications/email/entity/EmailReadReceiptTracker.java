package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EMAIL_READ_RECEIPT_TRACKER")
public class EmailReadReceiptTracker
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -6118718181276955321L;
  @Column(name="EMAIL_ID", nullable=false)
  private Long emailId;
  @Column(name="CONTACT_ID", nullable=false)
  private Long contactId;
  @Column(name="CONTACT_EMAIL", nullable=false)
  private String contactEmail;
  @Column(name="GROUP_ID", nullable=false)
  private Long groupId;
  @Column(name="READ_COUNT")
  private Integer readCount;
  
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
  
  public Long getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(Long emailId)
  {
    this.emailId = emailId;
  }
  
  public String getContactEmail()
  {
    return this.contactEmail;
  }
  
  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }
  
  public Integer getReadCount()
  {
    return this.readCount;
  }
  
  public void setReadCount(Integer readCount)
  {
    this.readCount = readCount;
  }
  
  public String toString()
  {
    return "EmailReadReceiptTracker [emailtId=" + this.emailId + ", contactId=" + this.contactId + ", contactEmail=" + this.contactEmail + ", groupId=" + this.groupId + ", readCount=" + this.readCount + "]";
  }
}
