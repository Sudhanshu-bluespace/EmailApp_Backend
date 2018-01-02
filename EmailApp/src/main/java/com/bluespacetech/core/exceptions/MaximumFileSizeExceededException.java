package com.bluespacetech.core.exceptions;

public class MaximumFileSizeExceededException
  extends Exception
{
  private static final long serialVersionUID = 953433131659475044L;
  
  public MaximumFileSizeExceededException(String msg)
  {
    super(msg);
  }
}
