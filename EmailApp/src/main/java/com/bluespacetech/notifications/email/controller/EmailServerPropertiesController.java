package com.bluespacetech.notifications.email.controller;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
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
@RequestMapping({"/emailServerProperties"})
@CrossOrigin
public class EmailServerPropertiesController
{
  @Autowired
  private EmailServerService emailServerService;
  @Autowired
  private EmailServerPropertiesService emailServerPropertiesService;
  
  @RequestMapping(value={"/{emailServerId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public ResponseEntity<EmailServerProperties> create(@PathVariable Long emailServerId, @RequestBody EmailServerProperties emailServerProperties)
    throws BusinessException
  {
    EmailServer emailServer = this.emailServerService.getEmailServerById(emailServerId);
    if (emailServer == null) {
      throw new BusinessException("Supplied EmailServer does not exist.");
    }
    emailServerProperties.setEmailServer(emailServer);
    
    EmailServerProperties updatedEmailServerProperties = this.emailServerPropertiesService.createEmailServerProperty(emailServerProperties);
    return new ResponseEntity(updatedEmailServerProperties, HttpStatus.CREATED);
  }
  
  @RequestMapping(value={"/{emailServerId}/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"}, produces={"application/json"})
  public ResponseEntity<EmailServerProperties> update(@PathVariable Long emailServerId, @PathVariable Long id, @RequestBody EmailServerProperties emailServerProperties)
    throws BusinessException
  {
    EmailServer emailServer = this.emailServerService.getEmailServerById(emailServerId);
    if (emailServer == null) {
      throw new BusinessException("Supplied EmailServer does not exist.");
    }
    emailServerProperties.setEmailServer(emailServer);
    
    EmailServerProperties createEmailServerProperties = this.emailServerPropertiesService.getEmailServerPropertiesById(id);
    if (createEmailServerProperties == null) {
      throw new BusinessException("Supplied EmailServerProperties does not exist.");
    }
    if (!emailServerProperties.getVersion().equals(createEmailServerProperties.getVersion())) {
      throw new BusinessException("Stale EmailServerProperties. Please update.");
    }
    EmailServerProperties updatedEmailServerProperties = this.emailServerPropertiesService.updateEmailServerProperty(emailServerProperties);
    return new ResponseEntity(updatedEmailServerProperties, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  public ResponseEntity<Void> delete(@PathVariable Long id)
    throws BusinessException
  {
    this.emailServerPropertiesService.deleteEmailServerProperty(id);
    return new ResponseEntity(HttpStatus.OK);
  }
  
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<EmailServerProperties> getEmailServerById(@PathVariable Long id)
    throws BusinessException
  {
    EmailServerProperties emailServerProperties = this.emailServerPropertiesService.getEmailServerPropertiesById(id);
    if (emailServerProperties == null) {
      throw new BusinessException("Supplied EmailServerProperties ID is invalid.");
    }
    return new ResponseEntity(emailServerProperties, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/emailServer/{emailServerId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<EmailServerProperties>> getAllEmailServerProperties(@PathVariable Long emailServerId)
    throws BusinessException
  {
    EmailServer emailServer = this.emailServerService.getEmailServerById(emailServerId);
    if (emailServer == null) {
      throw new BusinessException("Supplied EmailServer does not exist.");
    }
    List<EmailServerProperties> emailServerProperties = this.emailServerPropertiesService.findByEmailServer(emailServer);
    return new ResponseEntity(emailServerProperties, HttpStatus.OK);
  }
}
