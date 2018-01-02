package com.bluespacetech.contact.fileupload.batch.listeners;

import com.bluespacetech.contact.fileupload.dto.ContactUploadDTO;
import org.springframework.batch.core.ItemReadListener;

public class UploadContactItemReaderListener
  implements ItemReadListener<ContactUploadDTO>
{
  public void beforeRead() {}
  
  public void onReadError(Exception ex) {}
  
  public void afterRead(ContactUploadDTO contactUploadDTO) {}
}
