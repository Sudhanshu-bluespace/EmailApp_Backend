package com.bluespacetech.notifications.email.executionqueue;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EmailJobEndpoint
  implements Comparable<EmailJobEndpoint>
{
  private String batchId;
  private String requestId;
  private String requestUrl;
  private String campaignName;
  private String sender;
  private Path reportsFilePath;
  private List<EmailContactGroupVO> emailContactGroupList;
  
  public Path getReportsFilePath()
  {
    return this.reportsFilePath;
  }
  
  public void setReportsFilePath(Path reportsFilePath)
  {
    this.reportsFilePath = reportsFilePath;
  }
  
  public int compareTo(EmailJobEndpoint endpoint)
  {
    return getEmailContactGroupList().size() - endpoint.getEmailContactGroupList().size();
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    EmailJobEndpoint other = (EmailJobEndpoint)obj;
    if (this.batchId == null)
    {
      if (other.batchId != null) {
        return false;
      }
    }
    else if (!this.batchId.equals(other.batchId)) {
      return false;
    }
    if (this.campaignName == null)
    {
      if (other.campaignName != null) {
        return false;
      }
    }
    else if (!this.campaignName.equals(other.campaignName)) {
      return false;
    }
    if (this.requestId == null)
    {
      if (other.requestId != null) {
        return false;
      }
    }
    else if (!this.requestId.equals(other.requestId)) {
      return false;
    }
    return true;
  }
  
  public String getBatchId()
  {
    return this.batchId;
  }
  
  public String getCampaignName()
  {
    return this.campaignName;
  }
  
  public List<EmailContactGroupVO> getEmailContactGroupList()
  {
    if (this.emailContactGroupList == null) {
      this.emailContactGroupList = new ArrayList();
    }
    return this.emailContactGroupList;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public String getRequestUrl()
  {
    return this.requestUrl;
  }
  
  public String getSender()
  {
    return this.sender;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.batchId == null ? 0 : this.batchId.hashCode());
    result = 31 * result + (this.campaignName == null ? 0 : this.campaignName.hashCode());
    result = 31 * result + (this.requestId == null ? 0 : this.requestId.hashCode());
    return result;
  }
  
  public void setBatchId(String batchId)
  {
    this.batchId = batchId;
  }
  
  public void setCampaignName(String campaignName)
  {
    this.campaignName = campaignName;
  }
  
  public void setEmailContactGroupList(List<EmailContactGroupVO> emailContactGroupList)
  {
    this.emailContactGroupList = emailContactGroupList;
  }
  
  public void setRequestId(String requestId)
  {
    this.requestId = requestId;
  }
  
  public void setRequestUrl(String requestUrl)
  {
    this.requestUrl = requestUrl;
  }
  
  public void setSender(String sender)
  {
    this.sender = sender;
  }
  
  public String toString()
  {
    return "EmailJobEndpoint [batchId=" + this.batchId + ", requestId=" + this.requestId + ", requestUrl=" + this.requestUrl + ", campaignName=" + this.campaignName + ", sender=" + this.sender + ", emailContactGroupListSize=" + this.emailContactGroupList.size() + "]";
  }
}
