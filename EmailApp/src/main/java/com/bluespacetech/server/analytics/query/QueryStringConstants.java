package com.bluespacetech.server.analytics.query;

public class QueryStringConstants {
	
	public static final String getQuery_RecentCampaignSummaryStats(String userName)
	{
		return
			"SELECT e.created_user,sum(cg.unsubscribed) as unsubscribes,"
			+ "e.id,e.subject,e.text as content,date_format(e.creation_date,'%W, %M %D, %Y') as sentOn,sum(ecg.read_count) as clicks,count(ecg.email_id) as total_reach from email e, "
			+ "email_contact_group ecg,contact_group cg,groups g where e.created_user=ecg.created_user and "
			+ "e.created_user='"+userName+"'"
			+" and cg.group_id=ecg.group_id and e.id=ecg.email_id and cg.group_id=g.id and e.id =(select max(e.id) from email e where e.created_user='"+userName+"')"
			+ " group by e.id;";
	}
	public static final String getQuery_CampaignWisePerformanceStats(String userName)
	{
		return
			"SELECT e.created_user,sum(cg.unsubscribed) as unsubscribes, "
			+ "e.id,e.subject,e.text as content,date_format(e.creation_date,'%W, %M %D, %Y') as sentOn,sum(ecg.read_count) as clicks,count(ecg.email_id) as total_reach from email e, "
			+ "email_contact_group ecg,contact_group cg,groups g where e.created_user=ecg.created_user and "
			+ "e.created_user='"+userName+"'"
			+" and cg.group_id=ecg.group_id and e.id=ecg.email_id and cg.group_id=g.id group by e.id;";
	}
	
	public static final String getQuery_GroupWiseUnsubscriptionStats(String userName)
	{
		return
			"select g.created_user,sum(cg.unsubscribed) as unsubscribed, cg.group_id, "
			+ "g.name from contact_group cg, groups g where g.id=cg.group_id and g.created_user='"+userName+"' "
			+ "group by cg.group_id;";
	}

}
