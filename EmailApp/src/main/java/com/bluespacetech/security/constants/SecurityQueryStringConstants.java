package com.bluespacetech.security.constants;

/**
 * The Class SecurityQueryStringConstants.
 * @author Sudhanshu Tripathy
 */
public class SecurityQueryStringConstants
{

    /**
     * Gets the query pending approvals.
     *
     * @param userName the user name
     * @return the query pending approvals
     */
    public static String getQuery_PendingApprovals(String userName)
    {

        return "SELECT a.id_pending_approval,u.username,a.email,u.company_name,date_format(a.creation_date,'%W, %M %D, %Y'), a.status from account_approval a, "
                + "user_account u where a.id_pending_approval=u.id and "
                + "upper(a.status)='PENDING' and a.id_pending_approval NOT in (select id from user_account where user_account_type='ACC_TYPE_SUPER_ADMIN') and "
                + "a.user_account_id = (select id from user_account where username = '"+userName+"')";
    }

}
