package com.bluespacetech.contact.fileupload.batch;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.group.entity.Group;


public class ContactItemProcessor implements ItemProcessor<ContactUploadDTO, Contact> {
	
    @Override
    public Contact process(final ContactUploadDTO contactDTO) {

    	Map<String,Group> groupNameToGroupMap = CommonUtilCache.getGroupNameToGroupMap();
        final String firstName = contactDTO.getFirstName();
        final String lastName = contactDTO.getLastName();
        final String email = contactDTO.getEmail();

        Contact contact = new Contact();
        contact.getContactGroups().clear();
        
        String groups = contactDTO.getGroup();
        ContactGroup cg = null;

        if(groups.contains(";"))
        {
        	for(String groupName:groups.split(";"))
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
        
        for(ContactGroup cGroup:contact.getContactGroups())
        {
        	cGroup.setContact(contact);
        	cGroup.getGroup().getContactGroups().clear();
        	cGroup.getGroup().getContactGroups().addAll(contact.getContactGroups());
        	System.out.println("CG : "+cGroup);
        }
        return contact;
    }
    
    private ContactGroup createContactGroup(String groupName, Map<String,Group> groupNameToGroupMap)
    {
    	ContactGroup cg = new ContactGroup();
		cg.setActive(true);
		cg.setUnSubscribed(false);
    	if(groupNameToGroupMap.containsKey(groupName))
    	{
    		cg.setGroup(groupNameToGroupMap.get(groupName));
    	}
    	
    	return cg;
    }

}
