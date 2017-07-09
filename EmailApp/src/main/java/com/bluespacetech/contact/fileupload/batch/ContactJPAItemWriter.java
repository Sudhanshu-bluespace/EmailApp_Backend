package com.bluespacetech.contact.fileupload.batch;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;

public class ContactJPAItemWriter implements ItemWriter<Contact>
{

    private static final Logger LOGGER = LogManager.getLogger(ContactJPAItemWriter.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void write(List<? extends Contact> items)
    {
        for (Contact contact : items)
        {
            Contact c = contactRepository.save(contact);
            LOGGER.info("Saved contact : "+c+" successfully");
        }
    }
}
