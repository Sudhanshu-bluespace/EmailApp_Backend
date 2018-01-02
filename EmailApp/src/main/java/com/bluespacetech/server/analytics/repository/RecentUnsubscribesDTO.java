package com.bluespacetech.server.analytics.repository;

public class RecentUnsubscribesDTO
{
  private Long serialNo;
  private String firstName;
  private String lastName;
  private String email;
  private String unsubscribedOn;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public Long getSerialNo()
  {
    return this.serialNo;
  }
  
  public void setSerialNo(Long serialNo)
  {
    this.serialNo = serialNo;
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
  
  public String getUnsubscribedOn()
  {
    return this.unsubscribedOn;
  }
  
  public void setUnsubscribedOn(String unsubscribedOn)
  {
    this.unsubscribedOn = unsubscribedOn;
  }
  
  public String toString()
  {
    return "RecentUnsubscribesDTO [serialNo=" + this.serialNo + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", unsubscribedOn=" + this.unsubscribedOn + "]";
  }
}
