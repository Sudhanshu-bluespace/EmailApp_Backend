package com.bluespacetech.notifications.email.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.util.EmailUtils;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;


public class GroupContactEmailItemProcessor implements ItemProcessor<EmailContactGroupVO, ContactGroupMailMessage> {

	// private static final Logger log =
	// LoggerFactory.getLogger(EmailGroupContactItemProcessor.class);

	private String emailRequestURL;

	private static final Logger LOGGER = LogManager.getLogger(ContactGroupMailMessageItemWriter.class);

	private JavaMailSender mailSender;

	private VelocityEngine velocityEngine;

	@Override
	public ContactGroupMailMessage process(final EmailContactGroupVO emailContactGroupVO) throws Exception {
		
		LOGGER.info("Processing ContactGroupMailMesaage");
		
		final Random randomno = new Random();
		final long value = randomno.nextLong();
		final String unscribeLink = EmailUtils.generateUnscribeLink(emailContactGroupVO, emailRequestURL);
		final String readMailImageSRC = EmailUtils.generateReadMailImageSRC(emailContactGroupVO, emailRequestURL,
				value);

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", emailContactGroupVO.getContactFirstName());
		model.put("emailText", emailContactGroupVO.getMessage());
		model.put("unsubscribe", unscribeLink);
		model.put("readMailImageSRC", readMailImageSRC);
		final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocityTemplates/SimpleEmail.vm", model);

		final ContactGroupMailMessage contactGroupMailMessage = new ContactGroupMailMessage();
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
		simpleMailMessage.setTo(emailContactGroupVO.getContactEmail());
		if(mailSender instanceof JavaMailSenderImpl){
			System.out.println("mail sender found.. : " +mailSender);
			System.out.println(((JavaMailSenderImpl) mailSender).getHost()+" | "+((JavaMailSenderImpl) mailSender).getJavaMailProperties());
			String fromAddress = ((JavaMailSenderImpl) mailSender).getJavaMailProperties().getProperty("mail.from");
			simpleMailMessage.setFrom(fromAddress);
		}

		simpleMailMessage.setSubject(emailContactGroupVO.getSubject());
		simpleMailMessage.setSentDate(new Date());
		simpleMailMessage.setText(text, true);

		final EmailContactGroup emailContactGroup = new EmailContactGroup();
		emailContactGroup.setContactId(emailContactGroupVO.getContactId());
		emailContactGroup.setGroupId(emailContactGroupVO.getGroupId());
		emailContactGroup.setRandomNumber(value);
		emailContactGroup.setReadCount(0);
		if (emailContactGroupVO.getEmailId() != null) {
			emailContactGroup.setEmailId(emailContactGroupVO.getEmailId());
		} else {
			emailContactGroup.setMessage(text);
			emailContactGroup.setSubject(emailContactGroupVO.getSubject());
		}

		LOGGER.info("Setting emailcontactgroup : "+emailContactGroup);
		contactGroupMailMessage.setEmailContactGroup(emailContactGroup);
		contactGroupMailMessage.setMimeMessage(mimeMessage);
		LOGGER.info("Returning processed email message");
		return contactGroupMailMessage;
	}

	/**
	 * @param emailRequestURL
	 *            the emailRequestURL to set
	 */
	public void setEmailRequestURL(String emailRequestURL) {
		this.emailRequestURL = emailRequestURL;
	}

	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}