package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserAccountRepository
  extends JpaRepository<UserAccount, Long>
{
  public abstract UserAccount findUserAccountByUsername(String paramString);
  
  public abstract UserAccount findUserAccountByEmail(String paramString);
  
  public abstract UserAccount findUserAccountById(long paramLong);
}
