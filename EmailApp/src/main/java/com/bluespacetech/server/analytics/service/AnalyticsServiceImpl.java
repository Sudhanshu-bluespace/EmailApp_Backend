/**
 * 
 */
package com.bluespacetech.server.analytics.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.notifications.email.entity.EmailReadReceiptTracker;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import com.bluespacetech.notifications.email.repository.ReadReceiptTrackerRepository;
import com.bluespacetech.server.analytics.repository.AnalyticsRepositoryCustom;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.ReadReceiptDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;
import com.bluespacetech.server.analytics.util.JobIdStatusComparator;

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

    /** The read receipt repository. */
    @Autowired
    private ReadReceiptTrackerRepository readReceiptRepository;

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
    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOfDays, boolean isAdmin)
    {
        return analyticsRepository.getRecentUnsubscribes(numberOfDays, isAdmin);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getRecentUnsuscribedCount(int)
     */
    @Override
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsuscribedCount(int age, boolean isAdmin)
    {
        return analyticsRepository.getRecentlyUnsubscribedCount(age, isAdmin);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getJobStatusData(java.lang.String)
     */
    @Override
    public List<JobStatusResource> getJobStatusData(String userName, boolean isAdmin)
    {
        List<JobExecutionEntity> list = null;
        if (isAdmin)
        {
            list = jobExecutionRepository.findAll();
        }
        else
        {
            list = jobExecutionRepository.findBySenderOrderByCreationDateDesc(userName);
        }
        
        Collections.sort(list, new JobIdStatusComparator());
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
                res.setComments(
                        ent.getComments() == null || ent.getComments().trim().isEmpty() ? "-" : ent.getComments());
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
        List<JobExecutionEntity> list = jobExecutionRepository
                .findBySenderAndStatusIgnoreCaseOrderByCreationDateDesc(userName, status);
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
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#persistToDB(com.bluespacetech.notifications.email.entity.JobExecutionEntity)
     */
    @Override
    public JobExecutionEntity persistToDB(JobExecutionEntity endpoint)
    {
        return jobExecutionRepository.save(endpoint);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getJobStatusByBatchIdAndRequestId(java.lang.String, java.lang.String)
     */
    @Override
    public JobExecutionEntity getJobStatusByBatchIdAndRequestId(String requestId, String batchId)
    {
        return jobExecutionRepository.findByRequestIdAndBatchIdIgnoreCase(requestId, batchId);
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.server.analytics.service.AnalyticsService#getReadReceiptInfo(java.lang.Long)
     */
    @Override
    public List<ReadReceiptDTO> getReadReceiptInfo(Long emailId)
    {
        List<EmailReadReceiptTracker> recordList = readReceiptRepository.findByEmailId(emailId);
        List<ReadReceiptDTO> dtoList = new ArrayList<>();
        for(EmailReadReceiptTracker record : recordList)
        {
            ReadReceiptDTO dto = new ReadReceiptDTO();
            dto.setContactId(record.getContactId());
            dto.setContactEmail(record.getContactEmail());
            dto.setGroupId(record.getGroupId());
            dto.setEmailId(record.getEmailId());
            dto.setLastReadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(record.getLastUpdatedDate()));
            dto.setReadCount(record.getReadCount());
            dto.setReadReceiptId(record.getId());
            dtoList.add(dto);
        }
        
        return dtoList;
    }

}
