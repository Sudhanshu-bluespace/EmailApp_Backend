package com.bluespacetech.security.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken
{
  private static final int EXPIRATION = 1440;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String token;
  @OneToOne(targetEntity=UserAccount.class, fetch=FetchType.EAGER)
  @JoinColumn(nullable=false, name="username")
  private UserAccount user;
  private Date expiryDate;
  
  private Date calculateExpiryDate(int expiryTimeInMinutes)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(12, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }
  
  public VerificationToken(String token, UserAccount user)
  {
    this.token = token;
    this.user = user;
    this.expiryDate = calculateExpiryDate(1440);
  }
  
  public VerificationToken() {}
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getToken()
  {
    return this.token;
  }
  
  public UserAccount getUser()
  {
    return this.user;
  }
  
  public Date getExpiryDate()
  {
    return this.expiryDate;
  }
  
  public void setExpiryDate(Date expiryDate)
  {
    this.expiryDate = expiryDate;
  }
}
