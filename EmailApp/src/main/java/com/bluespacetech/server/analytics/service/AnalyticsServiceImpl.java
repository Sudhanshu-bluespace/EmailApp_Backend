/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.server.analytics.repository.AnalyticsRepositoryCustom;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;

/**
 * @author sudhanshu
 *
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
	
	@Autowired
	private AnalyticsRepositoryCustom analyticsRepository;

	@Override
	public RepositoryResponseDTO getRecentCampaignSummary(String username) {
		// TODO Auto-generated method stub
		return analyticsRepository.findRecentCampaignStats(username);
	}
	
	@Override
	public RepositoryResponseChartDTO getRecentCampaignChartSummary(String username) {
		// TODO Auto-generated method stub
		return analyticsRepository.findRecentCampaignChartStats(username);
	}

	@Override
	public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String username) {
		// TODO Auto-generated method stub
		return analyticsRepository.findCampaignWisePerformanceStats(username);
	}

	@Override
	public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String username) {
		// TODO Auto-generated method stub
		return analyticsRepository.findGroupWiseUnsubscriptionStats(username);
	}

}
