package com.bluespacetech.group.searchcriteria;

import java.io.Serializable;

public class GroupSearchCriteria
  implements Serializable
{
  private static final long serialVersionUID = 4218813103611029021L;
  private String name;
  private String comments;
  private String username;
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getComments()
  {
    return this.comments;
  }
  
  public void setComments(String comments)
  {
    this.comments = comments;
  }
}
