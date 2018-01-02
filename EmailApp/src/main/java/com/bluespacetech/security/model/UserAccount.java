package com.bluespacetech.security.model;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACCOUNT")
public class UserAccount
  extends BaseEntity
  implements Serializable
{
  private static final long serialVersionUID = 8617426954436904583L;
  @Column(name="USERNAME", nullable=false, length=40, unique=true)
  private String username;
  @Column(name="PASSWORD", nullable=false, length=1000)
  private String password;
  @Column(name="FIRST_NAME", nullable=false, length=20)
  private String firstName;
  @Column(name="MIDDLE_NAME", nullable=true, length=20)
  private String middleName;
  @Column(name="LAST_NAME", nullable=false, length=20)
  private String lastName;
  @Column(name="ADDRESS_LINE1", nullable=false, length=250)
  private String addressLine1;
  @Column(name="ADDRESS_LINE2", nullable=true, length=150)
  private String addressLine2;
  @Column(name="ACTIVE", nullable=false)
  private boolean active;
  @Column(name="VERIFIED_BY_ADMIN", nullable=false)
  private boolean verifiedByAdmin;
  @Column(name="ACC_EXPIRED", nullable=false)
  private boolean accountExpired;
  @Column(name="CRDTLS_EXPIRED", nullable=false)
  private boolean credentialsExpired;
  @Column(name="ACC_LOCKED", nullable=false)
  private boolean accountLocked;
  @Column(name="PHONE_NUMBER", nullable=false)
  private String phoneNumber;
  @Column(name="USER_ACCOUNT_TYPE")
  @Enumerated(EnumType.STRING)
  private UserAccountTypeConstant userAccountType;
  @OneToMany(mappedBy="userAccount", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval=true)
  private Collection<UserAccountUserGroup> userAccountUserGroups;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="COMPANY_NAME", nullable=false, referencedColumnName="COMPANY_NAME")
  private CompanyRegistration companyRegistration;
  @Column(name="EMAIL", nullable=false, unique=true)
  private String email;
  @Column(name="FEDERAL_ID", nullable=false)
  private String federalId;
  @Column(name="CITY", nullable=false)
  private String city;
  @Column(name="STATE", nullable=false)
  private String state;
  @Column(name="COUNTRY", nullable=false)
  private String country;
  @Column(name="ZIP_CODE", nullable=false)
  private String zipCode;
  
  public static long getSerialversionuid()
  {
    return 8617426954436904583L;
  }
  
  @Column(name="AUTO_RENEW")
  private Boolean autoRenew = Boolean.valueOf(false);
  @Column(name="COMMENTS")
  private String comments;
  @Column(name="PAYMENT_PLAN")
  private String paymentPlan;
  @Column(name="VALID_TO")
  private String validTo;
  @Column(name="PAYMENT_INFO")
  private String paymentInfo;
  @Column(name="TRIAL_PERIOD")
  private String trialPeriod;
  
  public String getAddressLine2()
  {
    return this.addressLine2;
  }
  
  public String getAddressLine1()
  {
    return this.addressLine1;
  }
  
  public Boolean getAutoRenew()
  {
    return this.autoRenew;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public String getComments()
  {
    return this.comments;
  }
  
  public CompanyRegistration getCompanyRegistration()
  {
    return this.companyRegistration;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getFederalId()
  {
    return this.federalId;
  }
  
  public String getFirstName()
  {
    return this.firstName;
  }
  
  public String getLastName()
  {
    return this.lastName;
  }
  
  public String getMiddleName()
  {
    return this.middleName;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPaymentInfo()
  {
    return this.paymentInfo;
  }
  
  public String getPaymentPlan()
  {
    return this.paymentPlan;
  }
  
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public String getTrialPeriod()
  {
    return this.trialPeriod;
  }
  
  public UserAccountTypeConstant getUserAccountType()
  {
    return this.userAccountType;
  }
  
  public Collection<UserAccountUserGroup> getUserAccountUserGroups()
  {
    return this.userAccountUserGroups;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public String getValidTo()
  {
    return this.validTo;
  }
  
  public String getZipCode()
  {
    return this.zipCode;
  }
  
  public boolean isAccountExpired()
  {
    return this.accountExpired;
  }
  
  public boolean isAccountLocked()
  {
    return this.accountLocked;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public boolean isAutoRenew()
  {
    return this.autoRenew.booleanValue();
  }
  
  public boolean isCredentialsExpired()
  {
    return this.credentialsExpired;
  }
  
  public boolean isVerifiedByAdmin()
  {
    return this.verifiedByAdmin;
  }
  
  public void setAccountExpired(boolean accountExpired)
  {
    this.accountExpired = accountExpired;
  }
  
  public void setAccountLocked(boolean accountLocked)
  {
    this.accountLocked = accountLocked;
  }
  
  public void setActive(boolean active)
  {
    this.active = active;
  }
  
  public void setAddressLine2(String addressLine2)
  {
    this.addressLine2 = addressLine2;
  }
  
  public void setAddressLine1(String addressLine1)
  {
    this.addressLine1 = addressLine1;
  }
  
  public void setAutoRenew(boolean autoRenew)
  {
    this.autoRenew = Boolean.valueOf(autoRenew);
  }
  
  public void setAutoRenew(Boolean autoRenew)
  {
    this.autoRenew = autoRenew;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public void setComments(String comments)
  {
    this.comments = comments;
  }
  
  public void setCompanyRegistration(CompanyRegistration companyRegistration)
  {
    this.companyRegistration = companyRegistration;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public void setCredentialsExpired(boolean credentialsExpired)
  {
    this.credentialsExpired = credentialsExpired;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public void setFederalId(String federalId)
  {
    this.federalId = federalId;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setPaymentInfo(String paymentInfo)
  {
    this.paymentInfo = paymentInfo;
  }
  
  public void setPaymentPlan(String paymentPlan)
  {
    this.paymentPlan = paymentPlan;
  }
  
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public void setTrialPeriod(String trialPeriod)
  {
    this.trialPeriod = trialPeriod;
  }
  
  public void setUserAccountType(UserAccountTypeConstant userAccountType)
  {
    this.userAccountType = userAccountType;
  }
  
  public void setUserAccountUserGroups(Collection<UserAccountUserGroup> userAccountUserGroups)
  {
    this.userAccountUserGroups = userAccountUserGroups;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public void setValidTo(String validTo)
  {
    this.validTo = validTo;
  }
  
  public void setVerifiedByAdmin(boolean verifiedByAdmin)
  {
    this.verifiedByAdmin = verifiedByAdmin;
  }
  
  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }
  
  public String toString()
  {
    return "UserAccount [username=" + this.username + ", firstName=" + this.firstName + ", middleName=" + this.middleName + ", lastName=" + this.lastName + ", addressLine1=" + this.addressLine1 + ", addressLine2=" + this.addressLine2 + ", active=" + this.active + ", verifiedByAdmin=" + this.verifiedByAdmin + ", accountExpired=" + this.accountExpired + ", credentialsExpired=" + this.credentialsExpired + ", accountLocked=" + this.accountLocked + ", phoneNumber=" + this.phoneNumber + ", userAccountType=" + this.userAccountType + ", userAccountUserGroups=" + this.userAccountUserGroups + ", companyRegistration=" + this.companyRegistration + ", email=" + this.email + ", federalId=" + this.federalId + ", city=" + this.city + ", state=" + this.state + ", country=" + this.country + ", zipCode=" + this.zipCode + ", autoRenew=" + this.autoRenew + ", comments=" + this.comments + ", paymentPlan=" + this.paymentPlan + ", validTo=" + this.validTo + ", paymentInfo=" + this.paymentInfo + ", trialPeriod=" + this.trialPeriod + "]";
  }
}
