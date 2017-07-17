/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.List;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;

/**
 * The Interface AnalyticsService.
 *
 * @author sudhanshu
 */
public interface AnalyticsService
{

    /**
     * Gets the recent campaign summary.
     *
     * @param username the username
     * @return the recent campaign summary
     */
    public RepositoryResponseDTO getRecentCampaignSummary(String username);

    /**
     * Gets the recent campaign chart summary.
     *
     * @param username the username
     * @return the recent campaign chart summary
     */
    public RepositoryResponseChartDTO getRecentCampaignChartSummary(String username);

    /**
     * Gets the campaign wise performance stats.
     *
     * @param username the username
     * @return the campaign wise performance stats
     */
    public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformanceStats(String username);

    /**
     * Gets the group wise unsubscription.
     *
     * @param username the username
     * @return the group wise unsubscription
     */
    public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(String username);
    
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats();

    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOFDays,boolean isAdmin);
    
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int age,boolean isAdmin);
    
    public List<JobStatusResource> getJobStatusData(String userName,boolean isAdmin);
    
    public List<JobStatusResource> getJobStatusDataByStatus(String userName,String status);
    
    public JobExecutionEntity persistToDB(JobExecutionEntity entity);
    
    public JobExecutionEntity getJobStatusByBatchIdAndRequestId(String requestId,String batchId);
}
