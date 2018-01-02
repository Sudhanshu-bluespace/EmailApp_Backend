package com.bluespacetech.notifications.email.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.repository.EmailRepository;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailServiceImpl
  implements EmailService
{
  @Autowired
  private EmailRepository emailRepository;
  @Autowired
  @PersistenceContext
  private EntityManager em;
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public Email createEmail(Email email)
    throws BusinessException
  {
    Email newEmail = (Email)this.emailRepository.save(email);
    return newEmail;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public void deleteEmail(Email email)
    throws BusinessException
  {
    if (email != null) {
      this.emailRepository.delete(email);
    }
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public Email createEmail(EmailVO emailVO)
    throws BusinessException
  {
    Email email = new Email();
    email.setMessage(emailVO.getMessage().getBytes());
    email.setSubject(emailVO.getSubject());
    email = createEmail(email);
    return email;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public List<Email> findAll()
  {
    return this.emailRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_SEND_EMAIL')")
  public List<EmailContactGroupVO> getEmailContactGroups(String query, EmailVO emailVO, Long emailId)
  {
    List<EmailContactGroupVO> list = new ArrayList();
    Query queryRes = this.em.createNativeQuery(query);
    List<Object[]> resultList = queryRes.getResultList();
    for (Object[] res : resultList)
    {
      EmailContactGroupVO emailContactGroupVO = new EmailContactGroupVO();
      emailContactGroupVO.setContactFirstName(res[0].toString());
      emailContactGroupVO.setContactLastName(res[1].toString());
      emailContactGroupVO.setContactEmail(res[2].toString());
      emailContactGroupVO.setGroupId(Long.valueOf(Long.parseLong(res[3].toString())));
      emailContactGroupVO.setContactId(Long.valueOf(Long.parseLong(res[4].toString())));
      emailContactGroupVO.setMessage(emailVO.getMessage());
      emailContactGroupVO.setSubject(emailVO.getSubject());
      emailContactGroupVO.setEmailId(emailId);
      list.add(emailContactGroupVO);
    }
    return list;
  }
}
