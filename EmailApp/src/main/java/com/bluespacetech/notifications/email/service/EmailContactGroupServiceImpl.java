package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.repository.EmailContactGroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
public class EmailContactGroupServiceImpl
  implements EmailContactGroupService
{
  @Autowired
  private EmailContactGroupRepository emailContactGroupRepository;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public EmailContactGroup createEmailContactGroup(EmailContactGroup emailContactGroup)
    throws BusinessException
  {
    EmailContactGroup newEmailContactGroup = (EmailContactGroup)this.emailContactGroupRepository.save(emailContactGroup);
    return newEmailContactGroup;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public List<EmailContactGroup> createEmailContactGroups(List<EmailContactGroup> emailContactGroups)
    throws BusinessException
  {
    List<EmailContactGroup> result = this.emailContactGroupRepository.save(emailContactGroups);
    return result;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public List<EmailContactGroup> findAll()
  {
    return this.emailContactGroupRepository.findAll();
  }
  
  public EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(Long contactId, Long groupId, String randomNumber)
  {
    return this.emailContactGroupRepository.findByContactIdAndGroupIdAndRandomNumber(contactId, groupId, randomNumber);
  }
  
  public EmailContactGroup updateEmailContactGroup(EmailContactGroup emailContactGroup)
    throws BusinessException
  {
    EmailContactGroup newEmailContactGroup = (EmailContactGroup)this.emailContactGroupRepository.save(emailContactGroup);
    return newEmailContactGroup;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public void deleteEmailContactGroup(List<EmailContactGroup> emailContactGroups)
    throws BusinessException
  {
    this.emailContactGroupRepository.delete(emailContactGroups);
  }
  
  public String findByEmailIdAndContactIdAndGroupId(Long emailId, Long contactId, Long groupId)
  {
    EmailContactGroup ecg = this.emailContactGroupRepository.findByEmailIdAndContactIdAndGroupId(emailId, contactId, groupId);
    if (ecg != null) {
      return ecg.getCreatedUser();
    }
    return null;
  }
}
