package com.bluespacetech.server.analytics.query;

/**
 * The Class QueryStringConstants.
 * @author Sudhanshu Tripathy
 */
public class QueryStringConstants
{
    
    /**
     * Gets the query company wise registration stats.
     *
     * @return the query company wise registration stats
     */
    public static final String getQuery_CompanyWiseRegistrationStats()
    {
        return "select c.company_name,sum(case when u.verified_by_admin=1  "
                + "then 1 else 0 end) as 'approved_count',sum(case when u.verified_by_admin=0 then 1 else 0 end) "
                + "as 'pending_count' from company_registration c,user_account u "
                + "where c.company_name = u.company_name group by c.company_name";
    }

    /**
     * Gets the query recent campaign summary stats.
     *
     * @param userName
     *            the user name
     * @return the query recent campaign summary stats
     */
    public static final String getQuery_RecentCampaignSummaryStats(String userName)
    {
        return "SELECT e.created_user,sum(cg.unsubscribed) as unsubscribes,"
                + "e.id,e.subject,e.text as content,date_format(e.creation_date,'%W, %M %D, %Y') as sentOn,sum(ecg.read_count) as clicks,count(ecg.email_id) as total_reach from email e, "
                + "email_contact_group ecg,contact_group cg,groups g where e.created_user=ecg.created_user and "
                + "e.created_user='" + userName + "'"
                + " and cg.group_id=ecg.group_id and cg.contact_id=ecg.contact_id and e.id=ecg.email_id and cg.group_id=g.id and e.id =(select max(e.id) from email e where e.created_user='"
                + userName + "')" + " group by e.id;";
    }
    
    public static final String getQuery_RecentlyUnsubscribedUserCountDistribution(int age)
    {
        return 
            "select date_format(cg.unsubscribed_date,'%M %D, %Y') as date, sum(cg.unsubscribed) as count "+
            "from contacts c,contact_group cg where c.id=cg.contact_id and cg.unsubscribed >= 0 "+
            "and cg.unsubscribed_date > NOW() - INTERVAL "+age+" DAY group by cg.unsubscribed_date; ";
    }
    
    public static final String getQuery_RecentlyUnsubscribedUsers(int age)
    {
        return "select c.first_name,c.last_name,c.email,date_format(cg.unsubscribed_date,'%M %D, %Y') as unsubscribedOn from contacts c,contact_group cg where c.id=cg.contact_id and cg.unsubscribed >= 0 and cg.unsubscribed_date > NOW() - INTERVAL "+age+" DAY;";
    }

    /**
     * Gets the query campaign wise performance stats.
     *
     * @param userName
     *            the user name
     * @return the query campaign wise performance stats
     */
    public static final String getQuery_CampaignWisePerformanceStats(String userName)
    {
        return "SELECT e.created_user,sum(cg.unsubscribed) as unsubscribes, "
                + "e.id,e.subject,e.text as content,date_format(e.creation_date,'%W, %M %D, %Y') as sentOn,sum(ecg.read_count) as clicks,count(ecg.email_id) as total_reach from email e, "
                + "email_contact_group ecg,contact_group cg,groups g where e.created_user=ecg.created_user and "
                + "e.created_user='" + userName + "'"
                + " and cg.group_id=ecg.group_id and cg.contact_id=ecg.contact_id and e.id=ecg.email_id and cg.group_id=g.id group by e.id;";
    }

    /**
     * Gets the query group wise unsubscription stats.
     *
     * @param userName
     *            the user name
     * @return the query group wise unsubscription stats
     */
    public static final String getQuery_GroupWiseUnsubscriptionStats(String userName)
    {
        return "select g.created_user,sum(cg.unsubscribed) as unsubscribed, cg.group_id, "
                + "g.name from contact_group cg, groups g where g.id=cg.group_id and g.created_user='" + userName + "' "
                + "group by cg.group_id;";
    }

}
