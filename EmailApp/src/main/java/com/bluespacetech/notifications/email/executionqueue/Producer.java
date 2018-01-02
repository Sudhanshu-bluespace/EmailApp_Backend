package com.bluespacetech.notifications.email.executionqueue;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer
  implements Callable<String>
{
  @Autowired
  private JobProcessingPriorityBlockingQueue priorityBlockingQueue;
  private EmailJobEndpoint jobEndPoint;
  private JobExecutionRepository jobExecutionRepository;
  private static final Logger LOGGER = LogManager.getLogger(Producer.class);
  
  public Producer(EmailJobEndpoint jobEndPoint, JobProcessingPriorityBlockingQueue priorityBlockingQueue, JobExecutionRepository jobExecutionRepository)
  {
    this.jobEndPoint = jobEndPoint;
    this.priorityBlockingQueue = priorityBlockingQueue;
    this.jobExecutionRepository = jobExecutionRepository;
  }
  
  public String call()
    throws Exception
  {
    produce();
    return "complete";
  }
  
  private void produce()
  {
    LOGGER.info("Producing target " + this.jobEndPoint);
    this.priorityBlockingQueue.put(this.jobEndPoint);
    String request_batch_id = this.jobEndPoint.getRequestId() + "|" + this.jobEndPoint.getBatchId();
    if (!CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(request_batch_id)) {
      CommonUtilCache.getBatchIdToEmailJobEndpointMap().put(request_batch_id, this.jobEndPoint);
    }
    LOGGER.info("Current Queue Head: " + this.priorityBlockingQueue.peek());
  }
}
