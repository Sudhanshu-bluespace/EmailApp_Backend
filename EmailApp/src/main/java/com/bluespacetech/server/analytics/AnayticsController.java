/**
 * 
 */
package com.bluespacetech.server.analytics;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.CompanyWiseRegistrationDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RecentUnsubscribesDTO;
import com.bluespacetech.server.analytics.repository.RecentlyUnsubscribedCountDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
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
        LOGGER.info("Recent Summary JSON : " + response);
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
        LOGGER.info("Campaign Wise performance JSON : " + response);
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
        LOGGER.info("Grop wise Unsbscription JSON : " + response);
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
        LOGGER.info("Recent Chart Summary JSON : " + response);
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
        LOGGER.info("Company Wise Registration JSON : " + response);
        return response;
    }
    
    /**
     * Gets the pending approvals.
     *
     * @param userName the user name
     * @return the pending approvals
     */
    @PostMapping(value = "getRecentUnsubscribes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecentUnsubscribesDTO> getRecentUnsubscribes(@RequestParam("age") String age)
    {
        List<RecentUnsubscribesDTO> response = analyticsService.getRecentUnsubscribes(Integer.parseInt(age));
        LOGGER.info("response : " + response);
        return response;
    }
    
    /**
     * Gets the pending approvals.
     *
     * @param userName the user name
     * @return the pending approvals
     */
    @PostMapping(value = "getRecentUnsubscribedCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecentlyUnsubscribedCountDTO> getRecentUnsubscribedCount(@RequestParam("age") String age)
    {
        List<RecentlyUnsubscribedCountDTO> response = analyticsService.getRecentUnsuscribedCount(Integer.parseInt(age));
        LOGGER.info("response : " + response);
        return response;
    }
}
