package com.bluespacetech.server.analytics.service;

import com.bluespacetech.notifications.email.entity.EmailReadReceiptTracker;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import com.bluespacetech.notifications.email.repository.ReadReceiptTrackerRepository;
import com.bluespacetech.server.analytics.repository.AnalyticsRepositoryCustom;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.ReadReceiptDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;
import com.bluespacetech.server.analytics.util.JobIdStatusComparator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsServiceImpl
  implements AnalyticsService
{
  @Autowired
  private AnalyticsRepositoryCustom analyticsRepository;
  @Autowired
  private JobExecutionRepository jobExecutionRepository;
  @Autowired
  private ReadReceiptTrackerRepository readReceiptRepository;
  
  public RepositoryResponseDTO getRecentCampaignSummary(String username)
  {
    return this.analyticsRepository.findRecentCampaignStats(username);
  }
  
  public RepositoryResponseChartDTO getRecentCampaignChartSummary(String username)
  {
    return this.analyticsRepository.findRecentCampaignChartStats(username);
  }
  
  public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String username)
  {
    return this.analyticsRepository.findCampaignWisePerformanceStats(username);
  }
  
  public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String username)
  {
    return this.analyticsRepository.findGroupWiseUnsubscriptionStats(username);
  }
  
  public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
  {
    return this.analyticsRepository.getCompanyWiseRegistrationStats();
  }
  
  public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOfDays, boolean isAdmin)
  {
    return this.analyticsRepository.getRecentUnsubscribes(numberOfDays, isAdmin);
  }
  
  public List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int age, boolean isAdmin)
  {
    return this.analyticsRepository.getRecentlyUnsubscribedCount(age, isAdmin);
  }
  
  public List<JobStatusResource> getJobStatusData(String userName, boolean isAdmin)
  {
    List<JobExecutionEntity> list = null;
    if (isAdmin) {
      list = this.jobExecutionRepository.findAll();
    } else {
      list = this.jobExecutionRepository.findBySenderOrderByCreationDateDesc(userName);
    }
    Collections.sort(list, new JobIdStatusComparator());
    List<JobStatusResource> resourceList = new ArrayList();
    if (list != null) {
      for (JobExecutionEntity ent : list)
      {
        JobStatusResource res = new JobStatusResource();
        res.setJobId(ent.getJobId());
        res.setRequestId(ent.getRequestId());
        res.setBatchId(Long.valueOf(ent.getBatchId()));
        res.setCampaignName(ent.getCampaignName());
        res.setEmailCount(ent.getEmailCount());
        res.setSender(ent.getSender());
        res.setStatus(ent.getStatus());
        res.setComments(
          (ent.getComments() == null) || (ent.getComments().trim().isEmpty()) ? "-" : ent.getComments());
        resourceList.add(res);
      }
    }
    return resourceList;
  }
  
  public List<JobStatusResource> getJobStatusDataByStatus(String userName, String status)
  {
    List<JobExecutionEntity> list = this.jobExecutionRepository.findBySenderAndStatusIgnoreCaseOrderByCreationDateDesc(userName, status);
    List<JobStatusResource> resourceList = new ArrayList();
    if (list != null) {
      for (JobExecutionEntity ent : list)
      {
        JobStatusResource res = new JobStatusResource();
        res.setJobId(ent.getJobId());
        res.setRequestId(ent.getRequestId());
        res.setBatchId(Long.valueOf(ent.getBatchId()));
        res.setCampaignName(ent.getCampaignName());
        res.setEmailCount(ent.getEmailCount());
        res.setSender(ent.getSender());
        res.setStatus(ent.getStatus());
        resourceList.add(res);
      }
    }
    return resourceList;
  }
  
  public JobExecutionEntity persistToDB(JobExecutionEntity endpoint)
  {
    return (JobExecutionEntity)this.jobExecutionRepository.save(endpoint);
  }
  
  public JobExecutionEntity getJobStatusByBatchIdAndRequestId(String requestId, String batchId)
  {
    return this.jobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(requestId, batchId);
  }
  
  public List<ReadReceiptDTO> getReadReceiptInfo(Long emailId)
  {
    List<EmailReadReceiptTracker> recordList = this.readReceiptRepository.findByEmailId(emailId);
    List<ReadReceiptDTO> dtoList = new ArrayList();
    for (EmailReadReceiptTracker record : recordList)
    {
      ReadReceiptDTO dto = new ReadReceiptDTO();
      dto.setContactId(record.getContactId());
      dto.setContactEmail(record.getContactEmail());
      dto.setGroupId(record.getGroupId());
      dto.setEmailId(record.getEmailId());
      dto.setLastReadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(record.getLastUpdatedDate()));
      dto.setReadCount(record.getReadCount().intValue());
      dto.setReadReceiptId(record.getId());
      dtoList.add(dto);
    }
    return dtoList;
  }
}
