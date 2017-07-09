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

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Autowired
    public EntityManagerFactory entityManagerFactory;
    
    @Bean
	@StepScope
    public FlatFileItemReader<ContactUploadDTO> contactUploadReader(@Value("#{jobParameters[file]}") String path) {
        FlatFileItemReader<ContactUploadDTO> reader = new FlatFileItemReader<ContactUploadDTO>();
        reader.setResource(new FileSystemResource(path));
        reader.setLineMapper(new DefaultLineMapper<ContactUploadDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName","email","group" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ContactUploadDTO>() {{
                setTargetType(ContactUploadDTO.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ContactItemProcessor contactProcessor() {
        ContactItemProcessor processor = new ContactItemProcessor();
        return processor;
    }

    @Bean
    public ContactJPAItemWriter writer() {
        ContactJPAItemWriter writer = new ContactJPAItemWriter();
        return writer;
    }

    
    @Bean(name = "uploadContactJob")
	public Job uploadContactJob() {
			return jobBuilderFactory.get("uploadContactJob").incrementer(new RunIdIncrementer()).flow(step2()).end().build();
	}

	@Bean
	public Step step2(){
		return stepBuilderFactory.get("step2").<ContactUploadDTO, Contact> chunk(5000)
				.reader(contactUploadReader(null))
				.listener(new UploadContactItemReaderListener())
				.processor(contactProcessor())
				.writer(writer())
				.listener(new UploadContactItemWriterListener())
				.build();
	}
}
