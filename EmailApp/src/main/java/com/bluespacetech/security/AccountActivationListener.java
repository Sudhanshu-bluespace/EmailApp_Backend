package com.bluespacetech.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.bluespacetech.notifications.email.util.EmailHandler;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.service.UserAccountService;

@Component
public class AccountActivationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    @Autowired
    private UserAccountService service;
    
    @Autowired
    private EmailHandler emailHandler;
    
	private static final Logger LOGGER = Logger.getLogger(AccountActivationListener.class.getName());
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
			this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserAccount user = event.getUser();
        LOGGER.debug("Listening to the publish token event...");
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();

        String subject = "Bluespace Mailer Registration | Account Created";
        String confirmationUrl 
          = event.getAppUrl() + "/new/regitrationConfirm?token=" + token;
        AccountCreationEmail mail = new AccountCreationEmail();
        mail.setMailTo(recipientAddress);
        mail.setMailFrom("bluespaceadmin@gmail.com");
        mail.setMailSubject(subject);
        mail.setVerificationUrl(confirmationUrl);
        mail.setUnsubscribeUrl(event.getAppUrl()+"/unsubscribe?email="+recipientAddress);
        
        Map < String, Object > model = new HashMap < String, Object > ();
        model.put("userName", user.getUsername());
        model.put("signature", "www.bluespacemail.com");
        mail.setModel(model);

        try 
        {
			emailHandler.sendVerificationEmail(mail);
		} 
        catch (IOException e) 
        {
        	String message = "Failed to send verification email";
			LOGGER.error(message+", reason: "+e.getMessage());
			throw new RuntimeException(message);
		}
    }
}
