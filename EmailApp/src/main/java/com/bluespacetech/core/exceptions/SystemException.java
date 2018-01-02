package com.bluespacetech.core.exceptions;

public class SystemException
  extends ApplicationException
{
  private static final long serialVersionUID = -7459803588866142666L;
  
  public SystemException(Throwable cause)
  {
    super(cause);
  }
  
  public SystemException(String message)
  {
    super(message);
  }
}
