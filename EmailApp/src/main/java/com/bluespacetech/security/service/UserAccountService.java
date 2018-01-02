package com.bluespacetech.security.service;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;

public abstract interface UserAccountService
{
  public abstract UserAccount findUserAccountByUsername(String paramString);
  
  public abstract UserAccount save(UserAccount paramUserAccount);
  
  public abstract UserAccount getUser(String paramString);
  
  public abstract void createVerificationToken(UserAccount paramUserAccount, String paramString);
  
  public abstract VerificationToken getVerificationToken(String paramString);
  
  public abstract String getEncodedPassword(String paramString);
}
