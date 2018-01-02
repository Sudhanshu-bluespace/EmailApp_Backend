package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="COMPANY_REGISTRATION")
public class CompanyRegistration
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -445934558495204027L;
  @Column(name="COMPANY_NAME", nullable=false, unique=true)
  private String companyName;
  @Column(name="DESCRIPTION", nullable=true)
  private String description;
  @Column(name="APPROVED", nullable=false)
  private boolean approved = false;
  
  public boolean isApproved()
  {
    return this.approved;
  }
  
  public void setApproved(boolean approved)
  {
    this.approved = approved;
  }
  
  public String getCompanyName()
  {
    return this.companyName;
  }
  
  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String toString()
  {
    return "CompanyRegistration [companyName=" + this.companyName + ", description=" + this.description + ", id=" + getId() + "]";
  }
}
