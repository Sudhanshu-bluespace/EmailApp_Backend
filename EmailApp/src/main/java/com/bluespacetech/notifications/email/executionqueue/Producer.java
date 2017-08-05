package com.bluespacetech.notifications.email.executionqueue;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;

/**
 * The Class Producer.
 */
@Component
public class Producer implements Callable<String>
{

    /** The priority blocking queue. */
    @Autowired
    private JobProcessingPriorityBlockingQueue priorityBlockingQueue;

    /** The job end point. */
    private EmailJobEndpoint jobEndPoint;
    private JobExecutionRepository jobExecutionRepository;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(Producer.class);

    /**
     * Instantiates a new producer.
     *
     * @param jobEndPoint the job end point
     * @param priorityBlockingQueue the priority blocking queue
     */
    public Producer(EmailJobEndpoint jobEndPoint, JobProcessingPriorityBlockingQueue priorityBlockingQueue,JobExecutionRepository jobExecutionRepository)
    {
        this.jobEndPoint = jobEndPoint;
        this.priorityBlockingQueue = priorityBlockingQueue;
        this.jobExecutionRepository = jobExecutionRepository;
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public String call() throws Exception
    {
        // TODO Auto-generated method stub
        produce();
        return "complete";
    }

    /**
     * Produce.
     */
    private void produce()
    {
        LOGGER.info("Producing target " + jobEndPoint);
        this.priorityBlockingQueue.put(jobEndPoint);
        String request_batch_id = jobEndPoint.getRequestId()+"|"+jobEndPoint.getBatchId();
        if(!CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(request_batch_id))
        {
            CommonUtilCache.getBatchIdToEmailJobEndpointMap().put(request_batch_id, jobEndPoint);
        }
        LOGGER.info("Current Queue Head: "+this.priorityBlockingQueue.peek());
    }



}
