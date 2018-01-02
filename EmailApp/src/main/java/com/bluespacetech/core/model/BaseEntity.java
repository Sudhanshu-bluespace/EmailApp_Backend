package com.bluespacetech.core.model;

import com.bluespacetech.core.exceptions.InvalidArgumentException;
import com.bluespacetech.core.utility.ViewUtil;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -246686943403665670L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;
  @Version
  @Column(name="VERSION", nullable=false)
  private Long version;
  @Column(name="LAST_UPDATE_DATE", nullable=false)
  private Timestamp lastUpdatedDate;
  @Column(name="LAST_UPDATED_USER", nullable=false)
  private String lastUpdatedUser;
  @Column(name="CREATION_DATE", nullable=false)
  private Timestamp creationDate;
  @Column(name="CREATED_USER", nullable=false)
  private String createdUser;
  private transient Long resourceObjectId;
  private transient boolean autoRunEnabled;
  private final transient String AUTO_ADMIN = "auto_admin";
  
  public boolean isAutoRunEnabled()
  {
    return this.autoRunEnabled;
  }
  
  public void setAutoRunEnabled(boolean autoRunEnabled)
  {
    this.autoRunEnabled = autoRunEnabled;
  }
  
  public Date cloneDate(Date date)
  {
    return date != null ? (Date)date.clone() : null;
  }
  
  public Timestamp cloneTimestamp(Timestamp timestamp)
  {
    return timestamp != null ? (Timestamp)timestamp.clone() : null;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    BaseEntity other = (BaseEntity)obj;
    if (this.id == null)
    {
      if (other.id != null) {
        return false;
      }
    }
    else if (!this.id.equals(other.id)) {
      return false;
    }
    return true;
  }
  
  public String getCreatedUser()
  {
    return this.createdUser;
  }
  
  public Timestamp getCreationDate()
  {
    return this.creationDate;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public Timestamp getLastUpdatedDate()
  {
    return this.lastUpdatedDate;
  }
  
  public String getLastUpdatedUser()
  {
    return this.lastUpdatedUser;
  }
  
  public Long getResourceObjectId()
  {
    return this.resourceObjectId;
  }
  
  public Long getVersion()
  {
    return this.version;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
    return result;
  }
  
  @PrePersist
  public void prePersist()
  {
    Date date = new Date();
    if (this.creationDate == null) {
      this.creationDate = new Timestamp(date.getTime());
    }
    if (this.createdUser == null) {
      if (!isAutoRunEnabled()) {
        this.createdUser = ViewUtil.getPrincipal();
      } else {
        this.createdUser = "auto_admin";
      }
    }
    this.lastUpdatedDate = new Timestamp(date.getTime());
    if (this.lastUpdatedUser == null) {
      if (!this.autoRunEnabled) {
        this.lastUpdatedUser = ViewUtil.getPrincipal();
      } else {
        this.lastUpdatedUser = "auto_admin";
      }
    }
  }
  
  public void setCreatedUser(String createdUser)
  {
    this.createdUser = createdUser;
  }
  
  public void setCreationDate(Timestamp creationDate)
  {
    this.creationDate = creationDate;
  }
  
  public void setLastUpdatedDate(Timestamp lastUpdatedDate)
  {
    this.lastUpdatedDate = lastUpdatedDate;
  }
  
  public void setLastUpdatedUser(String lastUpdatedUser)
  {
    this.lastUpdatedUser = lastUpdatedUser;
  }
  
  public void setResourceObjectId(Long resourceObjectId)
  {
    this.resourceObjectId = resourceObjectId;
  }
  
  public void validate()
    throws InvalidArgumentException
  {}
}
