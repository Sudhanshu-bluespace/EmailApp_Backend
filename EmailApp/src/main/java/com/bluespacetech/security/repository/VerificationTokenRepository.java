package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface VerificationTokenRepository
  extends JpaRepository<VerificationToken, Long>
{
  public abstract VerificationToken findByToken(String paramString);
  
  public abstract VerificationToken findTokenByUser(UserAccount paramUserAccount);
  
  public abstract VerificationToken findVerificationTokenByUserId(long paramLong);
}
