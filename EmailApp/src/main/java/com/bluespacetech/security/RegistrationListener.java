package com.bluespacetech.security;

import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bluespacetech.notifications.email.util.GoogleAuthorizeStarter;
import com.bluespacetech.notifications.email.util.GoogleMailAuthorizer;
import com.bluespacetech.notifications.email.util.MailConfiguration;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.service.UserAccountService;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

@Component
public class RegistrationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    @Autowired
    private UserAccountService service;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private MailConfiguration mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
			this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserAccount user = event.getUser();
        System.out.println("Listening to the publish token event...");
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \n" + "http://localhost:8080" + confirmationUrl);
        System.out.println("Sending verification email...: "+email);
        //JavaMailSender sender = mailSender.javaMailSender();
        
        // Google mail API client service..
        Gmail service;
		try {
			service = GoogleAuthorizeStarter.getGmailService();
	        MimeMessage mime = GoogleMailAuthorizer.createEmail(recipientAddress, "admin@bluespacetech", subject, message + " \n" + "http://localhost:8080" + confirmationUrl);
	        Message messages = GoogleMailAuthorizer.sendMessage(service, "sudhanshu@bluespacetech.com", mime);
	        System.out.println("Sent : "+messages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        System.out.println("Sent: "+messages);
    }
}
