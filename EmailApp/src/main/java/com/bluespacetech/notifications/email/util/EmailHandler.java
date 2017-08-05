package com.bluespacetech.notifications.email.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bluespacetech.security.model.AccountCreationEmail;

/**
 * The Class EmailHandler.
 */
@Component
public class EmailHandler {
	
    /** The mail sender. */
    @Autowired
    private MailConfiguration mailSender;
    
    /**
     * Send email to admin.
     *
     * @param mail the mail
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendEmailToAdmin(AccountCreationEmail mail) throws IOException
    {
    	 try 
    	 {
             JavaMailSender sender = mailSender.javaMailSender(); 
             MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
             ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success_1.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(new InternetAddress("no-reply@hireswing.com"));
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
             				+ "	<p>A new account with username "+mail.getModel().get("userName")+" has been created "
             						+ "and is pending for your approval. You can visit the "
             						+ "Bluespace Application and approve the account from the Accont Approval menu "
             						+ "on the left hand side of the dashbard. Upon your approval, a verification email will be sent to the user "
             						+ "and on self verification, appropriate grants would be provided.</p>"+
             						"<p>Thanks</p>"
             						+"<p>ContactSwing Support Team</p>"
             			+ "</div>"
             	+ "</div>"
             + "</body>"
           + "</html>";
             //System.out.println("Text : "+text);
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             
             //System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             //mimeMessageHelper.addInline("style", style);
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } 
    	 catch (MessagingException e) 
    	 {
             e.printStackTrace();
         }
    }
	
    /**
     * Send verification email.
     *
     * @param mail the mail
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendVerificationEmail(AccountCreationEmail mail) throws IOException
    {
    	 try {
         	JavaMailSender sender = mailSender.javaMailSender(); 
         	MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
            ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success_1.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(new InternetAddress("no-reply@hireswing.com"));
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
            		+".footer {margin-top:31%;margin-left:38%;width:75px;height:40px;background-color:#5F5F5E;border:1px solid #f2f2f2;"
            		+" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
            		+" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
            		+".footer a{color:white;margin-top:15px;text-decoration:none;}"
            		+".footer p{color:white;padding:10px;}"
             	+ "</style>"
             + "</head>"
             + "<body>"+
             		"<div class=\"container\">"
             			+ "<div class=\"mainMessageDiv\">"
             				+ "	<p>Welcome "+mail.getModel().get("userName")+", your account for ContactSwing has been created "
             						+ "successfully and is pending self-verification and approval. The activation process "
             						+ "mandates that you verify your email by clicking on the 'Verify my account' "
             						+ "link given below. Upon your acount verification, the account will be reviewed by our team "
             						+ "and you will be granted access to the feaures accordingly."+
             					"</p>"+
             					"<p>Thanks</p>"+
             					"<p>ContactSwing Support Team</p>"
             			+ "</div>"
             			+ "<div class=\"verifyAccountDiv\">"
             			    + "<p><a href=\""+mail.getVerificationUrl()+"\">Verify your Account</a></p>"
             			+ "</div>"
             			+ "<div class=\"footer\">"
             			    + "<p><a href=\"http://www.hireswing.com\">Visit Us"
             			    + "</p>"
             			+ "</div>"
             		+ "</div>"
             + "</body>"
           + "</html>";
             //System.out.println("Text : "+text);
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             
             //System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             //mimeMessageHelper.addInline("style", style);
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
    
    /**
     * Send account creation email.
     *
     * @param mail the mail
     * @param username the username
     * @param password the password
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendAccountCreationEmail(AccountCreationEmail mail,String username,String password) throws IOException
    {
    	 try {
    	     JavaMailSender sender = mailSender.javaMailSender(); 
    	     MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
             ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success_1.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(new InternetAddress("no-reply@hireswing.com"));
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
            		+".footer {margin-top:31%;margin-left:38%;width:75px;height:40px;background-color:#5F5F5E;border:1px solid #f2f2f2;"
            		+" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
            		+" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
            		+".footer a{color:white;margin-top:15px;text-decoration:none;}"
            		+".footer p{color:white;padding:10px;}"
             	+ "</style>"
             + "</head>"
             + "<body>"+
             		"<div class=\"container\">"
             			+ "<div class=\"mainMessageDiv\">"
             				+ "	<p>Welcome "+mail.getModel().get("userName")+", your account for ContactSwing has been created "
             						+ "successfully. You can login to your account with "+username+" as the username and "+password+" as the password"
             						+ ". We strongly advise you to change your password on the first login. Based on your subscription plan, "
             						+ "you have been granted access to the features available."+
             					"</p>"+
             					"<p>Thanks</p>"
             					+"<p>ContactSwing Support Team</p>"
             			+ "</div>"
                 		+ "<div class=\"footer\">"
                 		         + "<p><a href=\"http://www.hireswing.com\">Visit Us</a>"
                 			+ "</p>"
                 		+ "</div>"+
                 	"</div>"
             + "</body>"
           + "</html>";
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             //mimeMessageHelper.addInline("style", style);
             //System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
    
    
    /**
     * Send account creation email.
     *
     * @param mail the mail
     * @param username the username
     * @param password the password
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendAccountCreationOnHoldEmail(AccountCreationEmail mail,String username,String password) throws IOException
    {
         try {
             JavaMailSender sender = mailSender.javaMailSender(); 
             MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
             ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success_1.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(new InternetAddress("no-reply@hireswing.com"));
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
                        +".footer {margin-top:31%;margin-left:38%;width:75px;height:40px;background-color:#5F5F5E;border:1px solid #f2f2f2;"
                        +" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
                        +" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
                        +".footer a{color:white;margin-top:15px;text-decoration:none;}"
                        +".footer p{color:white;padding:10px;}"
                + "</style>"
             + "</head>"
             + "<body>"+
                        "<div class=\"container\">"
                                + "<div class=\"mainMessageDiv\">"
                                        + "     <p>Hello "+mail.getModel().get("userName")+", your request for account creation for ContactSwing has been put on hold "
                                                        + "by the administrator. Please contact our customer support for further details."+
                                                "</p>"+
                                                "<p>Thanks</p>"
                                                +"<p>ContactSwing Support Team</p>"
                                + "</div>"
                                + "<div class=\"footer\">"
                                        + "<p><a href=\"http://www.hireswing.com\">Visit Us</a>"
                                        + "</p>"
                                + "</div>"+
                        "</div>"
             + "</body>"
           + "</html>";
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             //mimeMessageHelper.addInline("style", style);
             System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
    
    
    /**
     * Send account creation email.
     *
     * @param mail the mail
     * @param username the username
     * @param password the password
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void sendAccountCreationRejectedEmail(AccountCreationEmail mail,String username,String password) throws IOException
    {
         try {
             JavaMailSender sender = mailSender.javaMailSender(); 
             MimeMessage mimeMessage = sender.createMimeMessage();
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
             ClassPathResource style = new ClassPathResource("/css/EmailStyles.css");
             ClassPathResource image = new ClassPathResource("/images/bluepsacemailer_createacc_success_1.png");
             mimeMessageHelper.setSubject(mail.getMailSubject());
             mimeMessageHelper.setFrom(new InternetAddress("no-reply@hireswing.com"));
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
                        +".footer {margin-top:31%;margin-left:38%;width:75px;height:40px;background-color:#5F5F5E;border:1px solid #f2f2f2;"
                        +" border-radius: 6px 6px 6px 6px; -moz-border-radius: 6px 6px 6px 6px; -webkit-border-radius: 6px 6px 6px 6px; border: 0px solid #000000;"
                        +" -webkit-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);-moz-box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);box-shadow: -2px 9px 23px -8px rgba(0,0,0,0.75);}"
                        +".footer a{color:white;margin-top:15px;text-decoration:none;}"
                        +".footer p{color:white;padding:10px;}"
                + "</style>"
             + "</head>"
             + "<body>"+
                        "<div class=\"container\">"
                                + "<div class=\"mainMessageDiv\">"
                                        + "     <p>Hello "+mail.getModel().get("userName")+", your request for account creation for ContactSwing has been rejected "
                                                        + "by the administrator. Please contact our customer support for further details."+
                                                "</p>"+
                                                "<p>Thanks</p>"
                                                +"<p>ContactSwing Support Team</p>"
                                + "</div>"
                                + "<div class=\"footer\">"
                                        + "<p><a href=\"http://www.hireswing.com\">Visit Us</a>"
                                        + "</p>"
                                + "</div>"+
                        "</div>"
             + "</body>"
           + "</html>";
             //mail.setMailContent(geContentFromTemplate(mail.getModel()));
             mimeMessageHelper.setText(text, true);
             //mimeMessageHelper.addInline("style", style);
             //System.out.println("Image : "+style.getFilename()+" | "+style.getPath()+ "| "+image.exists());
             mimeMessageHelper.addInline("image", image);
             sender.send(mimeMessageHelper.getMimeMessage());
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }

}
