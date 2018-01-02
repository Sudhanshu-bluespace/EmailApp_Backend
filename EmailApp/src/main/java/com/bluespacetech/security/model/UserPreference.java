package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_PREFERENCE")
public class UserPreference
  extends BaseEntity
{
  private static final long serialVersionUID = 6630017830597146593L;
  @OneToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="USER_ACCOUNT_ID", nullable=false)
  private UserAccount userAccount;
  @Column(name="DEFAULT_BRANCH_ID")
  private Long branchId;
  @Column(name="DEC_PERCENTAGE_IND")
  private boolean decreasePercentageInd;
  
  public UserAccount getUserAccount()
  {
    return this.userAccount;
  }
  
  public void setUserAccount(UserAccount userAccount)
  {
    this.userAccount = userAccount;
  }
  
  public Long getBranchId()
  {
    return this.branchId;
  }
  
  public void setBranchId(Long branchId)
  {
    this.branchId = branchId;
  }
  
  public boolean isDecreasePercentageInd()
  {
    return this.decreasePercentageInd;
  }
  
  public void setDecreasePercentageInd(boolean decreasePercentageInd)
  {
    this.decreasePercentageInd = decreasePercentageInd;
  }
}
