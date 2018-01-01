package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;


@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;
    
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private VerificationTokenRepository tokenRepository;

	@Override
	@Transactional
	public UserAccount findUserAccountByUsername(final String username) {
		return userAccountRepository.findUserAccountByUsername(username);
	}

	@Override
	public UserAccount save(UserAccount userAccount) {
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_USER);
        UserAccount user = userAccountRepository.save(userAccount);
        BaseResponseDAO response = null;
        if(user!=null)
        {
        	response = new BaseResponseDAO();
        	response.setResponseCode(200);
        	response.setResponseMessage("User "+user.getCreatedUser()+" created successfully");
        }
        return userAccountRepository.findUserAccountByUsername(user.getUsername());       
	}

	@Override
	public void createVerificationToken(UserAccount user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public UserAccount getUser(String username) {
		return userAccountRepository.findUserAccountByUsername(username);
	}
}