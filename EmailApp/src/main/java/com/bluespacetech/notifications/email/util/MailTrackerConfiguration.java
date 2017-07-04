package com.bluespacetech.notifications.email.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:/opt/packages/Oracle/BluespaceMailer/config/mail_tracker.properties")
public class MailTrackerConfiguration
{

    private static final Logger LOGGER = LogManager.getLogger(MailTrackerConfiguration.class);

    /** The protocol. */
    @Value("${mail.protocol}")
    private String protocol;
    
    /** The protocol. */
    @Value("${mail.store.protocol}")
    private String storeProtocol;

    /** The host. */
    @Value("${mail.pop3.host}")
    private String host;

    /** The port. */
    @Value("${mail.pop3.port}")
    private Integer port;

    /** The starttls. */
    @Value("${mail.pop3.starttls.enable}")
    private boolean starttls;
    
    @Value("${mail.pop3.auth}")
    private String auth;

    /** The username. */
    @Value("${mail.username}")
    private String username;

    /** The password. */
    @Value("${mail.password}")
    private String password;

    /** The ssl trust. */
    @Value("${storeType}")
    private String storeType;
    
    /** The debug flag. */
    @Value("${mail.debug}")
    private String mailDebug;
    

    public String getProtocol()
    {
        return protocol;
    }


    public String getStoreProtocol()
    {
        return storeProtocol;
    }


    public String getHost()
    {
        return host;
    }


    public Integer getPort()
    {
        return port;
    }


    public boolean isStarttls()
    {
        return starttls;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public String getStoreType()
    {
        return storeType;
    }


    public String getMailDebug()
    {
        return mailDebug;
    }

    public String getAuth()
    {
        return auth;
    }
    
    public void logMailTrackerConfig()
    {
        LOGGER.info(toString());
    }


    @Override
    public String toString()
    {
        return "MailTrackerConfiguration [protocol=" + protocol + ", storeProtocol=" + storeProtocol + ", host=" + host
                + ", port=" + port + ", starttls=" + starttls + ", auth=" + auth + ", username=" + username
                + ", password=" + password + ", storeType=" + storeType + ", mailDebug=" + mailDebug + "]";
    }
}
