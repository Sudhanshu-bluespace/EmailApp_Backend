/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import com.bluespacetech.server.analytics.repository.AnalyticsRepositoryCustom;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;

// TODO: Auto-generated Javadoc
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

    /** The job execution repository. */
    @Autowired
    private JobExecutionRepository jobExecutionRepository;

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

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getCompanyWiseRegistrationStats()
     */
    @Override
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
    {
        return analyticsRepository.getCompanyWiseRegistrationStats();
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getRecentUnsubscribes(int)
     */
    @Override
    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOfDays)
    {
        return analyticsRepository.getRecentUnsubscribes(numberOfDays);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getRecentUnsuscribedCount(int)
     */
    @Override
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int age)
    {
        return analyticsRepository.getRecentlyUnsubscribedCount(age);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getJobStatusData(java.lang.String)
     */
    @Override
    public List<JobStatusResource> getJobStatusData(String userName,boolean isAdmin)
    {
        List<JobExecutionEntity> list = null;
        if(isAdmin)
        {
            list = jobExecutionRepository.findAll();
        }
        else
        {
            list = jobExecutionRepository.findBySender(userName);
        }
        List<JobStatusResource> resourceList = new ArrayList<>();
        if (list != null)
        {
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

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getJobStatusDataByStatus(java.lang.String, java.lang.String)
     */
    @Override
    public List<JobStatusResource> getJobStatusDataByStatus(String userName, String status)
    {
        List<JobExecutionEntity> list = jobExecutionRepository.findBySenderAndStatusIgnoreCase(userName, status);
        List<JobStatusResource> resourceList = new ArrayList<>();
        if (list != null)
        {
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
    
    @Override
    public JobExecutionEntity persistToDB(JobExecutionEntity endpoint)
    {
        return jobExecutionRepository.save(endpoint);
    }
    
    @Override
    public JobExecutionEntity getJobStatusByBatchIdAndRequestId(String requestId,String batchId)
    {
        return jobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(requestId, batchId);
    }

}
