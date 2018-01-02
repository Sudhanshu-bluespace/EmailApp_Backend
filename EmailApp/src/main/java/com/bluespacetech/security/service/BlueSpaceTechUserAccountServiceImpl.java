package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.notifications.email.worker.EmailUserAccountWorker;
import com.bluespacetech.security.model.AccountApproval;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.AccountApprovalRepository;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.UserAccountRepositoryCustom;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class, ApplicationException.class})
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class BlueSpaceTechUserAccountServiceImpl
  implements BlueSpaceTechUserAccountService
{
  @Autowired
  UserAccountRepository userAccountRepository;
  @Autowired
  AccountApprovalRepository accountApprovalRepository;
  @Autowired
  UserAccountRepositoryCustom userAccountRepositoryCustom;
  @Autowired
  VerificationTokenRepository tokenRepository;
  @Autowired
  EmailUserAccountWorker emailUserAccountWorker;
  @Autowired
  UserAccountService service;
  
  @PreAuthorize("permitAll")
  public UserAccount findUserAccountByUsername(String username)
  {
    return this.userAccountRepository.findUserAccountByUsername(username);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
  public List<UserAccount> getAllUserAccounts()
  {
    return this.userAccountRepository.findAll();
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
  public UserAccount getUserAccountById(Long userAccountId)
  {
    return (UserAccount)this.userAccountRepository.findOne(userAccountId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
  public List<UserAccount> getUserAccountByIds(List<Long> userAccountIds)
  {
    return this.userAccountRepository.findAll(userAccountIds);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('CREATE_USERS')")
  public Map<UserAccount, String> createUserAccount(UserAccount userAccount)
    throws BusinessException
  {
    String randomPassword = RandomStringUtils.randomAlphanumeric(8);
    Map<UserAccount, String> map = new HashMap();
    String hashedPassword = this.service.getEncodedPassword(randomPassword);
    userAccount.setPassword(hashedPassword);
    userAccount.setVerifiedByAdmin(true);
    userAccount.setPhoneNumber("-");
    userAccount.setAccountExpired(false);
    userAccount.setAccountLocked(false);
    userAccount.setActive(true);
    userAccount.setAddressLine1("-");
    userAccount.setAddressLine2("-");
    userAccount.setAutoRenew(false);
    userAccount.setCity("-");
    userAccount.setCountry("-");
    userAccount.setState("-");
    userAccount.setFederalId("-");
    userAccount.setZipCode("-");
    for (UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()) {
      userAccountUserGroup.setUserAccount(userAccount);
    }
    UserAccount newUserAccount = (UserAccount)this.userAccountRepository.save(userAccount);
    map.put(newUserAccount, randomPassword);
    return map;
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('UPDATE_USERS')")
  public UserAccount updateUserAccount(UserAccount userAccount)
    throws BusinessException
  {
    for (UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()) {
      userAccountUserGroup.setUserAccount(userAccount);
    }
    return (UserAccount)this.userAccountRepository.save(userAccount);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('DELETE_USERS')")
  public void deleteUserAccount(Long userAccountId)
  {
    AccountApproval record = this.accountApprovalRepository.findAccountApprovalByIdPendingApproval(userAccountId);
    if (record != null) {
      this.accountApprovalRepository.delete(record.getId());
    }
    VerificationToken token = this.tokenRepository.findVerificationTokenByUserId(userAccountId.longValue());
    if (token != null) {
      this.tokenRepository.delete(token.getId());
    }
    this.userAccountRepository.delete(userAccountId);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
  public List<UserAccount> findUserAccountsBySearchCriteria(UserAccountSearchCriteria userAccountSearchCriteria)
  {
    return this.userAccountRepositoryCustom.findUserAccountsBySearchCriteria(userAccountSearchCriteria);
  }
  
  @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_USER') or hasAuthority('ACC_TYPE_EMPLOYEE')")
  public void changePasswordUserAccount(String oldPassword, String newPassword, String confirmPassword)
    throws BusinessException
  {
    if (!newPassword.equals(confirmPassword)) {
      throw new BusinessException("New Password and Confirm Password do not match.");
    }
    if (newPassword.equals(oldPassword)) {
      throw new BusinessException("New Password and Old Password cannot be same.");
    }
    String userName = ViewUtil.getPrincipal();
    UserAccount userAccount = this.userAccountRepository.findUserAccountByUsername(userName);
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    boolean oldPasspordMatched = encoder.matches(oldPassword, userAccount.getPassword());
    if (!oldPasspordMatched) {
      throw new BusinessException("Current Password is not correct.");
    }
    userAccount.setPassword(encoder.encode(newPassword));
    updateUserAccount(userAccount);
  }
}
