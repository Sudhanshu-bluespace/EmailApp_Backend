package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="EMAIL")
public class Email
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 5269234756986410938L;
  @NotEmpty(message="Message is mandatory.")
  @Lob
  @Column(name="TEXT", length=300000)
  private byte[] message;
  @NotEmpty(message="Subject is mandatory.")
  @Column(name="SUBJECT")
  private String subject;
  
  public byte[] getMessage()
  {
    return this.message;
  }
  
  public void setMessage(byte[] message)
  {
    this.message = message;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
}
