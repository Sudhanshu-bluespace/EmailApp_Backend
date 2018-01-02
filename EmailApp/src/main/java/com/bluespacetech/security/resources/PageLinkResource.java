package com.bluespacetech.security.resources;

import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class PageLinkResource
  extends ResourceSupport
{
  private String name;
  private String label;
  private int parentDisplayOrder;
  private int childDisplayOrder;
  private List<PageLinkResource> pages;
  private String url;
  private String type;
  private boolean selectBranchRequired;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public List<PageLinkResource> getPages()
  {
    return this.pages;
  }
  
  public void setPages(List<PageLinkResource> pages)
  {
    this.pages = pages;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public boolean isSelectBranchRequired()
  {
    return this.selectBranchRequired;
  }
  
  public void setSelectBranchRequired(boolean selectBranchRequired)
  {
    this.selectBranchRequired = selectBranchRequired;
  }
  
  public int getParentDisplayOrder()
  {
    return this.parentDisplayOrder;
  }
  
  public void setParentDisplayOrder(int parentDisplayOrder)
  {
    this.parentDisplayOrder = parentDisplayOrder;
  }
  
  public int getChildDisplayOrder()
  {
    return this.childDisplayOrder;
  }
  
  public void setChildDisplayOrder(int childDisplayOrder)
  {
    this.childDisplayOrder = childDisplayOrder;
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String label)
  {
    this.label = label;
  }
}
