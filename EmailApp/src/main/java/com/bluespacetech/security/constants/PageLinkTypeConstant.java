package com.bluespacetech.security.constants;

import com.bluespacetech.core.constants.Labeled;

public enum PageLinkTypeConstant
  implements Labeled
{
  LINK("link"),  TOGGLE("toggle");
  
  private String label;
  
  private PageLinkTypeConstant(String label)
  {
    this.label = label;
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
