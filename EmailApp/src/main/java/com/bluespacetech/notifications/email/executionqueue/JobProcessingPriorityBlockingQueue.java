package com.bluespacetech.notifications.email.executionqueue;

import java.util.concurrent.PriorityBlockingQueue;


public class JobProcessingPriorityBlockingQueue extends PriorityBlockingQueue<EmailJobEndpoint>
{
    /**
     * 
     */
    private static final long serialVersionUID = -534897999506801243L;


    private JobProcessingPriorityBlockingQueue()
    {

    }
    
    public static JobProcessingPriorityBlockingQueue getQueueInstance()
    {
        return InstanceProvider.INSTANCE;
    }
    
    public static class InstanceProvider
    {
        private static final JobProcessingPriorityBlockingQueue INSTANCE = new JobProcessingPriorityBlockingQueue();
    }

}
