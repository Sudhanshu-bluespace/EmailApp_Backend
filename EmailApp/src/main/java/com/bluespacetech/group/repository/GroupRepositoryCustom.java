package com.bluespacetech.group.repository;

import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import java.util.List;

public abstract interface GroupRepositoryCustom
{
  public abstract List<Group> findGroupsBySearchCriteria(GroupSearchCriteria paramGroupSearchCriteria);
}
