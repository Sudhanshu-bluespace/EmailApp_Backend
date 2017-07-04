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

/**
 * The Class AccountApprovalRepositoryCustomImpl.
 */
@Repository
public class AccountApprovalRepositoryCustomImpl implements AccountApprovalRepositoryCustom
{

    /** The em. */
    @PersistenceContext
    @Autowired
    EntityManager em;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.repository.AccountApprovalRepositoryCustom#getPendingApprovals(java.lang.String)
     */
    @Override
    public List<PendingAccountApprovalsDTO> getPendingApprovals(String userName)
    {

        String queryString = SecurityQueryStringConstants.getQuery_PendingApprovals(userName);
        List<PendingAccountApprovalsDTO> pendingApprovalList = new ArrayList<>();
        Query query = em.createNativeQuery(queryString);
        List<Object[]> responseList = query.getResultList();
        int i = 0;
        for (Object[] response : responseList)
        {
            i++;
            PendingAccountApprovalsDTO dto = new PendingAccountApprovalsDTO();
            dto.setId(Long.valueOf(response[0].toString()));
            dto.setName(response[1].toString());
            dto.setEmail(response[2].toString());
            dto.setCompanyName(response[3].toString());
            dto.setRegistrationRequestDate(response[4].toString());
            dto.setStatus(response[10].toString());
            dto.setStreet(response[5].toString());
            dto.setCity(response[6].toString());
            dto.setState(response[7].toString());
            dto.setCountry(response[8].toString());
            dto.setZipcode(response[9].toString());
            dto.setSerialNo(i);
            
            if("ON HOLD".equalsIgnoreCase(dto.getStatus()))
            {
                dto.setOnHold(true);
            }
            else if("REJECTED".equalsIgnoreCase(dto.getStatus()))
            {
                dto.setRejected(true);
            }
                    
            pendingApprovalList.add(dto);
        }
        return pendingApprovalList;
    }

}
