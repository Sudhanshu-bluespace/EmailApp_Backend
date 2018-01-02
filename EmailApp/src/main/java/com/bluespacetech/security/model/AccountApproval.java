package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT_APPROVAL")
public class AccountApproval
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = -927045001321641282L;
  @OneToOne(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
  @JoinColumn(name="USER_ACCOUNT_ID", nullable=false)
  private UserAccount userAccount;
  @Column(name="ID_PENDING_APPROVAL", nullable=false)
  private Long idPendingApproval;
  @Column(name="STATUS", nullable=false)
  private String status;
  @Column(name="email", nullable=false)
  private String email;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public UserAccount getUserAccount()
  {
    return this.userAccount;
  }
  
  public void setUserAccount(UserAccount userAccount)
  {
    this.userAccount = userAccount;
  }
  
  public Long getIdPendingApproval()
  {
    return this.idPendingApproval;
  }
  
  public void setIdPendingApproval(Long idPendingApproval)
  {
    this.idPendingApproval = idPendingApproval;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String toString()
  {
    return "AccountApproval [userAccount=" + this.userAccount + ", idPendingApproval=" + this.idPendingApproval + ", status=" + this.status + ", email=" + this.email + "]";
  }
}
