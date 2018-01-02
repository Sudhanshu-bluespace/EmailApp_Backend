package com.bluespacetech.notifications.email.util;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.controller.EmailController;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
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

@Component
public class BouncedEmailWatcher
{
  @Autowired
  private JobLauncher jobLauncher;
  @Autowired
  private MailTrackerConfiguration trackerConfig;
  private static final Logger LOGGER = LogManager.getLogger(EmailController.class);
  @Autowired
  @Qualifier("watchBouncedEmail")
  private Job job;
  
  @Scheduled(fixedDelay=120000L)
  public void run()
  {
    LOGGER.info("Executing email tracker job with Tracker config : " + this.trackerConfig);
    Map<String, JobParameter> jobParametersMap = new HashMap();
    try
    {
      jobParametersMap.put("host", new JobParameter(this.trackerConfig.getHost()));
      jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
      jobParametersMap.put("storeType", new JobParameter(this.trackerConfig.getStoreType()));
      jobParametersMap.put("username", new JobParameter(this.trackerConfig.getUsername()));
      jobParametersMap.put("password", new JobParameter(this.trackerConfig.getPassword()));
      jobParametersMap.put("port", new JobParameter(this.trackerConfig.getPort().toString()));
      jobParametersMap.put("storeProtocol", new JobParameter(this.trackerConfig.getStoreProtocol()));
      jobParametersMap.put("starttlsEnabled", new JobParameter(String.valueOf(this.trackerConfig.isStarttls())));
      JobExecution execution = this.jobLauncher.run(this.job, new JobParameters(jobParametersMap));
      
      LOGGER.info("Job status : " + execution.getExitStatus());
      if (execution.getExitStatus().getExitCode().contains("FAILED")) {
        throw new BusinessException("Failed to execute Job");
      }
    }
    catch (BusinessException|JobExecutionAlreadyRunningException|JobRestartException|JobInstanceAlreadyCompleteException|JobParametersInvalidException ex)
    {
      LOGGER.error("Failed to track email : " + ex.getMessage());
    }
  }
}
