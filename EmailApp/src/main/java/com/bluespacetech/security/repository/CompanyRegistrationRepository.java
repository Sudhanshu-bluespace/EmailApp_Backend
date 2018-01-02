package com.bluespacetech.security.repository;

import com.bluespacetech.security.model.CompanyRegistration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface CompanyRegistrationRepository
  extends JpaRepository<CompanyRegistration, Long>
{
  public abstract CompanyRegistration findCompanyRegistrationById(long paramLong);
  
  public abstract List<CompanyRegistration> findAll();
  
  public abstract CompanyRegistration findById(Long paramLong);
  
  public abstract CompanyRegistration findCompanyRegistrationByCompanyNameIgnoreCase(String paramString);
  
  public abstract List<CompanyRegistration> findCompanyRegistrationByCompanyNameLike(String paramString);
}
