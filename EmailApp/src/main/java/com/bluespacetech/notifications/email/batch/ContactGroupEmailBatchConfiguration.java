package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactGroupEmailBatchConfiguration.
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

    /** The email server service. */
    @Autowired
    private EmailServerService emailServerService;

    /** The email server properties service. */
    @Autowired
    private EmailServerPropertiesService emailServerPropertiesService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactGroupEmailBatchConfiguration.class);

    /** The data source. */
    @Autowired
    public DataSource dataSource;

    /**
     * Email contact group item reader.
     *
     * @param request_batch_id the request batch id
     * @return the item reader
     */
    @Bean
    @StepScope
    public ItemReader<EmailContactGroupVO> emailContactGroupItemReader(
            @Value("#{jobParameters[request_batch_id]}") String request_batch_id)
    {
        return new EmailContactGroupItemReader(CommonUtilCache.getBatchIdToEmailListMap().get(request_batch_id));
    }

    /**
     * Simple email writer.
     *
     * @param emailContactGroupService the email contact group service
     * @return the item writer
     */
    @Bean
    public ItemWriter<ContactGroupMailMessage> simpleEmailWriter(EmailContactGroupService emailContactGroupService)
    {
        ContactGroupMailMessageItemWriter writer = new ContactGroupMailMessageItemWriter();

        writer.setMailSender(this.javaMailSender);
        writer.setEmailContactGroupService(emailContactGroupService);
        writer.setEmailServerService(this.emailServerService);
        writer.setEmailServerPropertiesService(this.emailServerPropertiesService);
        return writer;
    }

    /**
     * Listener.
     *
     * @param request_batch_id the request batch id
     * @return the item write listener
     */
    @Bean
    @StepScope
    public ItemWriteListener<ContactGroupMailMessage> listener(
            @Value("#{jobParameters[request_batch_id]}") String request_batch_id)
    {
        EmailWriterListener listener = new EmailWriterListener(request_batch_id);
        return listener;
    }

    /**
     * Processor.
     *
     * @param emailRequestURL the email request URL
     * @param request_batch_id the request batch id
     * @return the group contact email item processor
     */
    @Bean
    @StepScope
    public GroupContactEmailItemProcessor processor(@Value("#{jobParameters[emailRequestURL]}") String emailRequestURL,
            @Value("#{jobParameters[request_batch_id]}") String request_batch_id)
    {
        GroupContactEmailItemProcessor processor = new GroupContactEmailItemProcessor();
        processor.setEmailRequestURL(emailRequestURL);
        processor.setMailSender(this.javaMailSender);
        processor.setRequestBatchId(request_batch_id);
        return processor;
    }

    /**
     * Contact group email job.
     *
     * @return the job
     */
    @Bean(name = { "contactGroupEmailJob" })
    public Job contactGroupEmailJob()
    {
        LOGGER.debug("Triggering Job (contactGroupEmailJob) from Contact Group Batch Config");

        return ((FlowJobBuilder) ((JobBuilder) this.jobBuilderFactory.get("contactGroupEmailJob")
                .incrementer(new RunIdIncrementer())).flow(step1()).end()).build();
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

        return this.stepBuilderFactory.get("step1").<EmailContactGroupVO, ContactGroupMailMessage> chunk(50)
                .reader(emailContactGroupItemReader(null))
                .processor(processor(null, null))
                .writer(simpleEmailWriter(this.emailContactGroupService)).listener(listener(null)).build();
    }
}
