package com.bluespacetech.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRIES")
public class Country
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 8501897230657578786L;
  @Column(name="SHORT_NAME")
  private String shortName;
  @Column(name="FULL_NAME")
  private String fullName;
  @Column(name="ISD_CODE")
  private String isdCode;
  
  public String getShortName()
  {
    return this.shortName;
  }
  
  public void setShortName(String shortName)
  {
    this.shortName = shortName;
  }
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
  
  public String getIsdCode()
  {
    return this.isdCode;
  }
  
  public void setIsdCode(String isdCode)
  {
    this.isdCode = isdCode;
  }
  
  public String toString()
  {
    return "Country [shortName=" + this.shortName + ", fullName=" + this.fullName + ", isdCode=" + this.isdCode + "]";
  }
}
