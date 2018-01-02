package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountRepositoryCustomImpl
  implements UserAccountRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<UserAccount> findUserAccountsBySearchCriteria(UserAccountSearchCriteria userAccountSearchCriteria)
  {
    StringBuilder queryString = new StringBuilder("select userAccount from UserAccount userAccount ");
    boolean whereClasuseAdded = false;
    if (userAccountSearchCriteria.getUsername() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.username = :userName ");
      whereClasuseAdded = true;
    }
    if (userAccountSearchCriteria.getUserAccountType() != null)
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.userAccountType = :userAccountType ");
      whereClasuseAdded = true;
    }
    if (userAccountSearchCriteria.isActive())
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.active = :active ");
      whereClasuseAdded = true;
    }
    if (userAccountSearchCriteria.isAccountExpired())
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.accountExpired = :accountExpired ");
      whereClasuseAdded = true;
    }
    if (userAccountSearchCriteria.isAccountLocked())
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.accountLocked = :accountLocked ");
      whereClasuseAdded = true;
    }
    if (userAccountSearchCriteria.isCredentialsExpired())
    {
      if (!whereClasuseAdded) {
        queryString = queryString.append(" where ");
      } else {
        queryString = queryString.append(" and ");
      }
      queryString = queryString.append(" userAccount.credentialsExpired = :credentialsExpired ");
      whereClasuseAdded = true;
    }
    if (!whereClasuseAdded) {
      queryString = queryString.append(" where ");
    } else {
      queryString = queryString.append(" and ");
    }
    queryString = queryString.append(" userAccount.userAccountType != 'ACC_TYPE_SUPER_ADMIN' ");
    whereClasuseAdded = true;
    
    TypedQuery<UserAccount> query = this.entityManager.createQuery(queryString.toString(), UserAccount.class);
    if (userAccountSearchCriteria.getUsername() != null) {
      query.setParameter("userName", userAccountSearchCriteria.getUsername());
    }
    if (userAccountSearchCriteria.getUserAccountType() != null) {
      query.setParameter("userAccountType", userAccountSearchCriteria.getUserAccountType());
    }
    if (userAccountSearchCriteria.isActive()) {
      query.setParameter("active", Boolean.valueOf(userAccountSearchCriteria.isActive()));
    }
    if (userAccountSearchCriteria.isAccountExpired()) {
      query.setParameter("accountExpired", Boolean.valueOf(userAccountSearchCriteria.isAccountExpired()));
    }
    if (userAccountSearchCriteria.isAccountLocked()) {
      query.setParameter("accountLocked", Boolean.valueOf(userAccountSearchCriteria.isAccountLocked()));
    }
    if (userAccountSearchCriteria.isCredentialsExpired()) {
      query.setParameter("credentialsExpired", Boolean.valueOf(userAccountSearchCriteria.isCredentialsExpired()));
    }
    return query.getResultList();
  }
}
