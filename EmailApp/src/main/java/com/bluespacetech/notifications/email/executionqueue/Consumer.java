package com.bluespacetech.notifications.email.executionqueue;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
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
import org.springframework.stereotype.Component;

@Component
public class Consumer
  implements Callable<String>
{
  private Job contactGroupEmailJob;
  private Long consumerId;
  private JobProcessingPriorityBlockingQueue blockingQueue;
  private JobLauncher jobLauncher;
  @Autowired
  private JobExecutionRepository jobExecutionRepository;
  private static final Logger LOGGER = LogManager.getLogger(Consumer.class);
  
  @Autowired
  public Consumer(JobProcessingPriorityBlockingQueue blockingQueue, @Qualifier("contactGroupEmailJob") Job job, JobLauncher jobLauncher)
  {
    this.blockingQueue = blockingQueue;
    this.contactGroupEmailJob = job;
    this.jobLauncher = jobLauncher;
  }
  
  public String call()
    throws Exception
  {
    consume();
    return "complete";
  }
  
  private void consume()
    throws InterruptedException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
  {
    List<EmailContactGroupVO> alreadyBlockedEmails = new ArrayList();
    for (;;)
    {
      EmailJobEndpoint endpoint = (EmailJobEndpoint)this.blockingQueue.take();
      LOGGER.info("[Consumer] - endPoint: " + endpoint);
      LOGGER.info("Current Queue Head: " + this.blockingQueue.peek());
      
      Long campaignId = Long.valueOf(0L);
      
      Map<String, JobParameter> jobParametersMap = new HashMap();
      String request_batch_id = endpoint.getRequestId() + "|" + endpoint.getBatchId();
      
      Iterator<EmailContactGroupVO> iterator = endpoint.getEmailContactGroupList().iterator();
      EmailContactGroupVO vo;
      while (iterator.hasNext())
      {
        vo = (EmailContactGroupVO)iterator.next();
        if (campaignId.longValue() == 0L) {
          campaignId = vo.getEmailId();
        }
        for (Map.Entry<String, List<String>> entry : CommonUtilCache.getBouncedEmailCache().entrySet()) {
          if (((List)entry.getValue()).contains(vo.getContactEmail()))
          {
            LOGGER.info("Contact " + vo.getContactEmail() + " is already blacklisted and will not be sent for further processing..");
            
            iterator.remove();
            if (alreadyBlockedEmails.contains(vo.getContactEmail())) {
              break;
            }
            alreadyBlockedEmails.add(vo); break;
          }
        }
      }
      LOGGER.info("Finsihed filtering blacklisted contacts from the job queue.." + alreadyBlockedEmails.size() + " contacts were filtered");
      synchronized (this)
      {
        persistJobExecutionToDB(endpoint.getBatchId(), endpoint.getRequestId(), "PROCESSING", null, "");
      }
      if (!CommonUtilCache.getBatchIdToEmailListMap().containsKey(request_batch_id))
      {
        CommonUtilCache.getBatchIdToEmailListMap().put(request_batch_id, endpoint.getEmailContactGroupList());
        LOGGER.debug("Batch Request Id : " + request_batch_id + " generated successfully");
      }
      jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
      jobParametersMap.put("request_batch_id", new JobParameter(request_batch_id));
      jobParametersMap.put("emailRequestURL", new JobParameter(endpoint.getRequestUrl()));
      LOGGER.debug("Launching email job");
      JobExecution execution = this.jobLauncher.run(this.contactGroupEmailJob, new JobParameters(jobParametersMap));
      LOGGER.info("Job Complete notification for campaign : " + endpoint.getCampaignName() + " | Job Id - " + execution
        .getJobId() + " | Status - " + execution.getExitStatus());
      
      String errors = "";
      if (("FAILED".equalsIgnoreCase(execution.getExitStatus().getExitCode())) || 
        (CommonUtilCache.getRequestIdVsErrorMap().containsKey(request_batch_id)))
      {
        errors = (String)CommonUtilCache.getRequestIdVsErrorMap().get(request_batch_id);
        LOGGER.info("Errors retrieved from Cache for " + request_batch_id + " : " + errors);
        CommonUtilCache.getRequestIdVsErrorMap().remove(request_batch_id);
      }
      synchronized (this)
      {
        LOGGER.info("Persisting job cmompletion records for campaign : " + endpoint.getCampaignName() + ", job ID : " + execution
          .getJobId() + ", batch : " + endpoint.getBatchId() + " to Database from synchronized context");
        
        persistJobExecutionToDB(endpoint.getRequestId(), endpoint.getBatchId(), execution
          .getExitStatus().getExitCode(), execution.getJobId(), errors);
        generateReport(endpoint, alreadyBlockedEmails, execution.getExitStatus().getExitCode(), execution
          .getJobId(), errors);
      }
      if (this.blockingQueue.isEmpty())
      {
        LOGGER.info("Queue is empty.. Consumer has finished processing all Jobs..");
        if (!CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().containsKey(campaignId)) {
          break;
        }
        Set<String> currentMapping = (Set)CommonUtilCache.getAlreadySelectedEmailsForCampaignMap().remove(campaignId);
        LOGGER.info("Cleared already existing cached Email Id Map with " + currentMapping
          .size() + " emails..");
        break;
      }
    }
  }
  
  private void persistJobExecutionToDB(String requestId, String batchId, String exitCode, Long jobId, String comments)
  {
    LOGGER.debug("Updating Job status on completion");
    JobExecutionEntity entity = this.jobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(requestId, batchId);
    if (entity != null)
    {
      entity.setJobId(jobId.toString());
      entity.setStatus(exitCode);
      entity.setComments(comments);
      this.jobExecutionRepository.save(entity);
    }
    if ((jobId != null) && (CommonUtilCache.getBatchIdToEmailListMap().containsKey(requestId + "|" + batchId)))
    {
      CommonUtilCache.getBatchIdToEmailListMap().remove(requestId + "|" + batchId);
      LOGGER.info("Cleared entry of the processed batch " + requestId + "|" + batchId + " from cache..");
      if (CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(requestId + "|" + batchId)) {
        CommonUtilCache.getBatchIdToEmailJobEndpointMap().remove(requestId + "|" + batchId);
      }
    }
  }
  
  private void generateReport(EmailJobEndpoint endpoint, List<EmailContactGroupVO> alreadyBlockedEmails, String status, Long jobId, String comments)
  {
    try
    {
      StringBuilder builder = new StringBuilder();
      if (Files.exists(endpoint.getReportsFilePath(), new LinkOption[0]))
      {
        List<EmailContactGroupVO> list = endpoint.getEmailContactGroupList();
        for (EmailContactGroupVO vo : list)
        {
          String newStatus = getOverriddenStatus(vo.getContactEmail(), status);
          if (newStatus.equalsIgnoreCase("BLOCKED")) {
            comments = "Blacklisted Contact";
          }
          builder.append(jobId).append(",").append(endpoint.getBatchId()).append(",").append(endpoint.getRequestId()).append(",").append(vo.getContactEmail()).append(",").append((vo.getContactFirstName() == null) || (vo.getContactFirstName().trim().isEmpty()) ? "-" : vo.getContactFirstName()).append(",").append((vo.getContactLastName() == null) || (vo.getContactLastName().trim().isEmpty()) ? "-" : vo.getContactLastName()).append(",").append(newStatus).append(",").append((comments == null) || (comments.trim().isEmpty()) ? "Delivery Attempted" : comments);
          builder.append(System.lineSeparator());
        }
        for (EmailContactGroupVO vo : alreadyBlockedEmails)
        {
          builder.append(jobId).append(",").append(endpoint.getBatchId()).append(",").append(endpoint.getRequestId()).append(",").append(vo.getContactEmail()).append(",").append((vo.getContactFirstName() == null) || (vo.getContactFirstName().trim().isEmpty()) ? "-" : vo.getContactFirstName()).append(",").append((vo.getContactLastName() == null) || (vo.getContactLastName().trim().isEmpty()) ? "-" : vo.getContactLastName()).append(",").append("BLOCKED").append(",").append("Already Blacklisted");
          builder.append(System.lineSeparator());
        }
        Files.write(endpoint.getReportsFilePath(), builder.toString().getBytes(), new OpenOption[] { StandardOpenOption.APPEND });
      }
      else
      {
        LOGGER.error("Report file does not exist. Skipping data generation");
      }
    }
    catch (IOException ex)
    {
      LOGGER.error("Failed to write data into report, reason : " + ex.getMessage());
    }
  }
  
  private String getOverriddenStatus(String email, String jobStatus)
  {
    String status = jobStatus;
    synchronized (this)
    {
      for (Map.Entry<String, List<String>> entry : CommonUtilCache.getBouncedEmailCache().entrySet()) {
        if (((List)entry.getValue()).contains(email))
        {
          status = "BLOCKED";
          break;
        }
      }
    }
    return status;
  }
  
  public String toString()
  {
    return "Consumer [consumerId=" + this.consumerId + "]";
  }
}
