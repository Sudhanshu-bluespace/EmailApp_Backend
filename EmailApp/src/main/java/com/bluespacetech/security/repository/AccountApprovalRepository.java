package com.bluespacetech.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.security.model.AccountApproval;

/**
 * The Interface AccountApprovalRepository.
 * @author Sudhanshu Tripathy
 */
public interface AccountApprovalRepository extends JpaRepository<AccountApproval, Long>
{

    /**
     * Find account approval by user account id.
     *
     * @param id the id
     * @return the account approval
     */
    AccountApproval findAccountApprovalByUserAccountId(Long id);

    /**
     * Find account approval by id pending approval.
     *
     * @param id the id
     * @return the account approval
     */
    AccountApproval findAccountApprovalByIdPendingApproval(Long id);

    /**
     * Find account approval by user account id and status.
     *
     * @param id the id
     * @param status the status
     * @return the list
     */
    List<AccountApproval> findAccountApprovalByUserAccountIdAndStatus(long id, String status);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<AccountApproval> findAll();

    /**
     * Find account approval by id.
     *
     * @param id the id
     * @return the account approval
     */
    AccountApproval findAccountApprovalById(long id);
}
