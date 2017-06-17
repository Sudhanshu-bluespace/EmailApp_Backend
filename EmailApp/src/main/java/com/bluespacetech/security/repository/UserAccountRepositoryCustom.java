package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;

/**
 * The Interface UserAccountRepositoryCustom.
 *
 * @author pradeep created date 30-Jan-2015
 */
public interface UserAccountRepositoryCustom
{

    /**
     * Find user accounts by search criteria.
     *
     * @param userAccountSearchCriteria the user account search criteria
     * @return the list
     */
    List<UserAccount> findUserAccountsBySearchCriteria(final UserAccountSearchCriteria userAccountSearchCriteria);

}
