package com.bluespacetech.notifications.email.valueobjects;

import com.bluespacetech.notifications.email.entity.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmailVO
  implements Serializable
{
  private static final long serialVersionUID = -3305258001034299815L;
  private List<String> toAddress = new ArrayList();
  private List<String> ccAddress = new ArrayList();
  private List<String> bccAddress = new ArrayList();
  private String fromAddress;
  private String message;
  private String subject;
  private Email email;
  private List<String> replyTo = new ArrayList();
  private Long groupId;
  private boolean personalizedEmail;
  private final List<Long> groupIdList = new ArrayList();
  
  public List<String> getToAddress()
  {
    return this.toAddress;
  }
  
  public void setToAddress(List<String> toAddress)
  {
    this.toAddress = toAddress;
  }
  
  public List<String> getCcAddress()
  {
    return this.ccAddress;
  }
  
  public void setCcAddress(List<String> ccAddress)
  {
    this.ccAddress = ccAddress;
  }
  
  public List<String> getBccAddress()
  {
    return this.bccAddress;
  }
  
  public void setBccAddress(List<String> bccAddress)
  {
    this.bccAddress = bccAddress;
  }
  
  public String getFromAddress()
  {
    return this.fromAddress;
  }
  
  public void setFromAddress(String fromAddress)
  {
    this.fromAddress = fromAddress;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
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
  
  public Email getEmail()
  {
    return this.email;
  }
  
  public void setEmail(Email email)
  {
    this.email = email;
  }
  
  public Long getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(Long groupId)
  {
    this.groupId = groupId;
  }
  
  public List<Long> getGroupIdList()
  {
    return this.groupIdList;
  }
  
  public boolean isPersonalizedEmail()
  {
    return this.personalizedEmail;
  }
  
  public void setPersonalizedEmail(boolean personalizedEmail)
  {
    this.personalizedEmail = personalizedEmail;
  }
  
  public List<String> getReplyTo()
  {
    return this.replyTo;
  }
}
