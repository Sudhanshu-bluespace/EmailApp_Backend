package com.bluespacetech.contact.bounce;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.service.BlockedContactService;

/**
 * The Class BouncedEmailJobConfiguration.
 */
@Configuration
@EnableBatchProcessing
public class BouncedEmailJobConfiguration
{

    /** The job builder factory. */
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    /** The step builder factory. */
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /** The data source. */
    @Autowired
    public DataSource dataSource;

    /** The entity manager factory. */
    @Autowired
    public EntityManagerFactory entityManagerFactory;

    /** The contact repository. */
    @Autowired
    private ContactRepository contactRepository;

    /** The blocked contact repository. */
    @Autowired
    private BlockedContactService blockedContactService;

    /**
     * Upload contact job.
     *
     * @return the job
     */
    @Bean(name = "watchBouncedEmail")
    public Job watchBouncedEmail()
    {
        return jobBuilderFactory.get("watchBouncedEmail").start(stepBounce()).build();
    }

    /**
     * Step bounce.
     *
     * @return the step
     */
    @Bean
    public Step stepBounce()
    {
        return stepBuilderFactory.get("stepBounce")
                .tasklet(new BouncedEmailReaderTasklet(contactRepository, blockedContactService)).build();
    }
}
