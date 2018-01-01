package com.bluespacetech.notifications.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluespacetech.notifications.email.entity.EmailReadReceiptTracker;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReadReceiptTrackerRepository.
 */
public interface ReadReceiptTrackerRepository extends JpaRepository<EmailReadReceiptTracker, Long>
{

    /**
     * Find by read receipt id.
     *
     * @param id the id
     * @return the email read receipt tracker
     */
    EmailReadReceiptTracker findById(Long id);

    /**
     * Find by contact email ignore case.
     *
     * @param email the email
     * @return the email read receipt tracker
     */
    List<EmailReadReceiptTracker> findByContactEmailIgnoreCase(String email);

    /**
     * Find by contact id.
     *
     * @param contactId the contact id
     * @return the list
     */
    List<EmailReadReceiptTracker> findByContactId(Long contactId);

    /**
     * Find by group id.
     *
     * @param groupId the group id
     * @return the list
     */
    List<EmailReadReceiptTracker> findByGroupId(Long groupId);

    /**
     * Find by contact id and group id and contact email ignore case.
     *
     * @param contactId the contact id
     * @param groupId the group id
     * @param email the email
     * @return the email read receipt tracker
     */
    EmailReadReceiptTracker findByContactIdAndGroupIdAndEmailId(Long contactId, Long groupId,
            Long emailId);

    /**
     * Find by email id.
     *
     * @param emailId the email id
     * @return the email read receipt tracker
     */
    List<EmailReadReceiptTracker> findByEmailId(Long emailId);
}
