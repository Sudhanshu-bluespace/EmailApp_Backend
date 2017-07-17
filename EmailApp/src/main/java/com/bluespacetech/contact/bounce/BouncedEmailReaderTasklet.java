package com.bluespacetech.contact.bounce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.service.BlockedContactService;

public class BouncedEmailReaderTasklet implements Tasklet
{
    private ContactRepository contactRepository;
    private BlockedContactService blockedContactService;
    private static final Logger LOGGER = LogManager.getLogger(BouncedEmailReaderTasklet.class);
    
    public BouncedEmailReaderTasklet(ContactRepository contactRepository, BlockedContactService blockedContactService)
    {
        this.contactRepository = contactRepository;
        this.blockedContactService = blockedContactService;
    }
   

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception
    {
        LOGGER.info("Executing job to scan bunced emails");
        
        String host = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("host");
        String storeType = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("storeType");
        String storeProtocol = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("storeProtocol");
        String port = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("port");
        String user = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("username");
        String password = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("password");
        String startTlsEnabled = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("starttlsEnabled");
        new MailChecker(contactRepository,blockedContactService).check(host, storeType, user, password,port,storeProtocol,startTlsEnabled);
        return RepeatStatus.FINISHED;
    }
}