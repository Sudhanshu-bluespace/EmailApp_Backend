package com.bluespacetech.notifications.email.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.controller.EmailController;

@Component
public class BouncedEmailWatcher
{
    /** The job launcher. */
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private MailTrackerConfiguration trackerConfig;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailController.class);

    /** The job. */
    @Autowired
    @Qualifier("watchBouncedEmail")
    private Job job;
    
    @Scheduled(fixedDelay = 240000)
    public void run()
    {
       LOGGER.info("Executing email tracker job with Tracker config : "+trackerConfig);
       final Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
       try
       {
           jobParametersMap.put("host", new JobParameter(trackerConfig.getHost()));
           jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
           jobParametersMap.put("storeType", new JobParameter(trackerConfig.getStoreType()));
           jobParametersMap.put("username", new JobParameter(trackerConfig.getUsername()));
           jobParametersMap.put("password", new JobParameter(trackerConfig.getPassword()));
           jobParametersMap.put("port", new JobParameter(trackerConfig.getPort().toString()));
           jobParametersMap.put("storeProtocol", new JobParameter(trackerConfig.getStoreProtocol()));
           jobParametersMap.put("starttlsEnabled", new JobParameter(String.valueOf(trackerConfig.isStarttls())));
           JobExecution execution = jobLauncher.run(job, new JobParameters(jobParametersMap));

           LOGGER.info("Job status : " + execution.getExitStatus());
           if (execution.getExitStatus().getExitCode().contains("FAILED"))
           {
               throw new BusinessException("Failed to execute Job");
           }
       }catch(BusinessException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex)
       {
          LOGGER.error("Failed to track email : "+ex.getMessage());
       }
    }
}
