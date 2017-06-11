package com.bluespacetech.security.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

@Entity
@Table(name = "ACCOUNT_APPROVAL")
public class AccountApproval extends BaseEntity implements Serializable {
	
	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -927045001321641282L;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
	private UserAccount userAccount;
	
	@Column(name="ID_PENDING_APPROVAL", nullable=false)
	private Long idPendingApproval;
	
	@Column(name="STATUS",nullable=false)
	private String status;
	
	@Column(name="email",nullable=false)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Long getIdPendingApproval() {
		return idPendingApproval;
	}

	public void setIdPendingApproval(Long idPendingApproval) {
		this.idPendingApproval = idPendingApproval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountApproval [userAccount=" + userAccount + ", idPendingApproval=" + idPendingApproval + ", status="
				+ status + ", email=" + email + "]";
	}
}
