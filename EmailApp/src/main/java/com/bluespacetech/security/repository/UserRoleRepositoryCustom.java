package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;
import java.util.List;

public abstract interface UserRoleRepositoryCustom
{
  public abstract List<UserRole> findUserRolesBySearchCriteria(UserRoleSearchCriteria paramUserRoleSearchCriteria);
}
