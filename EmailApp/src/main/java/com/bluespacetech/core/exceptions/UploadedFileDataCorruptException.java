package com.bluespacetech.core.exceptions;

public class UploadedFileDataCorruptException
  extends Exception
{
  private static final long serialVersionUID = 5722034327279845918L;
  
  public UploadedFileDataCorruptException(String message)
  {
    super(message);
  }
}
