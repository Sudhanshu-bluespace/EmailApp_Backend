/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailService;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;

/**
 * The Class EmailController.
 *
 * @author pradeep created date 30-Jan-2015
 * @author Sudhanshu Tripathy modified 17-April-2017
 */
@RestController
@RequestMapping("/emails")
@CrossOrigin
public class EmailController
{

    /** The job launcher. */
    @Autowired
    private JobLauncher jobLauncher;

    /** The email service. */
    @Autowired
    private EmailService emailService;

    /** The contact service. */
    @Autowired
    private ContactService contactService;

    /** The contact group service. */
    @Autowired
    private ContactGroupService contactGroupService;

    /** The email contact group service. */
    @Autowired
    private EmailContactGroupService emailContactGroupService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailController.class);

    /** The job. */
    @Autowired
    @Qualifier("groupEmailJob")
    private Job job;

    /**
     * Job.
     *
     * @param emailVO the email VO
     * @param request the request
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void job(@RequestBody final EmailVO emailVO, HttpServletRequest request)
    {
        Email email = null;
        try
        {
            final Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
            if (!emailVO.isPersonalizedEmail())
            {
                email = emailService.createEmail(emailVO);
            }
            if (emailVO.getGroupId() != null)
            {
                LOGGER.info("Running job for single group..");
                jobParametersMap.put("groupId", new JobParameter(emailVO.getGroupId()));
                if (email != null)
                {
                    jobParametersMap.put("emailId", new JobParameter(email.getId()));
                }
                jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
                jobParametersMap.put("message", new JobParameter(emailVO.getMessage()));
                jobParametersMap.put("subject", new JobParameter(emailVO.getSubject()));
                jobParametersMap.put("emailRequestURL", new JobParameter(request.getRequestURL().toString()));
                jobLauncher.run(job, new JobParameters(jobParametersMap));
            }
            else if (emailVO.getGroupIdList() != null && !emailVO.getGroupIdList().isEmpty())
            {
                for (final Long groupId : emailVO.getGroupIdList())
                {
                    if (email != null)
                    {
                        jobParametersMap.put("emailId", new JobParameter(email.getId()));
                    }
                    jobParametersMap.put("groupId", new JobParameter(groupId));
                    jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
                    jobParametersMap.put("message", new JobParameter(emailVO.getMessage()));
                    jobParametersMap.put("subject", new JobParameter(emailVO.getSubject()));
                    jobParametersMap.put("emailRequestURL", new JobParameter(request.getRequestURL().toString()));
                    JobExecution execution = jobLauncher.run(job, new JobParameters(jobParametersMap));

                    LOGGER.info("Job status : " + execution.getExitStatus());
                    if (execution.getExitStatus().getExitCode().contains("FAILED"))
                    {
                        throw new BusinessException("Failed to execute Job");
                    }
                }
            }
        }
        catch (final Exception e)
        {
            LOGGER.error("Error caught in controller : " + e.getMessage() + " Rolling back email creation entry");
            try
            {
                emailService.deleteEmail(email);
                LOGGER.info("Email record rolled back successfully");
            }
            catch (BusinessException e1)
            {
                LOGGER.error("Failed to rollback transaction, reason : [" + e1.getMessage() + "]");
            }
            throw new RuntimeException(e);

        }
    }

    /**
     * Unsubscribe to group.
     *
     * @param request the request
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public void unsubscribeToGroup(HttpServletRequest request)
    {
        /*
         * String reqContactId = null; try { reqContactId =
         * URLDecoder.decode(request.getParameter("contactId"), "UTF-8"); }
         * catch (final UnsupportedEncodingException e1) { // TODO
         * Auto-generated catch block e1.printStackTrace(); } String reqGroupId
         * = null; try { reqGroupId =
         * URLDecoder.decode(request.getParameter("groupId"), "UTF-8"); } catch
         * (final UnsupportedEncodingException e1) { // TODO Auto-generated
         * catch block e1.printStackTrace(); } final String contactEmail =
         * request.getParameter("contactEmail"); final CryptoUtil cryptoUtil =
         * new CryptoUtil(); Long contactId = null; Long groupId = null; try {
         * contactId =
         * Long.valueOf(cryptoUtil.decrypt(EmailUtils.EMAIL_SECRET_KEY,
         * reqContactId)); groupId =
         * Long.valueOf(cryptoUtil.decrypt(EmailUtils.EMAIL_SECRET_KEY,
         * reqGroupId)); } catch (InvalidKeyException | NoSuchAlgorithmException
         * | InvalidKeySpecException | NoSuchPaddingException |
         * InvalidAlgorithmParameterException | IllegalBlockSizeException |
         * BadPaddingException | IOException e) { // TODO Auto-generated catch
         * block e.printStackTrace(); }
         */
        final String reqContactId = request.getParameter("contactId");
        final String reqGroupId = request.getParameter("groupId");
        final String contactEmail = request.getParameter("contactEmail");
        Long groupId, contactId = null;
        contactId = Long.valueOf(reqContactId);
        groupId = Long.valueOf(reqGroupId);
        if (contactId != null && groupId != null)
        {
            final Contact contact = contactService.getContactById(contactId);
            if (!contact.getEmail().equals(contactEmail))
            {

            }
            else
            {
                try
                {
                    contactGroupService.unsubscribeContactGroup(contactId, groupId);
                }
                catch (final BusinessException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Read mail.
     *
     * @param request the request
     * @throws BusinessException the business exception
     */
    @RequestMapping(value = "/readMail", method = RequestMethod.GET)
    public void readMail(HttpServletRequest request) throws BusinessException
    {
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

    }

    /**
     * Handle contact not found exception.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<String> handleContactNotFoundException(final Exception e)
    {
        return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
