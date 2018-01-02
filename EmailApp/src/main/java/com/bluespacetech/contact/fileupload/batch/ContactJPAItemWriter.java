package com.bluespacetech.contact.fileupload.batch;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactJPAItemWriter
  implements ItemWriter<Contact>
{
  private static final Logger LOGGER = LogManager.getLogger(ContactJPAItemWriter.class);
  @Autowired
  private ContactRepository contactRepository;
  
  public void write(List<? extends Contact> items)
  {
    for (Contact contact : items)
    {
      Contact c = (Contact)this.contactRepository.save(contact);
      LOGGER.info("Saved contact : " + c + " successfully");
    }
  }
}
