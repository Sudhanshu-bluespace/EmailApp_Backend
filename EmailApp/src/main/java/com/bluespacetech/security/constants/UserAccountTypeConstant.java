package com.bluespacetech.security.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum UserAccountTypeConstant
{
  ACC_TYPE_SUPER_ADMIN("ACC_TYPE_SUPER_ADMIN"),  ACC_TYPE_ADMIN("ACC_TYPE_ADMIN"),  ACC_TYPE_EMPLOYEE("ACC_TYPE_EMPLOYEE"),  ACC_TYPE_USER("ACC_TYPE_USER");
  
  private String accountType;
  
  private UserAccountTypeConstant(String accountType)
  {
    this.accountType = accountType;
  }
  
  public String getAccountType()
  {
    return this.accountType;
  }
  
  public void setAccountType(String accountType)
  {
    this.accountType = accountType;
  }
  
  public static Set<UserAccountTypeConstant> getAllUserAccountTypes()
  {
    return new HashSet(Arrays.asList(values()));
  }
  
  public static UserAccountTypeConstant[] getUserAccountTypesForNewUser()
  {
    UserAccountTypeConstant[] userAccountTypesForNewUser = new UserAccountTypeConstant[3];
    userAccountTypesForNewUser[0] = ACC_TYPE_ADMIN;
    userAccountTypesForNewUser[1] = ACC_TYPE_EMPLOYEE;
    
    userAccountTypesForNewUser[2] = ACC_TYPE_USER;
    
    return userAccountTypesForNewUser;
  }
}
