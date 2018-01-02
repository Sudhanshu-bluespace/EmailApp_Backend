package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserGroupRepositoryCustomImpl
  implements UserGroupRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<UserGroup> findUserGroupsBySearchCriteria(UserGroupSearchCriteria userGroupSearchCriteria)
  {
    StringBuilder queryString = new StringBuilder("select userGroup from UserGroup userGroup ");
    boolean whereClasuseAdded = false;
    if (userGroupSearchCriteria.getGroupName() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userGroup.groupName like '%" + userGroupSearchCriteria.getGroupName() + "%'");
      whereClasuseAdded = true;
    }
    if (userGroupSearchCriteria.getDescription() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" upper(userGroup.description) like '%" + userGroupSearchCriteria.getDescription().toUpperCase() + "%'");
      whereClasuseAdded = true;
    }
    TypedQuery<UserGroup> query = this.entityManager.createQuery(queryString.toString(), UserGroup.class);
    
    return query.getResultList();
  }
}
