package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserGroupRepository
  extends JpaRepository<UserGroup, Long>
{
  public abstract UserGroup findUserGroupByGroupName(String paramString);
  
  public abstract List<UserGroup> findByDescriptionLike(String paramString);
  
  public abstract List<UserGroup> findByGroupNameLike(String paramString);
}
