package com.bluespacetech.server.analytics.repository;

public class RepositoryResponseChartDTO
{
  private int reach;
  private int clicks;
  private int unsubscribes;
  
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
  
  public String toString()
  {
    return "RepositoryResponseChartDTO [reach=" + this.reach + ", clicks=" + this.clicks + ", unsubscribes=" + this.unsubscribes + "]";
  }
  
  public int getUnsubscribes()
  {
    return this.unsubscribes;
  }
  
  public void setUnsubscribes(int unsubscribes)
  {
    this.unsubscribes = unsubscribes;
  }
}
