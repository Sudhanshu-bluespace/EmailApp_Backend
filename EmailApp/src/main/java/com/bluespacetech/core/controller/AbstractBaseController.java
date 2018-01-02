package com.bluespacetech.core.controller;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.resouces.ExceptionResource;
import com.bluespacetech.core.resouces.ExceptionResourceAssembler;
import java.io.PrintStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractBaseController
{
  @ExceptionHandler({AccessDeniedException.class})
  ResponseEntity<ExceptionResource> handleAccessDeniedException(Exception e)
  {
    System.out.println("Access Denied handler : " + e.getMessage());
    ExceptionResource exceptionResource = ExceptionResourceAssembler.toResource(e);
    return new ResponseEntity(exceptionResource, HttpStatus.FORBIDDEN);
  }
  
  @ExceptionHandler({BusinessException.class})
  ResponseEntity<ExceptionResource> handleBusinessException(BusinessException e)
  {
    ExceptionResource exceptionResource = ExceptionResourceAssembler.toResource(e);
    return new ResponseEntity(exceptionResource, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler({ApplicationException.class})
  ResponseEntity<ExceptionResource> handleApplicationException(ApplicationException e)
  {
    ExceptionResource exceptionResource = ExceptionResourceAssembler.toResource(e);
    return new ResponseEntity(exceptionResource, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler({RuntimeException.class})
  ResponseEntity<ExceptionResource> handleRuntimeException(RuntimeException e)
  {
    ExceptionResource exceptionResource = ExceptionResourceAssembler.toResource(e);
    return new ResponseEntity(exceptionResource, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler({Exception.class})
  ResponseEntity<ExceptionResource> handleUnHandledException(Exception e)
  {
    ExceptionResource exceptionResource = ExceptionResourceAssembler.toResource(e);
    return new ResponseEntity(exceptionResource, HttpStatus.BAD_REQUEST);
  }
}
