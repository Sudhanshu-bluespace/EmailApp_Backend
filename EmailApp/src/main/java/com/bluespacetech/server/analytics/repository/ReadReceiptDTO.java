package com.bluespacetech.server.analytics.repository;

public class ReadReceiptDTO
{
  private Long readReceiptId;
  private Long emailId;
  private Long contactId;
  private Long groupId;
  private String contactEmail;
  private String lastReadTime;
  private int readCount;
  
  public String toString()
  {
    return "ReadReceiptDTO [readReceiptId=" + this.readReceiptId + ", emailId=" + this.emailId + ", contactId=" + this.contactId + ", groupId=" + this.groupId + ", contactEmail=" + this.contactEmail + ", lastReadTime=" + this.lastReadTime + ", readCount=" + this.readCount + "]";
  }
  
  public Long getReadReceiptId()
  {
    return this.readReceiptId;
  }
  
  public void setReadReceiptId(Long readReceiptId)
  {
    this.readReceiptId = readReceiptId;
  }
  
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
  
  public String getContactEmail()
  {
    return this.contactEmail;
  }
  
  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }
  
  public String getLastReadTime()
  {
    return this.lastReadTime;
  }
  
  public void setLastReadTime(String lastReadTime)
  {
    this.lastReadTime = lastReadTime;
  }
  
  public int getReadCount()
  {
    return this.readCount;
  }
  
  public void setReadCount(int readCount)
  {
    this.readCount = readCount;
  }
}
