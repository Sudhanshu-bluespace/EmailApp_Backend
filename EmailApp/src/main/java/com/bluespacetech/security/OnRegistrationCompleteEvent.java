package com.bluespacetech.security;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.bluespacetech.security.model.UserAccount;

/**
 * The Class OnRegistrationCompleteEvent.
 * @author Sudhanshu Tripathy
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2153225348726321555L;

    /** The app url. */
    private String appUrl;

    /** The locale. */
    private Locale locale;

    /** The user. */
    private UserAccount user;

    /** The is account created by admin. */
    private boolean isAccountCreatedByAdmin;

    /**
     * Instantiates a new on registration complete event.
     *
     * @param user the user
     * @param locale the locale
     * @param appUrl the app url
     * @param isAccountCreatedByAdmin the is account created by admin
     */
    public OnRegistrationCompleteEvent(UserAccount user, Locale locale, String appUrl, boolean isAccountCreatedByAdmin)
    {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.isAccountCreatedByAdmin = isAccountCreatedByAdmin;
    }

    /**
     * Gets the app url.
     *
     * @return the app url
     */
    public String getAppUrl()
    {
        return appUrl;
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public UserAccount getUser()
    {
        return user;
    }

    /**
     * Checks if is account created by admin.
     *
     * @return true, if is account created by admin
     */
    public boolean isAccountCreatedByAdmin()
    {
        return isAccountCreatedByAdmin;
    }

    /*
     * (non-Javadoc)
     * @see java.util.EventObject#toString()
     */
    @Override
    public String toString()
    {
        return "OnRegistrationCompleteEvent [appUrl=" + appUrl + ", locale=" + locale + ", user=" + user
                + ", isAccountCreatedByAdmin=" + isAccountCreatedByAdmin + "]";
    }

}
