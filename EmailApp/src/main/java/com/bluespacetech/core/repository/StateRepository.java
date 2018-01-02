package com.bluespacetech.core.repository;

import com.bluespacetech.core.model.Country;
import com.bluespacetech.core.model.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface StateRepository
  extends JpaRepository<State, Long>
{
  public abstract List<State> findAll();
  
  public abstract State findById(Long paramLong);
  
  public abstract State findByName(String paramString);
  
  public abstract List<State> findByCountry(Country paramCountry);
}
