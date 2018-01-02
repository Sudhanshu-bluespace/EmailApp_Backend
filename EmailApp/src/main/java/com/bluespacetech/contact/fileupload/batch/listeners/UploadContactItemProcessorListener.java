package com.bluespacetech.contact.fileupload.batch.listeners;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import org.springframework.batch.core.ItemProcessListener;

public class UploadContactItemProcessorListener
  implements ItemProcessListener<ContactUploadDTO, Contact>
{
  public void afterProcess(ContactUploadDTO arg0, Contact arg1) {}
  
  public void beforeProcess(ContactUploadDTO arg0) {}
  
  public void onProcessError(ContactUploadDTO arg0, Exception arg1) {}
}
