package com.bluespacetech.server.analytics.repository;

public class RecentlyUnsubscribedCountDTO
{
  private String unsubscribedOn;
  private int count;
  
  public String getUnsubscribedOn()
  {
    return this.unsubscribedOn;
  }
  
  public void setUnsubscribedOn(String unsubscribedOn)
  {
    this.unsubscribedOn = unsubscribedOn;
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public void setCount(int count)
  {
    this.count = count;
  }
  
  public String toString()
  {
    return "RcentlyUnsubscribedCountDTO [unsubscribedOn=" + this.unsubscribedOn + ", count=" + this.count + "]";
  }
}
