package com.bluespacetech.contact.fileupload.batch;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.batch.listeners.UploadContactItemReaderListener;
import com.bluespacetech.contact.fileupload.batch.listeners.UploadContactItemWriterListener;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contact.service.BlockedContactService;

// TODO: Auto-generated Javadoc
/**
 * The Class BatchConfiguration.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration
{

    /** The job builder factory. */
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    /** The step builder factory. */
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /** The blocked contact service. */
    @Autowired
    private BlockedContactService blockedContactService;

    /** The data source. */
    @Autowired
    public DataSource dataSource;

    /** The entity manager factory. */
    @Autowired
    public EntityManagerFactory entityManagerFactory;

    /**
     * Contact upload reader.
     *
     * @param path the path
     * @return the flat file item reader
     */
    @Bean
    @StepScope
    public FlatFileItemReader<ContactUploadDTO> contactUploadReader(@Value("#{jobParameters[file]}") String path)
    {
        FlatFileItemReader<ContactUploadDTO> reader = new FlatFileItemReader<ContactUploadDTO>();
        reader.setResource(new FileSystemResource(path));
        reader.setLineMapper(new DefaultLineMapper<ContactUploadDTO>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "firstName", "lastName", "email", "group" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<ContactUploadDTO>() {
                    {
                        setTargetType(ContactUploadDTO.class);
                    }
                });
            }
        });
        return reader;
    }

    /**
     * Contact processor.
     *
     * @return the contact item processor
     */
    @Bean
    public ContactItemProcessor contactProcessor()
    {
        ContactItemProcessor processor = new ContactItemProcessor(blockedContactService);
        return processor;
    }

    /**
     * Writer.
     *
     * @return the contact JPA item writer
     */
    @Bean
    public ContactJPAItemWriter writer()
    {
        ContactJPAItemWriter writer = new ContactJPAItemWriter();
        return writer;
    }

    /**
     * Upload contact job.
     *
     * @return the job
     */
    @Bean(name = "uploadContactJob")
    public Job uploadContactJob()
    {
        return jobBuilderFactory.get("uploadContactJob").incrementer(new RunIdIncrementer()).flow(step2()).end()
                .build();
    }

    /**
     * Step 2.
     *
     * @return the step
     */
    @Bean
    public Step step2()
    {
        return stepBuilderFactory.get("step2").<ContactUploadDTO, Contact> chunk(5000).reader(contactUploadReader(null))
                .listener(new UploadContactItemReaderListener()).processor(contactProcessor()).writer(writer())
                .listener(new UploadContactItemWriterListener()).build();
    }
}
