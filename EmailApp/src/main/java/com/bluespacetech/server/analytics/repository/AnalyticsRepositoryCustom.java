package com.bluespacetech.server.analytics.repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

public abstract interface AnalyticsRepositoryCustom
{
  public abstract RepositoryResponseDTO findRecentCampaignStats(@Param("userName") String paramString);
  
  public abstract RepositoryResponseChartDTO findRecentCampaignChartStats(@Param("userName") String paramString);
  
  public abstract List<CampaignWisePerformanceStatsDTO> findCampaignWisePerformanceStats(@Param("username") String paramString);
  
  public abstract List<GroupWiseUnsubscriptionStatsDTO> findGroupWiseUnsubscriptionStats(@Param("username") String paramString);
  
  public abstract List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats();
  
  public abstract List<RecentUnsubscribesDTO> getRecentUnsubscribes(@Param("age") int paramInt, @Param("isAdmin") boolean paramBoolean);
  
  public abstract List<RecentlyUnsubscribedCountDTO> getRecentlyUnsubscribedCount(int paramInt, boolean paramBoolean);
}
