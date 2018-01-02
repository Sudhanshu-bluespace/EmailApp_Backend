package com.bluespacetech.group.repository;

import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryCustomImpl
  implements GroupRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<Group> findGroupsBySearchCriteria(GroupSearchCriteria groupSearchCriteria)
  {
    StringBuilder queryString = new StringBuilder("select G from Group G ");
    boolean whereClasuseAdded = false;
    if (groupSearchCriteria.getName() != null)
    {
      if (!whereClasuseAdded)
      {
        queryString = queryString.append(" where ");
        whereClasuseAdded = true;
      }
      else
      {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" G.name like :name ");
    }
    if (groupSearchCriteria.getComments() != null)
    {
      if (!whereClasuseAdded)
      {
        queryString = queryString.append(" where ");
        whereClasuseAdded = true;
      }
      else
      {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" G.comments like :comments ");
    }
    if (groupSearchCriteria.getUsername() != null)
    {
      if (!whereClasuseAdded)
      {
        queryString = queryString.append(" where ");
        whereClasuseAdded = true;
      }
      else
      {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" upper(G.createdUser) = :createdUser ");
    }
    TypedQuery<Group> query = this.entityManager.createQuery(queryString.toString(), Group.class);
    if (groupSearchCriteria.getName() != null) {
      query.setParameter("name", "%" + groupSearchCriteria.getName() + "%");
    }
    if (groupSearchCriteria.getComments() != null) {
      query.setParameter("comments", groupSearchCriteria.getComments());
    }
    if (groupSearchCriteria.getUsername() != null) {
      query.setParameter("createdUser", groupSearchCriteria.getUsername().toUpperCase());
    }
    return query.getResultList();
  }
}
