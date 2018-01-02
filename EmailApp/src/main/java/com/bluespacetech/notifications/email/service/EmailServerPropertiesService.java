package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import java.util.List;

public abstract interface EmailServerPropertiesService
{
  public abstract EmailServerProperties createEmailServerProperty(EmailServerProperties paramEmailServerProperties)
    throws BusinessException;
  
  public abstract void deleteEmailServerProperty(Long paramLong)
    throws BusinessException;
  
  public abstract EmailServerProperties updateEmailServerProperty(EmailServerProperties paramEmailServerProperties)
    throws BusinessException;
  
  public abstract List<EmailServerProperties> findByEmailServer(EmailServer paramEmailServer);
  
  public abstract EmailServerProperties getEmailServerPropertiesById(Long paramLong)
    throws BusinessException;
  
  public abstract List<EmailServerProperties> findByEmailServers(List<EmailServer> paramList);
}
