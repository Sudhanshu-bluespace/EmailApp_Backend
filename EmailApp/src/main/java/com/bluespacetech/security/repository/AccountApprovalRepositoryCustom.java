package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;

public interface AccountApprovalRepositoryCustom {

	List<PendingAccountApprovalsDTO> getPendingApprovals(String userName);
}
