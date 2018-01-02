package com.bluespacetech.core.repository;

import com.bluespacetech.core.model.City;
import com.bluespacetech.core.model.State;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface CityRepository
  extends JpaRepository<City, Long>
{
  public abstract List<City> findAll();
  
  public abstract City findById(Long paramLong);
  
  public abstract City findByName(String paramString);
  
  public abstract List<City> findByState(State paramState);
}
