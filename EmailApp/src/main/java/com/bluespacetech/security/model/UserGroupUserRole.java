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
@Table(name="USER_GROUP_USER_ROLE")
public class UserGroupUserRole
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -974709751016922398L;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="USER_GROUP_ID")
  private UserGroup userGroup;
  @Column(name="USER_ROLE_ID", nullable=false)
  private Long userRoleId;
  
  public UserGroup getUserGroup()
  {
    return this.userGroup;
  }
  
  public void setUserGroup(UserGroup userGroup)
  {
    this.userGroup = userGroup;
  }
  
  public Long getUserRoleId()
  {
    return this.userRoleId;
  }
  
  public void setUserRoleId(Long userRoleId)
  {
    this.userRoleId = userRoleId;
  }
}
