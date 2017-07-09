package com.bluespacetech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.executionqueue.JobProcessingPriorityBlockingQueue;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;

/**
 * The Class EmailAppApplication.
 * 
 * @author Sudhanshu Tripathy
 */
@SpringBootApplication
@EnableScheduling
public class EmailAppApplication extends SpringBootServletInitializer
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailAppApplication.class);
    
    @Autowired
    MailTemplateConfiguration templateConfiguration; 

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args)
    {
        LOGGER.info("Starting application");
        SpringApplication.run(EmailAppApplication.class, args);
    }
    
    @Bean
    public JobProcessingPriorityBlockingQueue blockingQueue()
    {
        return JobProcessingPriorityBlockingQueue.getQueueInstance();
    }
    
    @Bean
    public EmailJobEndpoint jobEndPoint()
    {
        return new EmailJobEndpoint();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.
     * SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(EmailAppApplication.class);
    }
}
