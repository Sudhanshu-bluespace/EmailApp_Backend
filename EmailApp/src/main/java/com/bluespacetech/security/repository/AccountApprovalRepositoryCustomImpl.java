package com.bluespacetech.security.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.constants.SecurityQueryStringConstants;
import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;

@Repository
public class AccountApprovalRepositoryCustomImpl implements AccountApprovalRepositoryCustom {
	
	@PersistenceContext 
	@Autowired
	EntityManager em;

	@Override
	public List<PendingAccountApprovalsDTO> getPendingApprovals(String userName) {

		String queryString = SecurityQueryStringConstants.getQuery_PendingApprovals(userName);
		List<PendingAccountApprovalsDTO> pendingApprovalList = new ArrayList<>();
		Query query = em.createNativeQuery(queryString);
		List<Object[]> responseList = query.getResultList();
		int i=0;
		for(Object[] response:responseList)
		{
			i++;
			PendingAccountApprovalsDTO dto = new PendingAccountApprovalsDTO();
			dto.setId(Long.valueOf(response[0].toString()));
			dto.setName(response[1].toString());
			dto.setEmail(response[2].toString());
			dto.setRegistrationRequestDate(response[3].toString());
			dto.setStatus(response[4].toString());
			dto.setSerialNo(i);
			pendingApprovalList.add(dto);
		}
		return pendingApprovalList;
	}

}
