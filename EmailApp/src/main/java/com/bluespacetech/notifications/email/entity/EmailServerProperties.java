package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.notifications.email.util.EmailServerPropertyValueTypeConstant;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="EMAIL_SERVER_PROPERTIES")
public class EmailServerProperties
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -8068866936838039346L;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="NAME")
  private String propertyName;
  @NotEmpty(message="Host is mandatory.")
  @Column(name="VALUE")
  private String value;
  @NotNull(message="Email server mandatory.")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="EMAIL_SERVER_ID", nullable=false)
  private EmailServer emailServer;
  @NotNull(message="Email server property value type is mandatory.")
  @Basic
  @Column(name="BRANCH_TYPE", length=50, nullable=false)
  @Enumerated(EnumType.STRING)
  private EmailServerPropertyValueTypeConstant emailServerPropertyValueTypeConstant;
  
  public String getPropertyName()
  {
    return this.propertyName;
  }
  
  public void setPropertyName(String propertyName)
  {
    this.propertyName = propertyName;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public EmailServer getEmailServer()
  {
    return this.emailServer;
  }
  
  public void setEmailServer(EmailServer emailServer)
  {
    this.emailServer = emailServer;
  }
  
  public EmailServerPropertyValueTypeConstant getEmailServerPropertyValueTypeConstant()
  {
    return this.emailServerPropertyValueTypeConstant;
  }
  
  public void setEmailServerPropertyValueTypeConstant(EmailServerPropertyValueTypeConstant emailServerPropertyValueTypeConstant)
  {
    this.emailServerPropertyValueTypeConstant = emailServerPropertyValueTypeConstant;
  }
}
