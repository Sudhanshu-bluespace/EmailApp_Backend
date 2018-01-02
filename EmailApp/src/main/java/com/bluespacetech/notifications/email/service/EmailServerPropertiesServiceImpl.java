package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.repository.EmailServerPropertiesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailServerPropertiesServiceImpl
  implements EmailServerPropertiesService
{
  @Autowired
  private EmailServerPropertiesRepository emailServerPropertiesRepository;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public List<EmailServerProperties> findByEmailServer(EmailServer emailServer)
  {
    return this.emailServerPropertiesRepository.findEmailServerPropertiesByEmailServer(emailServer);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServerProperties createEmailServerProperty(EmailServerProperties emailServerProperties)
    throws BusinessException
  {
    return (EmailServerProperties)this.emailServerPropertiesRepository.save(emailServerProperties);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public void deleteEmailServerProperty(Long emailServerPropertiesId)
    throws BusinessException
  {
    this.emailServerPropertiesRepository.delete(emailServerPropertiesId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServerProperties updateEmailServerProperty(EmailServerProperties emailServerProperties)
    throws BusinessException
  {
    return (EmailServerProperties)this.emailServerPropertiesRepository.save(emailServerProperties);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public EmailServerProperties getEmailServerPropertiesById(Long id)
    throws BusinessException
  {
    return (EmailServerProperties)this.emailServerPropertiesRepository.findOne(id);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS') or hasAuthority('ACCESS_SEND_EMAIL'))")
  public List<EmailServerProperties> findByEmailServers(List<EmailServer> emailServers)
  {
    return this.emailServerPropertiesRepository.findByEmailServerIn(emailServers);
  }
}
