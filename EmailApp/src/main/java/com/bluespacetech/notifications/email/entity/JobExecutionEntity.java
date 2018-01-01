package com.bluespacetech.notifications.email.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * The Class JobExecutionEntity.
 */
@Entity
@Table(name = "JOB_EXECUTION")
public class JobExecutionEntity extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4567393341711739824L;

    /** The request id. */
    @Column(name = "REQUEST_ID", nullable = false, length = 256)
    private String requestId;

    /** The batch id. */
    @Column(name = "BATCH_ID", nullable = false)
    private String batchId;

    /** The job id. */
    @Column(name = "JOB_ID", nullable = true)
    private String jobId;

    /** The sender. */
    @Column(name = "SENDER", nullable = false)
    private String sender;

    /** The campaign name. */
    @Column(name = "CAMPAIGN_NAME", nullable = false)
    private String campaignName;

    /** The status. */
    @Column(name = "STATUS", nullable = false)
    private String status;
    
    @Column(name="COMMENTS", nullable = true,length = 400)
    private String comments;

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /** The email count. */
    @Column(name = "EMAIL_COUNT", nullable = false)
    private Long emailCount;

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
    public String getBatchId()
    {
        return batchId;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the new batch id
     */
    public void setBatchId(String batchId)
    {
        this.batchId = batchId;
    }

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
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    @Override
    public String toString()
    {
        return "JobExecutionEntity [requestId=" + requestId + ", batchId=" + batchId + ", jobId=" + jobId + ", sender="
                + sender + ", campaignName=" + campaignName + ", status=" + status + ", comments=" + comments
                + ", emailCount=" + emailCount + "]";
    }
}
