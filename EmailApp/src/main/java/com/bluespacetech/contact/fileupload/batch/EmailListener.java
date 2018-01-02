package com.bluespacetech.contact.fileupload.batch;

import java.io.PrintStream;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;

public class EmailListener
  implements MessageChangedListener
{
  public void messageChanged(MessageChangedEvent arg0)
  {
    try
    {
      System.out.println(arg0.getMessage().getContentType() + " changed");
    }
    catch (MessagingException e)
    {
      e.printStackTrace();
    }
  }
}
