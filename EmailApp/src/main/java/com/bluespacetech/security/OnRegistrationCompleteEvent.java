package com.bluespacetech.security;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.bluespacetech.security.model.UserAccount;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2153225348726321555L;
	private String appUrl;
    private Locale locale;
    private UserAccount user;
 
    public OnRegistrationCompleteEvent(
      UserAccount user, Locale locale, String appUrl) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public UserAccount getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "OnRegistrationCompleteEvent [appUrl=" + appUrl + ", locale=" + locale + ", user=" + user + "]";
	}
     
    
    
}
