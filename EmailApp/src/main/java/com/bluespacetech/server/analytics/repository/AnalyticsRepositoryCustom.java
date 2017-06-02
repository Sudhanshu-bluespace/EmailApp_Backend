/**
 * 
 */
package com.bluespacetech.server.analytics.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

/**
 * @author sudhanshu
 *
 */

public interface AnalyticsRepositoryCustom {
	
	public RepositoryResponseDTO findRecentCampaignStats(@Param("userName") String userName);
	public RepositoryResponseChartDTO findRecentCampaignChartStats(@Param("userName") String userName);
	public List<CampaignWisePerformanceStatsDTO> findCampaignWisePerformanceStats(@Param("username") String userName);
	public List<GroupWiseUnsubscriptionStatsDTO> findGroupWiseUnsubscriptionStats(@Param("username") String userName);
	

	}
