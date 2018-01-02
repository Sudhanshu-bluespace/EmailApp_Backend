package com.bluespacetech.notifications.email.worker;

import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.security.model.UserAccount;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EmailUserAccountWorker
{
  @Autowired
  private JavaMailSender javaMailSender;
  @Autowired
  private EmailServerService emailServerService;
  @Autowired
  private VelocityEngine velocityEngine;
  
  @Async
  public void sendEmail(UserAccount userAccount, String userPassword)
    throws MailException, InterruptedException, MessagingException
  {
    Map<String, Object> model = new HashMap();
    model.put("userName", userAccount.getUsername());
    model.put("password", userPassword);
    String text = VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, "velocityTemplates/NewUserAccountEmail.vm", model);
    
    MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
    MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
    simpleMailMessage.setTo(userAccount.getEmail());
    simpleMailMessage.setSubject("New Account created");
    simpleMailMessage.setSentDate(new Date());
    simpleMailMessage.setText(text, true);
    this.javaMailSender.send(mimeMessage);
  }
}
