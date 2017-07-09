package com.bluespacetech.notifications.email.executionqueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

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
import org.springframework.stereotype.Component;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */

@Component
public class Consumer implements Callable<String>
{
    
    /** The contact group email job. */
    private Job contactGroupEmailJob;

    /** The consumer id. */
    private Long consumerId;

    /** The blocking queue. */
    private JobProcessingPriorityBlockingQueue blockingQueue;
    
    /** The job launcher. */
    private JobLauncher jobLauncher;

    /** The job execution repository. */
    @Autowired
    private JobExecutionRepository jobExecutionRepository;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(Consumer.class);

    /**
     * Instantiates a new consumer.
     *
     * @param blockingQueue the blocking queue
     * @param job the job
     * @param jobLauncher the job launcher
     */
    @Autowired
    public Consumer(JobProcessingPriorityBlockingQueue blockingQueue, @Qualifier("contactGroupEmailJob") Job job,
            JobLauncher jobLauncher)
    {
        this.blockingQueue = blockingQueue;
        this.contactGroupEmailJob = job;
        this.jobLauncher = jobLauncher;
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public String call() throws Exception
    {
        consume();
        return "complete";
    }

    /**
     * Consume.
     *
     * @throws InterruptedException the interrupted exception
     * @throws JobExecutionAlreadyRunningException the job execution already running exception
     * @throws JobRestartException the job restart exception
     * @throws JobInstanceAlreadyCompleteException the job instance already complete exception
     * @throws JobParametersInvalidException the job parameters invalid exception
     */
    private void consume() throws InterruptedException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException
    {
        while (true)
        {
            EmailJobEndpoint endpoint = blockingQueue.take();
            LOGGER.info("[Consumer] - endPoint: " + endpoint);
            LOGGER.info("Current Queue Head: " + blockingQueue.peek());

            final Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
            String request_batch_id = endpoint.getRequestId() + "|" + endpoint.getBatchId();

            synchronized(this)
            {
                persistJobExecutionToDB(endpoint.getBatchId(), endpoint.getRequestId(), "PROCESSING", null);
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
            JobExecution execution = jobLauncher.run(contactGroupEmailJob, new JobParameters(jobParametersMap));
            LOGGER.info("Job Complete notification for campaign : " + endpoint.getCampaignName() + " | Job Id - "
                    + execution.getJobId() + " | Status - " + execution.getExitStatus());

            synchronized(this)
            {
                LOGGER.info("Persisting job cmompletion records for campaign : "+endpoint.getCampaignName()+", job ID : "+execution.getJobId()+", batch : "+endpoint.getBatchId()+" to Database from synchronized context");
                persistJobExecutionToDB(endpoint.getRequestId(), endpoint.getBatchId(),
                    execution.getExitStatus().getExitCode(), execution.getJobId());
            }
            
            if(blockingQueue.isEmpty())
            {
                LOGGER.info("Queue is empty.. Consumer has finished processing all Jobs..");
                break;
            }
        }
    }

    /**
     * Persist job execution to DB.
     *
     * @param requestId the request id
     * @param batchId the batch id
     * @param exitCode the exit code
     * @param jobId the job id
     */
    private void persistJobExecutionToDB(String requestId, String batchId, String exitCode, Long jobId)
    {
        LOGGER.debug("Updating Job status on completion");
        JobExecutionEntity entity = jobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(requestId, batchId);
        if (entity != null)
        {
            entity.setJobId(jobId.toString());
            entity.setStatus(exitCode);
            jobExecutionRepository.save(entity);
        }

        if (jobId != null && CommonUtilCache.getBatchIdToEmailListMap().containsKey(requestId + "|" + batchId))
        {
            CommonUtilCache.getBatchIdToEmailListMap().remove(requestId + "|" + batchId);
            LOGGER.info("Cleared entry of the processed batch " + requestId + "|" + batchId + " from cache..");
            
            if(CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(requestId+"|"+batchId))
            {
                CommonUtilCache.getBatchIdToEmailJobEndpointMap().remove(requestId + "|" + batchId);
            }
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Consumer [consumerId=" + consumerId + "]";
    }

}
