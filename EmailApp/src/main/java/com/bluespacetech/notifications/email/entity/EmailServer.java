package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="EMAIL_SERVER")
public class EmailServer
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 4111075785941540894L;
  @NotEmpty(message="Name is mandatory.")
  @Column(name="NAME", unique=true)
  private String name;
  @NotEmpty(message="Protocol is mandatory.")
  @Column(name="PROTOCOL")
  private String protocol;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="HOST")
  private String host;
  @NotEmpty(message="Port is mandatory.")
  @Column(name="PORT")
  private String port;
  @Column(name="MAILS_PER_SESSION")
  private Integer mailsPerSession;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="FROM_ADDRESS")
  private String fromAddress;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="EMAIL_USERNAME")
  private String emailUsername;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="EMAIL_PASSWORD")
  private String emailPassword;
  @Column(name="DEFAULT_SERVER")
  private Boolean defaultServer;
  
  public String getEmailUsername()
  {
    return this.emailUsername;
  }
  
  public void setEmailUsername(String emailUsername)
  {
    this.emailUsername = emailUsername;
  }
  
  public String getEmailPassword()
  {
    return this.emailPassword;
  }
  
  public void setEmailPassword(String emailPassword)
  {
    this.emailPassword = emailPassword;
  }
  
  public String getProtocol()
  {
    return this.protocol;
  }
  
  public void setProtocol(String protocol)
  {
    this.protocol = protocol;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public void setHost(String host)
  {
    this.host = host;
  }
  
  public String getPort()
  {
    return this.port;
  }
  
  public void setPort(String port)
  {
    this.port = port;
  }
  
  public String getFromAddress()
  {
    return this.fromAddress;
  }
  
  public void setFromAddress(String fromAddress)
  {
    this.fromAddress = fromAddress;
  }
  
  public Integer getMailsPerSession()
  {
    return this.mailsPerSession;
  }
  
  public void setMailsPerSession(Integer mailsPerSession)
  {
    this.mailsPerSession = mailsPerSession;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Boolean getDefaultServer()
  {
    return this.defaultServer;
  }
  
  public void setDefaultServer(Boolean defaultServer)
  {
    this.defaultServer = defaultServer;
  }
}
