package com.bluespacetech.server.analytics.model;

public class CampaignSummary
{
  private String campaignName;
  private String totalNumberOfEmails;
  private int readCount;
  private int bounceCount;
  private String subject;
  private String description;
  
  public String getCampaignName()
  {
    return this.campaignName;
  }
  
  public void setCampaignName(String campaignName)
  {
    this.campaignName = campaignName;
  }
  
  public String getTotalNumberOfEmails()
  {
    return this.totalNumberOfEmails;
  }
  
  public void setTotalNumberOfEmails(String totalNumberOfEmails)
  {
    this.totalNumberOfEmails = totalNumberOfEmails;
  }
  
  public int getReadCount()
  {
    return this.readCount;
  }
  
  public void setReadCount(int readCount)
  {
    this.readCount = readCount;
  }
  
  public int getBounceCount()
  {
    return this.bounceCount;
  }
  
  public void setBounceCount(int bounceCount)
  {
    this.bounceCount = bounceCount;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
}
