package com.bluespacetech.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;

/**
 * The Interface VerificationTokenRepository.
 * @author Sudhanshu Tripathy
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>
{

    /**
     * Find by token.
     *
     * @param token the token
     * @return the verification token
     */
    VerificationToken findByToken(String token);

    /**
     * Find token by user.
     *
     * @param user the user
     * @return the verification token
     */
    VerificationToken findTokenByUser(UserAccount user);

    /**
     * Find verification token by user id.
     *
     * @param id the id
     * @return the verification token
     */
    VerificationToken findVerificationTokenByUserId(long id);
}