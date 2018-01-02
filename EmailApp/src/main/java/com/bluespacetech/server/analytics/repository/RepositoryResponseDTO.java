package com.bluespacetech.server.analytics.repository;

public class RepositoryResponseDTO
{
  private long email_id;
  private String created_user;
  private String content;
  private String subject;
  private int reach;
  private int clicks;
  private int unsubscribes;
  private String sentOn;
  private double clickPercentage;
  private double unsubscribePercentage;
  
  public double getClickPercentage()
  {
    return this.clickPercentage;
  }
  
  public void setClickPercentage(double clickPercentage)
  {
    this.clickPercentage = clickPercentage;
  }
  
  public double getUnsubscribePercentage()
  {
    return this.unsubscribePercentage;
  }
  
  public void setUnsubscribePercentage(double unsubscribePercentage)
  {
    this.unsubscribePercentage = unsubscribePercentage;
  }
  
  public String getSentOn()
  {
    return this.sentOn;
  }
  
  public void setSentOn(String sentOn)
  {
    this.sentOn = sentOn;
  }
  
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
  
  public String toString()
  {
    return "RepositoryResponseDTO [email_id=" + this.email_id + ", created_user=" + this.created_user + ", content=" + this.content + ", subject=" + this.subject + ", reach=" + this.reach + ", clicks=" + this.clicks + ", unsubscribes=" + this.unsubscribes + ", sentOn=" + this.sentOn + ", clickPercentage=" + this.clickPercentage + ", unsubscribePercentage=" + this.unsubscribePercentage + "]";
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
}
