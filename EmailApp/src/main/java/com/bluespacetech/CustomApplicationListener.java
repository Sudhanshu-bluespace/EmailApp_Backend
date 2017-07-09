package com.bluespacetech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.core.repository.CityRepository;
import com.bluespacetech.core.repository.CountryRepository;
import com.bluespacetech.core.repository.StateRepository;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;

/**
 * The listener interface for receiving customApplication events. The class that is interested in processing a customApplication event implements this interface, and the object created with that class
 * is registered with a component using the component's <code>addCustomApplicationListener<code> method. When the customApplication event occurs, that object's appropriate method is invoked.
 *
 * @see CustomApplicationEvent
 */
@Component
public class CustomApplicationListener implements ApplicationListener<ApplicationReadyEvent>
{
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(CustomApplicationListener.class);

    /** The template configuration. */
    @Autowired
    MailTemplateConfiguration templateConfiguration;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        populateIgnoreList();
        populateBlacklistedDomainList();
        populateProhibtedContentList();
    }

    /**
     * Populate ignore list.
     */
    private void populateIgnoreList()
    {
        String[] ignored = templateConfiguration.getIgnoreList().split("\\,");
        for (String ignore : ignored)
        {
            CommonUtilCache.getIgnoreList().add(ignore.trim());
        }
        LOGGER.info("Populated Ignore List successfully : " + CommonUtilCache.getIgnoreList());
    }
    
    private void populateProhibtedContentList()
    {
        String[] prohibited = templateConfiguration.getProhibitedContent().split("\\,");
        for (String block : prohibited)
        {
            CommonUtilCache.getProhibitedContentList().add(block.trim());
        }
        LOGGER.info("Populated Prohibited Content List successfully : " + CommonUtilCache.getProhibitedContentList());
    }

    /**
     * Populate blacklisted domain list.
     */
    private static void populateBlacklistedDomainList()
    {
        LOGGER.info("Populating Blacklisted Domains");
        try
        {
            List<String> domainsList = Files
                    .readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/config/DEA_refList.txt"));
            CommonUtilCache.getBlacklistedDomainList().addAll(domainsList);
            LOGGER.info(CommonUtilCache.getBlacklistedDomainList().size() + " blacklists were fetched completely");
        }
        catch (IOException e)
        {
            LOGGER.error("Failed to read black listed domains list, reason: [" + e.getMessage() + "]");
        }
    }
}
