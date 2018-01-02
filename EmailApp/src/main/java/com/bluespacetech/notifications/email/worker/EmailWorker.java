package com.bluespacetech.notifications.email.worker;

import com.bluespacetech.notifications.email.valueobjects.EmailVO;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class EmailWorker
{
  private final JavaMailSender javaMailSender;
  
  public EmailWorker(JavaMailSender javaMailSender)
  {
    this.javaMailSender = javaMailSender;
  }
  
  @Async
  void sendEmail(EmailVO email)
  {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo((String[])email.getToAddress().toArray(new String[email.getToAddress().size()]));
    mail.setFrom(email.getFromAddress());
    mail.setSubject(email.getSubject());
    mail.setText(email.getMessage());
    this.javaMailSender.send(mail);
  }
}
