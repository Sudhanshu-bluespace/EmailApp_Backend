package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.repository.EmailServerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailServerServiceImpl
  implements EmailServerService
{
  @Autowired
  private EmailServerRepository emailServerRepository;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServer createEmailServer(EmailServer emailServer)
    throws BusinessException
  {
    validateEmailServer(emailServer);
    EmailServer newEmailServer = (EmailServer)this.emailServerRepository.save(emailServer);
    return newEmailServer;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public List<EmailServer> findAll()
  {
    return this.emailServerRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public void deleteEmailServer(Long emailServerId)
    throws BusinessException
  {
    this.emailServerRepository.delete(emailServerId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServer updateEmailServer(EmailServer emailServer)
    throws BusinessException
  {
    validateEmailServer(emailServer);
    EmailServer newEmailServer = (EmailServer)this.emailServerRepository.save(emailServer);
    return newEmailServer;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServer findEmailServerByName(String emailServerName)
    throws BusinessException
  {
    return this.emailServerRepository.findByName(emailServerName);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServer getEmailServerById(Long id)
    throws BusinessException
  {
    return (EmailServer)this.emailServerRepository.findOne(id);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServer getDefaultEmailServer()
    throws BusinessException
  {
    List<EmailServer> emailServers = this.emailServerRepository.findByDefaultServer(Boolean.valueOf(true));
    return (emailServers != null) && (emailServers.size() > 0) ? (EmailServer)emailServers.get(0) : null;
  }
  
  private void validateEmailServer(EmailServer emailServer)
    throws BusinessException
  {
    if ((emailServer.getDefaultServer() != null) && (emailServer.getDefaultServer() != null)) {
      if (getDefaultEmailServer() != null) {
        throw new BusinessException("Default server already exists.");
      }
    }
  }
}
