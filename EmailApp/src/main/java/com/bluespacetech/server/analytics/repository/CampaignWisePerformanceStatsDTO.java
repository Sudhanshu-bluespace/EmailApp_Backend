package com.bluespacetech.server.analytics.repository;

public class CampaignWisePerformanceStatsDTO
{
  private int unsubscribes;
  private int group_id;
  private long email_id;
  private String campaignName;
  private String groupName;
  private int clicks;
  private int totalReach;
  private String createdUser;
  private String subject;
  private String content;
  private String sentOn;
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getSentOn()
  {
    return this.sentOn;
  }
  
  public void setSentOn(String sentOn)
  {
    this.sentOn = sentOn;
  }
  
  public String getCreatedUser()
  {
    return this.createdUser;
  }
  
  public void setCreatedUser(String createdUser)
  {
    this.createdUser = createdUser;
  }
  
  public int getUnsubscribes()
  {
    return this.unsubscribes;
  }
  
  public void setUnsubscribes(int unsubscribes)
  {
    this.unsubscribes = unsubscribes;
  }
  
  public int getGroup_id()
  {
    return this.group_id;
  }
  
  public void setGroup_id(int group_id)
  {
    this.group_id = group_id;
  }
  
  public long getEmail_id()
  {
    return this.email_id;
  }
  
  public void setEmail_id(long email_id)
  {
    this.email_id = email_id;
  }
  
  public String getCampaignName()
  {
    return this.campaignName;
  }
  
  public void setCampaignName(String campaignName)
  {
    this.campaignName = campaignName;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public int getClicks()
  {
    return this.clicks;
  }
  
  public void setClicks(int clicks)
  {
    this.clicks = clicks;
  }
  
  public int getTotalReach()
  {
    return this.totalReach;
  }
  
  public void setTotalReach(int totalReach)
  {
    this.totalReach = totalReach;
  }
  
  public String toString()
  {
    return "CampaignWisePerformanceStats [unsubscribes=" + this.unsubscribes + ", group_id=" + this.group_id + ", email_id=" + this.email_id + ", campaignName=" + this.campaignName + ", groupName=" + this.groupName + ", clicks=" + this.clicks + ", totalReach=" + this.totalReach + "]";
  }
}
