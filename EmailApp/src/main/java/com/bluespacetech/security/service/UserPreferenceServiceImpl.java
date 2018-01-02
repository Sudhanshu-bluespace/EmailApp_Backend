package com.bluespacetech.security.service;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;
import com.bluespacetech.security.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor={Exception.class, RuntimeException.class, BusinessException.class})
public class UserPreferenceServiceImpl
  implements UserPreferenceService
{
  @Autowired
  private UserPreferenceRepository userPreferenceRepository;
  @Autowired
  private UserAccountService userAccountService;
  
  public UserPreference saveUserPreference(UserPreference userPreference)
    throws Exception
  {
    UserPreference result = userPreference;
    
    UserAccount userAccount = this.userAccountService.findUserAccountByUsername(ViewUtil.getPrincipal());
    if (userAccount == null) {
      throw new Exception("Account does not exist for user. Cannot save preferences.");
    }
    result.setUserAccount(userAccount);
    return (UserPreference)this.userPreferenceRepository.save(result);
  }
  
  public UserPreference findUserPreferenceByUserAccount(UserAccount userAccount)
  {
    UserPreference userPreference = this.userPreferenceRepository.findUserPreferenceByUserAccount(userAccount);
    if (userPreference == null)
    {
      userPreference = new UserPreference();
      userPreference.setUserAccount(userAccount);
      userPreference = (UserPreference)this.userPreferenceRepository.save(userPreference);
    }
    return userPreference;
  }
  
  public UserPreference getCurrentUserPreference()
    throws Exception
  {
    UserAccount userAccount = this.userAccountService.findUserAccountByUsername(ViewUtil.getPrincipal());
    if (userAccount == null) {
      throw new Exception("No current user.");
    }
    return findUserPreferenceByUserAccount(userAccount);
  }
}
