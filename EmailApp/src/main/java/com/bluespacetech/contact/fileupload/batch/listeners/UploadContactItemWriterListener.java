package com.bluespacetech.contact.fileupload.batch.listeners;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.bluespacetech.contact.entity.Contact;

public class UploadContactItemWriterListener implements ItemWriteListener<Contact> {

	@Override
	public void beforeWrite(List<? extends Contact> items) {
		System.out.println("ItemWriteListener - beforeWrite");
	}

	@Override
	public void afterWrite(List<? extends Contact> items) {
		System.out.println("ItemWriteListener - afterWrite");
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Contact> items) {
		System.out.println("ItemWriteListener - onWriteError "+exception.getMessage());
	}

}
