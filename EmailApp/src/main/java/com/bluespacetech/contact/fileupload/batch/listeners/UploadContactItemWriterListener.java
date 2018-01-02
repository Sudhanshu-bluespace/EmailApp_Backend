package com.bluespacetech.contact.fileupload.batch.listeners;

import com.bluespacetech.contact.entity.Contact;
import java.util.List;
import org.springframework.batch.core.ItemWriteListener;

public class UploadContactItemWriterListener
  implements ItemWriteListener<Contact>
{
  public void beforeWrite(List<? extends Contact> items) {}
  
  public void afterWrite(List<? extends Contact> items) {}
  
  public void onWriteError(Exception exception, List<? extends Contact> items) {}
}
