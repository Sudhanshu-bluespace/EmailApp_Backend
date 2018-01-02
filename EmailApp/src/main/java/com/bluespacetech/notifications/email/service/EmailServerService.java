package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import java.util.List;

public abstract interface EmailServerService
{
  public abstract EmailServer createEmailServer(EmailServer paramEmailServer)
    throws BusinessException;
  
  public abstract void deleteEmailServer(Long paramLong)
    throws BusinessException;
  
  public abstract EmailServer updateEmailServer(EmailServer paramEmailServer)
    throws BusinessException;
  
  public abstract EmailServer findEmailServerByName(String paramString)
    throws BusinessException;
  
  public abstract EmailServer getEmailServerById(Long paramLong)
    throws BusinessException;
  
  public abstract EmailServer getDefaultEmailServer()
    throws BusinessException;
  
  public abstract List<EmailServer> findAll();
}
