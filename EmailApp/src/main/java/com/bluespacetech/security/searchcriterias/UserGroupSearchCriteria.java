package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;

public class UserGroupSearchCriteria
  implements SearchCriteria
{
  private static final long serialVersionUID = 8240848078901892898L;
  private Long branchId;
  private String groupName;
  private String description;
  
  public Long getBranchId()
  {
    return this.branchId;
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
}
