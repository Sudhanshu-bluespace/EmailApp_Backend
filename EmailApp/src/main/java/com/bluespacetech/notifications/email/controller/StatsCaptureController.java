package com.bluespacetech.notifications.email.controller;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.crypto.Decryptor;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.exceptions.ContactAlreadySubscribedException;
import com.bluespacetech.core.exceptions.ContactAlreadyUnsubscribedException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.entity.EmailReadReceiptTracker;
import com.bluespacetech.notifications.email.repository.ReadReceiptTrackerRepository;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/track"})
public class StatsCaptureController
{
  @Autowired
  private EmailContactGroupService emailContactGroupService;
  @Autowired
  private ReadReceiptTrackerRepository readReceiptRepository;
  @Autowired
  private ContactService contactService;
  @Autowired
  private ContactGroupService contactGroupService;
  private static final Logger LOGGER = LogManager.getLogger(StatsCaptureController.class);
  
  @RequestMapping(value={"/readMail"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void readMail(HttpServletRequest request)
    throws BusinessException
  {
    LOGGER.info("Detected Email being read by a recipent. Processing statistics..");
    String reqContactId = request.getParameter("contactId");
    String reqGroupId = request.getParameter("groupId");
    String reqEmailRandomNumber = request.getParameter("emailRandomNumber");
    if ((reqContactId == null) || (reqGroupId == null) || (reqEmailRandomNumber == null) || (reqContactId.trim().isEmpty()) || 
      (reqGroupId.trim().isEmpty()) || (reqEmailRandomNumber.trim().isEmpty()))
    {
      LOGGER.warn("No input parameters deteted.. Skipping Stats Capture for readMail()");
      return;
    }
    Long contactId = null;
    contactId = Long.valueOf(reqContactId);
    Long groupId = Long.valueOf(reqGroupId);
    if ((contactId != null) && (groupId != null))
    {
      EmailContactGroup emailContactGroup = this.emailContactGroupService.findByContactIdAndGroupIdAndRandomNumber(contactId, groupId, reqEmailRandomNumber);
      if (emailContactGroup != null)
      {
        Integer readCount = emailContactGroup.getReadCount();
        readCount = Integer.valueOf(readCount != null ? (readCount = Integer.valueOf(readCount.intValue() + 1)).intValue() : 1);
        emailContactGroup.setReadCount(readCount);
        this.emailContactGroupService.updateEmailContactGroup(emailContactGroup);
        
        Contact contact = this.contactService.findById(contactId);
        if (contact != null)
        {
          String email = contact.getEmail();
          
          EmailReadReceiptTracker receipt = this.readReceiptRepository.findByContactIdAndGroupIdAndEmailId(contactId, groupId, emailContactGroup.getEmailId());
          if (receipt != null)
          {
            receipt.setReadCount(Integer.valueOf(receipt.getReadCount().intValue() + 1));
            this.readReceiptRepository.save(receipt);
            LOGGER.info("Updated read count for receipt " + receipt + " to database");
          }
          else
          {
            EmailReadReceiptTracker newReceipt = new EmailReadReceiptTracker();
            newReceipt.setContactId(contactId);
            newReceipt.setGroupId(groupId);
            newReceipt.setContactEmail(email.trim());
            newReceipt.setEmailId(emailContactGroup.getEmailId());
            newReceipt.setReadCount(Integer.valueOf(1));
            
            this.readReceiptRepository.save(newReceipt);
            LOGGER.info("Added read receipt " + newReceipt + " to database");
          }
        }
        else
        {
          LOGGER.warn("Could not locate contact with ID " + contactId + " in repository. Read Receipts cannot not be tracked");
        }
      }
    }
    LOGGER.info("Stats for Email updated successfully");
  }
  
  @RequestMapping(value={"/subscribe"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void selfSubscribeToGroup(HttpServletRequest request, HttpServletResponse response)
  {
    Long groupId = null;Long contactId = null;
    String contactEmail = null;
    Long emailId = null;
    String token = request.getParameter("token");
    if ((token == null) || (token.trim().isEmpty()))
    {
      LOGGER.warn("No token parameter found in request.. Probably a boot strap call only..");
      return;
    }
    LOGGER.info("Self Subscribe token : " + token);
    if ((token == null) || (token.trim().isEmpty()))
    {
      LOGGER.error("No input token found... No subscribe to proceed with..");
      return;
    }
    String decryptedToken = Decryptor.Decrypt(token);
    if ((decryptedToken == null) || (decryptedToken.trim().isEmpty()))
    {
      LOGGER.error("Failed to decrypt self-subscribe token : " + token + ", looks like the token is malformed.. Subscribe has failed..");
      return;
    }
    String[] split = decryptedToken.split("&");
    for (String splitToken : split)
    {
      String[] secondSplit = splitToken.split("=");
      if ("contactEmail".equalsIgnoreCase(secondSplit[0])) {
        contactEmail = secondSplit[1];
      } else if ("contactId".equalsIgnoreCase(secondSplit[0])) {
        contactId = Long.valueOf(secondSplit[1]);
      } else if ("groupId".equalsIgnoreCase(secondSplit[0])) {
        groupId = Long.valueOf(secondSplit[1]);
      } else if ("emailId".equalsIgnoreCase(secondSplit[0])) {
        emailId = Long.valueOf(secondSplit[1]);
      }
    }
    LOGGER.info("Contact id " + contactId + " initiated an Unsubscribe request. Processing!");
    if ((contactId != null) && (groupId != null))
    {
      Contact contact = this.contactService.getContactById(contactId);
      if (contact == null) {
        LOGGER.error("Failed to subscribe contact, Could not locate contact_id " + contactId);
      } else if (contact.getEmail().equals(contactEmail)) {
        try
        {
          String createdUser = this.emailContactGroupService.findByEmailIdAndContactIdAndGroupId(emailId, contactId, groupId);
          if (createdUser == null) {
            LOGGER.error("Failed to process full unsubscription request. Could not locate account that added this contact");
          } else {
            try
            {
              this.contactGroupService.subscribeContactGroup(contactId, groupId, createdUser);
            }
            catch (ContactAlreadyUnsubscribedException e)
            {
              try
              {
                response.getOutputStream().println("Your unsubscription request has already been processed.");
              }
              catch (IOException ex)
              {
                LOGGER.error("Failed to send response, reason:  [" + ex.getMessage() + "]");
              }
            }
          }
          LOGGER.info("Contact ID " + contactId + " has been re-subscribed to the mailing list successfully");
          response.getOutputStream().println("You have successsfully subscribed to the portal");
        }
        catch (BusinessException|IOException e)
        {
          LOGGER.error("Failed to unsubscribe contact, reason: [ " + e.getMessage() + " ]");
        }
        catch (ContactAlreadySubscribedException ex)
        {
          try
          {
            response.getOutputStream().println("Your subscription request has already been processed.");
          }
          catch (IOException e)
          {
            LOGGER.error("Failed to send response, reason:  [" + ex.getMessage() + "]");
          }
        }
      }
    }
  }
  
  @RequestMapping(value={"/unsubscribe"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void unsubscribeToGroup(HttpServletRequest request, HttpServletResponse response)
  {
    Long groupId = null;Long contactId = null;
    String contactEmail = null;
    boolean fullUnsubscribe = false;
    Long emailId = null;
    
    String token = request.getParameter("token");
    if ((token == null) || (token.trim().isEmpty()))
    {
      LOGGER.error("No input token found... No unsubscribe to proceed with..");
      return;
    }
    String decryptedToken = Decryptor.Decrypt(token);
    if ((decryptedToken == null) || (decryptedToken.trim().isEmpty()))
    {
      LOGGER.error("Failed to decrypt unsubscribe token : " + token + ", looks like the token is malformed.. UnSubscribe has failed..");
      return;
    }
    String[] split = decryptedToken.split("&");
    for (String splitToken : split)
    {
      String[] secondSplit = splitToken.split("=");
      if ("contactEmail".equalsIgnoreCase(secondSplit[0])) {
        contactEmail = secondSplit[1];
      } else if ("contactId".equalsIgnoreCase(secondSplit[0])) {
        contactId = Long.valueOf(secondSplit[1]);
      } else if ("groupId".equalsIgnoreCase(secondSplit[0])) {
        groupId = Long.valueOf(secondSplit[1]);
      } else if ("full".equalsIgnoreCase(secondSplit[0])) {
        fullUnsubscribe = true;
      } else if ("emailId".equalsIgnoreCase(secondSplit[0])) {
        emailId = Long.valueOf(secondSplit[1]);
      }
    }
    LOGGER.info("Contact id " + contactId + " initiated an Unsubscribe request. Processing!");
    if ((contactId != null) && (groupId != null))
    {
      Contact contact = this.contactService.getContactById(contactId);
      if (contact == null) {
        LOGGER.error("Failed to unsubscribe contact, Could not locate contact_id " + contactId);
      } else if (contact.getEmail().equals(contactEmail)) {
        try
        {
          if (fullUnsubscribe)
          {
            String createdUser = this.emailContactGroupService.findByEmailIdAndContactIdAndGroupId(emailId, contactId, groupId);
            if (createdUser == null) {
              LOGGER.error("Failed to process full unsubscription request. Could not locate account that added this contact");
            } else {
              this.contactGroupService.fullUnsubscribeContactGroup(contactId, groupId, createdUser);
            }
          }
          else
          {
            this.contactGroupService.unsubscribeContactGroup(contactId, groupId);
          }
          LOGGER.info("Contact ID " + contactId + " has been unsubscribed from the mailing list successfully");
          
          response.getOutputStream().println("You have been successsfully unsubscribed from the portal");
        }
        catch (BusinessException|IOException e)
        {
          LOGGER.error("Failed to unsubscribe contact, reason: [ " + e.getMessage() + " ]");
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
            LOGGER.error("Failed to send response, reason:  [" + e.getMessage() + "]");
          }
        }
      }
    }
  }
}
