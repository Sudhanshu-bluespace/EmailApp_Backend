package com.bluespacetech.security.service;

import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl
  implements UserAccountService
{
  @Autowired
  UserAccountRepository userAccountRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private VerificationTokenRepository tokenRepository;
  
  @Transactional
  public UserAccount findUserAccountByUsername(String username)
  {
    return this.userAccountRepository.findUserAccountByUsername(username);
  }
  
  public UserAccount save(UserAccount userAccount)
  {
    BaseResponseDAO response = null;
    UserAccount user = (UserAccount)this.userAccountRepository.save(userAccount);
    if (user != null)
    {
      response = new BaseResponseDAO();
      response.setResponseCode(200L);
      response.setResponseMessage("User " + user.getCreatedUser() + " created/updated successfully");
    }
    return this.userAccountRepository.findUserAccountByUsername(user.getUsername());
  }
  
  public String getEncodedPassword(String rawPassword)
  {
    return this.passwordEncoder.encode(rawPassword);
  }
  
  public void createVerificationToken(UserAccount user, String token)
  {
    VerificationToken myToken = new VerificationToken(token, user);
    this.tokenRepository.save(myToken);
  }
  
  public VerificationToken getVerificationToken(String VerificationToken)
  {
    return this.tokenRepository.findByToken(VerificationToken);
  }
  
  public UserAccount getUser(String username)
  {
    return this.userAccountRepository.findUserAccountByUsername(username);
  }
}
