package com.bluespacetech.security;

import com.bluespacetech.notifications.email.util.EmailHandler;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.service.UserAccountService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AccountActivationListener
  implements ApplicationListener<OnRegistrationCompleteEvent>
{
  @Autowired
  private UserAccountService service;
  @Autowired
  private EmailHandler emailHandler;
  private static final Logger LOGGER = LogManager.getLogger(AccountActivationListener.class.getName());
  
  public void onApplicationEvent(OnRegistrationCompleteEvent event)
  {
    confirmRegistration(event);
  }
  
  private void confirmRegistration(OnRegistrationCompleteEvent event)
  {
    UserAccount user = event.getUser();
    LOGGER.debug("Listening to the publish token event...");
    
    String recipientAddress = user.getEmail();
    String subject = "ContactSwing User Registration | Account Created";
    
    AccountCreationEmail mail = new AccountCreationEmail();
    mail.setMailTo(recipientAddress);
    mail.setMailFrom("no-reply@hireswing.com");
    mail.setMailSubject(subject);
    if (!event.isAccountCreatedByAdmin())
    {
      LOGGER.info("INternal Account creation by Admin. Skiiping token creation and verification link");
      String token = UUID.randomUUID().toString();
      this.service.createVerificationToken(user, token);
      String confirmationUrl = event.getAppUrl() + "/new/regitrationConfirm?token=" + token;
      mail.setVerificationUrl(confirmationUrl);
      mail.setUnsubscribeUrl(event.getAppUrl() + "/unsubscribe?email=" + recipientAddress);
    }
    Map<String, Object> model = new HashMap();
    model.put("userName", user.getUsername());
    model.put("signature", "www.bluespacemail.com");
    mail.setModel(model);
    try
    {
      if (event.isAccountCreatedByAdmin()) {
        this.emailHandler.sendAccountCreationEmail(mail, event.getUser().getUsername(), event
          .getUser().getPassword());
      } else if ("APPROVE".equalsIgnoreCase(event.getRequestType())) {
        this.emailHandler.sendVerificationEmail(mail);
      } else if ("HOLD".equalsIgnoreCase(event.getRequestType())) {
        this.emailHandler.sendAccountCreationOnHoldEmail(mail, event.getUser().getUsername(), event
          .getUser().getPassword());
      } else if ("REJECT".equalsIgnoreCase(event.getRequestType())) {
        this.emailHandler.sendAccountCreationRejectedEmail(mail, event.getUser().getUsername(), event
          .getUser().getPassword());
      }
    }
    catch (IOException e)
    {
      String message = "Failed to send verification/account creation email";
      LOGGER.error(message + ", reason: " + e.getMessage());
      throw new RuntimeException(message);
    }
  }
}
