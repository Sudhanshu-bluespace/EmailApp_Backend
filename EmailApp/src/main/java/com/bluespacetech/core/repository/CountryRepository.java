/**
 * 
 */
package com.bluespacetech.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.core.model.Country;

/**
 * @author sudhanshu
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country,Long>
{
    public List<Country> findAll();
    public Country findById(Long id);
    public Country findByShortNameIgnoreCase(String name);
    public Country findByFullNameIgnoreCase(String name);
}
