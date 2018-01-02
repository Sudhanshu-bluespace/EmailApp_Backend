package com.bluespacetech.core.repository;

import com.bluespacetech.core.model.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface CountryRepository
  extends JpaRepository<Country, Long>
{
  public abstract List<Country> findAll();
  
  public abstract Country findById(Long paramLong);
  
  public abstract Country findByShortNameIgnoreCase(String paramString);
  
  public abstract Country findByFullNameIgnoreCase(String paramString);
}
