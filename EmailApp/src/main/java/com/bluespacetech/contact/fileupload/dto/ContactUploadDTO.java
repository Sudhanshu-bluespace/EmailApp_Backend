package com.bluespacetech.contact.fileupload.dto;

public class ContactUploadDTO
{
  private String firstName;
  private String lastName;
  private String email;
  private String group;
  
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
  
  public String toString()
  {
    return "ContactUploadDTO [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", group=" + this.group + "]";
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getGroup()
  {
    return this.group;
  }
  
  public void setGroup(String group)
  {
    this.group = group;
  }
}
