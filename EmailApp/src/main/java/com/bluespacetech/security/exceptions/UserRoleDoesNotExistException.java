package com.bluespacetech.security.exceptions;

public class UserRoleDoesNotExistException
  extends RuntimeException
{
  private static final long serialVersionUID = -6139375536512870147L;
  
  public UserRoleDoesNotExistException(Throwable cause)
  {
    super(cause);
  }
  
  public UserRoleDoesNotExistException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public UserRoleDoesNotExistException(String message)
  {
    super(message);
  }
  
  public UserRoleDoesNotExistException() {}
}
