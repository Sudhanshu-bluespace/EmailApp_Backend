/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.entity.JobExecutionEntity;
import com.bluespacetech.notifications.email.executionqueue.Consumer;
import com.bluespacetech.notifications.email.executionqueue.EmailJobEndpoint;
import com.bluespacetech.notifications.email.executionqueue.JobProcessingPriorityBlockingQueue;
import com.bluespacetech.notifications.email.executionqueue.Producer;
import com.bluespacetech.notifications.email.repository.JobExecutionRepository;
import com.bluespacetech.notifications.email.service.EmailService;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;
import com.bluespacetech.server.analytics.query.QueryStringConstants;

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
   // @Autowired
   // private JobLauncher jobLauncher;

    /** The email service. */
    @Autowired
    private EmailService emailService;

    @Autowired
    private JobExecutionRepository jobExecutionRepository;

    @Autowired
    private Consumer consumer;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(EmailController.class);

    /** The job. */
    @Autowired
    @Qualifier("contactGroupEmailJob")
    private Job contactGroupEmailJob;

    /**
     * Job.
     *
     * @param emailVO the email VO
     * @param request the request
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void job(@RequestBody final EmailVO emailVO, HttpServletRequest request, HttpServletResponse response)
    {
        Email email = null;
        try
        {
            // final Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
            if (!emailVO.isPersonalizedEmail())
            {
                email = emailService.createEmail(emailVO);
            }

            String createdDate = new SimpleDateFormat("yyyyMMddHHmmss").format(email.getCreationDate());
            String requestId = "RQ_" + email.getCreatedUser() + "_" + createdDate;

            String emailBody = emailVO.getMessage();
            String formattedBody = "";
          /*  if (emailBody.contains("<img src="))
            {
                String regex = "<img([\\w\\W]+?)>";
                Pattern pattern = Pattern.compile(regex);
                java.util.regex.Matcher matcher = pattern.matcher(emailBody);
                while (matcher.find())
                {
                    String res = matcher.group();
                    if(res.contains("+"))
                    {
                        res = res.replaceAll("+", "\\+");
                    }
                    formattedBody = emailBody.replace(res, "<img src=\"cid:image\">");
                }
            }
            if ("".equalsIgnoreCase(formattedBody.trim()))
            {
                for (String blocked : CommonUtilCache.getProhibitedContentList())
                {
                    if (emailBody.toLowerCase().contains(blocked.toLowerCase()))
                    {
                        LOGGER.error(
                                "Found Prohibited content in email body even after formatting. The campaign cannot be triggered.");
                        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                        response.getOutputStream().println(
                                "{ \"Error\": \"Email campaign has been blocked as it was found to contain prohibited content. \"}");
                    }
                }
            }
            else
            {*/
                for (String blocked : CommonUtilCache.getProhibitedContentList())
                {
                    if (emailBody.toLowerCase().contains(blocked.toLowerCase()))
                    {
                        LOGGER.error("Found Prohibited content in email body. The campaign cannot be triggered.");
                        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                        response.getOutputStream().println(
                                "{ \"Error\": \"Email campaign has been blocked as it was found to contain prohibited content. \"}");
                    }
                }
            //}

            String queryGetContacts = QueryStringConstants.getQuery_QUERY_FIND_CONTACTS();

            Long emailId = 0L;
            if (email != null)
            {
                emailId = email.getId();
            }

            if (emailVO.getGroupId() != null)
            {
                LOGGER.info("Running job for single group..");

                queryGetContacts = queryGetContacts + " AND CG.GROUP_ID = " + emailVO.getGroupId();

                // jobParametersMap.put("groupId", new JobParameter(emailVO.getGroupId()));
                /*
                 * if (email != null) { jobParametersMap.put("emailId", new JobParameter(email.getId())); } jobParametersMap.put("dateAndTime", new JobParameter(new Date()));
                 * jobParametersMap.put("message", new JobParameter(emailVO.getMessage())); jobParametersMap.put("subject", new JobParameter(emailVO.getSubject()));
                 * jobParametersMap.put("emailRequestURL", new JobParameter(request.getRequestURL().toString())); jobLauncher.run(job, new JobParameters(jobParametersMap));
                 */
            }
            else if (emailVO.getGroupIdList() != null && !emailVO.getGroupIdList().isEmpty())
            {
                StringBuilder queryAppender = new StringBuilder();

                for (final Long groupId : emailVO.getGroupIdList())
                {
                    queryAppender.append(groupId).append(",");
                    /*
                     * if (email != null) { jobParametersMap.put("emailId", new JobParameter(email.getId())); } //jobParametersMap.put("groupId", new JobParameter(groupId));
                     * jobParametersMap.put("dateAndTime", new JobParameter(new Date())); jobParametersMap.put("message", new JobParameter(emailVO.getMessage())); jobParametersMap.put("subject", new
                     * JobParameter(emailVO.getSubject())); jobParametersMap.put("emailRequestURL", new JobParameter(request.getRequestURL().toString())); JobExecution execution = jobLauncher.run(job,
                     * new JobParameters(jobParametersMap)); LOGGER.info("Job status : " + execution.getExitStatus()); if (execution.getExitStatus().getExitCode().contains("FAILED")) { throw new
                     * BusinessException("Failed to execute Job"); }
                     */
                }
                queryGetContacts = queryGetContacts + " AND CG.GROUP_ID IN ("
                        + queryAppender.substring(0, queryAppender.lastIndexOf(",")) + ")";
                LOGGER.info("Final Email Query : " + queryGetContacts);
            }

            List<EmailContactGroupVO> recipientList = emailService.getEmailContactGroups(queryGetContacts, emailVO,
                    emailId);
            if (recipientList == null)
            {
                LOGGER.warn("No recipients found");
            }
            else if (recipientList.size() >= 1000)
            {
                List<List<EmailContactGroupVO>> splitList = chopped(recipientList, 1000);
                JobProcessingPriorityBlockingQueue queueInstance = JobProcessingPriorityBlockingQueue
                        .getQueueInstance();

                DelegatingSecurityContextExecutorService securedExecutorService = null;
                long i = 0;
                for (List<EmailContactGroupVO> internalList : splitList)
                {
                    i++;
                    EmailJobEndpoint endpoint = new EmailJobEndpoint();
                    endpoint.getEmailContactGroupList().addAll(internalList);
                    endpoint.setBatchId(String.valueOf(i));
                    endpoint.setRequestId(requestId);
                    endpoint.setCampaignName(emailVO.getSubject());
                    endpoint.setSender(ViewUtil.getPrincipal());
                    endpoint.setRequestUrl(request.getRequestURL().toString());

                    Producer producer = new Producer(endpoint, queueInstance, jobExecutionRepository);
                    // Consumer consumer = new Consumer(queueInstance, contactGroupEmailJob, jobLauncher);

                    securedExecutorService = new DelegatingSecurityContextExecutorService(
                            Executors.newFixedThreadPool(splitList.size()), ViewUtil.gtecurityContext());

                    persistJobExecutionToDB(endpoint);
                    securedExecutorService.submit(producer);
                }

                if (splitList.size() > 8)
                {
                    LOGGER.info("Maintaing a ratio of producers :consumers = 4:1 for batches greater than 8");
                    for (int j = 0; j < splitList.size() / 4; j++)
                    {
                        securedExecutorService.submit(consumer);
                    }
                }
                else
                {
                    LOGGER.info("Based on the batch size, one consumer should handle the entire production load..");
                    securedExecutorService.submit(consumer);
                }
                securedExecutorService.shutdown();
            }
            else
            {
                DelegatingSecurityContextExecutorService securedExecutorService = new DelegatingSecurityContextExecutorService(
                        Executors.newFixedThreadPool(2), ViewUtil.gtecurityContext());

                JobProcessingPriorityBlockingQueue queueInstance = JobProcessingPriorityBlockingQueue
                        .getQueueInstance();
                EmailJobEndpoint endpoint = new EmailJobEndpoint();
                endpoint.getEmailContactGroupList().addAll(recipientList);
                endpoint.setBatchId("1");
                endpoint.setRequestId(requestId);
                endpoint.setCampaignName(emailVO.getSubject());
                endpoint.setSender(ViewUtil.getPrincipal());
                endpoint.setRequestUrl(request.getRequestURL().toString());

                Producer producer = new Producer(endpoint, queueInstance, jobExecutionRepository);
                // Consumer consumer = new Consumer(queueInstance, contactGroupEmailJob, jobLauncher);

                persistJobExecutionToDB(endpoint);
                securedExecutorService.submit(producer);
                securedExecutorService.submit(consumer);
                securedExecutorService.shutdown();
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
     * Persist job execution to DB.
     *
     * @param jobEndPoint2 the job end point 2
     */
    private void persistJobExecutionToDB(EmailJobEndpoint jobEndPoint)
    {
        JobExecutionEntity entity = new JobExecutionEntity();
        entity.setBatchId(jobEndPoint.getBatchId());
        entity.setRequestId(jobEndPoint.getRequestId());
        entity.setEmailCount(Long.valueOf(jobEndPoint.getEmailContactGroupList().size()));
        entity.setCampaignName(jobEndPoint.getCampaignName());
        entity.setSender(jobEndPoint.getSender());
        entity.setJobId("-");
        entity.setStatus("QUEUED");
        jobExecutionRepository.save(entity);
        LOGGER.info("Batch entry for " + jobEndPoint.getRequestId() + "|" + jobEndPoint.getBatchId()
                + " saved successfully");
    }

    // chops a list into non-view sublists of length L
    <T> List<List<T>> chopped(List<T> list, final int L)
    {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L)
        {
            parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
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
