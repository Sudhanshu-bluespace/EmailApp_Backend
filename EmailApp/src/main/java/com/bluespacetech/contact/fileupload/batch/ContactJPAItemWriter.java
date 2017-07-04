package com.bluespacetech.contact.fileupload.batch;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.bluespacetech.contact.entity.Contact;

import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.notifications.email.validators.EmailMXRecordDNSValidator;

public class ContactJPAItemWriter implements ItemWriter<Contact> {
    
        private static final Logger LOGGER = LogManager.getLogger(ContactJPAItemWriter.class);

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void write(List<? extends Contact> items){
		for (Contact contact : items) {
		        List<String> mxRecords = EmailMXRecordDNSValidator.validateMxRecord(contact.getEmail());
		        if(mxRecords.isEmpty())
		        {
		            LOGGER.warn("MX records / DNS could not be verified for "+contact.getEmail()+", This contact will be saved as spam");
		            //TODO write code to aave this to a spam list
		        }
		        else
		        {
		            Contact c = contactRepository.save(contact);
		            LOGGER.info("saved contact : " + c);
		        }
		}
	}
}
