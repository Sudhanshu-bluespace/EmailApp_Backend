package com.bluespacetech.notifications.email.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;

@Configuration
//@PropertySource("classpath:mail.properties")
@PropertySource("file:/opt/packages/Oracle/BluespaceMailer/config/mail.properties")
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
	public JavaMailSender javaMailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		final Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", auth);
		mailProperties.put("mail.smtp.starttls.enable", starttls);
		mailProperties.put("mail.smtp.ssl.trust", sslTrust);
		mailProperties.put("mail.from", from);
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);

		mailSender.setUsername(username);
		mailSender.setPassword(password);
		
		System.out.println("Configuration : "+toString());
		return mailSender;
	}
	
	 @Bean
	    public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
	        VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
	        Properties props = new Properties();
	        props.put("resource.loader", "class");
	        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	 
	        velocityEngineFactory.setVelocityProperties(props);
	        return velocityEngineFactory.createVelocityEngine();
	    }

	@Override
	public String toString() {
		return "MailConfiguration [protocol=" + protocol + ", host=" + host + ", port=" + port + ", auth=" + auth
				+ ", starttls=" + starttls + ", from=" + from + ", username=" + username + ", password=" + password
				+ ", sslTrust=" + sslTrust + "]";
	}
	
	
}