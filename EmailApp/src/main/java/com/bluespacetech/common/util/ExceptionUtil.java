package com.bluespacetech.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Class ExceptionUtil.
 */
public class ExceptionUtil
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ExceptionUtil.class);

    /**
     * Gets the error root cause.
     *
     * @param throwable the throwable
     * @return the error root cause
     */
    public static String getErrorRootCause(Throwable throwable)
    {
        Throwable cause = null;
        Throwable result = throwable;

        LOGGER.warn("Diving in to get the root cause of the error");
        while (null != (cause = result.getCause()) && (result != cause))
        {
            result = cause;
            LOGGER.warn("Found cause : " + result.getMessage().trim().replaceAll("[\\r\\n\\t]", " "));
        }

        return result.getLocalizedMessage();
    }

}
