package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.security.constants.GrantConstant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE_AUTHORITY")
public class UserRoleAuthority
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -6708597673610237653L;
  @Column(name="AUTH_GRANT", nullable=false, length=100)
  @Enumerated(EnumType.STRING)
  private GrantConstant authorityGrant;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="USER_ROLE_ID")
  private UserRole userRole;
  
  public GrantConstant getAuthorityGrant()
  {
    return this.authorityGrant;
  }
  
  public void setAuthorityGrant(GrantConstant authorityGrant)
  {
    this.authorityGrant = authorityGrant;
  }
  
  public UserRole getUserRole()
  {
    return this.userRole;
  }
  
  public void setUserRole(UserRole userRole)
  {
    this.userRole = userRole;
  }
}
