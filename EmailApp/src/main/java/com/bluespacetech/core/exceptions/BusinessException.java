package com.bluespacetech.core.exceptions;

public class BusinessException
  extends ApplicationException
{
  private static final long serialVersionUID = 2156225803330994760L;
  
  public BusinessException(Throwable cause)
  {
    super(cause);
    initCause(cause);
  }
  
  public BusinessException(String message)
  {
    super(message);
  }
}
