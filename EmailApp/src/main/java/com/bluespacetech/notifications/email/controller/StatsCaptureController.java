package com.bluespacetech.notifications.email.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.crypto.Decryptor;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.exceptions.ContactAlreadySubscribedException;
import com.bluespacetech.core.exceptions.ContactAlreadyUnsubscribedException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;

@RestController
@CrossOrigin
@RequestMapping("/track")
public class StatsCaptureController
{

    /** The email contact group service. */
    @Autowired
    private EmailContactGroupService emailContactGroupService;
    
    /** The contact service. */
    @Autowired
    private ContactService contactService;

    /** The contact group service. */
    @Autowired
    private ContactGroupService contactGroupService;
    
    private static final Logger LOGGER = LogManager.getLogger(StatsCaptureController.class);

    /**
     * Read mail.
     *
     * @param request the request
     * @throws BusinessException the business exception
     */
    @RequestMapping(value = "/readMail", method = RequestMethod.GET)
    public void readMail(HttpServletRequest request) throws BusinessException
    {
        LOGGER.info("Detected Email being read by a recipent. Processing statistics..");
        final String reqContactId = request.getParameter("contactId");
        final String reqGroupId = request.getParameter("groupId");
        final String reqEmailRandomNumber = request.getParameter("emailRandomNumber");

        Long groupId, contactId, emailRandomNumber = null;
        contactId = Long.valueOf(reqContactId);
        groupId = Long.valueOf(reqGroupId);
        emailRandomNumber = Long.valueOf(reqEmailRandomNumber);
        if (contactId != null && groupId != null)
        {
            final EmailContactGroup emailContactGroup = emailContactGroupService
                    .findByContactIdAndGroupIdAndRandomNumber(contactId, groupId, emailRandomNumber);
            if (emailContactGroup != null)
            {
                Integer readCount = emailContactGroup.getReadCount();
                readCount = readCount != null ? ++readCount : 1;
                emailContactGroup.setReadCount(readCount);
                emailContactGroupService.updateEmailContactGroup(emailContactGroup);
            }
        }
        
        LOGGER.info("Stats for Email updated successfully");

    }
    
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public void selfSubscribeToGroup(HttpServletRequest request,HttpServletResponse response)
    {
        Long groupId=null, contactId = null;
        String contactEmail = null;
        Long emailId=null;
        final String token = request.getParameter("token");
        String decryptedToken = Decryptor.Decrypt(token);
        String[] split = decryptedToken.split("&");
        for(String splitToken : split)
        {
            String[] secondSplit = splitToken.split("=");
            if("contactEmail".equalsIgnoreCase(secondSplit[0]))
            {
                contactEmail = secondSplit[1];
            }
            else if("contactId".equalsIgnoreCase(secondSplit[0]))
            {
                contactId = Long.valueOf(secondSplit[1]);
            }
            else if("groupId".equalsIgnoreCase(secondSplit[0]))
            {
                groupId=Long.valueOf(secondSplit[1]);
            }
            else if("emailId".equalsIgnoreCase(secondSplit[0]))
            {
                emailId=Long.valueOf(secondSplit[1]);
            }
        }
            
        //contactId = Long.valueOf(reqContactId);
        //groupId = Long.valueOf(reqGroupId);
        
        LOGGER.info("Contact id "+contactId+" initiated an Unsubscribe request. Processing!");
        
        if (contactId != null && groupId != null)
        {
            final Contact contact = contactService.getContactById(contactId);
            if(contact==null)
            {
                LOGGER.error("Failed to subscribe contact, Could not locate contact_id "+contactId);
            }
            else if (!contact.getEmail().equals(contactEmail))
            {

            }
            else
            {
                try
                {
                    String createdUser = emailContactGroupService.findByEmailIdAndContactIdAndGroupId(emailId, contactId, groupId);
                    if(createdUser==null)
                    {
                        LOGGER.error("Failed to process full unsubscription request. Could not locate account that added this contact");
                    }
                    else
                    {
                        try
                        {
                            contactGroupService.subscribeContactGroup(contactId, groupId,createdUser);
                        }
                        catch (ContactAlreadyUnsubscribedException e)
                        {
                            try
                            {
                                response.getOutputStream().println("Your unsubscription request has already been processed.");
                            }
                            catch (IOException ex)
                            {
                                LOGGER.error("Failed to send response, reason:  ["+ex.getMessage()+"]");
                            }
                        }
                    }

                    LOGGER.info("Contact ID "+contactId+" has been re-subscribed to the mailing list successfully");
                    response.getOutputStream().println("You have successsfully subscribed to the portal");
                }
                catch (final BusinessException | IOException e)
                {
                    LOGGER.error("Failed to unsubscribe contact, reason: [ "+e.getMessage()+" ]");
                }
                catch (ContactAlreadySubscribedException ex)
                {
                    try
                    {
                        response.getOutputStream().println("Your subscription request has already been processed.");
                    }
                    catch (IOException e)
                    {
                        LOGGER.error("Failed to send response, reason:  ["+ex.getMessage()+"]");
                    }
                }
            }
        }
    }
    

    /**
     * Unsubscribe to group.
     *
     * @param request the request
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public void unsubscribeToGroup(HttpServletRequest request,HttpServletResponse response)
    {
        Long groupId=null, contactId = null;
        String contactEmail = null;
        boolean fullUnsubscribe = false;
        Long emailId=null;
        
        final String token = request.getParameter("token");
        String decryptedToken = Decryptor.Decrypt(token);
        String[] split = decryptedToken.split("&");
        for(String splitToken : split)
        {
            String[] secondSplit = splitToken.split("=");
            if("contactEmail".equalsIgnoreCase(secondSplit[0]))
            {
                contactEmail = secondSplit[1];
            }
            else if("contactId".equalsIgnoreCase(secondSplit[0]))
            {
                contactId = Long.valueOf(secondSplit[1]);
            }
            else if("groupId".equalsIgnoreCase(secondSplit[0]))
            {
                groupId=Long.valueOf(secondSplit[1]);
            }
            else if("full".equalsIgnoreCase(secondSplit[0]))
            {
                fullUnsubscribe = true;
            }
            else if("emailId".equalsIgnoreCase(secondSplit[0]))
            {
                emailId=Long.valueOf(secondSplit[1]);
            }
        }
            
        //contactId = Long.valueOf(reqContactId);
        //groupId = Long.valueOf(reqGroupId);
        
        LOGGER.info("Contact id "+contactId+" initiated an Unsubscribe request. Processing!");
        
        if (contactId != null && groupId != null)
        {
            final Contact contact = contactService.getContactById(contactId);
            if(contact==null)
            {
                LOGGER.error("Failed to unsubscribe contact, Could not locate contact_id "+contactId);
            }
            else if (!contact.getEmail().equals(contactEmail))
            {

            }
            else
            {
                try
                {
                    if(fullUnsubscribe)
                    {
                        String createdUser = emailContactGroupService.findByEmailIdAndContactIdAndGroupId(emailId, contactId, groupId);
                        if(createdUser==null)
                        {
                            LOGGER.error("Failed to process full unsubscription request. Could not locate account that added this contact");
                        }
                        else
                        {
                            contactGroupService.fullUnsubscribeContactGroup(contactId, groupId,createdUser);
                        }
                    }
                    else
                    {
                        contactGroupService.unsubscribeContactGroup(contactId, groupId);
                    }
                    LOGGER.info("Contact ID "+contactId+" has been unsubscribed from the mailing list successfully");
                    response.getOutputStream().println("You have been successsfully unsubscribed from the portal");
                }
                catch (final BusinessException | IOException e)
                {
                    LOGGER.error("Failed to unsubscribe contact, reason: [ "+e.getMessage()+" ]");
                    e.printStackTrace();
                }
                catch (ContactAlreadyUnsubscribedException ex)
                {
                    try
                    {
                        response.getOutputStream().println("Your unsubscription request has already been processed.");
                    }
                    catch (IOException e)
                    {
                        LOGGER.error("Failed to send response, reason:  ["+e.getMessage()+"]");
                    }
                }
            }
        }
    }

}
