package com.bluespacetech.server.analytics.service;

import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.ReadReceiptDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;
import java.util.List;

public abstract interface AnalyticsService
{
  public abstract RepositoryResponseDTO getRecentCampaignSummary(String paramString);
  
  public abstract RepositoryResponseChartDTO getRecentCampaignChartSummary(String paramString);
  
  public abstract List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String paramString);
  
  public abstract List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String paramString);
  
  public abstract List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats();
  
  public abstract List<RecentUnsubscribesDTO> getRecentUnsubscribes(int paramInt, boolean paramBoolean);
  
  public abstract List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int paramInt, boolean paramBoolean);
  
  public abstract List<JobStatusResource> getJobStatusData(String paramString, boolean paramBoolean);
  
  public abstract List<JobStatusResource> getJobStatusDataByStatus(String paramString1, String paramString2);
  
  public abstract JobExecutionEntity persistToDB(JobExecutionEntity paramJobExecutionEntity);
  
  public abstract JobExecutionEntity getJobStatusByBatchIdAndRequestId(String paramString1, String paramString2);
  
  public abstract List<ReadReceiptDTO> getReadReceiptInfo(Long paramLong);
}
