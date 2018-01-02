package com.bluespacetech.core.exceptions;

public class ContactAlreadySubscribedException
  extends Exception
{
  private static final long serialVersionUID = -793874346312953740L;
  
  public ContactAlreadySubscribedException(String message)
  {
    super(message);
  }
}
