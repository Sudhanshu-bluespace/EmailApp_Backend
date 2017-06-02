/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.List;

import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;

/**
 * @author sudhanshu
 *
 */
public interface AnalyticsService {

	public RepositoryResponseDTO getRecentCampaignSummary(String username);
	public RepositoryResponseChartDTO getRecentCampaignChartSummary(String username);
	public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String username);
	public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String username);
}
