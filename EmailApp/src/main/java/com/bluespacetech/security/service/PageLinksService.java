package com.bluespacetech.security.service;

import com.bluespacetech.security.constants.PageLinkConstant;
import java.util.Set;

public abstract interface PageLinksService
{
  public abstract Set<PageLinkConstant> getPageLinksAllowedForUser();
}
