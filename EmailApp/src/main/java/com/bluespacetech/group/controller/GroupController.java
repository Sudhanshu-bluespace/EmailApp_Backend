package com.bluespacetech.group.controller;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import com.bluespacetech.group.service.GroupService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/groups"})
@CrossOrigin
public class GroupController
{
  @Autowired
  private GroupService groupService;
  
  @PostMapping(value={"/searchAll"}, produces={"application/json"})
  public ResponseEntity<List<Group>> getGroups(@RequestParam("username") String username)
  {
    List<Group> groups = this.groupService.findByCreatedUser(username);
    
    return new ResponseEntity(groups, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/searchCriteria"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
  public ResponseEntity<List<Group>> getGroupsBySearchCriteria(@RequestBody GroupSearchCriteria groupSearchCriteria)
  {
    if (groupSearchCriteria.getName() != null)
    {
      groupSearchCriteria.setName(groupSearchCriteria.getName().trim());
      if (groupSearchCriteria.getName().trim().equals("")) {
        groupSearchCriteria.setName(null);
      }
    }
    if (groupSearchCriteria.getComments() != null)
    {
      groupSearchCriteria.setComments(groupSearchCriteria.getComments().trim());
      if (groupSearchCriteria.getComments().trim().equals("")) {
        groupSearchCriteria.setComments(null);
      }
    }
    List<Group> groups = this.groupService.findBySearchCriteria(groupSearchCriteria);
    return new ResponseEntity(groups, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<Group> getGroupById(@PathVariable Long id)
    throws BusinessException
  {
    Group group = this.groupService.getGroupById(id);
    if (group == null) {
      throw new BusinessException("Supplied Group ID is invalid.");
    }
    group.setContactCount(Integer.valueOf(group.getContactGroups().size()));
    
    return new ResponseEntity(group, HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public ResponseEntity<Void> create(@RequestBody Group group)
    throws BusinessException
  {
    this.groupService.createGroup(group);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody Group group)
    throws BusinessException
  {
    Group currentGroup = this.groupService.getGroupById(id);
    if (currentGroup == null) {
      throw new BusinessException("Supplied Group does not exist.");
    }
    if (!currentGroup.getVersion().equals(group.getVersion())) {
      throw new BusinessException("Stale Group. Please update.");
    }
    Group groupUpdated = this.groupService.updateGroup(group);
    return new ResponseEntity(groupUpdated, HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  public ResponseEntity<Void> delete(@PathVariable Long id)
    throws BusinessException
  {
    this.groupService.deleteGroup(id);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @ExceptionHandler({BusinessException.class})
  ResponseEntity<String> handleGroupNotFoundException(Exception e)
  {
    return new ResponseEntity(String.format("{\"reason\":\"%s\"}", new Object[] { e.getMessage() }), HttpStatus.NOT_FOUND);
  }
}
