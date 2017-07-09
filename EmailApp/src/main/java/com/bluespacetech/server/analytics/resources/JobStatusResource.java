package com.bluespacetech.server.analytics.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * The Class JobStatusResource.
 */
public class JobStatusResource extends ResourceSupport
{
    
    /** The job id. */
    private String jobId;
    
    /** The request id. */
    private String requestId;
    
    /** The batch id. */
    private Long batchId;
    
    /** The campaign name. */
    private String campaignName;
    
    /** The email count. */
    private Long emailCount;
    
    /** The sender. */
    private String sender;
    
    /** The status. */
    private String status;
    
    /**
     * Gets the job id.
     *
     * @return the job id
     */
    public String getJobId()
    {
        return jobId;
    }
    
    /**
     * Sets the job id.
     *
     * @param jobId the new job id
     */
    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }
    
    /**
     * Gets the request id.
     *
     * @return the request id
     */
    public String getRequestId()
    {
        return requestId;
    }
    
    /**
     * Sets the request id.
     *
     * @param requestId the new request id
     */
    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }
    
    /**
     * Gets the batch id.
     *
     * @return the batch id
     */
    public Long getBatchId()
    {
        return batchId;
    }
    
    /**
     * Sets the batch id.
     *
     * @param batchId the new batch id
     */
    public void setBatchId(Long batchId)
    {
        this.batchId = batchId;
    }
    
    /**
     * Gets the campaign name.
     *
     * @return the campaign name
     */
    public String getCampaignName()
    {
        return campaignName;
    }
    
    /**
     * Sets the campaign name.
     *
     * @param campaignName the new campaign name
     */
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    
    /**
     * Gets the email count.
     *
     * @return the email count
     */
    public Long getEmailCount()
    {
        return emailCount;
    }
    
    /**
     * Sets the email count.
     *
     * @param emailCount the new email count
     */
    public void setEmailCount(Long emailCount)
    {
        this.emailCount = emailCount;
    }
    
    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public String getSender()
    {
        return sender;
    }
    
    /**
     * Sets the sender.
     *
     * @param sender the new sender
     */
    public void setSender(String sender)
    {
        this.sender = sender;
    }
    
    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    

}
