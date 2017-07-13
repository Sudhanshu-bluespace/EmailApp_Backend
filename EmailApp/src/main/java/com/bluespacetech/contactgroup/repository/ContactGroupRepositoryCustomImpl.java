package com.bluespacetech.contactgroup.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;

@Repository
public class ContactGroupRepositoryCustomImpl implements ContactGroupRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ContactGroup getContactGroupByContactIdAndGroupId(Long contactId, Long groupId) {
		StringBuilder queryString = new StringBuilder("from ContactGroup CG");
		if (contactId != null && groupId != null) {
			queryString = queryString.append(" where ");
			queryString = queryString.append(" CG.contactGroupPK.contact.id = :contactId and  CG.contactGroupPK.group.id = :groupId");
		}
		TypedQuery<ContactGroup> query = entityManager.createQuery(queryString.toString(), ContactGroup.class);
		query.setParameter("contactId", contactId);
		query.setParameter("groupId", groupId);
		ContactGroup contactGroup = query.getSingleResult();
		return contactGroup;
	}

    @Override
    public List<ContactGroup> getContactGroupsByContactId(Long contactId)
    {
        StringBuilder queryString = new StringBuilder("from ContactGroup CG");
        if (contactId != null) {
                queryString = queryString.append(" where ");
                queryString = queryString.append(" CG.contactGroupPK.contact.id = :contactId");
        }
        TypedQuery<ContactGroup> query = entityManager.createQuery(queryString.toString(), ContactGroup.class);
        query.setParameter("contactId", contactId);
        List<ContactGroup> contactGroups = query.getResultList();
        return contactGroups;
    }

}
