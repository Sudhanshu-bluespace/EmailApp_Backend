package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserRoleRepository
  extends JpaRepository<UserRole, Long>
{
  public abstract UserRole findUserRoleByRoleName(String paramString);
  
  public abstract List<UserRole> findByDescriptionLike(String paramString);
  
  public abstract List<UserRole> findByRoleNameLike(String paramString);
}
