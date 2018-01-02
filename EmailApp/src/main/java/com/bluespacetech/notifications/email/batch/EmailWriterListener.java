package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;

public class EmailWriterListener
  implements ItemWriteListener<ContactGroupMailMessage>
{
  private String requestBatchId;
  
  public EmailWriterListener(String reqquestBatchId)
  {
    this.requestBatchId = reqquestBatchId;
  }
  
  private static final Logger LOGGER = LogManager.getLogger(EmailWriterListener.class);
  
  public void afterWrite(List<? extends ContactGroupMailMessage> arg0)
  {
    LOGGER.debug("Finished writing " + arg0);
  }
  
  public void beforeWrite(List<? extends ContactGroupMailMessage> arg0)
  {
    LOGGER.debug("Starting to write " + arg0);
  }
  
  public void onWriteError(Exception arg0, List<? extends ContactGroupMailMessage> arg1)
  {
    LOGGER.error("Failed to send emails");
    getErrorRootCause(arg0);
  }
  
  private void getErrorRootCause(Throwable throwable)
  {
    Throwable cause = null;
    Throwable result = throwable;
    
    LOGGER.warn("Diving in to get the root cause of the error");
    while ((null != (cause = result.getCause())) && (result != cause))
    {
      result = cause;
      LOGGER.warn("Found cause : " + result.getMessage().trim().replaceAll("[\\r\\n\\t]", " "));
    }
    String rootCause = result.getMessage().trim().replaceAll("[\\r\\n\\t]", " ");
    if (!CommonUtilCache.getRequestIdVsErrorMap().containsKey(this.requestBatchId))
    {
      CommonUtilCache.getRequestIdVsErrorMap().put(this.requestBatchId, rootCause);
      LOGGER.info("Errors : " + rootCause + " pushed to cache successfully for request id " + this.requestBatchId);
    }
  }
}
