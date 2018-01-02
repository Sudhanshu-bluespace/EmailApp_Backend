package com.bluespacetech.core.exceptions;

public class InvalidUploadFileTypeException
  extends Exception
{
  private static final long serialVersionUID = 6839632052304877996L;
  
  public InvalidUploadFileTypeException(String message)
  {
    super(message);
  }
}
