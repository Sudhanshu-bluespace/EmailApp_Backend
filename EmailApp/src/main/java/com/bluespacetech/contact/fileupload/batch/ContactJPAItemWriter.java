package com.bluespacetech.contact.fileupload.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.bluespacetech.contact.entity.Contact;

import com.bluespacetech.contact.repository.ContactRepository;

public class ContactJPAItemWriter implements ItemWriter<Contact> {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void write(List<? extends Contact> items){
		System.out.println("Received the information of {} students" + items.size());
		for (Contact contact : items) {
			Contact c = contactRepository.save(contact);
			System.out.println("saved contact : " + c);
		}
	}
}
