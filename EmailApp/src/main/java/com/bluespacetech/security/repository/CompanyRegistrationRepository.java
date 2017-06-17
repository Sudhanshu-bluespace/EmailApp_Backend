package com.bluespacetech.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.security.model.CompanyRegistration;

/**
 * The Interface CompanyRegistrationRepository.
 * @author Sudhanshu Tripathy
 */
public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration, Long>
{

    /**
     * Find company registration by id.
     *
     * @param id the id
     * @return the company registration
     */
    public CompanyRegistration findCompanyRegistrationById(long id);

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    public List<CompanyRegistration> findAll();

    /**
     * Find company registration by company name ignore case.
     *
     * @param name the name
     * @return the company registration
     */
    public CompanyRegistration findCompanyRegistrationByCompanyNameIgnoreCase(String name);

    /**
     * Find company registration by company name like.
     *
     * @param name the name
     * @return the list
     */
    public List<CompanyRegistration> findCompanyRegistrationByCompanyNameLike(String name);

}
