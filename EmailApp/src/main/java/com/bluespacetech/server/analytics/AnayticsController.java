package com.bluespacetech.server.analytics;

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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@CrossOrigin
@RequestMapping({"/analytics"})
public class AnayticsController
{
  private static final Logger LOGGER = LogManager.getLogger(AnayticsController.class.getName());
  @Autowired
  UserAccountRepository userAccountRepository;
  @Autowired
  ContactService contactService;
  @Autowired
  private AnalyticsService analyticsService;
  
  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String viewAnalyticsPage(@RequestParam("analyticsType") String analyticsType, WebRequest request)
  {
    return "analytics";
  }
  
  @RequestMapping(value={"/recentSummary"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public RepositoryResponseDTO getRecentCampaignSummary(@RequestParam("userName") String userName)
  {
    RepositoryResponseDTO response = this.analyticsService.getRecentCampaignSummary(userName);
    LOGGER.debug("Recent Summary JSON : " + response);
    return response;
  }
  
  @RequestMapping(value={"/campaignWisePerformance"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformance(@RequestParam("userName") String userName)
  {
    List<CampaignWisePerformanceStatsDTO> response = this.analyticsService.getCampaignWisePerformanceStats(userName);
    LOGGER.debug("Campaign Wise performance JSON : " + response);
    return response;
  }
  
  @RequestMapping(value={"/groupWiseUnsubscription"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(@RequestParam("userName") String userName)
  {
    List<GroupWiseUnsubscriptionStatsDTO> response = this.analyticsService.getGroupWiseUnsubscription(userName);
    LOGGER.debug("Grop wise Unsbscription JSON : " + response);
    return response;
  }
  
  @RequestMapping(value={"/recentChartSummary"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public RepositoryResponseChartDTO getRecentCampaignChartSummary(@RequestParam("userName") String userName)
  {
    RepositoryResponseChartDTO response = this.analyticsService.getRecentCampaignChartSummary(userName);
    LOGGER.debug("Recent Chart Summary JSON : " + response);
    return response;
  }
  
  @RequestMapping(value={"/getCompanyWiseRegistrationStats"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
  {
    List<CompanyWiseRegistrationDTO> response = this.analyticsService.getCompanyWiseRegistrationStats();
    LOGGER.debug("Company Wise Registration JSON : " + response);
    return response;
  }
  
  @RequestMapping(value={"/getReadReceiptInfo"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public List<ReadReceiptDTO> getReadReceiptInfo(@RequestParam("emailId") String emailId)
  {
    List<ReadReceiptDTO> response = this.analyticsService.getReadReceiptInfo(Long.valueOf(Long.parseLong(emailId)));
    LOGGER.debug("Read Receipts for Email id " + emailId + " JSON : " + response);
    return response;
  }
  
  @PostMapping(value={"getRecentUnsubscribes"}, produces={"application/json"})
  public List<RecentUnsubscribesDTO> getRecentUnsubscribes(@RequestParam("age") String age, @RequestParam("isAdmin") String isAdmin)
  {
    boolean admin = false;
    if (Boolean.TRUE.toString().equalsIgnoreCase(isAdmin)) {
      admin = true;
    }
    List<RecentUnsubscribesDTO> response = this.analyticsService.getRecentUnsubscribes(Integer.parseInt(age), admin);
    LOGGER.debug("response : " + response);
    return response;
  }
  
  @PostMapping(value={"getRecentUnsubscribedCount"}, produces={"application/json"})
  public List<RecentlyUnsubscribedCountDTO> getRecentUnsubscribedCount(@RequestParam("age") String age, @RequestParam("isAdmin") String isAdmin)
  {
    boolean admin = false;
    if (Boolean.TRUE.toString().equalsIgnoreCase(isAdmin)) {
      admin = true;
    }
    List<RecentlyUnsubscribedCountDTO> response = this.analyticsService.getRecentUnsuscribedCount(Integer.parseInt(age), admin);
    LOGGER.debug("response : " + response);
    return response;
  }
  
  @PostMapping(value={"getJobStatusData"}, produces={"application/json"})
  public List<JobStatusResource> getJobStatusData(@RequestParam("userName") String userName)
  {
    UserAccount user = this.userAccountRepository.findUserAccountByUsername(userName);
    List<JobStatusResource> response = null;
    if ((UserAccountTypeConstant.ACC_TYPE_ADMIN.equals(user.getUserAccountType())) || 
      (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(user.getUserAccountType()))) {
      response = this.analyticsService.getJobStatusData(userName, true);
    } else {
      response = this.analyticsService.getJobStatusData(userName, false);
    }
    LOGGER.debug("response Job Status : " + response);
    return response;
  }
  
  @PostMapping(value={"cancelJob"}, produces={"application/json"})
  public ResponseEntity<String> cancelJob(@RequestParam("batchId") String batchId, @RequestParam("requestId") String requestId, HttpServletResponse response)
  {
    LOGGER.debug("Processing Job cancellation request");
    if (CommonUtilCache.getBatchIdToEmailJobEndpointMap().containsKey(requestId + "|" + batchId))
    {
      EmailJobEndpoint endpoint = (EmailJobEndpoint)CommonUtilCache.getBatchIdToEmailJobEndpointMap().get(requestId + "|" + batchId);
      if (JobProcessingPriorityBlockingQueue.getQueueInstance().contains(endpoint))
      {
        boolean result = JobProcessingPriorityBlockingQueue.getQueueInstance().remove(endpoint);
        LOGGER.info("Endpoint " + endpoint + " removed from Queue successfully, [result: " + result + "]");
        JobExecutionEntity entity = this.analyticsService.getJobStatusByBatchIdAndRequestId(requestId, batchId);
        if (entity != null)
        {
          entity.setStatus("CANCELLED");
          entity.setLastUpdatedDate(new Timestamp(new Date().getTime()));
          this.analyticsService.persistToDB(entity);
        }
        return new ResponseEntity(String.format("{\"Success\":\"%s\"}", new Object[] { "Job Cancellation Successful" }), HttpStatus.OK);
      }
      return new ResponseEntity(String.format("{\"Error\":\"%s\"}", new Object[] { "Job has already been picked for processing" }), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(String.format("{\"Error\":\"%s\"}", new Object[] { "Job has already completed" }), HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @PostMapping(value={"getBlockedContacts"}, produces={"application/json"})
  public List<BlockedContacts> getBlockedContacts()
  {
    return this.contactService.getBlockedContacts();
  }
}
