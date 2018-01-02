package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACCESS_CONTROL")
public class AccessControl
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -927045001321641282L;
  @ManyToOne(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
  @JoinColumn(name="USER_ACCOUNT_ID", nullable=false)
  private UserAccount userAccount;
  @Column(name="BRANCH_ID", nullable=false)
  private Long branchId;
  
  public void setUserAccount(UserAccount userAccount)
  {
    this.userAccount = userAccount;
  }
  
  public UserAccount getUserAccount()
  {
    return this.userAccount;
  }
  
  public Long getBranchId()
  {
    return this.branchId;
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId;
  }
}
