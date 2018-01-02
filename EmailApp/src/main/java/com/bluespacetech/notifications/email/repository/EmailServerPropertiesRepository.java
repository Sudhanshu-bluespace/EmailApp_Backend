package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface EmailServerPropertiesRepository
  extends JpaRepository<EmailServerProperties, Long>
{
  public abstract List<EmailServerProperties> findEmailServerPropertiesByEmailServer(EmailServer paramEmailServer);
  
  public abstract List<EmailServerProperties> findByEmailServerIn(List<EmailServer> paramList);
}
