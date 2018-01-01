package com.bluespacetech.contact.fileupload.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.xbill.DNS.MXRecord;

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
    @Override
    public Contact process(final ContactUploadDTO contactDTO)
    {

        Map<String, Group> groupNameToGroupMap = CommonUtilCache.getGroupNameToGroupMap();
        final String firstName = contactDTO.getFirstName();
        final String lastName = contactDTO.getLastName();
        final String email = contactDTO.getEmail();
        String uploader = ViewUtil.getPrincipal();
        if (uploader == null || uploader.trim().isEmpty())
        {
            uploader = "Guest";
        }

        if (email == null || email.trim().isEmpty())
        {
            return null;
        }
        else if (!EmailUtils.isEmailValid(email))
        {
            LOGGER.warn("Invalid Email found " + email + " , will be skipped");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",INPUT_VALIDATION_FAILED");
            return null;
        }
        else if (CommonUtilCache.getIgnoreList().contains(email.substring(0, email.indexOf("@") + 1)))
        {
            LOGGER.warn("Email " + email + " falls under IGNORED LIST, will be skipped");
            addEmailToBlockedList(email, contactDTO, "IGNORED");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",SPAM/IGNORED_LIST");
            return null;
        }
        /*
         * else if (CommonUtilCache.getIgnoreList().contains(firstName)) { LOGGER.warn("First Name " + firstName + " falls under IGNORED LIST, will be skipped");
         * contactUtilService.addEmailToBlockedList(email, contactDTO, "IGNORED"); return null; } else if (CommonUtilCache.getIgnoreList().contains(lastName)) { LOGGER.warn("Last Name " + lastName +
         * " falls under IGNORED LIST, will be skipped"); contactUtilService.addEmailToBlockedList(email, contactDTO, "IGNORED"); return null; }
         */
        else if (CommonUtilCache.getBlacklistedDomainList().contains(email.split("@")[1].trim()))
        {
            LOGGER.warn("Email " + email + " detected to be BLACKLISTED. Will be added to the blacklisted group");
            addEmailToBlockedList(email, contactDTO, "BLACKLISTED");
            CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",BLACKLISTED");
            return null;
        }
        else if (!validatedDomains.contains(email.split("@")[1]))
        {
            String mxRecord = EmailMXRecordDNSValidator.validateMxRecord(email.trim());
            if (mxRecord == null || mxRecord.trim().isEmpty())
            {
                LOGGER.warn("No MX records found for email " + email + ", Potential candidate for blacklist");
                addEmailToBlockedList(email, contactDTO, "INVALID_MX_RECORDS");
                CommonUtilCache.getFailedValidationContacts().add(uploader + "," + email + ",INVALID_MX_RECORDS");
                return null;
            }
            else
            {
                validatedDomains.add(email.split("@")[1]);
                LOGGER.debug("Validated domain " + email.split("@")[1]
                        + " for MX reords successfully. Added to local cache");
                
                /*List<BlockedContacts> contact = blockedContactService.findBlockedContactByEmailAndReason(email, "INVALID_MX_RECORDS");
                if(contact!=null && !contact.isEmpty())
                {
                    for (BlockedContacts c : contact)
                    {
                        if ("INVALID_MX_RECORDS".equalsIgnoreCase(c.getReason()))
                        {

                            blockedContactService.remove(c);
                            CommonUtilCache.getFailedValidationContacts()
                                    .remove(uploader + "," + email + ",INVALID_MX_RECORDS");
                            LOGGER.info("Removed re-vlidated contact [" + uploader + "," + email
                                    + ",INVALID_MX_RECORDS] successfully from blacklist");
                        }
                    }
                }*/
            }
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
            else
            {
                validatedGroups.add(groups);
            }
        }

        ContactGroup cg = null;

        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);

        for (String groupName : validatedGroups)
        {
            LOGGER.debug("Creating new contact group for " + groupName + " and contact " + contact);
            cg = createContactGroup(groupName, groupNameToGroupMap);
        }

        contact.getContactGroups().add(cg);

        for (ContactGroup cGroup : contact.getContactGroups())
        {
            cGroup.setContact(contact);
            cGroup.getGroup().getContactGroups().clear();
            cGroup.getGroup().getContactGroups().addAll(contact.getContactGroups());
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
        return CommonUtilCache.getExistingContacts().containsKey(uploader)
                && CommonUtilCache.getExistingContacts().get(uploader).contains(toSearch);
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

    /**
     * Adds the email to blocked list.
     *
     * @param email the email
     * @param contactUploadDTO the contact upload DTO
     * @param reason the reason
     */
    private void addEmailToBlockedList(String email, ContactUploadDTO contactUploadDTO, String reason)
    {
        // Query query = em.createNativeQuery("select * from blocked_contacts b where upper(b.email)='"+email.toUpperCase()+"' and upper(b.reason) = '"+reason.toUpperCase()+"'");
        // BlockedContacts blocked = (BlockedContacts)query.getSingleResult();

        // BlockedContacts contacts = blockedContactService.findBlockedContactByEmailAndReason(email,reason);
        // if (blocked == null)
        // {
        BlockedContacts contactToBlock = new BlockedContacts();
        contactToBlock.setEmail(email);
        contactToBlock.setFirstName(contactUploadDTO.getFirstName());
        contactToBlock.setLastName(contactUploadDTO.getLastName());
        contactToBlock.setReason(reason);
        blockedContactService.addBlockedContact(contactToBlock);
        // }
        // else
        // {
        // LOGGER.warn("Contact is already in blacklisted");
        // }
    }
}
