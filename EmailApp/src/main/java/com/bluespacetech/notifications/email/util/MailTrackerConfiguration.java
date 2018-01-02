package com.bluespacetech.notifications.email.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"file:/opt/packages/Oracle/BluespaceMailer/config/mail_tracker.properties"})
public class MailTrackerConfiguration
{
  private static final Logger LOGGER = LogManager.getLogger(MailTrackerConfiguration.class);
  @Value("${mail.protocol}")
  private String protocol;
  @Value("${mail.store.protocol}")
  private String storeProtocol;
  @Value("${mail.pop3.host}")
  private String host;
  @Value("${mail.pop3.port}")
  private Integer port;
  @Value("${mail.pop3.starttls.enable}")
  private boolean starttls;
  @Value("${mail.pop3.auth}")
  private String auth;
  @Value("${mail.username}")
  private String username;
  @Value("${mail.password}")
  private String password;
  @Value("${storeType}")
  private String storeType;
  @Value("${mail.debug}")
  private String mailDebug;
  
  public String getProtocol()
  {
    return this.protocol;
  }
  
  public String getStoreProtocol()
  {
    return this.storeProtocol;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public Integer getPort()
  {
    return this.port;
  }
  
  public boolean isStarttls()
  {
    return this.starttls;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getStoreType()
  {
    return this.storeType;
  }
  
  public String getMailDebug()
  {
    return this.mailDebug;
  }
  
  public String getAuth()
  {
    return this.auth;
  }
  
  public void logMailTrackerConfig()
  {
    LOGGER.info(toString());
  }
  
  public String toString()
  {
    return "MailTrackerConfiguration [protocol=" + this.protocol + ", storeProtocol=" + this.storeProtocol + ", host=" + this.host + ", port=" + this.port + ", starttls=" + this.starttls + ", auth=" + this.auth + ", username=" + this.username + ", password=" + this.password + ", storeType=" + this.storeType + ", mailDebug=" + this.mailDebug + "]";
  }
}
