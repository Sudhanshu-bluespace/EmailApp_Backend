package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepositoryCustomImpl
  implements UserRoleRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<UserRole> findUserRolesBySearchCriteria(UserRoleSearchCriteria userRoleSearchCriteria)
  {
    StringBuilder queryString = new StringBuilder("select userRole from UserRole userRole ");
    boolean whereClasuseAdded = false;
    if (userRoleSearchCriteria.getRoleName() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userRole.roleName like '%" + userRoleSearchCriteria.getRoleName() + "%'");
      whereClasuseAdded = true;
    }
    if (userRoleSearchCriteria.getDescription() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" upper(userRole.description) like '%" + userRoleSearchCriteria.getDescription().toUpperCase() + "%'");
      whereClasuseAdded = true;
    }
    TypedQuery<UserRole> query = this.entityManager.createQuery(queryString.toString(), UserRole.class);
    
    return query.getResultList();
  }
}
