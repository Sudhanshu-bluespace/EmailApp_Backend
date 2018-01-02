package com.bluespacetech.security.dao;

public class PendingAccountApprovalsDTO
{
  private long serialNo;
  private long id;
  private String name;
  private String email;
  private String companyName;
  private String registrationRequestDate;
  private String address;
  private String city;
  private String state;
  private String country;
  private String zipcode;
  private boolean approved;
  private boolean onHold;
  private boolean rejected;
  private String status;
  
  public boolean isApproved()
  {
    return this.approved;
  }
  
  public void setApproved(boolean approved)
  {
    this.approved = approved;
  }
  
  public boolean isOnHold()
  {
    return this.onHold;
  }
  
  public void setOnHold(boolean onHold)
  {
    this.onHold = onHold;
  }
  
  public boolean isRejected()
  {
    return this.rejected;
  }
  
  public void setRejected(boolean rejected)
  {
    this.rejected = rejected;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getZipcode()
  {
    return this.zipcode;
  }
  
  public void setZipcode(String zipcode)
  {
    this.zipcode = zipcode;
  }
  
  public String getCompanyName()
  {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }
  
  public long getSerialNo()
  {
    return this.serialNo;
  }
  
  public void setSerialNo(long serialNo)
  {
    this.serialNo = serialNo;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getRegistrationRequestDate()
  {
    return this.registrationRequestDate;
  }
  
  public void setRegistrationRequestDate(String registrationRequestDate)
  {
    this.registrationRequestDate = registrationRequestDate;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String toString()
  {
    return "PendingAccountApprovalsDTO [serialNo=" + this.serialNo + ", id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", companyName=" + this.companyName + ", registrationRequestDate=" + this.registrationRequestDate + ", address=" + this.address + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", zipcode=" + this.zipcode + ", approved=" + this.approved + ", onHold=" + this.onHold + ", rejected=" + this.rejected + ", status=" + this.status + "]";
  }
}
