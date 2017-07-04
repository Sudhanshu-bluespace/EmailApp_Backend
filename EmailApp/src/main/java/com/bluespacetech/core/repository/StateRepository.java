package com.bluespacetech.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.core.model.Country;
import com.bluespacetech.core.model.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long>
{
    public List<State> findAll();
    public State findById(Long id);
    public State findByName(String name);
    public List<State> findByCountry(Country country);
}
