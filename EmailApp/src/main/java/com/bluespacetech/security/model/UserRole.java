package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLE")
public class UserRole
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 1128455181710325147L;
  @Column(name="ROLE_NAME", nullable=false, length=40, unique=true)
  private String roleName;
  @Column(name="DESCRIPTION", nullable=false)
  private String description;
  @OneToMany(mappedBy="userRole", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
  private Collection<UserRoleAuthority> userRoleAuthorities;
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public Collection<UserRoleAuthority> getUserRoleAuthorities()
  {
    return this.userRoleAuthorities;
  }
  
  public void setUserRoleAuthorities(Collection<UserRoleAuthority> userRoleAuthorities)
  {
    this.userRoleAuthorities = userRoleAuthorities;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
}
