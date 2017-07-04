package com.bluespacetech.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bluespacetech.group.entity.Group;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtilCache.
 */
public class CommonUtilCache
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(CommonUtilCache.class);
    
    /** The group name to group map. */
    private static Map<String, Group> groupNameToGroupMap = new HashMap<>();
    
    /** The black listed domains. */
    private static List<String> blackListedDomains = new ArrayList<>(1000);
    
    /** The ignore list. */
    private static List<String> ignoreList = new ArrayList<>();

    /**
     * Instantiates a new common util cache.
     */
    private CommonUtilCache()
    {

    }

    /**
     * Gets the group name to group map.
     *
     * @return the group name to group map
     */
    public static Map<String, Group> getGroupNameToGroupMap()
    {
        LOGGER.info("Returning groupNameToGroupMap");
        return groupNameToGroupMap;
    }

    /**
     * Gets the blacklisted domain list.
     *
     * @return the blacklisted domain list
     */
    public static List<String> getBlacklistedDomainList()
    {
        return blackListedDomains;
    }
    
    
    public static List<String> getIgnoreList()
    {
        return ignoreList;
    }
}
