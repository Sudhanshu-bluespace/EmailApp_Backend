package com.bluespacetech.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.core.model.City;
import com.bluespacetech.core.model.State;

@Repository
public interface CityRepository extends JpaRepository<City,Long>
{
    public List<City> findAll();
    public City findById(Long id);
    public City findByName(String name);
    public List<City> findByState(State state);
}
