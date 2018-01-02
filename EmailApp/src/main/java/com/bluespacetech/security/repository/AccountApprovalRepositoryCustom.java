package com.bluespacetech.security.repository;

import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;
import java.util.List;

public abstract interface AccountApprovalRepositoryCustom
{
  public abstract List<PendingAccountApprovalsDTO> getPendingApprovals(String paramString);
}
