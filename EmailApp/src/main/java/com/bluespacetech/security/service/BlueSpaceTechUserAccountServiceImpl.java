package com.bluespacetech.security.service;

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

/**
 * The Class BlueSpaceTechUserAccountServiceImpl.
 * @author Sudhanshu Tripathy
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
        ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class BlueSpaceTechUserAccountServiceImpl implements BlueSpaceTechUserAccountService
{

    /** The user account repository. */
    @Autowired
    UserAccountRepository userAccountRepository;

    /** The account approval repository. */
    @Autowired
    AccountApprovalRepository accountApprovalRepository;

    /** The user account repository custom. */
    @Autowired
    UserAccountRepositoryCustom userAccountRepositoryCustom;

    /** The token repository. */
    @Autowired
    VerificationTokenRepository tokenRepository;

    /** The email user account worker. */
    @Autowired
    EmailUserAccountWorker emailUserAccountWorker;
    
    @Autowired
    UserAccountService service;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#findUserAccountByUsername(java.lang.String)
     */
    @Override
    @PreAuthorize("permitAll")
    public UserAccount findUserAccountByUsername(final String username)
    {
        return userAccountRepository.findUserAccountByUsername(username);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#getAllUserAccounts()
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
    public List<UserAccount> getAllUserAccounts()
    {
        return userAccountRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#getUserAccountById(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
    public UserAccount getUserAccountById(final Long userAccountId)
    {
        return userAccountRepository.findOne(userAccountId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#getUserAccountByIds(java.util.List)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
    public List<UserAccount> getUserAccountByIds(final List<Long> userAccountIds)
    {
        return userAccountRepository.findAll(userAccountIds);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#createUserAccount(com.bluespacetech.security.model.UserAccount)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('CREATE_USERS')")
    public Map<UserAccount,String> createUserAccount(final UserAccount userAccount) throws BusinessException
    {
        final String randomPassword = RandomStringUtils.randomAlphanumeric(8);
        Map<UserAccount,String> map = new HashMap<>();
        final String hashedPassword = service.getEncodedPassword(randomPassword);
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

        for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups())
        {
            userAccountUserGroup.setUserAccount(userAccount);
        }
        final UserAccount newUserAccount = userAccountRepository.save(userAccount);
        map.put(newUserAccount, randomPassword);
        return map;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#updateUserAccount(com.bluespacetech.security.model.UserAccount)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('UPDATE_USERS')")
    public UserAccount updateUserAccount(final UserAccount userAccount) throws BusinessException
    {
        for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups())
        {
            userAccountUserGroup.setUserAccount(userAccount);
        }
        return userAccountRepository.save(userAccount);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#deleteUserAccount(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('DELETE_USERS')")
    public void deleteUserAccount(final Long userAccountId)
    {
        AccountApproval record = accountApprovalRepository.findAccountApprovalByIdPendingApproval(userAccountId);
        if(record!=null)
        {
            accountApprovalRepository.delete(record.getId());
        }
        
        VerificationToken token = tokenRepository.findVerificationTokenByUserId(userAccountId);
        
        if(token!=null)
        {
            tokenRepository.delete(token.getId());
        }
        
        userAccountRepository.delete(userAccountId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#findUserAccountsBySearchCriteria(com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
    public List<UserAccount> findUserAccountsBySearchCriteria(final UserAccountSearchCriteria userAccountSearchCriteria)
    {
        return userAccountRepositoryCustom.findUserAccountsBySearchCriteria(userAccountSearchCriteria);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.BlueSpaceTechUserAccountService#changePasswordUserAccount(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_USER') or hasAuthority('ACC_TYPE_EMPLOYEE')")
    public void changePasswordUserAccount(final String oldPassword, final String newPassword,
            final String confirmPassword) throws BusinessException
    {

        if (!newPassword.equals(confirmPassword))
        {
            throw new BusinessException("New Password and Confirm Password do not match.");
        }

        if (newPassword.equals(oldPassword))
        {
            throw new BusinessException("New Password and Old Password cannot be same.");
        }

        final String userName = ViewUtil.getPrincipal();
        final UserAccount userAccount = userAccountRepository.findUserAccountByUsername(userName);
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        final boolean oldPasspordMatched = encoder.matches(oldPassword, userAccount.getPassword());
        if (!oldPasspordMatched)
        {
            throw new BusinessException("Current Password is not correct.");
        }
        userAccount.setPassword(encoder.encode(newPassword));
        this.updateUserAccount(userAccount);
    }

}