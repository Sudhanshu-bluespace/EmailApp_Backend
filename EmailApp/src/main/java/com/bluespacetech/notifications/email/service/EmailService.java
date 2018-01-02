package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;
import java.util.List;

public abstract interface EmailService
{
  public abstract Email createEmail(Email paramEmail)
    throws BusinessException;
  
  public abstract Email createEmail(EmailVO paramEmailVO)
    throws BusinessException;
  
  public abstract void deleteEmail(Email paramEmail)
    throws BusinessException;
  
  public abstract List<Email> findAll();
  
  public abstract List<EmailContactGroupVO> getEmailContactGroups(String paramString, EmailVO paramEmailVO, Long paramLong);
}
