package com.bluespacetech.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bluespacetech.group.entity.Group;

public class CommonUtilCache {

	private static final Logger LOGGER = LogManager.getLogger(CommonUtilCache.class);
	public static Map<String,Group> groupNameToGroupMap = new HashMap<>();
	
	private CommonUtilCache()
	{
		
	}
	
	public static Map<String,Group> getGroupNameToGroupMap()
	{
		LOGGER.info("Returning groupNameToGroupMap");
		return groupNameToGroupMap;
	}
}
