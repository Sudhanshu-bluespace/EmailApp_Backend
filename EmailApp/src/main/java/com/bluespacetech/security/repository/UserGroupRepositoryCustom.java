package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;
import java.util.List;

public abstract interface UserGroupRepositoryCustom
{
  public abstract List<UserGroup> findUserGroupsBySearchCriteria(UserGroupSearchCriteria paramUserGroupSearchCriteria);
}
