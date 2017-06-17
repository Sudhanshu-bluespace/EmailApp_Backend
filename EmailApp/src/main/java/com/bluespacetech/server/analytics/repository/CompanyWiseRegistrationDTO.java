package com.bluespacetech.server.analytics.repository;

/**
 * The Class CompanyWiseRegistrationDTO.
 */
public class CompanyWiseRegistrationDTO
{

    /** The company name. */
    private String companyName;

    /** The approved count. */
    private int approvedCount;

    /** The pending count. */
    private int pendingCount;

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
     * Gets the approved count.
     *
     * @return the approved count
     */
    public int getApprovedCount()
    {
        return approvedCount;
    }

    /**
     * Sets the approved count.
     *
     * @param approvedCount the new approved count
     */
    public void setApprovedCount(int approvedCount)
    {
        this.approvedCount = approvedCount;
    }

    /**
     * Gets the pending count.
     *
     * @return the pending count
     */
    public int getPendingCount()
    {
        return pendingCount;
    }

    /**
     * Sets the pending count.
     *
     * @param pendingCount the new pending count
     */
    public void setPendingCount(int pendingCount)
    {
        this.pendingCount = pendingCount;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "CompanyWiseRegistrationDTO [companyName=" + companyName + ", approvedCount=" + approvedCount
                + ", pendingCount=" + pendingCount + "]";
    }

}
