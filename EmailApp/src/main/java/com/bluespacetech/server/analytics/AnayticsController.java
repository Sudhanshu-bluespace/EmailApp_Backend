/**
 * 
 */
package com.bluespacetech.server.analytics;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.server.analytics.repository.CampaignWisePerformanceStatsDTO;
import com.bluespacetech.server.analytics.repository.GroupWiseUnsubscriptionStatsDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseChartDTO;
import com.bluespacetech.server.analytics.repository.RepositoryResponseDTO;
import com.bluespacetech.server.analytics.service.AnalyticsService;

/**
 * @author sudhanshu
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/analytics")
public class AnayticsController {
	
	@RequestMapping(value = "/", method= RequestMethod.GET)
	public String viewAnalyticsPage(@RequestParam("analyticsType") String analyticsType ,
			WebRequest request) {
	
    return "analytics";
	}
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@RequestMapping(value="/recentSummary",method=RequestMethod.POST)
	public RepositoryResponseDTO getRecentCampaignSummary(@RequestParam("userName") String userName)
	{
		RepositoryResponseDTO response = analyticsService.getRecentCampaignSummary(userName);
		System.out.println("Response : "+response);
		return response;
	}
	
	@RequestMapping(value="/campaignWisePerformance",method=RequestMethod.POST)
	public List<CampaignWisePerformanceStatsDTO> getCampaignWisePerformance(@RequestParam("userName") String userName)
	{
		List<CampaignWisePerformanceStatsDTO> response = analyticsService.getCampaignWisePerformanceStats(userName);
		System.out.println("Response : "+response);
		return response;
	}
	
	@RequestMapping(value="/groupWiseUnsubscription",method=RequestMethod.POST)
	public List<GroupWiseUnsubscriptionStatsDTO> getGroupWiseUnsubscription(@RequestParam("userName") String userName)
	{
		List<GroupWiseUnsubscriptionStatsDTO> response = analyticsService.getGroupWiseUnsubscription(userName);
		System.out.println("Response : "+response);
		return response;
	}
	
	@RequestMapping(value="/recentChartSummary",method=RequestMethod.POST)
	public RepositoryResponseChartDTO getRecentCampaignChartSummary(@RequestParam("userName") String userName)
	{
		RepositoryResponseChartDTO response = analyticsService.getRecentCampaignChartSummary(userName);
		System.out.println("Response : "+response);
		return response;
	}
}
