package com.bluespacetech.notifications.email.batch;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;

/**
 * The listener interface for receiving emailWriter events. The class that is interested in processing a emailWriter event implements this interface, and the object created with that class is
 * registered with a component using the component's <code>addEmailWriterListener<code> method. When the emailWriter event occurs, that object's appropriate method is invoked.
 *
 * @see EmailWriterEvent
 */
public class EmailWriterListener implements ItemWriteListener<ContactGroupMailMessage>
{
    private String requestBatchId;

    public EmailWriterListener(String reqquestBatchId)
    {
        this.requestBatchId = reqquestBatchId;
    }

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailWriterListener.class);

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.ItemWriteListener#afterWrite(java.util.List)
     */
    @Override
    public void afterWrite(List<? extends ContactGroupMailMessage> arg0)
    {
        LOGGER.debug("Finished writing " + arg0);

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.ItemWriteListener#beforeWrite(java.util.List)
     */
    @Override
    public void beforeWrite(List<? extends ContactGroupMailMessage> arg0)
    {
        LOGGER.debug("Starting to write " + arg0);

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.core.ItemWriteListener#onWriteError(java.lang.Exception, java.util.List)
     */
    @Override
    public void onWriteError(Exception arg0, List<? extends ContactGroupMailMessage> arg1)
    {
        LOGGER.error("Failed to send emails");
        getErrorRootCause(arg0);
    }

    /**
     * Gets the error root cause.
     *
     * @param throwable the throwable
     * @return the error root cause
     */
    private void getErrorRootCause(Throwable throwable)
    {
        Throwable cause = null;
        Throwable result = throwable;

        //StringBuilder builder = new StringBuilder();
        LOGGER.warn("Diving in to get the root cause of the error");
        while (null != (cause = result.getCause()) && (result != cause))
        {
            result = cause;
            LOGGER.warn("Found cause : " + result.getMessage().trim().replaceAll("[\\r\\n\\t]", " "));
            //builder.append(result.getMessage().trim().replaceAll("[\\r\\n\\t]", " ")).append(", ");
        }
        
        String rootCause = result.getMessage().trim().replaceAll("[\\r\\n\\t]", " ");
        if (!CommonUtilCache.getRequestIdVsErrorMap().containsKey(requestBatchId))
        {
            CommonUtilCache.getRequestIdVsErrorMap().put(requestBatchId, rootCause);
            LOGGER.info("Errors : "+rootCause+" pushed to cache successfully for request id "+requestBatchId);
        }

    }

}
