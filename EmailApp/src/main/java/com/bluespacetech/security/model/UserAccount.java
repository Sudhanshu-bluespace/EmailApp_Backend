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

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /** The username. */
    @Column(name = "USERNAME", nullable = false, length = 40, unique = true)
    private String username;
    
    /** The password. */
    @Column(name = "PASSWORD", nullable = false, length = 1000)
    private String password;
    
    /** The first name. */
    @Column(name = "FIRST_NAME", nullable = false, length = 20)
    private String firstName;
    
    /** The middle name. */
    @Column(name = "MIDDLE_NAME", nullable = true, length = 20)
    private String middleName;
    
    /** The last name. */
    @Column(name = "LAST_NAME", nullable = false, length = 20)
    private String lastName;
    
    /** The adress line 1. */
    @Column(name = "ADDRESS_LINE1", nullable = false, length = 250)
    private String addressLine1;

    /** The address line 2. */
    @Column(name = "ADDRESS_LINE2", nullable = true, length = 150)
    private String addressLine2;

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
    @Column(name="FEDERAL_ID",nullable = false)
    private String federalId;

    /** The city. */
    @Column(name="CITY",nullable = false)
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
     * Gets the address line 2.
     *
     * @return the address line 2
     */
    public String getAddressLine2()
    {
        return addressLine2;
    }
    
    /**
     * Gets the adress line 1.
     *
     * @return the adress line 1
     */
    public String getAddressLine1()
    {
        return addressLine1;
    }
    
    /**
     * Gets the auto renew.
     *
     * @return the auto renew
     */
    public Boolean getAutoRenew()
    {
        return autoRenew;
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
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments()
    {
        return comments;
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
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry()
    {
        return country;
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
     * Gets the federal id.
     *
     * @return the federal id
     */
    public String getFederalId()
    {
        return federalId;
    }
    
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }
    
    
    
    /**
     * Gets the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName()
    {
        return middleName;
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
     * Gets the payment info.
     *
     * @return the payment info
     */
    public String getPaymentInfo()
    {
        return paymentInfo;
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
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
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
     * Gets the trial period.
     *
     * @return the trial period
     */
    public String getTrialPeriod()
    {
        return trialPeriod;
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
     * Gets the user account user groups.
     *
     * @return the userAccountUserGroups
     */
    public Collection<UserAccountUserGroup> getUserAccountUserGroups()
    {
        return userAccountUserGroups;
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
     * Gets the valid to.
     *
     * @return the valid to
     */
    public String getValidTo()
    {
        return validTo;
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
     * Checks if is account expired.
     *
     * @return true, if is account expired
     */
    public boolean isAccountExpired()
    {
        return accountExpired;
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
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive()
    {
        return active;
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
     * Checks if is credentials expired.
     *
     * @return true, if is credentials expired
     */
    public boolean isCredentialsExpired()
    {
        return credentialsExpired;
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
     * Sets the account expired.
     *
     * @param accountExpired the new account expired
     */
    public void setAccountExpired(final boolean accountExpired)
    {
        this.accountExpired = accountExpired;
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
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(final boolean active)
    {
        this.active = active;
    }

    /**
     * Sets the address line 2.
     *
     * @param addressLine2 the new address line 2
     */
    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    /**
     * Sets the adress line 1.
     *
     * @param adressLine1 the new adress line 1
     */
    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
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
     * Sets the auto renew.
     *
     * @param autoRenew the new auto renew
     */
    public void setAutoRenew(Boolean autoRenew)
    {
        this.autoRenew = autoRenew;
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
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments)
    {
        this.comments = comments;
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
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(String country)
    {
        this.country = country;
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
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
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
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the new middle name
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
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
     * Sets the payment info.
     *
     * @param paymentInfo the new payment info
     */
    public void setPaymentInfo(String paymentInfo)
    {
        this.paymentInfo = paymentInfo;
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
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
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
     * Sets the trial period.
     *
     * @param trialPeriod the new trial period
     */
    public void setTrialPeriod(String trialPeriod)
    {
        this.trialPeriod = trialPeriod;
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
     * Sets the user account user groups.
     *
     * @param userAccountUserGroups the userAccountUserGroups to set
     */
    public void setUserAccountUserGroups(final Collection<UserAccountUserGroup> userAccountUserGroups)
    {
        this.userAccountUserGroups = userAccountUserGroups;
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
     * Sets the valid to.
     *
     * @param validTo the new valid to
     */
    public void setValidTo(String validTo)
    {
        this.validTo = validTo;
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
     * Sets the zip code.
     *
     * @param zipCode the new zip code
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UserAccount [username=" + username + ", firstName=" + firstName + ", middleName=" + middleName
                + ", lastName=" + lastName + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                + ", active=" + active + ", verifiedByAdmin=" + verifiedByAdmin + ", accountExpired=" + accountExpired
                + ", credentialsExpired=" + credentialsExpired + ", accountLocked=" + accountLocked + ", phoneNumber="
                + phoneNumber + ", userAccountType=" + userAccountType + ", userAccountUserGroups="
                + userAccountUserGroups + ", companyRegistration=" + companyRegistration + ", email=" + email
                + ", federalId=" + federalId + ", city=" + city
                + ", state=" + state + ", country=" + country + ", zipCode=" + zipCode + ", autoRenew=" + autoRenew
                + ", comments=" + comments + ", paymentPlan=" + paymentPlan + ", validTo=" + validTo + ", paymentInfo="
                + paymentInfo + ", trialPeriod=" + trialPeriod + "]";
    }

}
