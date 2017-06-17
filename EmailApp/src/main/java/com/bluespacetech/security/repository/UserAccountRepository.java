package com.bluespacetech.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserAccount;

/**
 * The Interface UserAccountRepository.
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{

    /**
     * Find user account by username.
     *
     * @param username the username
     * @return the user account
     */
    UserAccount findUserAccountByUsername(final String username);

    /**
     * Find user account by email.
     *
     * @param username the username
     * @return the user account
     */
    UserAccount findUserAccountByEmail(final String username);

    /**
     * Find user account by id.
     *
     * @param id the id
     * @return the user account
     */
    UserAccount findUserAccountById(final long id);

}
