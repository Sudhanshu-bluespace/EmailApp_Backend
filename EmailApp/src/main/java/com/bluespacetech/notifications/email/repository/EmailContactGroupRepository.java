package com.bluespacetech.notifications.email.repository;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface EmailContactGroupRepository
  extends JpaRepository<EmailContactGroup, Long>
{
  public abstract EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(Long paramLong1, Long paramLong2, String paramString);
  
  public abstract EmailContactGroup findByEmailIdAndContactIdAndGroupId(Long paramLong1, Long paramLong2, Long paramLong3);
}
