package com.bluespacetech.server.analytics.repository;

import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.server.analytics.query.QueryStringConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnalyticsRepositoryCustomImpl
  implements AnalyticsRepositoryCustom
{
  @PersistenceContext
  @Autowired
  EntityManager em;
  SimpleDateFormat sdf = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss");
  
  public RepositoryResponseDTO findRecentCampaignStats(String userName)
  {
    String queryString = QueryStringConstants.getQuery_RecentCampaignSummaryStats(userName);
    Query query = this.em.createNativeQuery(queryString);
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
        Integer.parseInt(response[6].toString()) / Integer.parseInt(response[7].toString()) * 100);
      dto.setUnsubscribePercentage(
        Integer.parseInt(response[1].toString()) / Integer.parseInt(response[7].toString()) * 100);
    }
    catch (NoResultException ex)
    {
      dto = new RepositoryResponseDTO();
    }
    return dto;
  }
  
  public RepositoryResponseChartDTO findRecentCampaignChartStats(String userName)
  {
    String queryString = QueryStringConstants.getQuery_RecentCampaignSummaryStats(userName);
    
    Query query = this.em.createNativeQuery(queryString);
    Object[] response = (Object[])query.getSingleResult();
    RepositoryResponseChartDTO dto = new RepositoryResponseChartDTO();
    dto.setReach(Integer.parseInt(response[6].toString()));
    dto.setClicks(Integer.parseInt(response[5].toString()));
    dto.setUnsubscribes(Integer.parseInt(response[0].toString()));
    
    return dto;
  }
  
  public List<CampaignWisePerformanceStatsDTO> findCampaignWisePerformanceStats(String userName)
  {
    String queryString = QueryStringConstants.getQuery_CampaignWisePerformanceStats(userName);
    
    List<CampaignWisePerformanceStatsDTO> campaignWiseStatsList = new ArrayList();
    Query query = this.em.createNativeQuery(queryString);
    List<Object[]> responseList = query.getResultList();
    for (Object[] response : responseList)
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
  
  public List<GroupWiseUnsubscriptionStatsDTO> findGroupWiseUnsubscriptionStats(String userName)
  {
    String queryString = QueryStringConstants.getQuery_GroupWiseUnsubscriptionStats(userName);
    List<GroupWiseUnsubscriptionStatsDTO> groupWiseUnsubscriptionStats = new ArrayList();
    Query query = this.em.createNativeQuery(queryString);
    List<Object[]> responseList = query.getResultList();
    for (Object[] response : responseList)
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
  
  public List<CompanyWiseRegistrationDTO> getCompanyWiseRegistrationStats()
  {
    String queryString = QueryStringConstants.getQuery_CompanyWiseRegistrationStats();
    List<CompanyWiseRegistrationDTO> companyWiseRegistrationstats = new ArrayList();
    Query query = this.em.createNativeQuery(queryString);
    List<Object[]> responseList = query.getResultList();
    for (Object[] response : responseList)
    {
      CompanyWiseRegistrationDTO dto = new CompanyWiseRegistrationDTO();
      dto.setCompanyName(response[0].toString());
      dto.setApprovedCount(Integer.valueOf(response[1].toString()).intValue());
      dto.setPendingCount(Integer.valueOf(response[2].toString()).intValue());
      companyWiseRegistrationstats.add(dto);
    }
    return companyWiseRegistrationstats;
  }
  
  public List<RecentUnsubscribesDTO> getRecentUnsubscribes(int numberOfDays, boolean isAdmin)
  {
    String queryString = isAdmin ? QueryStringConstants.getQuery_RecentlyUnsubscribedUsers(numberOfDays) : QueryStringConstants.getQuery_RecentlyUnsubscribedUsers(numberOfDays, ViewUtil.getPrincipal());
    List<RecentUnsubscribesDTO> RecentUnsubscribes = new ArrayList();
    Query query = this.em.createNativeQuery(queryString);
    List<Object[]> responseList = query.getResultList();
    long i = 0L;
    for (Object[] response : responseList)
    {
      i += 1L;
      RecentUnsubscribesDTO dto = new RecentUnsubscribesDTO();
      dto.setSerialNo(Long.valueOf(i));
      dto.setFirstName(response[0].toString());
      dto.setLastName(response[1].toString());
      dto.setEmail(response[2].toString());
      dto.setUnsubscribedOn(response[3].toString());
      RecentUnsubscribes.add(dto);
    }
    return RecentUnsubscribes;
  }
  
  public List<RecentlyUnsubscribedCountDTO> getRecentlyUnsubscribedCount(int numberOfDays, boolean isAdmin)
  {
    String queryString = isAdmin ? QueryStringConstants.getQuery_RecentlyUnsubscribedUserCountDistribution(numberOfDays) : QueryStringConstants.getQuery_RecentlyUnsubscribedUserCountDistribution(numberOfDays, ViewUtil.getPrincipal());
    List<RecentlyUnsubscribedCountDTO> RecentUnsubscribes = new ArrayList();
    Query query = this.em.createNativeQuery(queryString);
    List<Object[]> responseList = query.getResultList();
    long i = 0L;
    for (Object[] response : responseList)
    {
      i += 1L;
      RecentlyUnsubscribedCountDTO dto = new RecentlyUnsubscribedCountDTO();
      dto.setUnsubscribedOn(response[0].toString());
      dto.setCount(Integer.parseInt(response[1].toString()));
      RecentUnsubscribes.add(dto);
    }
    return RecentUnsubscribes;
  }
}
