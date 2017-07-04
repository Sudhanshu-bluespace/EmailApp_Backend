package com.bluespacetech.core.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.core.model.Country;

/**
 * The Class Country.
 *
 * @author sudhanshu
 */
@Entity
@Table(name = "STATES")
public class State extends BaseEntity implements Serializable
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4492182633820026922L;

    /** The short name. */
    @Column(name="NAME")
    private String name;
    
    /** The country. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;
    
    /** The state code. */
    @Column(name="STATE_CODE")
    private String stateCode;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public Country getCountry()
    {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(Country country)
    {
        this.country = country;
    }

    /**
     * Gets the state code.
     *
     * @return the state code
     */
    public String getStateCode()
    {
        return stateCode;
    }

    /**
     * Sets the state code.
     *
     * @param stateCode the new state code
     */
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "State [name=" + name + ", country=" + country + ", stateCode=" + stateCode + "]";
    }
    
    
    
    
}

