package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserPreferenceRepository
  extends JpaRepository<UserPreference, Long>
{
  public abstract UserPreference findUserPreferenceByUserAccount(UserAccount paramUserAccount);
}
