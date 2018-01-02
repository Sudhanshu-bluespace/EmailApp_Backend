package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;

public class EmailJobListener
  implements JobExecutionListener
{
  private JobExecutionRepository JobExecutionRepository;
  private static final Logger LOGGER = LogManager.getLogger(EmailJobListener.class);
  
  public EmailJobListener(JobExecutionRepository JobExecutionRepository)
  {
    this.JobExecutionRepository = JobExecutionRepository;
  }
  
  public void afterJob(JobExecution exec)
  {
    LOGGER.info("Updating Job status after completion");
    Map<String, JobParameter> params = exec.getJobParameters().getParameters();
    JobParameter param = (JobParameter)params.get("request_batch_id");
    String request_batch_id = (String)param.getValue();
    String[] split = request_batch_id.split("\\|");
    JobExecutionEntity entity = this.JobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(split[0], split[1]);
    if (entity != null)
    {
      entity.setStatus(exec.getExitStatus().getExitCode());
      this.JobExecutionRepository.save(entity);
    }
    if (CommonUtilCache.getBatchIdToEmailListMap().containsKey(request_batch_id))
    {
      CommonUtilCache.getBatchIdToEmailListMap().remove(request_batch_id);
      LOGGER.info("Cleared entry of the processed batch from cache..");
    }
  }
  
  public void beforeJob(JobExecution exec) {}
}
