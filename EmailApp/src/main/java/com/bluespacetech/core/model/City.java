package com.bluespacetech.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CITIES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 109106418830060348L;
  @Column(name="NAME")
  private String name;
  @ManyToOne(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
  @JoinColumn(name="STATE_ID", nullable=false)
  private State state;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public void setState(State state)
  {
    this.state = state;
  }
  
  public String toString()
  {
    return "City [name=" + this.name + ", state=" + this.state + "]";
  }
}
