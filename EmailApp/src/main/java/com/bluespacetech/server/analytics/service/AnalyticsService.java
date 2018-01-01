/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.List;

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

    /**
     * Gets the company wise registration stats.
     *
     * @return the company wise registration stats
     */
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats();

    /**
     * Gets the recent unsubscribes.
     *
     * @param numberOFDays the number OF days
     * @param isAdmin the is admin
     * @return the recent unsubscribes
     */
    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOFDays, boolean isAdmin);

    /**
     * Gets the recent unsuscribed count.
     *
     * @param age the age
     * @param isAdmin the is admin
     * @return the recent unsuscribed count
     */
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int age, boolean isAdmin);

    /**
     * Gets the job status data.
     *
     * @param userName the user name
     * @param isAdmin the is admin
     * @return the job status data
     */
    public List<JobStatusResource> getJobStatusData(String userName, boolean isAdmin);

    /**
     * Gets the job status data by status.
     *
     * @param userName the user name
     * @param status the status
     * @return the job status data by status
     */
    public List<JobStatusResource> getJobStatusDataByStatus(String userName, String status);

    /**
     * Persist to DB.
     *
     * @param entity the entity
     * @return the job execution entity
     */
    public JobExecutionEntity persistToDB(JobExecutionEntity entity);

    /**
     * Gets the job status by batch id and request id.
     *
     * @param requestId the request id
     * @param batchId the batch id
     * @return the job status by batch id and request id
     */
    public JobExecutionEntity getJobStatusByBatchIdAndRequestId(String requestId, String batchId);

    /**
     * Gets the read receipt info.
     *
     * @param emailId the email id
     * @return the read receipt info
     */
    public List<ReadReceiptDTO> getReadReceiptInfo(Long emailId);
}
