/**
 * 
 */
package com.bluespacetech.server.analytics.repository;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluespacetech.server.analytics.query.QueryStringConstants;
import com.sun.media.jfxmedia.logging.Logger;

/**
 * @author sudhanshu
 *
 */

@Repository
public class AnalyticsRepositoryCustomImpl implements AnalyticsRepositoryCustom {

	@PersistenceContext 
	@Autowired
	EntityManager em;
	
	SimpleDateFormat sdf = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss");

	@Override
	public RepositoryResponseDTO findRecentCampaignStats(String userName) {

		String queryString = QueryStringConstants.getQuery_RecentCampaignSummaryStats(userName);
		Query query = em.createNativeQuery(queryString);
		RepositoryResponseDTO dto = null;
		try
		{
			Object[] response = (Object[])query.getSingleResult();
			
			dto = new RepositoryResponseDTO();
			dto.setEmail_id(Long.parseLong(response[2].toString()));
			dto.setCreated_user(response[0].toString());
			dto.setContent(response[4].toString());
			dto.setSubject(response[3].toString());
			dto.setReach(Integer.parseInt(response[7].toString()));
			dto.setClicks(Integer.parseInt(response[6].toString()));
			dto.setUnsubscribes(Integer.parseInt(response[1].toString()));
			dto.setSentOn(response[5].toString());
			dto.setClickPercentage(
					(Integer.parseInt(response[6].toString())/Integer.parseInt(response[7].toString()))*100
					);
			dto.setUnsubscribePercentage(
					(Integer.parseInt(response[1].toString())/Integer.parseInt(response[7].toString()))*100
					);
		}
		catch(NoResultException ex)
		{
			dto = new RepositoryResponseDTO();
		}
		
		return dto;
	}
	
	@Override
	public RepositoryResponseChartDTO findRecentCampaignChartStats(String userName) {
		String queryString = QueryStringConstants.getQuery_RecentCampaignSummaryStats(userName);
		
		Query query = em.createNativeQuery(queryString);
		Object[] response = (Object[])query.getSingleResult();
		RepositoryResponseChartDTO dto = new RepositoryResponseChartDTO();
		dto.setReach(Integer.parseInt(response[6].toString()));
		dto.setClicks(Integer.parseInt(response[5].toString()));
		dto.setUnsubscribes(Integer.parseInt(response[0].toString()));
		
		return dto;
	}

	@Override
	public List<CampaignWisePerformanceStatsDTO> findCampaignWisePerformanceStats(String userName) {
		String queryString = QueryStringConstants.getQuery_CampaignWisePerformanceStats(userName);
		
		List<CampaignWisePerformanceStatsDTO> campaignWiseStatsList= new ArrayList<>();
		Query query = em.createNativeQuery(queryString);
		List<Object[]> responseList = query.getResultList();
		for(Object[] response:responseList)
		{
			CampaignWisePerformanceStatsDTO dto = new CampaignWisePerformanceStatsDTO();
			dto.setEmail_id(Long.parseLong(response[2].toString()));
			dto.setCreatedUser(response[0].toString());
			dto.setContent(response[4].toString());
			dto.setSubject(response[3].toString());
			dto.setTotalReach(Integer.parseInt(response[7].toString()));
			dto.setClicks(Integer.parseInt(response[6].toString()));
			dto.setUnsubscribes(Integer.parseInt(response[1].toString()));
			dto.setSentOn(response[5].toString());
			campaignWiseStatsList.add(dto);
		}
		
		return campaignWiseStatsList;
	}

	@Override
	public List<GroupWiseUnsubscriptionStatsDTO> findGroupWiseUnsubscriptionStats(String userName) {
		// TODO Auto-generated method stub
		String queryString = QueryStringConstants.getQuery_GroupWiseUnsubscriptionStats(userName);
		List<GroupWiseUnsubscriptionStatsDTO> groupWiseUnsubscriptionStats = new ArrayList<>();
		Query query = em.createNativeQuery(queryString);
		List<Object[]> responseList = query.getResultList();
		for(Object[] response:responseList)
		{
			GroupWiseUnsubscriptionStatsDTO dto = new GroupWiseUnsubscriptionStatsDTO();
			dto.setUnsubscribed(Integer.parseInt(response[1].toString()));
			dto.setCreatedUser(response[0].toString());
			dto.setGroupId(Integer.parseInt(response[2].toString()));
			dto.setGroupName(response[3].toString());
			groupWiseUnsubscriptionStats.add(dto);
		}
		return groupWiseUnsubscriptionStats;
	}
	
	

}
