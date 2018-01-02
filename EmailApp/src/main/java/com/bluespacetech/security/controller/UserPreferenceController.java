package com.bluespacetech.security.controller;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.model.UserPreference;
import com.bluespacetech.security.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/userPreference"})
public class UserPreferenceController
  extends AbstractBaseController
{
  @Autowired
  UserPreferenceService userPreferenceService;
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<UserPreference> getUserPreference()
    throws Exception
  {
    UserPreference userPreference = this.userPreferenceService.getCurrentUserPreference();
    userPreference.setUserAccount(null);
    
    return new ResponseEntity(userPreference, HttpStatus.OK);
  }
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"}, produces={"application/json"})
  public ResponseEntity<UserPreference> update(@RequestBody UserPreference userPreference)
    throws Exception
  {
    UserPreference result = this.userPreferenceService.saveUserPreference(userPreference);
    result.setUserAccount(null);
    return new ResponseEntity(result, HttpStatus.OK);
  }
}
