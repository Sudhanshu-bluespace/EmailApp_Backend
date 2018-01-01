package com.bluespacetech.security.service;

import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;

public interface UserAccountService {

	UserAccount findUserAccountByUsername(final String username);
    UserAccount save(UserAccount userAccount);
    UserAccount getUser(String verificationToken);
 
    void createVerificationToken(UserAccount user, String token);
 
    VerificationToken getVerificationToken(String VerificationToken);

}