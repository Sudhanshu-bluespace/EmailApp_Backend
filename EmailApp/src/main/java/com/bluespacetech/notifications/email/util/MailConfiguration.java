package com.bluespacetech.notifications.email.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sqladmin.SQLAdminScopes;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

	@Value("${mail.protocol}")
	private String protocol;
	@Value("${mail.host}")
	private String host;
	@Value("${mail.port}")
	private int port;
	@Value("${mail.smtp.auth}")
	private boolean auth;
	@Value("${mail.smtp.starttls.enable}")
	private boolean starttls;
	@Value("${mail.from}")
	private String from;
	@Value("${mail.username}")
	private String username;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.smtp.ssl.trust}")
	private String sslTrust;

	@Bean(name = "javaMailSender")
	public JavaMailSender javaMailSender() throws FileNotFoundException, IOException {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		final Properties mailProperties = new Properties();
		//GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("classpath:oAuth-a0631f95a53d.json"))
			//    .createScoped(Collections.singleton(SQLAdminScopes.SQLSERVICE_ADMIN));
		mailProperties.put("mail.smtp.auth", auth);
		mailProperties.put("mail.smtp.starttls.enable", starttls);
		mailProperties.put("mail.smtp.ssl.trust", sslTrust);
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		
		printMailSender(mailSender);
		
		return mailSender;
	}
	
	private void printMailSender(JavaMailSenderImpl mailSender)
	{
		System.out.println("==Email Sender Properties====");
		System.out.println("user: "+mailSender.getUsername());
		System.out.println("port: "+mailSender.getPort());
		System.out.println("host: "+mailSender.getHost());
		System.out.println("protocol: "+mailSender.getProtocol());
		System.out.println("props: "+mailSender.getJavaMailProperties());
	}
	
	
}