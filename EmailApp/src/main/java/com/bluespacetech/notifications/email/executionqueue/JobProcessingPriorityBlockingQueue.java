package com.bluespacetech.notifications.email.executionqueue;

import java.util.concurrent.PriorityBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class JobProcessingPriorityBlockingQueue.
 */
public class JobProcessingPriorityBlockingQueue extends PriorityBlockingQueue<EmailJobEndpoint>
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -534897999506801243L;

    /**
     * Instantiates a new job processing priority blocking queue.
     */
    private JobProcessingPriorityBlockingQueue()
    {

    }

    /**
     * Gets the queue instance.
     *
     * @return the queue instance
     */
    public static JobProcessingPriorityBlockingQueue getQueueInstance()
    {
        return InstanceProvider.INSTANCE;
    }

    /**
     * The Class InstanceProvider.
     */
    public static class InstanceProvider
    {

        /** The Constant INSTANCE. */
        private static final JobProcessingPriorityBlockingQueue INSTANCE = new JobProcessingPriorityBlockingQueue();
    }

}
