package com.bluespacetech.contact.bounce;

import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.service.BlockedContactService;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BouncedEmailJobConfiguration
{
  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  public StepBuilderFactory stepBuilderFactory;
  @Autowired
  public DataSource dataSource;
  @Autowired
  public EntityManagerFactory entityManagerFactory;
  @Autowired
  private ContactRepository contactRepository;
  @Autowired
  private BlockedContactService blockedContactService;
  
  @Bean(name={"watchBouncedEmail"})
  public Job watchBouncedEmail()
  {
    return this.jobBuilderFactory.get("watchBouncedEmail").start(stepBounce()).build();
  }
  
  @Bean
  public Step stepBounce()
  {
    return this.stepBuilderFactory.get("stepBounce").tasklet(new BouncedEmailReaderTasklet(this.contactRepository, this.blockedContactService)).build();
  }
}
