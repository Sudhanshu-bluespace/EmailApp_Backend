package com.bluespacetech.common.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bluespacetech.group.entity.Group;
import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

/**
 * The Class CommonUtilCache.
 */
public class CommonUtilCache
{

    /** The Constant LOGGER. */
   // private static final Logger LOGGER = LogManager.getLogger(CommonUtilCache.class);

    /** The group name to group map. */
    private static Map<String, Group> groupNameToGroupMap = new HashMap<>();
    
    private static Set<String> failedValidationContacts = new HashSet<>();

    /** The black listed domains. */
    private static List<String> blackListedDomains = new ArrayList<>(1000);

    /** The ignore list. */
    private static List<String> ignoreList = new ArrayList<>();

    /** The ignore list. */
    private static List<String> prohibitedContentList = new ArrayList<>();

    /** The batch id to email list map. */
    private static Map<String, List<EmailContactGroupVO>> batchIdToEmailListMap = new HashMap<>();
    
    private static Map<Long,List<Path>> tempFileCleanupMap = new HashMap<>();

    /** The batch id to email job endpoint map. */
    private static Map<String, EmailJobEndpoint> batchIdToEmailJobEndpointMap = new HashMap<>();

    /** The bounced emails cache. */
    private static Map<String, List<String>> bouncedEmailsCache = new HashMap<>();
    
    private static Map<String,String> requestIdVsErrorMap = new HashMap<>();
    
    private static Set<String> existingContacts = new HashSet<>();
    
    public static Map<String, String> getRequestIdVsErrorMap()
    {
        return requestIdVsErrorMap;
    }

    public static Map<Long, List<Path>> getTempFileCleanupMap()
    {
        return tempFileCleanupMap;
    }
    
    public static Set<String> getExistingContacts()
    {
        return existingContacts;
    }

    /**
     * Gets the bounced email cache.
     *
     * @return the bounced email cache
     */
    public static Map<String, List<String>> getBouncedEmailCache()
    {
        if (!bouncedEmailsCache.containsKey("HARD_BOUNCE"))
        {
            bouncedEmailsCache.put("HARD_BOUNCE", new ArrayList<>());
        }
        if (!bouncedEmailsCache.containsKey("SOFT_BOUNCE"))
        {
            bouncedEmailsCache.put("SOFT_BOUNCE", new ArrayList<>());
        }

        return bouncedEmailsCache;
    }
    
    public static Set<String> getFailedValidationContacts()
    {
        return failedValidationContacts;
    }

    /**
     * Gets the batch id to email job endpoint map.
     *
     * @return the batch id to email job endpoint map
     */
    public static Map<String, EmailJobEndpoint> getBatchIdToEmailJobEndpointMap()
    {
        return batchIdToEmailJobEndpointMap;
    }

    /**
     * Gets the batch id to email list map.
     *
     * @return the batch id to email list map
     */
    public static Map<String, List<EmailContactGroupVO>> getBatchIdToEmailListMap()
    {
        return batchIdToEmailListMap;
    }

    /**
     * Gets the prohibited content list.
     *
     * @return the prohibited content list
     */
    public static List<String> getProhibitedContentList()
    {
        return prohibitedContentList;
    }

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
        // LOGGER.debug("Returning groupNameToGroupMap");
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

    /**
     * Gets the ignore list.
     *
     * @return the ignore list
     */
    public static List<String> getIgnoreList()
    {
        return ignoreList;
    }
}
