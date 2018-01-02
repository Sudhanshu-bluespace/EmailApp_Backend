package com.bluespacetech.notifications.email.controller;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.service.EmailServerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emailServer"})
@CrossOrigin
public class EmailServerController
{
  @Autowired
  private EmailServerService emailServerService;
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public ResponseEntity<Void> create(@RequestBody EmailServer emailServer)
    throws BusinessException
  {
    this.emailServerService.createEmailServer(emailServer);
    return new ResponseEntity(HttpStatus.CREATED);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"}, produces={"application/json"})
  public ResponseEntity<EmailServer> update(@PathVariable Long id, @RequestBody EmailServer emailServer)
    throws BusinessException
  {
    EmailServer currentEmailServer = this.emailServerService.getEmailServerById(id);
    if (currentEmailServer == null) {
      throw new BusinessException("Supplied EmailServer does not exist.");
    }
    if (!currentEmailServer.getVersion().equals(emailServer.getVersion())) {
      throw new BusinessException("Stale EmailServer. Please update.");
    }
    EmailServer updatedEmailServer = this.emailServerService.updateEmailServer(emailServer);
    return new ResponseEntity(updatedEmailServer, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  public ResponseEntity<Void> delete(@PathVariable Long id)
    throws BusinessException
  {
    this.emailServerService.deleteEmailServer(id);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<EmailServer> getEmailServerById(@PathVariable Long id)
    throws BusinessException
  {
    EmailServer emailServer = this.emailServerService.getEmailServerById(id);
    if (emailServer == null) {
      throw new BusinessException("Supplied EmailServer ID is invalid.");
    }
    return new ResponseEntity(emailServer, HttpStatus.OK);
  }
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<EmailServer>> getAllEmailServers()
    throws BusinessException
  {
    List<EmailServer> emailServers = this.emailServerService.findAll();
    return new ResponseEntity(emailServers, HttpStatus.OK);
  }
}
