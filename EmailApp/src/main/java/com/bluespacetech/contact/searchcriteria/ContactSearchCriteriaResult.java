package com.bluespacetech.contact.searchcriteria;

import java.io.Serializable;

public class ContactSearchCriteriaResult
  implements Serializable
{
  private static final long serialVersionUID = -8455110031782999764L;
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
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
}
