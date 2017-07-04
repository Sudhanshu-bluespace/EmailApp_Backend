package com.bluespacetech.contact.fileupload.batch;

import javax.mail.MessagingException;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;

public class EmailListener implements MessageChangedListener
{

    @Override
    public void messageChanged(MessageChangedEvent arg0)
    {
        try
        {
            System.out.println(arg0.getMessage().getContentType()+" changed");
        }
        catch (MessagingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
