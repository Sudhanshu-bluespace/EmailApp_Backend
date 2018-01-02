package com.bluespacetech.notifications.email.valueobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileAttachResponse
{
  private String link;
  private String error;
  
  public String getError()
  {
    return this.error;
  }
  
  public void setError(String error)
  {
    this.error = error;
  }
  
  public void setLink(String link)
  {
    this.link = link;
  }
  
  public String getLink()
  {
    return this.link;
  }
}
