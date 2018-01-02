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
@Table(name="USER_ACCOUNT_USER_GROUP")
public class UserAccountUserGroup
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -7235237614145212463L;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="USER_ACCOUNT_ID")
  private UserAccount userAccount;
  @Column(name="USER_GROUP_ID", nullable=false)
  private Long userGroupId;
  
  public UserAccount getUserAccount()
  {
    return this.userAccount;
  }
  
  public void setUserAccount(UserAccount userAccount)
  {
    this.userAccount = userAccount;
  }
  
  public Long getUserGroupId()
  {
    return this.userGroupId;
  }
  
  public void setUserGroupId(Long userGroup)
  {
    this.userGroupId = userGroup;
  }
}
