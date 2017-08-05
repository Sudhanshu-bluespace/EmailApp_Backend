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
            dto.setName(getResponse(response[1]));
            dto.setEmail(getResponse(response[2]));
            dto.setCompanyName(getResponse(response[3]));
            dto.setRegistrationRequestDate(getResponse(response[4]));
            dto.setStatus(getResponse(response[10]));
            dto.setAddress(getResponse(response[5]));
            dto.setCity(getResponse(response[6]));
            dto.setState(getResponse(response[7]));
            dto.setCountry(getResponse(response[8]));
            dto.setZipcode(getResponse(response[9]));
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
    
    private String getResponse(Object input)
    {
        if(input == null)
        {
            return "-";            
        }
        else
        {
            return input.toString();
        }
    }

}
