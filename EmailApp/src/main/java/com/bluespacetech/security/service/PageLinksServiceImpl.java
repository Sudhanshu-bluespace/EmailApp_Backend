package com.bluespacetech.security.service;

import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class PageLinksServiceImpl.
 */
@Service
@Transactional
public class PageLinksServiceImpl implements PageLinksService
{

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.security.service.PageLinksService#getPageLinksAllowedForUser()
     */
    @Transactional
    public Set<PageLinkConstant> getPageLinksAllowedForUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> authorities = new HashSet<>();
        UserAccountTypeConstant userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;

        if ((authentication != null) && (authentication.getAuthorities() != null))
        {
            for (final GrantedAuthority grantedAuthority : authentication.getAuthorities())
            {
                authorities.add(grantedAuthority.getAuthority());
                if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()
                        .equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
                }
                else if (UserAccountTypeConstant.ACC_TYPE_USER.getAccountType().equals(grantedAuthority.getAuthority()))
                {
                    userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;
                }
            }
        }
        
        final Set<PageLinkConstant> linksToBeDisplayed = new HashSet<PageLinkConstant>();
        for (final PageLinkConstant topLevelPage : PageLinkConstant.getTogglepages())
        {
            boolean topLinkAllowedForUser = false;
            for (UserAccountTypeConstant userAccountTypeConstant : topLevelPage.getUserAccountTypes())
            {
                if (userAccountTypeConstant.equals(userAccountType))
                {
                    topLinkAllowedForUser = true;
                    break;
                }
            }
            if (topLinkAllowedForUser)
            {
                for (GrantConstant grantConstant : topLevelPage.getGrants())
                {
                    if ((userAccountType.equals(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN))
                            || (authorities.contains(grantConstant.toString().toUpperCase()))
                            || (GrantConstant.NONE.equals(grantConstant)))
                    {
                        (linksToBeDisplayed).add(topLevelPage);
                        break;
                    }
                }
            }
        }
        return linksToBeDisplayed;
    }
}
