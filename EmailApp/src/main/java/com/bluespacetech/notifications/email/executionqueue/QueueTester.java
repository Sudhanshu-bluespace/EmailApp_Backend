package com.bluespacetech.notifications.email.executionqueue;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

/*public class QueueTester
{
    public static void main(String[] args) throws InterruptedException
    {
        JobProcessingPriorityBlockingQueue queue = JobProcessingPriorityBlockingQueue.getQueueInstance();
        putInQueue("1",queue,601,"one");
        putInQueue("2",queue,402,"two");
        putInQueue("3",queue,808,"three");
        
        System.out.println("Current state : "+queue.peek());
        
        EmailJobEndpoint p = queue.take();
        System.out.println("Current state after take 1: "+queue.peek());
        putInQueue("3",queue,308,"four");
        System.out.println("Current state put : "+queue.peek());
        queue.take();
        System.out.println("Current state aftr take 2: "+queue.peek());
        
    }
    
    private static void putInQueue(String reqd,JobProcessingPriorityBlockingQueue queue,int size,String name)
    {
        EmailJobEndpoint endpoint = new EmailJobEndpoint();
        endpoint.setRequestId("1");
        
        for(int i=0;i<size;i++)
        {
            endpoint.getEmailContactGroupList().add(new EmailContactGroupVO());
        }
        
        endpoint.setCampaignName("One");
        queue.put(endpoint);
    }
}*/
