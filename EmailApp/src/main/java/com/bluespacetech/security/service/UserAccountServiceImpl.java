package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;

/**
 * The Class UserAccountServiceImpl.
 * @author Sudhanshu Tripathy
 */
@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService
{

    /** The user account repository. */
    @Autowired
    UserAccountRepository userAccountRepository;

    /** The password encoder. */
    @Autowired
    PasswordEncoder passwordEncoder;

    /** The token repository. */
    @Autowired
    private VerificationTokenRepository tokenRepository;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#findUserAccountByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserAccount findUserAccountByUsername(final String username)
    {
        return userAccountRepository.findUserAccountByUsername(username);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#save(com.bluespacetech.security.model.UserAccount)
     */
    @Override
    public UserAccount save(UserAccount userAccount)
    {

        BaseResponseDAO response = null;
        UserAccount user = userAccountRepository.save(userAccount);
        if (user != null)
        {
            response = new BaseResponseDAO();
            response.setResponseCode(200);
            response.setResponseMessage("User " + user.getCreatedUser() + " created/updated successfully");
        }
        return userAccountRepository.findUserAccountByUsername(user.getUsername());
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#getEncodedPassword(java.lang.String)
     */
    public String getEncodedPassword(String rawPassword)
    {
        return passwordEncoder.encode(rawPassword);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#createVerificationToken(com.bluespacetech.security.model.UserAccount, java.lang.String)
     */
    @Override
    public void createVerificationToken(UserAccount user, String token)
    {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);

    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#getVerificationToken(java.lang.String)
     */
    @Override
    public VerificationToken getVerificationToken(String VerificationToken)
    {
        return tokenRepository.findByToken(VerificationToken);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.UserAccountService#getUser(java.lang.String)
     */
    @Override
    public UserAccount getUser(String username)
    {
        return userAccountRepository.findUserAccountByUsername(username);
    }
}