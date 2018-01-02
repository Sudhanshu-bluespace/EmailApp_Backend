package com.bluespacetech.server.analytics.repository;

public class CompanyWiseRegistrationDTO
{
  private String companyName;
  private int approvedCount;
  private int pendingCount;
  
  public String getCompanyName()
  {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }
  
  public int getApprovedCount()
  {
    return this.approvedCount;
  }
  
  public void setApprovedCount(int approvedCount)
  {
    this.approvedCount = approvedCount;
  }
  
  public int getPendingCount()
  {
    return this.pendingCount;
  }
  
  public void setPendingCount(int pendingCount)
  {
    this.pendingCount = pendingCount;
  }
  
  public String toString()
  {
    return "CompanyWiseRegistrationDTO [companyName=" + this.companyName + ", approvedCount=" + this.approvedCount + ", pendingCount=" + this.pendingCount + "]";
  }
}
