package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import java.util.List;
import java.util.Map;

public abstract interface BlueSpaceTechUserAccountService
{
  public abstract UserAccount findUserAccountByUsername(String paramString);
  
  public abstract List<UserAccount> getAllUserAccounts();
  
  public abstract UserAccount getUserAccountById(Long paramLong);
  
  public abstract List<UserAccount> getUserAccountByIds(List<Long> paramList);
  
  public abstract Map<UserAccount, String> createUserAccount(UserAccount paramUserAccount)
    throws BusinessException;
  
  public abstract UserAccount updateUserAccount(UserAccount paramUserAccount)
    throws BusinessException;
  
  public abstract void deleteUserAccount(Long paramLong);
  
  public abstract List<UserAccount> findUserAccountsBySearchCriteria(UserAccountSearchCriteria paramUserAccountSearchCriteria);
  
  public abstract void changePasswordUserAccount(String paramString1, String paramString2, String paramString3)
    throws BusinessException;
}
