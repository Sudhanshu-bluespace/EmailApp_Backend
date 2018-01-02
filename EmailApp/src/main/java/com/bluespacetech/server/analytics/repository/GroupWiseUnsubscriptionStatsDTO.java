package com.bluespacetech.server.analytics.repository;

public class GroupWiseUnsubscriptionStatsDTO
{
  private String createdUser;
  private int unsubscribed;
  private int groupId;
  private String groupName;
  
  public String getCreatedUser()
  {
    return this.createdUser;
  }
  
  public void setCreatedUser(String createdUser)
  {
    this.createdUser = createdUser;
  }
  
  public int getUnsubscribed()
  {
    return this.unsubscribed;
  }
  
  public void setUnsubscribed(int unsubscribed)
  {
    this.unsubscribed = unsubscribed;
  }
  
  public int getGroupId()
  {
    return this.groupId;
  }
  
  public void setGroupId(int groupId)
  {
    this.groupId = groupId;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public String toString()
  {
    return "GroupWiseUnsubscriptionStatsDTO [createdUser=" + this.createdUser + ", unsubscribed=" + this.unsubscribed + ", groupId=" + this.groupId + ", groupName=" + this.groupName + "]";
  }
}
