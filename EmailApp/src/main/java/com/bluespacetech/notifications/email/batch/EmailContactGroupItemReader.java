package com.bluespacetech.notifications.email.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

/**
 * The Class EmailContactGroupItemReader.
 */
public class EmailContactGroupItemReader implements ItemReader<EmailContactGroupVO>
{

    /** The contact count. */
    private int contactCount;
    
    /** The contact list VO. */
    private List<EmailContactGroupVO> contactListVO;
    
    /**
     * Instantiates a new email contact group item reader.
     *
     * @param contactListVO the contact list VO
     */
    public EmailContactGroupItemReader( List<EmailContactGroupVO> contactListVO)
    {
        this.contactListVO = contactListVO;
        contactCount = 0;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public EmailContactGroupVO read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {
        EmailContactGroupVO contact = null;
        if(contactListVO!=null && !contactListVO.isEmpty()&& contactCount < contactListVO.size())
        {
            contact = contactListVO.get(contactCount);
            contactCount++;
        }   
        return contact;       
    }
}
