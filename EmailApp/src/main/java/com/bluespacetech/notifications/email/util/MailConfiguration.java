package com.bluespacetech.notifications.email.util;

import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@PropertySource({"file:/opt/packages/Oracle/BluespaceMailer/config/mail.properties"})
public class MailConfiguration
{
  @Value("${mail.config.protocol}")
  private String mailProtocol;
  @Value("${mail.config.host}")
  private String mailHost;
  @Value("${mail.config.port}")
  private int mailPort;
  @Value("${mail.config.smtp.auth}")
  private boolean auth;
  @Value("${mail.config.smtp.starttls.enable}")
  private boolean starttls;
  @Value("${mail.config.username}")
  private String mailUsername;
  @Value("${mail.config.password}")
  private String mailPassword;
  @Value("${mail.smtp.ssl.trust}")
  private String sslTrust;
  @Value("${mail.debug}")
  private String mailDebug;
  @Value("${mail.bounce.to}")
  private String bounceAddress;
  private static final Logger LOGGER = LogManager.getLogger(MailConfiguration.class);
  
  @Bean(name={"javaMailSender"})
  public JavaMailSender javaMailSender()
  {
    final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    
    Properties mailProperties = new Properties();
    mailProperties.put("mail.smtp.auth", Boolean.valueOf(this.auth));
    mailProperties.put("mail.smtp.starttls.enable", Boolean.valueOf(this.starttls));
    mailProperties.put("mail.smtp.ssl.trust", this.sslTrust);
    mailProperties.put("mail.smtp.from", this.bounceAddress);
    mailProperties.put("mail.debug", this.mailDebug);
    
    mailSender.setJavaMailProperties(mailProperties);
    mailSender.setHost(this.mailHost);
    mailSender.setPort(this.mailPort);
    mailSender.setProtocol(this.mailProtocol);
    
    mailSender.setUsername(this.mailUsername);
    mailSender.setPassword(this.mailPassword);
    
    LOGGER.info("Email Configuration : " + mailSender.getJavaMailProperties());
    return mailSender;
  }
  
  @Bean
  public VelocityEngine getVelocityEngine()
    throws VelocityException, IOException
  {
    VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
    Properties props = new Properties();
    props.put("resource.loader", "class");
    props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    
    velocityEngineFactory.setVelocityProperties(props);
    return velocityEngineFactory.createVelocityEngine();
  }
  
  public String getMailProtocol()
  {
    return this.mailProtocol;
  }
  
  public void setMailProtocol(String mailProtocol)
  {
    this.mailProtocol = mailProtocol;
  }
  
  public String getMailHost()
  {
    return this.mailHost;
  }
  
  public void setMailHost(String mailHost)
  {
    this.mailHost = mailHost;
  }
  
  public int getMailPort()
  {
    return this.mailPort;
  }
  
  public void setMailPort(int mailPort)
  {
    this.mailPort = mailPort;
  }
  
  public boolean isAuth()
  {
    return this.auth;
  }
  
  public void setAuth(boolean auth)
  {
    this.auth = auth;
  }
  
  public boolean isStarttls()
  {
    return this.starttls;
  }
  
  public void setStarttls(boolean starttls)
  {
    this.starttls = starttls;
  }
  
  public String getMailUsername()
  {
    return this.mailUsername;
  }
  
  public void setMailUsername(String mailUsername)
  {
    this.mailUsername = mailUsername;
  }
  
  public String getMailPassword()
  {
    return this.mailPassword;
  }
  
  public void setMailPassword(String mailPassword)
  {
    this.mailPassword = mailPassword;
  }
  
  public String getSslTrust()
  {
    return this.sslTrust;
  }
  
  public void setSslTrust(String sslTrust)
  {
    this.sslTrust = sslTrust;
  }
  
  public String getMailDebug()
  {
    return this.mailDebug;
  }
  
  public void setMailDebug(String mailDebug)
  {
    this.mailDebug = mailDebug;
  }
  
  public String getBounceAddress()
  {
    return this.bounceAddress;
  }
  
  public void setBounceAddress(String bounceAddress)
  {
    this.bounceAddress = bounceAddress;
  }
  
  public String toString()
  {
    return "MailConfiguration [mailProtocol=" + this.mailProtocol + ", mailHost=" + this.mailHost + ", mailPort=" + this.mailPort + ", auth=" + this.auth + ", starttls=" + this.starttls + " mailUsername=" + this.mailUsername + ", mailPassword=" + this.mailPassword + ", sslTrust=" + this.sslTrust + ", mailDebug=" + this.mailDebug + ", fromAddress=" + this.bounceAddress + "]";
  }
}
