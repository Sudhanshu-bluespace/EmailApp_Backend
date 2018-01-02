package com.bluespacetech.server.analytics.searchcriterias;

import com.bluespacetech.core.searchcriterias.SearchCriteria;

public class RecentCampaignStatsSearchCriteria
  implements SearchCriteria
{
  private static final long serialVersionUID = 8649162540952455330L;
  private long branchId;
  private long email_id;
  private String created_user;
  private String content;
  private String subject;
  private int reach;
  private int clicks;
  private int unsubscribes;
  
  public long getEmail_id()
  {
    return this.email_id;
  }
  
  public void setEmail_id(long email_id)
  {
    this.email_id = email_id;
  }
  
  public String getCreated_user()
  {
    return this.created_user;
  }
  
  public void setCreated_user(String created_user)
  {
    this.created_user = created_user;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public int getReach()
  {
    return this.reach;
  }
  
  public void setReach(int reach)
  {
    this.reach = reach;
  }
  
  public int getClicks()
  {
    return this.clicks;
  }
  
  public void setClicks(int clicks)
  {
    this.clicks = clicks;
  }
  
  public int getUnsubscribes()
  {
    return this.unsubscribes;
  }
  
  public void setUnsubscribes(int unsubscribes)
  {
    this.unsubscribes = unsubscribes;
  }
  
  public void setBranchId(long branchId)
  {
    this.branchId = branchId;
  }
  
  public Long getBranchId()
  {
    return Long.valueOf(this.branchId);
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId.longValue();
  }
}
