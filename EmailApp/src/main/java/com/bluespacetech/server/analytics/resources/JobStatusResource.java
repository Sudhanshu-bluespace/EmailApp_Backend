package com.bluespacetech.server.analytics.resources;

import org.springframework.hateoas.ResourceSupport;

public class JobStatusResource
  extends ResourceSupport
{
  private String jobId;
  private String requestId;
  private Long batchId;
  private String campaignName;
  private Long emailCount;
  private String sender;
  private String status;
  private String comments;
  
  public String getComments()
  {
    return this.comments;
  }
  
  public void setComments(String comments)
  {
    this.comments = comments;
  }
  
  public String getJobId()
  {
    return this.jobId;
  }
  
  public void setJobId(String jobId)
  {
    this.jobId = jobId;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public void setRequestId(String requestId)
  {
    this.requestId = requestId;
  }
  
  public Long getBatchId()
  {
    return this.batchId;
  }
  
  public void setBatchId(Long batchId)
  {
    this.batchId = batchId;
  }
  
  public String getCampaignName()
  {
    return this.campaignName;
  }
  
  public void setCampaignName(String campaignName)
  {
    this.campaignName = campaignName;
  }
  
  public Long getEmailCount()
  {
    return this.emailCount;
  }
  
  public void setEmailCount(Long emailCount)
  {
    this.emailCount = emailCount;
  }
  
  public String getSender()
  {
    return this.sender;
  }
  
  public void setSender(String sender)
  {
    this.sender = sender;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}
