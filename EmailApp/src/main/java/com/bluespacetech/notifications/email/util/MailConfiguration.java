package com.bluespacetech.notifications.email.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    @Value("${mail.protocol}")
    private String protocol;

    /** The host. */
    @Value("${mail.host}")
    private String host;

    /** The port. */
    @Value("${mail.port}")
    private int port;

    /** The auth. */
    @Value("${mail.smtp.auth}")
    private boolean auth;

    /** The starttls. */
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;

    /** The from. */
    @Value("${mail.from}")
    private String from;

    /** The username. */
    @Value("${mail.username}")
    private String username;

    /** The password. */
    @Value("${mail.password}")
    private String password;

    /** The ssl trust. */
    @Value("${mail.smtp.ssl.trust}")
    private String sslTrust;

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
        mailProperties.put("mail.from", from);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "MailConfiguration [protocol=" + protocol + ", host=" + host + ", port=" + port + ", auth=" + auth
                + ", starttls=" + starttls + ", from=" + from + ", username=" + username + ", password=" + password
                + ", sslTrust=" + sslTrust + "]";
    }

}