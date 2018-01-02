package com.bluespacetech;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.common.util.ExceptionUtil;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.service.BlockedContactService;
import com.bluespacetech.core.repository.CityRepository;
import com.bluespacetech.core.repository.CountryRepository;
import com.bluespacetech.core.repository.StateRepository;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

    /** The country repository. */
    @Autowired
    CountryRepository countryRepository;

    /** The state repository. */
    @Autowired
    StateRepository stateRepository;

    /** The blocked contact service. */
    @Autowired
    private BlockedContactService blockedContactService;

    /** The city repository. */
    @Autowired
    CityRepository cityRepository;

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        populateIgnoreList();
        populateBlacklistedDomainList();
        populateProhibtedContentList();
        populatedBouncedEmailsCache();
    }

    /**
     * Populate ignore list.
     */
    private void populateIgnoreList()
    {
        String[] ignored = this.templateConfiguration.getIgnoreList().split("\\,");
        for (String ignore : ignored)
        {
            CommonUtilCache.getIgnoreList().add(ignore.trim());
        }
        LOGGER.info("Populated Ignore List successfully : " + CommonUtilCache.getIgnoreList());
    }

    /**
     * Populate prohibted content list.
     */
    private void populateProhibtedContentList()
    {
        String[] prohibited = this.templateConfiguration.getProhibitedContent().split("\\,");
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
            List<String> domainsList = Files.readAllLines(
                    Paths.get("/opt/packages/Oracle/BluespaceMailer/config/DEA_refList.txt", new String[0]));
            CommonUtilCache.getBlacklistedDomainList().addAll(domainsList);
            LOGGER.info(CommonUtilCache.getBlacklistedDomainList().size() + " blacklists were fetched completely");
        }
        catch (IOException e)
        {
            LOGGER.error("Failed to read black listed domains list, reason: [" + e.getMessage() + "]");
        }
    }

    /**
     * Populated bounced emails cache.
     */
    private void populatedBouncedEmailsCache()
    {
        LOGGER.info("Populating Bounced Email Cache");
        try
        {
            List<BlockedContacts> blockedContacts = this.blockedContactService.findAll();
            for (BlockedContacts contact : blockedContacts)
            {
                String reason = contact.getReason();
                if (!CommonUtilCache.getBouncedEmailCache().containsKey(reason))
                {
                    CommonUtilCache.getBouncedEmailCache().put(reason, new ArrayList());
                }
                if (contact.getReason().contains("BOUNCE"))
                {
                    CommonUtilCache.getBouncedEmailCache().get(reason).add(contact.getEmail());
                }
                if ("INVALID_MX_RECORDS".equalsIgnoreCase(contact.getReason()))
                {
                    CommonUtilCache.getBlacklistedContactEmails().add(contact.getEmail());
                }
            }
            LOGGER.info(
                    "Populated Bounced Email Cache and Set successfully.. Number of blacklist due to Invalid MX records : "
                            + CommonUtilCache.getBlacklistedContactEmails().size());
            LOGGER.info("Number of bounce categories in Bounced Email Cache : "
                    + CommonUtilCache.getBouncedEmailCache().size());
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to populate BouncedEmailCache / Set of Blacklisted Contacts, reason : ["
                    + ExceptionUtil.getErrorRootCause(e) + "]");
        }
    }
}
