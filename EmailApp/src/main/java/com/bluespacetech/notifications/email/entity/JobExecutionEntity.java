package com.bluespacetech.notifications.email.entity;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="JOB_EXECUTION")
public class JobExecutionEntity
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 4567393341711739824L;
  @Column(name="REQUEST_ID", nullable=false, length=256)
  private String requestId;
  @Column(name="BATCH_ID", nullable=false)
  private String batchId;
  @Column(name="JOB_ID", nullable=true)
  private String jobId;
  @Column(name="SENDER", nullable=false)
  private String sender;
  @Column(name="CAMPAIGN_NAME", nullable=false)
  private String campaignName;
  @Column(name="STATUS", nullable=false)
  private String status;
  @Column(name="COMMENTS", nullable=true, length=400)
  private String comments;
  @Column(name="EMAIL_COUNT", nullable=false)
  private Long emailCount;
  
  public String getComments()
  {
    return this.comments;
  }
  
  public void setComments(String comments)
  {
    this.comments = comments;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public void setRequestId(String requestId)
  {
    this.requestId = requestId;
  }
  
  public String getBatchId()
  {
    return this.batchId;
  }
  
  public void setBatchId(String batchId)
  {
    this.batchId = batchId;
  }
  
  public String getJobId()
  {
    return this.jobId;
  }
  
  public void setJobId(String jobId)
  {
    this.jobId = jobId;
  }
  
  public String getSender()
  {
    return this.sender;
  }
  
  public void setSender(String sender)
  {
    this.sender = sender;
  }
  
  public String getCampaignName()
  {
    return this.campaignName;
  }
  
  public void setCampaignName(String campaignName)
  {
    this.campaignName = campaignName;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Long getEmailCount()
  {
    return this.emailCount;
  }
  
  public void setEmailCount(Long emailCount)
  {
    this.emailCount = emailCount;
  }
  
  public static long getSerialversionuid()
  {
    return 4567393341711739824L;
  }
  
  public String toString()
  {
    return "JobExecutionEntity [requestId=" + this.requestId + ", batchId=" + this.batchId + ", jobId=" + this.jobId + ", sender=" + this.sender + ", campaignName=" + this.campaignName + ", status=" + this.status + ", comments=" + this.comments + ", emailCount=" + this.emailCount + "]";
  }
}
