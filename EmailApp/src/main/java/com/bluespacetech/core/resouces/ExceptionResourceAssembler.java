package com.bluespacetech.core.resouces;

public class ExceptionResourceAssembler
{
  public static ExceptionResource toResource(Exception exception)
  {
    ExceptionResource exceptionResource = new ExceptionResource();
    exceptionResource.setMessage(exception.getMessage());
    exceptionResource.setExceptiontype(exception.getClass().getName());
    return exceptionResource;
  }
}
