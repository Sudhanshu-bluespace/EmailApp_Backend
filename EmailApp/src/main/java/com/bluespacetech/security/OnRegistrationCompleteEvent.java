package com.bluespacetech.security;

import com.bluespacetech.security.model.UserAccount;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent
  extends ApplicationEvent
{
  private static final long serialVersionUID = 2153225348726321555L;
  private String appUrl;
  private Locale locale;
  private UserAccount user;
  private boolean isAccountCreatedByAdmin;
  private String requestType;
  
  public OnRegistrationCompleteEvent(UserAccount user, Locale locale, String appUrl, boolean isAccountCreatedByAdmin, String requestType)
  {
    super(user);
    
    this.user = user;
    this.locale = locale;
    this.appUrl = appUrl;
    this.isAccountCreatedByAdmin = isAccountCreatedByAdmin;
    this.requestType = requestType;
  }
  
  public String getAppUrl()
  {
    return this.appUrl;
  }
  
  public Locale getLocale()
  {
    return this.locale;
  }
  
  public UserAccount getUser()
  {
    return this.user;
  }
  
  public boolean isAccountCreatedByAdmin()
  {
    return this.isAccountCreatedByAdmin;
  }
  
  public String getRequestType()
  {
    return this.requestType;
  }
  
  public String toString()
  {
    return "OnRegistrationCompleteEvent [appUrl=" + this.appUrl + ", locale=" + this.locale + ", user=" + this.user + ", isAccountCreatedByAdmin=" + this.isAccountCreatedByAdmin + "]";
  }
}
