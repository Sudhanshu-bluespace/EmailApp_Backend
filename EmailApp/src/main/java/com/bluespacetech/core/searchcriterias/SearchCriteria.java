package com.bluespacetech.core.searchcriterias;

import java.io.Serializable;

public abstract interface SearchCriteria
  extends Serializable
{
  public abstract Long getBranchId();
  
  public abstract void setBranchId(Long paramLong);
}
