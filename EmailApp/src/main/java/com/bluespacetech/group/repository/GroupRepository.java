package com.bluespacetech.group.repository;

import com.bluespacetech.group.entity.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface GroupRepository
  extends JpaRepository<Group, Long>
{
  public abstract List<Group> findByNameLike(String paramString);
  
  @Query("select g from Group g where upper(g.name)=?1")
  public abstract Group findByName(String paramString);
  
  public abstract List<Group> findByCreatedUser(String paramString);
}
