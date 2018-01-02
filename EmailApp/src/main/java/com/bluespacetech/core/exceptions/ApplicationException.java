package com.bluespacetech.core.exceptions;

public class ApplicationException
  extends Exception
{
  private static final long serialVersionUID = 3212309298381775252L;
  
  public ApplicationException(String message)
  {
    super(message);
  }
  
  public ApplicationException(Throwable cause)
  {
    super(cause);
  }
}
