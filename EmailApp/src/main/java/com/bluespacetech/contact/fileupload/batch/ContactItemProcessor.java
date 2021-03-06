package com.bluespacetech.contact.fileupload.batch;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.BlockedContacts;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contact.service.BlockedContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.notifications.email.util.EmailUtils;
import com.bluespacetech.notifications.email.validators.EmailMXRecordDNSValidator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactItemProcessor.
 */
public class ContactItemProcessor implements ItemProcessor<ContactUploadDTO, Contact>
{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactItemProcessor.class);

    /** The blocked contact service. */
    private BlockedContactService blockedContactService;

    /** The validated domains. */
    private List<String> validatedDomains = new ArrayList<>();

    /**
     * Instantiates a new contact item processor.
     *
     * @param blockedContactService the blocked contact service
     */
    public ContactItemProcessor(BlockedContactService blockedContactService)
    {
        this.blockedContactService = blockedContactService;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
     */
    public Contact process(ContactUploadDTO contactDTO)
    {
        Map<String, Group> groupNameToGroupMap = CommonUtilCache.getGroupNameToGroupMap();
        String firstName = contactDTO.getFirstName();
        String lastName = contactDTO.getLastName();
        String email = contactDTO.getEmail();
        String uploader = ViewUtil.getPrincipal();
        if ((uploader == null) || (uploader.trim().isEmpty()))
        {
            uploader = "Guest";
        }
        if ((email == null) || (email.trim().isEmpty()))
        {
            return null;
        }
        if (!EmailUtils.isEmailValid(email))
        {
            LOGGER.warn("Invalid Email found " + email + " , will be skipped");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",INPUT_VALIDATION_FAILED");
            return null;
        }
        if (CommonUtilCache.getIgnoreList().contains(email.substring(0, email.indexOf("@") + 1)))
        {
            LOGGER.warn("Email " + email + " falls under IGNORED LIST, will be skipped");
            addEmailToBlockedList(email, contactDTO, "IGNORED");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",SPAM/IGNORED_LIST");
            return null;
        }
        if (CommonUtilCache.getBlacklistedDomainList().contains(email.split("@")[1].trim()))
        {
            LOGGER.warn("Email " + email + " detected to be BLACKLISTED. Will be added to the blacklisted group");
            addEmailToBlockedList(email, contactDTO, "BLACKLISTED");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",BLACKLISTED");
            return null;
        }
        if (!this.validatedDomains.contains(email.split("@")[1]))
        {
            String mxRecord = EmailMXRecordDNSValidator.validateMxRecord(email.trim());
            if ((mxRecord == null) || (mxRecord.trim().isEmpty()))
            {
                LOGGER.warn("No MX records found for email " + email + ", Potential candidate for blacklist");
                addEmailToBlockedList(email, contactDTO, "INVALID_MX_RECORDS");
                CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",INVALID_MX_RECORDS");
                return null;
            }
            this.validatedDomains.add(email.split("@")[1]);
            LOGGER.debug(
                    "Validated domain " + email.split("@")[1] + " for MX reords successfully. Added to local cache");
        }
        Contact contact = new Contact();
        contact.getContactGroups().clear();

        String groups = contactDTO.getGroup();

        List<String> validatedGroups = new ArrayList<>();
        if (groups.contains(";"))
        {
            for (String group : groups.split(":"))
            {
                String toSearch = (contactDTO.getFirstName() + "," + contactDTO.getLastName() + ","
                        + contactDTO.getEmail() + "," + group).toLowerCase();
                if (isContactAlreadyExistent(toSearch, uploader))
                {
                    LOGGER.warn("Contact " + contactDTO.getEmail() + " is already associated with group " + group
                            + ", will not be added again");
                }
                else
                {
                    validatedGroups.add(group);
                }
            }
        }
        else
        {
            String toSearch = (contactDTO.getFirstName() + "," + contactDTO.getLastName() + "," + contactDTO.getEmail()
                    + "," + groups).toLowerCase();
            if (isContactAlreadyExistent(toSearch, uploader))
            {
                LOGGER.warn("Contact " + contactDTO.getEmail() + " is already associated with group " + groups
                        + ", will not be added again");

                return null;
            }
            validatedGroups.add(groups);
        }
        ContactGroup cg = null;

        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        for (String groupName : validatedGroups)
        {
            cg = createContactGroup(groupName, groupNameToGroupMap);
            LOGGER.debug("Created new contact group for " + groupName + " and contact " + contact+", details : "+cg);
        }
        contact.getContactGroups().add(cg);
        for (ContactGroup cGroup : contact.getContactGroups())
        {
            cGroup.setContact(contact);
            if (cGroup.getGroup().getContactGroups().contains(cGroup))
            {
                cGroup.getGroup().getContactGroups().add(cGroup);
            }
        }
        return contact;
    }

    /**
     * Checks if is contact already existent.
     *
     * @param toSearch the to search
     * @param uploader the uploader
     * @return true, if is contact already existent
     */
    private boolean isContactAlreadyExistent(String toSearch, String uploader)
    {
        return (CommonUtilCache.getExistingContacts().containsKey(uploader))
                && ((CommonUtilCache.getExistingContacts().get(uploader)).contains(toSearch));
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

        if (groupNameToGroupMap.containsKey(groupName.toLowerCase()))
        {
            cg.setGroup( groupNameToGroupMap.get(groupName.toLowerCase()));
        }
        return cg;
    }

    /**
     * Adds the email to blocked list.
     *
     * @param email the email
     * @param contactUploadDTO the contact upload DTO
     * @param reason the reason
     */
    private void addEmailToBlockedList(String email, ContactUploadDTO contactUploadDTO, String reason)
    {
        BlockedContacts contactToBlock = new BlockedContacts();
        contactToBlock.setEmail(email);
        contactToBlock.setFirstName(contactUploadDTO.getFirstName());
        contactToBlock.setLastName(contactUploadDTO.getLastName());
        contactToBlock.setReason(reason);
        this.blockedContactService.addBlockedContact(contactToBlock);
    }
}
