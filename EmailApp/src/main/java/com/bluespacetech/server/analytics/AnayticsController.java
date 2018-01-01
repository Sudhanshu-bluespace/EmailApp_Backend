/**
 * 
 */
package com.bluespacetech.server.analytics;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.executionqueue.JobProcessingPriorityBlockingQueue;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.ReadReceiptDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.resources.JobStatusResource;
import com.bluespacetech.server.analytics.service.AnalyticsService;

/**
 * The Class AnayticsController.
 *
 * @author sudhanshu
 */
@RestController
@CrossOrigin
@RequestMapping("/analytics")
public class AnayticsController
{
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(AnayticsController.class.getName());

    /** The user account repository. */
    @Autowired
    UserAccountRepository userAccountRepository;
    
    @Autowired
    ContactService contactService;

    /**
     * View analytics page.
     *
     * @param analyticsType the analytics type
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewAnalyticsPage(@RequestParam("analyticsType") String analyticsType, WebRequest request)
    {

        return "analytics";
    }

    /** The analytics service. */
    @Autowired
    private AnalyticsService analyticsService;

    /**
     * Gets the recent campaign summary.
     *
     * @param userName the user name
     * @return the recent campaign summary
     */
    @RequestMapping(value = "/recentSummary", method = RequestMethod.POST)
    public RepositoryResponseDTO getRecentCampaignSummary(@RequestParam("userName") String userName)
    {
        RepositoryResponseDTO response = analyticsService.getRecentCampaignSummary(userName);
        LOGGER.debug("Recent Summary JSON : " + response);
        return response;
    }

    /**
     * Gets the campaign wise performance.
     *
     * @param userName the user name
     * @return the campaign wise performance
     */
    @RequestMapping(value = "/campaignWisePerformance", method = RequestMethod.POST)
    public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformance(@RequestParam("userName") String userName)
    {
        List<CampaignWisePerformanceStatsDTO> response = analyticsService.getCampaignWisePerformanceStats(userName);
        LOGGER.debug("Campaign Wise performance JSON : " + response);
        return response;
    }

    /**
     * Gets the group wise unsubscription.
     *
     * @param userName the user name
     * @return the group wise unsubscription
     */
    @RequestMapping(value = "/groupWiseUnsubscription", method = RequestMethod.POST)
    public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(@RequestParam("userName") String userName)
    {
        List<GroupWiseUnsubscriptionStatsDTO> response = analyticsService.getGroupWiseUnsubscription(userName);
        LOGGER.debug("Grop wise Unsbscription JSON : " + response);
        return response;
    }

    /**
     * Gets the recent campaign chart summary.
     *
     * @param userName the user name
     * @return the recent campaign chart summary
     */
    @RequestMapping(value = "/recentChartSummary", method = RequestMethod.POST)
    public RepositoryResponseChartDTO getRecentCampaignChartSummary(@RequestParam("userName") String userName)
    {
        RepositoryResponseChartDTO response = analyticsService.getRecentCampaignChartSummary(userName);
        LOGGER.debug("Recent Chart Summary JSON : " + response);
        return response;
    }

    /**
     * Gets the company wise registration stats.
     *
     * @return the company wise registration stats
     */
    @RequestMapping(value = "/getCompanyWiseRegistrationStats", method = RequestMethod.GET)
    public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
    {
        List<CompanyWiseRegistrationDTO> response = analyticsService.getCompanyWiseRegistrationStats();
        LOGGER.debug("Company Wise Registration JSON : " + response);
        return response;
    }
    
    /**
     * Gets the company wise registration stats.
     *
     * @return the company wise registration stats
     */
    @RequestMapping(value = "/getReadReceiptInfo", method = RequestMethod.POST)
    public List<ReadReceiptDTO> getReadReceiptInfo(@RequestParam("emailId") String emailId)
    {
        List<ReadReceiptDTO> response = analyticsService.getReadReceiptInfo(Long.parseLong(emailId));
        LOGGER.debug("Read Receipts for Email id " + emailId + " JSON : " + response);
        return response;
    }

    /**
     * Gets the pending approvals.
     *
     * @param age the age
     * @return the pending approvals
     */
    @PostMapping(value = "getRecentUnsubscribes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(@RequestParam("age") String age,@RequestParam("isAdmin")String isAdmin)
    {
        boolean admin = false;
        if(Boolean.TRUE.toString().equalsIgnoreCase(isAdmin))
        {
            admin=true;
        }
        List<RecentUnsubscribesDTO> response = analyticsService.getRecentUnsubscribes(Integer.parseInt(age),admin);
        LOGGER.debug("response : " + response);
        return response;
    }

    /**
     * Gets the pending approvals.
     *
     * @param age the age
     * @return the pending approvals
     */
    @PostMapping(value = "getRecentUnsubscribedCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsubscribedCount(@RequestParam("age") String age,@RequestParam("isAdmin")String isAdmin)
    {
        boolean admin = false;
        if(Boolean.TRUE.toString().equalsIgnoreCase(isAdmin))
        {
            admin=true;
        }
        List<RecentlyUnsubscribedCountDTO> response = analyticsService.getRecentUnsuscribedCount(Integer.parseInt(age),admin);
        LOGGER.debug("response : " + response);
        return response;
    }

    /**
     * Gets the job status data.
     *
     * @param userName the user name
     * @return the job status data
     */
    @PostMapping(value = "getJobStatusData", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobStatusResource> getJobStatusData(@RequestParam("userName") String userName)
    {
        UserAccount user = userAccountRepository.findUserAccountByUsername(userName);
        List<JobStatusResource> response = null;
        if (UserAccountTypeConstant.ACC_TYPE_ADMIN.equals(user.getUserAccountType())
                || UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(user.getUserAccountType()))
        {
            response = analyticsService.getJobStatusData(userName, true);
        }
        else
        {
            response = analyticsService.getJobStatusData(userName, false);
        }
        LOGGER.debug("response Job Status : " + response);
        return response;
    }

    /**
     * Cancel job.
     *
     * @param batchId the batch id
     * @param requestId the request id
     * @param response the response
     * @return the response entity
     */
    @PostMapping(value = "cancelJob", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelJob(@RequestParam("batchId") String batchId,
            @RequestParam("requestId") String requestId, HttpServletResponse response)
    {
        LOGGER.debug("Processing Job cancellation request");
        if (CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(requestId + "|" + batchId))
        {
            EmailJobEndpoint endpoint = CommonUtilCache.getBatchIdToEmailJobEndpointMap()
                    .get(requestId + "|" + batchId);
            if (JobProcessingPriorityBlockingQueue.getQueueInstance().contains(endpoint))
            {
                boolean result = JobProcessingPriorityBlockingQueue.getQueueInstance().remove(endpoint);
                LOGGER.info("Endpoint " + endpoint + " removed from Queue successfully, [result: " + result + "]");
                JobExecutionEntity entity = analyticsService.getJobStatusByBatchIdAndRequestId(requestId, batchId);
                if (entity != null)
                {
                    entity.setStatus("CANCELLED");
                    entity.setLastUpdatedDate(new Timestamp(new Date().getTime()));
                    analyticsService.persistToDB(entity);
                }
                return new ResponseEntity<String>(String.format("{\"Success\":\"%s\"}", "Job Cancellation Successful"),
                        HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<String>(
                        String.format("{\"Error\":\"%s\"}", "Job has already been picked for processing"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
        {
            return new ResponseEntity<String>(String.format("{\"Error\":\"%s\"}", "Job has already completed"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value="getBlockedContacts",produces=MediaType.APPLICATION_JSON_VALUE)
    public List<BlockedContacts> getBlockedContacts()
    {
        return contactService.getBlockedContacts();
    }
}
