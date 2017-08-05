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

        return "SELECT a.id_pending_approval,concat(u.first_name,' ',u.last_name),a.email,u.company_name,date_format(a.creation_date,'%W, %M %D, %Y'),"
                + "concat((case when u.address_line1 is null then '' else u.address_line1 end),' ',(case when u.address_line2 is null then '' else u.address_line2 end)) as address,u.city,u.state,u.country,u.zip_code, a.status from account_approval a, "
                + "user_account u where a.id_pending_approval=u.id and "
                + "upper(a.status) IN ('PENDING','ON HOLD','REJECTED') and a.id_pending_approval NOT in (select id from user_account where user_account_type='ACC_TYPE_SUPER_ADMIN') and "
                + "a.user_account_id = (select id from user_account where username = '"+userName+"')";
    }

}
