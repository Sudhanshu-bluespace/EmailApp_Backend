package com.bluespacetech.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * The Class CompanyRegistration.
 * @author Sudhanshu Tripathy
 */
@Entity
@Table(name = "COMPANY_REGISTRATION")
public class CompanyRegistration extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -445934558495204027L;

    /** The company name. */
    @Column(name = "COMPANY_NAME", nullable = false, unique = true)
    private String companyName;

    /** The description. */
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    /** The approved. */
    @Column(name = "APPROVED", nullable = false)
    private boolean approved = false;

    /**
     * Checks if is approved.
     *
     * @return true, if is approved
     */
    public boolean isApproved()
    {
        return approved;
    }

    /**
     * Sets the approved.
     *
     * @param approved the new approved
     */
    public void setApproved(boolean approved)
    {
        this.approved = approved;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompanyName()
    {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the new company name
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "CompanyRegistration [companyName=" + companyName + ", description=" + description + ", id=" + getId()
                + "]";
    }

}
