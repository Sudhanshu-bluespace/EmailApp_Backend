package com.bluespacetech;

import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.executionqueue.JobProcessingPriorityBlockingQueue;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailAppApplication
  extends SpringBootServletInitializer
{
  private static final Logger LOGGER = LogManager.getLogger(EmailAppApplication.class);
  @Autowired
  MailTemplateConfiguration templateConfiguration;
  
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
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
  {
    return application.sources(new Class[] { EmailAppApplication.class });
  }
}
