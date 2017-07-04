/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.model;

/**
 * Entity class for user accounts
 *
 * @author Pradeep
 */
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.security.constants.UserAccountTypeConstant;

/**
 * The Class UserAccount.
 */
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends BaseEntity implements Serializable
{

    /**
     * Unique serial version id for this class.
     */
    private static final long serialVersionUID = 8617426954436904583L;

    /** The username. */
    @Column(name = "USERNAME", nullable = false, length = 40, unique = true)
    private String username;

    /** The password. */
    @Column(name = "PASSWORD", nullable = false, length = 1000)
    private String password;

    /** The active. */
    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    /** The verified by admin. */
    @Column(name = "VERIFIED_BY_ADMIN", nullable = false)
    private boolean verifiedByAdmin;

    /** The account expired. */
    @Column(name = "ACC_EXPIRED", nullable = false)
    private boolean accountExpired;

    /** The credentials expired. */
    @Column(name = "CRDTLS_EXPIRED", nullable = false)
    private boolean credentialsExpired;

    /** The account locked. */
    @Column(name = "ACC_LOCKED", nullable = false)
    private boolean accountLocked;

    /** The phone number. */
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    /** The user account type. */
    @Column(name = "USER_ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    private UserAccountTypeConstant userAccountType;

    /** The user account user groups. */
    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Collection<UserAccountUserGroup> userAccountUserGroups;

    /** The company registration. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_NAME", nullable = false, referencedColumnName = "COMPANY_NAME")
    private CompanyRegistration companyRegistration;

    /** The email. */
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    
    /** The federal id. */
    @Column(name="FEDERAL_ID",nullable = false, unique=true)
    private String federalId;
    
    /** The address. */
    @Column(name="ADDRESS")
    private String address;
    
    /** The street. */
    @Column(name="STREET")
    private String street;
    
    /** The city. */
    @Column(name="CITY",nullable = false, unique=true)
    private String city;
    
    /** The state. */
    @Column(name="STATE",nullable = false)
    private String state;
    
    /** The country. */
    @Column(name="COUNTRY",nullable = false)
    private String country;
    
    /** The zip code. */
    @Column(name="ZIP_CODE",nullable = false)
    private String zipCode;
    
    /** The auto renew. */
    @Column(name="AUTO_RENEW")
    private Boolean autoRenew = false;
    
    /** The comments. */
    @Column(name="COMMENTS")
    private String comments;
    
    /** The payment plan. */
    @Column(name="PAYMENT_PLAN")
    private String paymentPlan;
    
    /** The valid to. */
    @Column(name="VALID_TO")
    private String validTo;
    
    /** The payment info. */
    @Column(name="PAYMENT_INFO")
    private String paymentInfo;
    
    /** The trial period. */
    @Column(name="TRIAL_PERIOD")
    private String trialPeriod;
    
    
    
    /**
     * Gets the federal id.
     *
     * @return the federal id
     */
    public String getFederalId()
    {
        return federalId;
    }

    /**
     * Sets the federal id.
     *
     * @param federalId the new federal id
     */
    public void setFederalId(String federalId)
    {
        this.federalId = federalId;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Gets the street.
     *
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Sets the street.
     *
     * @param street the new street
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the new zip code
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Checks if is auto renew.
     *
     * @return true, if is auto renew
     */
    public boolean isAutoRenew()
    {
        return autoRenew;
    }

    /**
     * Sets the auto renew.
     *
     * @param autoRenew the new auto renew
     */
    public void setAutoRenew(boolean autoRenew)
    {
        this.autoRenew = autoRenew;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * Gets the payment plan.
     *
     * @return the payment plan
     */
    public String getPaymentPlan()
    {
        return paymentPlan;
    }

    /**
     * Sets the payment plan.
     *
     * @param paymentPlan the new payment plan
     */
    public void setPaymentPlan(String paymentPlan)
    {
        this.paymentPlan = paymentPlan;
    }

    /**
     * Gets the valid to.
     *
     * @return the valid to
     */
    public String getValidTo()
    {
        return validTo;
    }

    /**
     * Sets the valid to.
     *
     * @param validTo the new valid to
     */
    public void setValidTo(String validTo)
    {
        this.validTo = validTo;
    }

    /**
     * Gets the payment info.
     *
     * @return the payment info
     */
    public String getPaymentInfo()
    {
        return paymentInfo;
    }

    /**
     * Sets the payment info.
     *
     * @param paymentInfo the new payment info
     */
    public void setPaymentInfo(String paymentInfo)
    {
        this.paymentInfo = paymentInfo;
    }

    /**
     * Gets the trial period.
     *
     * @return the trial period
     */
    public String getTrialPeriod()
    {
        return trialPeriod;
    }

    /**
     * Sets the trial period.
     *
     * @param trialPeriod the new trial period
     */
    public void setTrialPeriod(String trialPeriod)
    {
        this.trialPeriod = trialPeriod;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(final String username)
    {
        this.username = username;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Checks if is verified by admin.
     *
     * @return true, if is verified by admin
     */
    public boolean isVerifiedByAdmin()
    {
        return verifiedByAdmin;
    }

    /**
     * Sets the verified by admin.
     *
     * @param verifiedByAdmin the new verified by admin
     */
    public void setVerifiedByAdmin(boolean verifiedByAdmin)
    {
        this.verifiedByAdmin = verifiedByAdmin;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(final String password)
    {
        this.password = password;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(final boolean active)
    {
        this.active = active;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive()
    {
        return active;
    }

    /**
     * Checks if is account expired.
     *
     * @return true, if is account expired
     */
    public boolean isAccountExpired()
    {
        return accountExpired;
    }

    /**
     * Sets the account expired.
     *
     * @param accountExpired the new account expired
     */
    public void setAccountExpired(final boolean accountExpired)
    {
        this.accountExpired = accountExpired;
    }

    /**
     * Checks if is credentials expired.
     *
     * @return true, if is credentials expired
     */
    public boolean isCredentialsExpired()
    {
        return credentialsExpired;
    }

    /**
     * Sets the credentials expired.
     *
     * @param credentialsExpired the new credentials expired
     */
    public void setCredentialsExpired(final boolean credentialsExpired)
    {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * Checks if is account locked.
     *
     * @return true, if is account locked
     */
    public boolean isAccountLocked()
    {
        return accountLocked;
    }

    /**
     * Gets the company registration.
     *
     * @return the company registration
     */
    public CompanyRegistration getCompanyRegistration()
    {
        return companyRegistration;
    }

    /**
     * Sets the company registration.
     *
     * @param companyRegistration the new company registration
     */
    public void setCompanyRegistration(CompanyRegistration companyRegistration)
    {
        this.companyRegistration = companyRegistration;
    }

    /**
     * Sets the account locked.
     *
     * @param accountLocked the new account locked
     */
    public void setAccountLocked(final boolean accountLocked)
    {
        this.accountLocked = accountLocked;
    }

    /**
     * Gets the user account type.
     *
     * @return the user account type
     */
    public UserAccountTypeConstant getUserAccountType()
    {
        return userAccountType;
    }

    /**
     * Sets the user account type.
     *
     * @param userAccountType the new user account type
     */
    public void setUserAccountType(final UserAccountTypeConstant userAccountType)
    {
        this.userAccountType = userAccountType;
    }

    /**
     * Gets the user account user groups.
     *
     * @return the userAccountUserGroups
     */
    public Collection<UserAccountUserGroup> getUserAccountUserGroups()
    {
        return userAccountUserGroups;
    }

    /**
     * Sets the user account user groups.
     *
     * @param userAccountUserGroups the userAccountUserGroups to set
     */
    public void setUserAccountUserGroups(final Collection<UserAccountUserGroup> userAccountUserGroups)
    {
        this.userAccountUserGroups = userAccountUserGroups;
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
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UserAccount [username=" + username + ", password=" + password + ", active=" + active
                + ", verifiedByAdmin=" + verifiedByAdmin + ", accountExpired=" + accountExpired
                + ", credentialsExpired=" + credentialsExpired + ", accountLocked=" + accountLocked + ", phoneNumber="
                + phoneNumber + ", userAccountType=" + userAccountType + ", userAccountUserGroups="
                + userAccountUserGroups + ", companyRegistration=" + companyRegistration + ", email=" + email
                + ", federalId=" + federalId + ", address=" + address + ", street=" + street + ", city=" + city
                + ", state=" + state + ", country=" + country + ", zipCode=" + zipCode + ", autoRenew=" + autoRenew
                + ", comments=" + comments + ", paymentPlan=" + paymentPlan + ", validTo=" + validTo + ", paymentInfo="
                + paymentInfo + ", trialPeriod=" + trialPeriod + "]";
    }

}
