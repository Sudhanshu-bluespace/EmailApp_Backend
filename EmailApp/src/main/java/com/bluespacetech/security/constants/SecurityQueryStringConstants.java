package com.bluespacetech.security.constants;

public class SecurityQueryStringConstants {
	
	public static String getQuery_PendingApprovals(String userName){ 		
		
		return
			"SELECT a.id_pending_approval,u.username,a.email,date_format(a.creation_date,'%W, %M %D, %Y'), a.status from account_approval a, "
			+ "user_account u where a.user_account_id=u.id and "
			+ "upper(a.status)='PENDING' and a.id_pending_approval NOT in (select id from user_account where user_account_type='ACC_TYPE_SUPER_ADMIN') and "
			+ "u.username = '"+userName+"'";
	}

}
