/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.server.analytics.repository.AnalyticsRepositoryCustom;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;

/**
 * The Class AnalyticsServiceImpl.
 *
 * @author Sudhanshu Tripathy
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService
{

    /** The analytics repository. */
    @Autowired
    private AnalyticsRepositoryCustom analyticsRepository;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getRecentCampaignSummary(java.lang.String)
     */
    @Override
    public RepositoryResponseDTO getRecentCampaignSummary(String username)
    {
        // TODO Auto-generated method stub
        return analyticsRepository.findRecentCampaignStats(username);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getRecentCampaignChartSummary(java.lang.String)
     */
    @Override
    public RepositoryResponseChartDTO getRecentCampaignChartSummary(String username)
    {
        // TODO Auto-generated method stub
        return analyticsRepository.findRecentCampaignChartStats(username);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getCampaignWisePerformanceStats(java.lang.String)
     */
    @Override
    public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String username)
    {
        // TODO Auto-generated method stub
        return analyticsRepository.findCampaignWisePerformanceStats(username);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getGroupWiseUnsubscription(java.lang.String)
     */
    @Override
    public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String username)
    {
        // TODO Auto-generated method stub
        return analyticsRepository.findGroupWiseUnsubscriptionStats(username);
    }

    @Override
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
    {
        return analyticsRepository.getCompanyWiseRegistrationStats();
    }

}
