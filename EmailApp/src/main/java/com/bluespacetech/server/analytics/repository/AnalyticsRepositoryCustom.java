/**
 * 
 */
package com.bluespacetech.server.analytics.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

/**
 * The Interface AnalyticsRepositoryCustom.
 *
 * @author sudhanshu
 */

public interface AnalyticsRepositoryCustom
{

    /**
     * Find recent campaign stats.
     *
     * @param userName the user name
     * @return the repository response DTO
     */
    public RepositoryResponseDTO findRecentCampaignStats(@Param("userName") String userName);

    /**
     * Find recent campaign chart stats.
     *
     * @param userName the user name
     * @return the repository response chart DTO
     */
    public RepositoryResponseChartDTO findRecentCampaignChartStats(@Param("userName") String userName);

    /**
     * Find campaign wise performance stats.
     *
     * @param userName the user name
     * @return the list
     */
    public List<CampaignWisePerformanceStatsDTO> findCampaignWisePerformanceStats(@Param("username") String userName);

    /**
     * Find group wise unsubscription stats.
     *
     * @param userName the user name
     * @return the list
     */
    public List<GroupWiseUnsubscriptionStatsDTO> findGroupWiseUnsubscriptionStats(@Param("username") String userName);   
    
    /**
     * Gets the company wise registration stats.
     *
     * @return the company wise registration stats
     */
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats();

}
