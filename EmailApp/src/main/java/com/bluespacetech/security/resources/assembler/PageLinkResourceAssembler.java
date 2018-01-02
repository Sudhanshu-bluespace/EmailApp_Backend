package com.bluespacetech.security.resources.assembler;

import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.constants.PageLinkTypeConstant;
import com.bluespacetech.security.controller.PageLinksController;
import com.bluespacetech.security.resources.PageLinkResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class PageLinkResourceAssembler
  extends ResourceAssemblerSupport<PageLinkConstant, PageLinkResource>
{
  public PageLinkResourceAssembler()
  {
    super(PageLinksController.class, PageLinkResource.class);
  }
  
  public PageLinkResource toResource(PageLinkConstant pageLinkConstant)
  {
    PageLinkResource pageLinkResource = new PageLinkResource();
    pageLinkResource.setName(pageLinkConstant.toString());
    pageLinkResource.setLabel(pageLinkConstant.getLabel());
    pageLinkResource.setSelectBranchRequired(pageLinkConstant.isBranchRequired());
    pageLinkResource.setType(pageLinkConstant.getUrlType().getLabel());
    pageLinkResource.setUrl(pageLinkConstant.getPageUrl());
    pageLinkResource.setParentDisplayOrder(pageLinkConstant.getDisplayOrder());
    
    List<PageLinkResource> children = new ArrayList();
    for (PageLinkConstant childPageLink : pageLinkConstant.getChildPageLinksAllowedForUser())
    {
      PageLinkResource childPageLinkResource = new PageLinkResource();
      childPageLinkResource.setName(childPageLink.toString());
      childPageLinkResource.setSelectBranchRequired(childPageLink.isBranchRequired());
      childPageLinkResource.setType(childPageLink.getUrlType().getLabel());
      childPageLinkResource.setUrl(childPageLink.getPageUrl());
      childPageLinkResource.setChildDisplayOrder(childPageLink.getDisplayOrder());
      children.add(childPageLinkResource);
    }
    if (children.size() > 0) {
      pageLinkResource.setPages(children);
    }
    return pageLinkResource;
  }
}
