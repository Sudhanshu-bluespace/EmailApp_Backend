package com.bluespacetech.security.exceptions;

public class UserAccountDoesNotExistException
  extends RuntimeException
{
  private static final long serialVersionUID = -6139375536512870147L;
  
  public UserAccountDoesNotExistException(Throwable cause)
  {
    super(cause);
  }
  
  public UserAccountDoesNotExistException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public UserAccountDoesNotExistException(String message)
  {
    super(message);
  }
  
  public UserAccountDoesNotExistException() {}
}
