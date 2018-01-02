package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.EmailServer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface EmailServerRepository
  extends JpaRepository<EmailServer, Long>
{
  public abstract EmailServer findByName(String paramString);
  
  public abstract List<EmailServer> findByDefaultServer(Boolean paramBoolean);
}
