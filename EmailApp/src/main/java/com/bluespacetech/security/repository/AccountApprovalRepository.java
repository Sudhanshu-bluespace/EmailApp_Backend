package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.AccountApproval;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface AccountApprovalRepository
  extends JpaRepository<AccountApproval, Long>
{
  public abstract AccountApproval findAccountApprovalByUserAccountId(Long paramLong);
  
  public abstract AccountApproval findAccountApprovalByIdPendingApproval(Long paramLong);
  
  public abstract List<AccountApproval> findAccountApprovalByUserAccountIdAndStatus(long paramLong, String paramString);
  
  public abstract List<AccountApproval> findAll();
  
  public abstract AccountApproval findAccountApprovalById(long paramLong);
}
