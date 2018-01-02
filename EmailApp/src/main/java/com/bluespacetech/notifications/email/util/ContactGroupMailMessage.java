package com.bluespacetech.notifications.email.util;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import java.io.Serializable;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class ContactGroupMailMessage
  implements Serializable
{
  private static final long serialVersionUID = 6064992838049760633L;
  private EmailContactGroup emailContactGroup;
  private MimeMessage mimeMessage;
  private MimeMessagePreparator preparator;
  private String requestBatchId;
  
  public String getRequestBatchId()
  {
    return this.requestBatchId;
  }
  
  public void setRequestBatchId(String requestBatchId)
  {
    this.requestBatchId = requestBatchId;
  }
  
  public MimeMessagePreparator getPreparator()
  {
    return this.preparator;
  }
  
  public void setPreparator(MimeMessagePreparator preparator)
  {
    this.preparator = preparator;
  }
  
  public MimeMessage getMimeMessage()
  {
    return this.mimeMessage;
  }
  
  public void setMimeMessage(MimeMessage mimeMessage)
  {
    this.mimeMessage = mimeMessage;
  }
  
  public EmailContactGroup getEmailContactGroup()
  {
    return this.emailContactGroup;
  }
  
  public void setEmailContactGroup(EmailContactGroup emailContactGroup)
  {
    this.emailContactGroup = emailContactGroup;
  }
}
