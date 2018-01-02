package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface JobExecutionRepository
  extends JpaRepository<JobExecutionEntity, Long>
{
  public abstract JobExecutionEntity findById(Long paramLong);
  
  public abstract JobExecutionEntity findByRequestIdAndBatchIdIgnoreCase(String paramString1, String paramString2);
  
  public abstract List<JobExecutionEntity> findByRequestIdIgnoreCase(String paramString);
  
  public abstract List<JobExecutionEntity> findByStatusIgnoreCase(String paramString);
  
  public abstract List<JobExecutionEntity> findByRequestIdAndStatusIgnoreCaseOrderByCreationDateDesc(String paramString1, String paramString2);
  
  public abstract List<JobExecutionEntity> findAll();
  
  public abstract List<JobExecutionEntity> findBySenderOrderByCreationDateDesc(String paramString);
  
  public abstract List<JobExecutionEntity> findBySenderAndStatusIgnoreCaseOrderByCreationDateDesc(String paramString1, String paramString2);
}
