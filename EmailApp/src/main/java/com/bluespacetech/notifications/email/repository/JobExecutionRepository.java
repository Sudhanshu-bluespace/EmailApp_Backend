package com.bluespacetech.notifications.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.notifications.email.entity.JobExecutionEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface JobExecutionRepository.
 */
@Repository
public interface JobExecutionRepository extends JpaRepository<JobExecutionEntity,Long>
{
    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the job execution entity
     */
    public JobExecutionEntity findById(Long id);
    
    /**
     * Find by request id and batch id ignore case.
     *
     * @param requestId the request id
     * @param batchId the batch id
     * @return the job execution entity
     */
    public JobExecutionEntity findByRequestIdAndBatchIdIgnoreCase(String requestId,String batchId);
    
    /**
     * Find by request id ignore case.
     *
     * @param requestId the request id
     * @return the list
     */
    public List<JobExecutionEntity> findByRequestIdIgnoreCase(String requestId);
    
    /**
     * Find by status ignore case.
     *
     * @param status the status
     * @return the list
     */
    public List<JobExecutionEntity> findByStatusIgnoreCase(String status);
    
    /**
     * Find by request id and status ignore case.
     *
     * @param requestId the request id
     * @param status the status
     * @return the list
     */
    public List<JobExecutionEntity> findByRequestIdAndStatusIgnoreCaseOrderByCreationDateDesc(String requestId,String status);
    
    /* (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    public List<JobExecutionEntity> findAll();
    
    /**
     * Find by sender.
     *
     * @param userName the user name
     * @return the list
     */
    public List<JobExecutionEntity> findBySenderOrderByCreationDateDesc(String userName);
    
    /**
     * Find by sender and status ignore case.
     *
     * @param userName the user name
     * @param status the status
     * @return the list
     */
    public List<JobExecutionEntity> findBySenderAndStatusIgnoreCaseOrderByCreationDateDesc(String userName,String status);
}
