package com.bluespacetech.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STATES")
public class State
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -4492182633820026922L;
  @Column(name="NAME")
  private String name;
  @ManyToOne(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
  @JoinColumn(name="COUNTRY_ID", nullable=false)
  private Country country;
  @Column(name="STATE_CODE")
  private String stateCode;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Country getCountry()
  {
    return this.country;
  }
  
  public void setCountry(Country country)
  {
    this.country = country;
  }
  
  public String getStateCode()
  {
    return this.stateCode;
  }
  
  public void setStateCode(String stateCode)
  {
    this.stateCode = stateCode;
  }
  
  public String toString()
  {
    return "State [name=" + this.name + ", country=" + this.country + ", stateCode=" + this.stateCode + "]";
  }
}
