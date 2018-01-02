package com.bluespacetech.core.exceptions;

public class InvalidArgumentException
  extends ApplicationException
{
  private static final long serialVersionUID = -3057500960082633455L;
  
  public InvalidArgumentException(String message)
  {
    super(message);
  }
}
