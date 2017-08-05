package com.bluespacetech.notifications.email.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ContactGroupEmailBatchConfiguration.
 * @author Sudhanshu Tripathy
 */
@Configuration
@EnableBatchProcessing
public class ContactGroupEmailBatchConfiguration
{

    /** The job builder factory. */
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    /** The step builder factory. */
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /** The email contact group service. */
    @Autowired
    public EmailContactGroupService emailContactGroupService;
    
    /** The java mail sender. */
    @Autowired
    public JavaMailSender javaMailSender;
    
    @Autowired
    private EmailServerService emailServerService;
    
    @Autowired
    private EmailServerPropertiesService emailServerPropertiesService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactGroupEmailBatchConfiguration.class);

    /** The data source. */
    @Autowired
    public DataSource dataSource;

    /** The query find contacts. */
   // private static String QUERY_FIND_CONTACTS = "SELECT FIRST_NAME, LAST_NAME, EMAIL, GROUP_ID, CONTACT_ID FROM CONTACTS "
     //       + "C, CONTACT_GROUP CG WHERE CG.CONTACT_ID = C.ID AND CG.UNSUBSCRIBED = 0";

    /**
     * Database item reader.
     *
     * @param dataSource the data source
     * @param groupId the group id
     * @param emailId the email id
     * @param message the message
     * @param subject the subject
     * @return the jdbc cursor item reader
     */
  /*  @Bean
    @StepScope
    JdbcCursorItemReader<EmailContactGroupVO> databaseItemReader(DataSource dataSource,
            @Value("#{jobParameters[groupId]}") Long groupId, @Value("#{jobParameters[emailId]}") Long emailId,
            @Value("#{jobParameters[message]}") String message, @Value("#{jobParameters[subject]}") String subject)
    {
        final JdbcCursorItemReader<EmailContactGroupVO> databaseReader = new JdbcCursorItemReader<EmailContactGroupVO>();
        databaseReader.setDataSource(dataSource);
        final ContactGroupEmailRowMapper emailContactGroupRowMapper = new ContactGroupEmailRowMapper();
        emailContactGroupRowMapper.setMessage(message);
        emailContactGroupRowMapper.setSubject(subject);
        if (emailId != null)
        {
            emailContactGroupRowMapper.setEmailId(emailId);
        }
        databaseReader.setRowMapper(emailContactGroupRowMapper);
        databaseReader.setSql(QUERY_FIND_CONTACTS);
        QUERY_FIND_CONTACTS = QUERY_FIND_CONTACTS + " AND CG.GROUP_ID = " + groupId;

        LOGGER.info("Returning read object from : " + QUERY_FIND_CONTACTS);
        return databaseReader;
    }*/
    
    @Bean
    @StepScope
    public ItemReader<EmailContactGroupVO> emailContactGroupItemReader(@Value("#{jobParameters[request_batch_id]}") String request_batch_id)
    {
        //LOGGER.info("Inside my custom reader for batch request id : "+request_batch_id);
        return new EmailContactGroupItemReader(CommonUtilCache.getBatchIdToEmailListMap().get(request_batch_id));
    }

    /**
     * Simple email writer.
     *
     * @param emailContactGroupService the email contact group service
     * @return the item writer
     */
    @Bean
    public ItemWriter<ContactGroupMailMessage> simpleEmailWriter(
            final EmailContactGroupService emailContactGroupService)
    {
        final ContactGroupMailMessageItemWriter writer = new ContactGroupMailMessageItemWriter();

        writer.setMailSender(javaMailSender);
        writer.setEmailContactGroupService(emailContactGroupService);
        writer.setEmailServerService(emailServerService);
        writer.setEmailServerPropertiesService(emailServerPropertiesService);
        return writer;
    }
    
    @Bean
    @StepScope
    public ItemWriteListener<ContactGroupMailMessage> listener(@Value("#{jobParameters[request_batch_id]}") String request_batch_id)
    {
        final EmailWriterListener listener = new EmailWriterListener(request_batch_id);
        return listener;
    }

    /**
     * Processor.
     *
     * @param emailRequestURL the email request URL
     * @return the group contact email item processor
     */
    @Bean
    @StepScope
    public GroupContactEmailItemProcessor processor(@Value("#{jobParameters[emailRequestURL]}") String emailRequestURL)
    {
        final GroupContactEmailItemProcessor processor = new GroupContactEmailItemProcessor();
        processor.setEmailRequestURL(emailRequestURL);
        processor.setMailSender(javaMailSender);
        return processor;
    }

    /**
     * Group email job.
     *
     * @return the job
     */
    @Bean(name = "contactGroupEmailJob")
    public Job contactGroupEmailJob()
    {
        LOGGER.debug("Triggering Job (contactGroupEmailJob) from Contact Group Batch Config");
        return jobBuilderFactory.get("contactGroupEmailJob").incrementer(new RunIdIncrementer()).flow(step1()).end()
                .build();
    }

    /**
     * Step 1.
     *
     * @return the step
     */
    @Bean
    public Step step1()
    {
        LOGGER.debug("Executing Batch Job for email processing");
        return stepBuilderFactory.get("step1").<EmailContactGroupVO, ContactGroupMailMessage> chunk(50)
                .reader(emailContactGroupItemReader(null))
                .processor(processor(null))
                .writer(simpleEmailWriter(emailContactGroupService))
                .listener(listener(null))
                .build();
    }
}
