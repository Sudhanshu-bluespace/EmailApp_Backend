/**
 * 
 */
package com.bluespacetech.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class Country.
 *
 * @author sudhanshu
 */
@Entity
@Table(name = "COUNTRIES")
public class Country extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8501897230657578786L;
    
    /** The short name. */
    @Column(name="SHORT_NAME")
    private String shortName;
    
    /** The country name. */
    @Column(name="FULL_NAME")
    private String fullName;
    
    /** The isd code. */
    @Column(name="ISD_CODE")
    private String isdCode;

    /**
     * Gets the short name.
     *
     * @return the short name
     */
    public String getShortName()
    {
        return shortName;
    }

    /**
     * Sets the short name.
     *
     * @param shortName the new short name
     */
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    /**
     * Gets the country name.
     *
     * @return the country name
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName the new country name
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * Gets the isd code.
     *
     * @return the isd code
     */
    public String getIsdCode()
    {
        return isdCode;
    }

    /**
     * Sets the isd code.
     *
     * @param isdCode the new isd code
     */
    public void setIsdCode(String isdCode)
    {
        this.isdCode = isdCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Country [shortName=" + shortName + ", fullName=" + fullName + ", isdCode=" + isdCode + "]";
    }
    
    
    
}
