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
@Table(name="USER_GROUP")
public class UserGroup
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 1128455181710325147L;
  @Column(name="GROUP_NAME", nullable=false, length=40, unique=true)
  private String groupName;
  @Column(name="DESCRIPTION", nullable=false)
  private String description;
  @OneToMany(mappedBy="userGroup", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
  private Collection<UserGroupUserRole> userGroupUserRoles;
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getGroupName()
  {
    return this.groupName;
  }
  
  public void setGroupName(String groupName)
  {
    this.groupName = groupName;
  }
  
  public Collection<UserGroupUserRole> getUserGroupUserRoles()
  {
    return this.userGroupUserRoles;
  }
  
  public void setUserGroupUserRole(Collection<UserGroupUserRole> userGroupUserRoles)
  {
    this.userGroupUserRoles = userGroupUserRoles;
  }
}
