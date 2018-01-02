package com.bluespacetech.security.exceptions;

public class UserGroupDoesNotExistException
  extends RuntimeException
{
  private static final long serialVersionUID = -6139375536512870147L;
  
  public UserGroupDoesNotExistException(Throwable cause)
  {
    super(cause);
  }
  
  public UserGroupDoesNotExistException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public UserGroupDoesNotExistException(String message)
  {
    super(message);
  }
  
  public UserGroupDoesNotExistException() {}
}
