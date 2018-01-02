package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface EmailRepository
  extends JpaRepository<Email, Long>
{
  public abstract Email findById(Long paramLong);
}
