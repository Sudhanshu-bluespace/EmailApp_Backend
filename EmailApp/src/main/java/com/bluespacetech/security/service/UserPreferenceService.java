package com.bluespacetech.security.service;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;

public abstract interface UserPreferenceService
{
  public abstract UserPreference saveUserPreference(UserPreference paramUserPreference)
    throws Exception;
  
  public abstract UserPreference findUserPreferenceByUserAccount(UserAccount paramUserAccount);
  
  public abstract UserPreference getCurrentUserPreference()
    throws Exception;
}
