package com.bluespacetech.contact.fileupload.batch.listeners;

import org.springframework.batch.core.ItemProcessListener;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;

public class UploadContactItemProcessorListener implements ItemProcessListener<ContactUploadDTO, Contact> {

	@Override
	public void afterProcess(ContactUploadDTO arg0, Contact arg1) {
		
	}

	@Override
	public void beforeProcess(ContactUploadDTO arg0) {
		
	}

	@Override
	public void onProcessError(ContactUploadDTO arg0, Exception arg1) {
		
	}

}
