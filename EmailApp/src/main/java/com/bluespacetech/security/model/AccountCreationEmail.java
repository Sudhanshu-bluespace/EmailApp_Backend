package com.bluespacetech.security.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AccountCreationEmail
{
  private String mailFrom;
  private String mailTo;
  private String mailCc;
  private String mailBcc;
  private String mailSubject;
  private String mailContent;
  private String contentType;
  private String verificationUrl;
  private String unsubscribeUrl;
  private List<Object> attachments;
  private Map<String, Object> model;
  
  public String getUnsubscribeUrl()
  {
    return this.unsubscribeUrl;
  }
  
  public void setUnsubscribeUrl(String unsubscribeUrl)
  {
    this.unsubscribeUrl = unsubscribeUrl;
  }
  
  public String getVerificationUrl()
  {
    return this.verificationUrl;
  }
  
  public void setVerificationUrl(String verificationUrl)
  {
    this.verificationUrl = verificationUrl;
  }
  
  public AccountCreationEmail()
  {
    this.contentType = "text/plain";
  }
  
  public String getContentType()
  {
    return this.contentType;
  }
  
  public void setContentType(String contentType)
  {
    this.contentType = contentType;
  }
  
  public String getMailBcc()
  {
    return this.mailBcc;
  }
  
  public void setMailBcc(String mailBcc)
  {
    this.mailBcc = mailBcc;
  }
  
  public String getMailCc()
  {
    return this.mailCc;
  }
  
  public void setMailCc(String mailCc)
  {
    this.mailCc = mailCc;
  }
  
  public String getMailFrom()
  {
    return this.mailFrom;
  }
  
  public void setMailFrom(String mailFrom)
  {
    this.mailFrom = mailFrom;
  }
  
  public String getMailSubject()
  {
    return this.mailSubject;
  }
  
  public void setMailSubject(String mailSubject)
  {
    this.mailSubject = mailSubject;
  }
  
  public String getMailTo()
  {
    return this.mailTo;
  }
  
  public void setMailTo(String mailTo)
  {
    this.mailTo = mailTo;
  }
  
  public Date getMailSendDate()
  {
    return new Date();
  }
  
  public String getMailContent()
  {
    return this.mailContent;
  }
  
  public void setMailContent(String mailContent)
  {
    this.mailContent = mailContent;
  }
  
  public List<Object> getAttachments()
  {
    return this.attachments;
  }
  
  public void setAttachments(List<Object> attachments)
  {
    this.attachments = attachments;
  }
  
  public Map<String, Object> getModel()
  {
    return this.model;
  }
  
  public void setModel(Map<String, Object> model)
  {
    this.model = model;
  }
}
