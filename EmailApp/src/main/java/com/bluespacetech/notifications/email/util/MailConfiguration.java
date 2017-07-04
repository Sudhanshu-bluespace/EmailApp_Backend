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

/**
 * The Class MailConfiguration.
 * 
 * @author Sudhanshu Tripathy
 */
@Configuration
// @PropertySource("classpath:mail.properties")
@PropertySource("file:/opt/packages/Oracle/BluespaceMailer/config/mail.properties")
public class MailConfiguration
{

    /** The protocol. */
    @Value("${mail.config.protocol}")
    private String mailProtocol;

    /** The host. */
    @Value("${mail.config.host}")
    private String mailHost;

    /** The port. */
    @Value("${mail.config.port}")
    private int mailPort;

    /** The auth. */
    @Value("${mail.config.smtp.auth}")
    private boolean auth;

    /** The starttls. */
    @Value("${mail.config.smtp.starttls.enable}")
    private boolean starttls;

    /** The from. */
    @Value("${mail.config.from}")
    private String mailFrom;

    /** The username. */
    @Value("${mail.config.username}")
    private String mailUsername;

    /** The password. */
    @Value("${mail.config.password}")
    private String mailPassword;

    /** The ssl trust. */
    @Value("${mail.smtp.ssl.trust}")
    private String sslTrust;
    
    /** The debug flag. */
    @Value("${mail.debug}")
    private String mailDebug;
    
    /** The bounce address. */
    @Value("${mail.bounce.to}")
    private String bounceAddress;
    
    @Value("${mail.smtp.dsn.notify}")
    private String feedbackAddress;
    
    @Value("${mail.smtp.dsn.ret}")
    private String feedbackType;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(MailConfiguration.class);

    /**
     * Java mail sender.
     *
     * @return the java mail sender
     */
    @Bean(name = "javaMailSender")
    public JavaMailSender javaMailSender()
    {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        final Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.ssl.trust", sslTrust);
        //mailProperties.put("mail.smtp.from", bounceAddress);
        mailProperties.put("mail.debug", mailDebug);
        mailProperties.put("mail.smtp.dsn.notify", feedbackAddress);
        
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setProtocol(mailProtocol);

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        LOGGER.info("Configuration : " + toString());
        return mailSender;
    }

    /**
     * Gets the velocity engine.
     *
     * @return the velocity engine
     * @throws VelocityException
     *             the velocity exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Bean
    public VelocityEngine getVelocityEngine() throws VelocityException, IOException
    {
        VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngineFactory.setVelocityProperties(props);
        return velocityEngineFactory.createVelocityEngine();
    }

    public String getMailFrom()
    {
        return mailFrom;
    }

    @Override
    public String toString()
    {
        return "MailConfiguration [mailProtocol=" + mailProtocol + ", mailHost=" + mailHost + ", mailPort=" + mailPort
                + ", auth=" + auth + ", starttls=" + starttls + ", mailFrom=" + mailFrom + ", mailUsername="
                + mailUsername + ", mailPassword=" + mailPassword + ", sslTrust=" + sslTrust + ", mailDebug="
                + mailDebug + ", bounceAddress=" + bounceAddress + ", feedbackAddress=" + feedbackAddress
                + ", feedbackType=" + feedbackType + "]";
    }
    

    
    

}