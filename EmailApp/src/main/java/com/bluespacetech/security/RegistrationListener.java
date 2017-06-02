package com.bluespacetech.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.bluespacetech.notifications.email.util.MailConfiguration;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.service.UserAccountService;

@Component
public class RegistrationListener implements
  ApplicationListener<OnRegistrationCompleteEvent> {
  
    @Autowired
    private UserAccountService service;
  
    //@Autowired
    //private MessageSource messages;
  
    @Autowired
    private MailConfiguration mailSender;
    
    @Autowired
    VelocityEngine velocityEngine;
    
	private static final Logger LOGGER = Logger.getLogger(RegistrationListener.class.getName());
 
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
			sendEmail(mail);
		} 
        catch (IOException e) 
        {
        	String message = "Failed to send verification email";
			LOGGER.error(message+", reason: "+e.getMessage());
			throw new RuntimeException(message);
		}
    }
    
    private void sendEmail(AccountCreationEmail mail) throws IOException
    {
    	 try {
         	JavaMailSender sender = mailSender.javaMailSender(); 
         	MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
            ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(mail.getMailFrom());
             mimeMessageHelper.setTo(mail.getMailTo());
             String text = 
             "<html>"
             + "<head>"
             	+ "<style>"
             		+".container {background:url(\"cid:image\") no-repeat;height:1300px;width:790px;border:1px solid #f2f2f2;}"
             		+".mainMessageDiv {margin-top:76%;margin-left:46%;width:350px;}"
            		+".verifyAccountDiv {margin-top:7%;margin-left:39%;width:132px;height:30px;background-color:#68a0c4;border:1px solid #f2f2f2;"
            		+" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
            		+" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
            		+".verifyAccountDiv a{color:white;margin-top:15px;text-decoration:none;}"
            		+".verifyAccountDiv p{padding:7px;}"
            		+".footer {margin-top:31%;margin-left:31%;width:254px;height:40px;background-color:#5F5F5E;border:1px solid #f2f2f2;"
            		+" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
            		+" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
            		+".footer a{color:white;margin-top:15px;text-decoration:none;}"
            		+".footer p{color:white;padding:10px;}"
             	+ "</style>"
             + "</head>"
             + "<body>"+
             		"<div class=\"container\">"
             			+ "<div class=\"mainMessageDiv\">"
             				+ "	<p>Welcome "+mail.getModel().get("userName")+", your account for Bluespacemailer has been created "
             						+ "successfully and is pending self-verification and approval. The activation process "
             						+ "mandates that you verify your email by clicking on the 'Verify my account' "
             						+ "link given below. Upon your acount verification, the account will be reviewed by our team "
             						+ "and you will be granted access to the feaures accordingly.</p>"+
             						"<p>Thanks</p>"
             						+"<p>Bluespace Mailer Team</p>"
             		+ "</div>"
             		+ "<div class=\"verifyAccountDiv\">"
             			+ "<p><a href=\""+mail.getVerificationUrl()+"\">Verify your Account</a></p>"
             		+ "</div>"
             		+ "<div class=\"footer\">"
             			+ "<p><a href=\"#\">Visit Bluespacemailer.com</a> | " 
             				+ "<a href=\""+mail.getUnsubscribeUrl()+"\">Unsubscribe</a>"
             			+ "</p>"
             		+ "</div>"
             	+ "</div>"
             + "</body>"
           + "</html>";
             System.out.println("Text : "+text);
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             
             System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             //mimeMessageHelper.addInline("style", style);
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
    
    
    public String geContentFromTemplate(Map < String, Object > model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocityTemplates/AccountCreationEmailTemplate.vm", model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
