package com.bluespacetech.security.controller;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.resources.PageLinkResource;
import com.bluespacetech.security.resources.assembler.PageLinkResourceAssembler;
import com.bluespacetech.security.service.PageLinksService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pageLinks"})
public class PageLinksController
  extends AbstractBaseController
{
  @Autowired
  PageLinksService pageLinksService;
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<PageLinkResource>> getPageLinksAllowedForUser()
  {
    Set<PageLinkConstant> pageLinks = this.pageLinksService.getPageLinksAllowedForUser();
    
    List<PageLinkResource> branchResources = new PageLinkResourceAssembler().toResources(pageLinks);
    return new ResponseEntity(branchResources, HttpStatus.OK);
  }
}
