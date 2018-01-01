package com.bluespacetech.notifications.email.batch;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;

/**
 * The listener interface for receiving emailJob events.
 * The class that is interested in processing a emailJob
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEmailJobListener<code> method. When
 * the emailJob event occurs, that object's appropriate
 * method is invoked.
 *
 * @see EmailJobEvent
 */

public class EmailJobListener implements JobExecutionListener
{
    
    /** The Job execution repository. */
    private JobExecutionRepository JobExecutionRepository;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailJobListener.class);
    
    public EmailJobListener(JobExecutionRepository JobExecutionRepository)
    {
        this.JobExecutionRepository = JobExecutionRepository;
    }

    /* (non-Javadoc)
     * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.springframework.batch.core.JobExecution)
     */
    @Override
    public void afterJob(JobExecution exec)
    {
        LOGGER.info("Updating Job status after completion");
        Map<String,JobParameter> params = exec.getJobParameters().getParameters();
        JobParameter param = params.get("request_batch_id");
        String request_batch_id = (String)param.getValue();
        String[] split = request_batch_id.split("\\|");
        JobExecutionEntity entity = JobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(split[0], split[1]);
        if(entity!=null)
        {
            entity.setStatus(exec.getExitStatus().getExitCode());
            JobExecutionRepository.save(entity);
        }
        
        if(CommonUtilCache.getBatchIdToEmailListMap().containsKey(request_batch_id) )
        {
            CommonUtilCache.getBatchIdToEmailListMap().remove(request_batch_id);
            LOGGER.info("Cleared entry of the processed batch from cache..");
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.springframework.batch.core.JobExecution)
     */
    @Override
    public void beforeJob(JobExecution exec)
    {
     
    }
}
