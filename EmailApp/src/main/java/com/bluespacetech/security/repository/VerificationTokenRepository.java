package com.bluespacetech.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);
  VerificationToken findTokenByUser(UserAccount user);
}