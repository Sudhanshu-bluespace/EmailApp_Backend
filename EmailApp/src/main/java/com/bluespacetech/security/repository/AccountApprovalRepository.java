package com.bluespacetech.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.security.model.AccountApproval;

public interface AccountApprovalRepository extends JpaRepository<AccountApproval, Long> {

	AccountApproval findAccountApprovalByUserAccountId(Long id);
	AccountApproval findAccountApprovalByIdPendingApproval(Long id);
	List<AccountApproval> findAccountApprovalByUserAccountIdAndStatus(long id,String status);
	List<AccountApproval> findAll();
	AccountApproval findAccountApprovalById(long id);
}
