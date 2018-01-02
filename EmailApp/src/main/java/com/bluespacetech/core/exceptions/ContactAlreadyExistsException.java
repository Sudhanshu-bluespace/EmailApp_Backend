package com.bluespacetech.core.exceptions;

public class ContactAlreadyExistsException
  extends Exception
{
  private static final long serialVersionUID = -4860143140380180304L;
  
  public ContactAlreadyExistsException(String message)
  {
    super(message);
  }
}
