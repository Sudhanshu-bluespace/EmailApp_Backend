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

/**
 * The Class AccountApproval.
 * @author Sudhanshu Tripathy
 */
@Entity
@Table(name = "ACCOUNT_APPROVAL")
public class AccountApproval extends BaseEntity implements Serializable
{

    /**
     * Universal serial version id for this class.
     */
    private static final long serialVersionUID = -927045001321641282L;

    /** The user account. */
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
    private UserAccount userAccount;

    /** The id pending approval. */
    @Column(name = "ID_PENDING_APPROVAL", nullable = false)
    private Long idPendingApproval;

    /** The status. */
    @Column(name = "STATUS", nullable = false)
    private String status;

    /** The email. */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Gets the user account.
     *
     * @return the user account
     */
    public UserAccount getUserAccount()
    {
        return userAccount;
    }

    /**
     * Sets the user account.
     *
     * @param userAccount the new user account
     */
    public void setUserAccount(UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }

    /**
     * Gets the id pending approval.
     *
     * @return the id pending approval
     */
    public Long getIdPendingApproval()
    {
        return idPendingApproval;
    }

    /**
     * Sets the id pending approval.
     *
     * @param idPendingApproval the new id pending approval
     */
    public void setIdPendingApproval(Long idPendingApproval)
    {
        this.idPendingApproval = idPendingApproval;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "AccountApproval [userAccount=" + userAccount + ", idPendingApproval=" + idPendingApproval + ", status="
                + status + ", email=" + email + "]";
    }
}
