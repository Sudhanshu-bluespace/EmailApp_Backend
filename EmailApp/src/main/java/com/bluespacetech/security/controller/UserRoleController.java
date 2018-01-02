package com.bluespacetech.security.controller;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.exceptions.UserRoleDoesNotExistException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.resources.UserRoleResource;
import com.bluespacetech.security.resources.assembler.UserRoleResourceAssembler;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;
import com.bluespacetech.security.service.UserRoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/userRoles"})
public class UserRoleController
  extends AbstractBaseController
{
  @Autowired
  UserRoleService userRoleService;
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<UserRoleResource>> getUserRoles()
  {
    List<UserRole> userRoles = this.userRoleService.getAllUserRoles();
    
    List<UserRoleResource> userRoleResources = new UserRoleResourceAssembler().toResources(userRoles);
    return new ResponseEntity(userRoleResources, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<UserRoleResource> getUserRoleById(@PathVariable Long id)
  {
    UserRole userRole = this.userRoleService.getUserRoleById(id);
    if (userRole == null) {
      throw new UserRoleDoesNotExistException("Supplied Financial year is invalid.");
    }
    UserRoleResource userRoleResource = new UserRoleResourceAssembler().toCompleteResource(userRole);
    return new ResponseEntity(userRoleResource, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"}, consumes={"application/json"})
  public ResponseEntity<List<UserRoleResource>> getUserRoles(@RequestBody UserRoleSearchCriteria userRoleSearchCriteria)
    throws BusinessException
  {
    List<UserRole> userRoles = this.userRoleService.findUserRolesBySearchCriteria(userRoleSearchCriteria);
    
    List<UserRoleResource> userRoleResources = new ArrayList();
    if (userRoleResources != null) {
      userRoleResources = new UserRoleResourceAssembler().toResources(userRoles);
    }
    return new ResponseEntity(userRoleResources, HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public ResponseEntity<Void> create(@RequestBody UserRoleResource userRoleResource)
    throws BusinessException
  {
    UserRole userRole = UserRoleResourceAssembler.getUserRoleFromResource(userRoleResource);
    
    UserRole result = this.userRoleService.createUserRole(userRole);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserRoleResource userRoleResource)
    throws BusinessException
  {
    UserRole currentUserRole = this.userRoleService.getUserRoleById(id);
    if (currentUserRole == null) {
      throw new UserRoleDoesNotExistException("Supplied User Role is invalid.");
    }
    if (!currentUserRole.getVersion().equals(userRoleResource
      .getVersion())) {
      throw new BusinessException("Stale User Role. Please update.");
    }
    UserRole sourceUserRole = UserRoleResourceAssembler.getUserRoleFromResource(userRoleResource);
    
    UserRoleResourceAssembler.copyUserRoleInto(sourceUserRole, currentUserRole);
    
    this.userRoleService.updateUserRole(currentUserRole);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  public ResponseEntity<Void> delete(@PathVariable Long id)
  {
    this.userRoleService.deleteUserRole(id);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @ExceptionHandler({UserRoleDoesNotExistException.class})
  ResponseEntity<String> handleUserRoleNotFoundException(Exception e)
  {
    return new ResponseEntity(String.format("{\"reason\":\"%s\"}", new Object[] {e
      .getMessage() }), HttpStatus.NOT_FOUND);
  }
}
