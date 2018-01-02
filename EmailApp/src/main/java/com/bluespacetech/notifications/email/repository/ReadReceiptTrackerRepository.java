package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.EmailReadReceiptTracker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface ReadReceiptTrackerRepository
  extends JpaRepository<EmailReadReceiptTracker, Long>
{
  public abstract EmailReadReceiptTracker findById(Long paramLong);
  
  public abstract List<EmailReadReceiptTracker> findByContactEmailIgnoreCase(String paramString);
  
  public abstract List<EmailReadReceiptTracker> findByContactId(Long paramLong);
  
  public abstract List<EmailReadReceiptTracker> findByGroupId(Long paramLong);
  
  public abstract EmailReadReceiptTracker findByContactIdAndGroupIdAndEmailId(Long paramLong1, Long paramLong2, Long paramLong3);
  
  public abstract List<EmailReadReceiptTracker> findByEmailId(Long paramLong);
}
