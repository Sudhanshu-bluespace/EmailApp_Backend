package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;

/**
 * The Interface AccountApprovalRepositoryCustom.
 */
public interface AccountApprovalRepositoryCustom
{

    /**
     * Gets the pending approvals.
     *
     * @param userName the user name
     * @return the pending approvals
     */
    List<PendingAccountApprovalsDTO> getPendingApprovals(String userName);
}
