package com.bluespacetech.security.dao;

/**
 * The Class PendingAccountApprovalsDTO.
 * @author Sudhanshu Tripathy
 */
public class PendingAccountApprovalsDTO
{

    /** The serial no. */
    private long serialNo;

    /** The id. */
    private long id;

    /** The name. */
    private String name;

    /** The email. */
    private String email;

    /** The company name. */
    private String companyName;

    /** The registration request date. */
    private String registrationRequestDate;
    
    private String street;
    
    private String city;
    
    private String state;
    
    private String country;
    
    private String zipcode;
    
    private boolean approved;
    
    private boolean onHold;
    
    private boolean rejected;

    public boolean isApproved()
    {
        return approved;
    }

    public void setApproved(boolean approved)
    {
        this.approved = approved;
    }

    public boolean isOnHold()
    {
        return onHold;
    }

    public void setOnHold(boolean onHold)
    {
        this.onHold = onHold;
    }

    public boolean isRejected()
    {
        return rejected;
    }

    public void setRejected(boolean rejected)
    {
        this.rejected = rejected;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    /** The status. */
    private String status;

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
     * Gets the serial no.
     *
     * @return the serial no
     */
    public long getSerialNo()
    {
        return serialNo;
    }

    /**
     * Sets the serial no.
     *
     * @param serialNo the new serial no
     */
    public void setSerialNo(long serialNo)
    {
        this.serialNo = serialNo;
    }

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
     * Gets the id.
     *
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id)
    {
        this.id = id;
    }

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
     * Gets the registration request date.
     *
     * @return the registration request date
     */
    public String getRegistrationRequestDate()
    {
        return registrationRequestDate;
    }

    /**
     * Sets the registration request date.
     *
     * @param registrationRequestDate the new registration request date
     */
    public void setRegistrationRequestDate(String registrationRequestDate)
    {
        this.registrationRequestDate = registrationRequestDate;
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

    @Override
    public String toString()
    {
        return "PendingAccountApprovalsDTO [serialNo=" + serialNo + ", id=" + id + ", name=" + name + ", email=" + email
                + ", companyName=" + companyName + ", registrationRequestDate=" + registrationRequestDate + ", street="
                + street + ", city=" + city + ", state=" + state + ", country=" + country + ", zipcode=" + zipcode
                + ", approved=" + approved + ", onHold=" + onHold + ", rejected=" + rejected + ", status=" + status
                + "]";
    }
}
