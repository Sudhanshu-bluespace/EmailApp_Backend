/**
 * 
 */
package com.bluespacetech.server.analytics.util;

import java.util.Comparator;

import com.bluespacetech.notifications.email.entity.JobExecutionEntity;

/**
 * The Class JobIdStatusComparator.
 *
 * @author sudhanshu
 */
public class JobIdStatusComparator implements Comparator<JobExecutionEntity>
{

    /*
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(JobExecutionEntity o1, JobExecutionEntity o2)
    {
        if (o1.getJobId() == null || o1.getJobId().trim().isEmpty() || o2.getJobId() == null
                || o2.getJobId().trim().isEmpty())
        {
            return -1;
        }
        
        if("-".equalsIgnoreCase(o1.getJobId().trim())||"-".equalsIgnoreCase(o2.getJobId().trim()))
        {
            return 1;
        }

        return Long.valueOf(o2.getJobId().trim()).compareTo(Long.valueOf(o1.getJobId().trim()));
    }

}
