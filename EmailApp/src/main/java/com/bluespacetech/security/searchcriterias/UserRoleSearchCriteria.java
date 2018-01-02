package com.bluespacetech.security.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;

public class UserRoleSearchCriteria
  implements SearchCriteria
{
  private static final long serialVersionUID = 442834923078965556L;
  private Long branchId;
  private String roleName;
  private String description;
  
  public Long getBranchId()
  {
    return this.branchId;
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId;
  }
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
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
