package com.bluespacetech.security.dao;

import java.io.Serializable;

public class BaseResponseDAO
  implements Serializable
{
  private static final long serialVersionUID = 5653788318911044280L;
  private long responseCode;
  private String responseMessage;
  private long errorCode;
  private String errorMessage;
  
  public long getResponseCode()
  {
    return this.responseCode;
  }
  
  public void setResponseCode(long responseCode)
  {
    this.responseCode = responseCode;
  }
  
  public String getResponseMessage()
  {
    return this.responseMessage;
  }
  
  public void setResponseMessage(String responseMessage)
  {
    this.responseMessage = responseMessage;
  }
  
  public long getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(long errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
}
