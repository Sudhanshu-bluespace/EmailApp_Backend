package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import java.util.List;

public abstract interface UserAccountRepositoryCustom
{
  public abstract List<UserAccount> findUserAccountsBySearchCriteria(UserAccountSearchCriteria paramUserAccountSearchCriteria);
}
