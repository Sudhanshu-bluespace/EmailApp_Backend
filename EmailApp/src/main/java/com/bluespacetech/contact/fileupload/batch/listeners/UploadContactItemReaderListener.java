package com.bluespacetech.contact.fileupload.batch.listeners;

import org.springframework.batch.core.ItemReadListener;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;

public class UploadContactItemReaderListener implements ItemReadListener<ContactUploadDTO> {

	@Override
	public void beforeRead() {
		System.out.println("ItemReadListener - beforeRead");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("ItemReadListener - onReadError "+ex.getMessage());
	}

	@Override
	public void afterRead(ContactUploadDTO contactUploadDTO) {
		System.out.println("ItemReadListener - afterRead "+contactUploadDTO);
		
	}

}