package com.bluespacetech.contact.fileupload.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.common.util.ContactUtilService;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.notifications.email.validators.EmailMXRecordDNSValidator;

/**
 * The Class ContactItemProcessor.
 */
public class ContactItemProcessor implements ItemProcessor<ContactUploadDTO, Contact>
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactItemProcessor.class);

    @Autowired
    private ContactUtilService contactUtilService;

    private List<String> validatedDomains = new ArrayList<>();

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    @Override
    public Contact process(final ContactUploadDTO contactDTO)
    {

        Map<String, Group> groupNameToGroupMap = CommonUtilCache.getGroupNameToGroupMap();
        final String firstName = contactDTO.getFirstName();
        final String lastName = contactDTO.getLastName();
        final String email = contactDTO.getEmail();

        if (email == null || email.trim().isEmpty())
        {
            return null;
        }
        else if (CommonUtilCache.getIgnoreList().contains(email.substring(0, email.indexOf("@") + 1)))
        {
            LOGGER.warn("Email " + email + " falls under IGNORED LIST, will be skipped");
            contactUtilService.addEmailToBlockedList(email, contactDTO, "IGNORED");
            return null;
        }
        else if (CommonUtilCache.getBlacklistedDomainList().contains(email.split("@")[1].trim()))
        {
            LOGGER.warn("Email " + email + " detected to be BLACKLISTED. Will be added to the blacklisted group");
            contactUtilService.addEmailToBlockedList(email, contactDTO, "BLACKLISTED");
        }
        else if (!validatedDomains.contains(email.split("@")[1]))
        {
            List<String> mxRecords = EmailMXRecordDNSValidator.validateMxRecord(email.trim());
            if (mxRecords == null || mxRecords.isEmpty())
            {
                LOGGER.warn("No MX records found for email " + email + ", Potential candidate for blacklist");
                contactUtilService.addEmailToBlockedList(email, contactDTO, "INVALID_MX_RECORDS");
                return null;
            }
            else
            {
                validatedDomains.add(email.split("@")[1]);
                LOGGER.info("Validated domain "+email.split("@")[1]+" for MX reords successfully. Added to local cache");
            }
        }

        Contact contact = new Contact();
        contact.getContactGroups().clear();

        String groups = contactDTO.getGroup();
        ContactGroup cg = null;

        if (groups.contains(";"))
        {
            for (String groupName : groups.split(";"))
            {
                cg = createContactGroup(groupName, groupNameToGroupMap);
            }
        }
        else
        {
            cg = createContactGroup(groups, groupNameToGroupMap);
        }

        contact.getContactGroups().add(cg);
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);

        for (ContactGroup cGroup : contact.getContactGroups())
        {
            cGroup.setContact(contact);
            cGroup.getGroup().getContactGroups().clear();
            cGroup.getGroup().getContactGroups().addAll(contact.getContactGroups());
        }
        return contact;
    }

    /**
     * Creates the contact group.
     *
     * @param groupName the group name
     * @param groupNameToGroupMap the group name to group map
     * @return the contact group
     */
    private ContactGroup createContactGroup(String groupName, Map<String, Group> groupNameToGroupMap)
    {
        ContactGroup cg = new ContactGroup();
        cg.setActive(true);
        cg.setUnSubscribed(false);
        if (groupNameToGroupMap.containsKey(groupName))
        {
            cg.setGroup(groupNameToGroupMap.get(groupName));
        }

        return cg;
    }
}
