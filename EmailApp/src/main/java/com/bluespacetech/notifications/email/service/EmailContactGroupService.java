package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import java.util.List;

public abstract interface EmailContactGroupService
{
  public abstract EmailContactGroup createEmailContactGroup(EmailContactGroup paramEmailContactGroup)
    throws BusinessException;
  
  public abstract void deleteEmailContactGroup(List<EmailContactGroup> paramList)
    throws BusinessException;
  
  public abstract List<EmailContactGroup> createEmailContactGroups(List<EmailContactGroup> paramList)
    throws BusinessException;
  
  public abstract List<EmailContactGroup> findAll();
  
  public abstract EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(Long paramLong1, Long paramLong2, String paramString);
  
  public abstract String findByEmailIdAndContactIdAndGroupId(Long paramLong1, Long paramLong2, Long paramLong3);
  
  public abstract EmailContactGroup updateEmailContactGroup(EmailContactGroup paramEmailContactGroup)
    throws BusinessException;
}
