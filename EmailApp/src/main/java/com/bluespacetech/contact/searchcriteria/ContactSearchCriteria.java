package com.bluespacetech.contact.searchcriteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactSearchCriteria
  implements Serializable
{
  private static final long serialVersionUID = -8455110031782999764L;
  private String firstName;
  private String lastName;
  private String email;
  private String username;
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  private List<Long> groupIds = new ArrayList();
  
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
  
  public List<Long> getGroupIds()
  {
    return this.groupIds;
  }
  
  public void setGroupIds(List<Long> groupIds)
  {
    this.groupIds = groupIds;
  }
}
