package com.bluespacetech.contact.repository;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryCustomImpl
  implements ContactRepositoryCustom
{
  @PersistenceContext
  private EntityManager entityManager;
  
  public List<Contact> findContactsBySearchCriteria(ContactSearchCriteria contactSearchCriteria)
  {
    StringBuilder queryString = new StringBuilder("select DISTINCT C from Contact C JOIN C.contactGroups CG ");
    boolean whereClasuseAdded = false;
    if (contactSearchCriteria.getFirstName() != null)
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
      queryString = queryString.append(" C.firstName like :firstName ");
    }
    if (contactSearchCriteria.getLastName() != null)
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
      queryString = queryString.append(" C.lastName like :lastName ");
    }
    if (contactSearchCriteria.getUsername() != null)
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
      queryString = queryString.append(" UPPER(C.createdUser) = :username ");
    }
    if (contactSearchCriteria.getEmail() != null)
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
      queryString = queryString.append(" C.email like :email ");
    }
    if ((contactSearchCriteria.getGroupIds() != null) && (contactSearchCriteria.getGroupIds().size() > 0))
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
      queryString = queryString.append(" CG.contactGroupPK.group.id in :groupIds ");
    }
    TypedQuery<Contact> query = this.entityManager.createQuery(queryString.toString(), Contact.class);
    if (contactSearchCriteria.getFirstName() != null) {
      query.setParameter("firstName", "%" + contactSearchCriteria.getFirstName() + "%");
    }
    if (contactSearchCriteria.getLastName() != null) {
      query.setParameter("lastName", contactSearchCriteria.getLastName());
    }
    if (contactSearchCriteria.getUsername() != null) {
      query.setParameter("username", contactSearchCriteria.getUsername().toUpperCase());
    }
    if (contactSearchCriteria.getEmail() != null) {
      query.setParameter("email", contactSearchCriteria.getEmail());
    }
    if ((contactSearchCriteria.getGroupIds() != null) && (contactSearchCriteria.getGroupIds().size() > 0)) {
      query.setParameter("groupIds", contactSearchCriteria.getGroupIds());
    }
    List<Contact> contacts = query.getResultList();
    return contacts;
  }
}
