/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.core.crypto.Encryptor;
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
import com.bluespacetech.notifications.email.valueobjects.FileAttachResponse;
import com.bluespacetech.server.analytics.query.QueryStringConstants;
import com.google.gson.Gson;

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

    /** The job execution repository. */
    @Autowired
    private JobExecutionRepository jobExecutionRepository;

    /** The consumer. */
    @Autowired
    private Consumer consumer;

    private Path reportsFilePath;

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
     * @param response the response
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
            // String formattedBody = "";
            /*
             * if (emailBody.contains("<img src=")) { String regex = "<img([\\w\\W]+?)>"; Pattern pattern = Pattern.compile(regex); java.util.regex.Matcher matcher = pattern.matcher(emailBody); while
             * (matcher.find()) { String res = matcher.group(); if(res.contains("+")) { res = res.replaceAll("+", "\\+"); } formattedBody = emailBody.replace(res, "<img src=\"cid:image\">"); } } if
             * ("".equalsIgnoreCase(formattedBody.trim())) { for (String blocked : CommonUtilCache.getProhibitedContentList()) { if (emailBody.toLowerCase().contains(blocked.toLowerCase())) {
             * LOGGER.error( "Found Prohibited content in email body even after formatting. The campaign cannot be triggered."); response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
             * response.getOutputStream().println( "{ \"Error\": \"Email campaign has been blocked as it was found to contain prohibited content. \"}"); } } } else {
             */
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
            // }

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

            FileSystemResource reportsDir = new FileSystemResource("/opt/packages/Oracle/BluespaceMailer/reports");
            if (!reportsDir.exists())
            {
                try
                {
                    Files.createDirectory(Paths.get(reportsDir.getPath()));
                }
                catch (IOException ex)
                {
                    LOGGER.error("Failed to create new reports directory " + reportsDir.getPath()
                            + ", reports will not be generated for this campaign");
                }
            }

            String fileName = email.getCreatedUser() + "_" + emailVO.getSubject().replaceAll("\\s", "\\-") + "_"
                    + getCurrentDate() + "_" + emailId + ".csv";
            this.reportsFilePath = Paths.get(reportsDir.getPath(), fileName);
            
            try
            {
                Files.createFile(reportsFilePath);
                StringBuilder sb = new StringBuilder();
                sb.append(",,,,,")
                .append("Generated Report for Campaign")
                .append(",")
                .append(email.getSubject())
                .append(System.lineSeparator())
                .append(",,,,,")
                .append("Campaign Date : ").append(",")
                .append(getCurrentDateWithSpaces())
                .append(System.lineSeparator())
                .append(",,,,,")
                .append("Generated Campaign ID : ")
                .append(",")
                .append(email.getId())
                .append(System.lineSeparator())
                .append(",,,,,")
                .append("Campaign Sender username")
                .append(",")
                .append(email.getCreatedUser())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("JOB_ID,BATCH_ID,REQUEST_ID,CONTACT_EMAIL,FIRST_NAME,LAST_NAME,SEND_EMAIL_STATUS,COMMENTS")
                .append(System.lineSeparator());
                
                Files.write(reportsFilePath, sb.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }
            catch (IOException ex)
            {
                LOGGER.error("Failed to create new reports file " + fileName + " in " + reportsDir.getPath()
                        + ", reports will not be generated for this campaign");
            }

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
                    endpoint.setReportsFilePath(reportsFilePath);

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
                endpoint.setReportsFilePath(reportsFilePath);

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
                LOGGER.info("Email record rolled back successfully, Deleting reports file if already generated..");
                try
                {
                    Files.deleteIfExists(reportsFilePath);
                }
                catch (IOException e1)
                {
                    LOGGER.warn("Failed to delete reports file "+reportsFilePath+", please delete manually");
                }
            }
            catch (BusinessException e1)
            {
                LOGGER.error("Failed to rollback transaction, reason : [" + e1.getMessage() + "]");
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * Attach file.
     *
     * @param file the file
     * @param id the id
     * @param response the response
     * @param request the request
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @PutMapping(value = "/attachFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public void attachFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id,
            HttpServletResponse response, HttpServletRequest request) throws IOException
    {
        // HttpHeaders headers = new HttpHeaders();
        FileAttachResponse res = new FileAttachResponse();
        if (!file.isEmpty())
        {

            // String fileFilerNegativeRegex = "^(.*\\.(?!(exe|mp3|mp4|java|js|ts|zip|7z|gz|tar|bin|bat|sh)$))?[^.]*$";
            String fileFilterRegex = "^(.*\\.(exe|mp3|mp4|java|js|ts|zip|7z|gz|tar|bin|bat|mkv|avi|3gp|aac|wmv|sh)$)?[^.]*$";
            Pattern patternFileFilter = Pattern.compile(fileFilterRegex);
            java.util.regex.Matcher matcher = patternFileFilter.matcher(file.getOriginalFilename());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            if (matcher.find())
            {
                LOGGER.info("Restricted File detected - match = [" + matcher.group() + "]"
                        + ", attachment will not be allowed..");
                String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                /*
                 * res.setError("Files of type (" + ext + ") cannot be sent as attachment due to security reasons"); response.setStatus(HttpStatus.BAD_REQUEST.value()); response.getWriter().write(new
                 * Gson().toJson(res));
                 */

                Map<Object, Object> responseData = new HashMap<>();
                responseData.put("error",
                        "Files of type (" + ext + ") cannot be sent as attachment due to security reasons");
                String jsonResponseData = new Gson().toJson(responseData);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getWriter().write(jsonResponseData);
            }
            else
            {
                try
                {
                    // Get the file and save it somewhere
                    byte[] bytes = file.getBytes();
                    Resource resource = new FileSystemResource("/opt/packages/Oracle/BluespaceMailer/temp");

                    System.out.println("Resource : " + resource.getFilename() + " | " + resource.exists());
                    String originalFileName = file.getOriginalFilename().replaceAll("\\s", "_");
                    String date = getCurrentDate();
                    String fileName = id + "_" + date + "_" + originalFileName;

                    File tempFile = new File(resource.getFile() + File.separator + fileName);

                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(bytes);
                    fos.close();
                    response.setStatus(200);

                    URL url = new URL(request.getRequestURL().toString());
                    String host = url.getHost();
                    String scheme = url.getProtocol();
                    int port = url.getPort();
                    URI uri = new URI(scheme, null, host, port, null, null, null);

                    String encryptedPath = Encryptor.Encrypt(tempFile.getPath());
                    String link = uri.toString() + "/products/downloadFile?path=" + encryptedPath;

                    // FileAttachResponse res = new FileAttachResponse();
                    res.setLink(link);

                    response.getWriter().write(new Gson().toJson(res));

                }
                catch (Exception e)
                {
                    // res.setError("Failed to transfer file to temp location, reason :" + e.getMessage());
                    // response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    // response.getWriter().write(new Gson().toJson(res));

                    Map<Object, Object> responseData = new HashMap<>();
                    responseData.put("error", e.getMessage());
                    String jsonResponseData = new Gson().toJson(responseData);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.getWriter().write(jsonResponseData);
                }
            }
        }
        else
        {
            // res.setError(HttpStatus.BAD_REQUEST + " No input file found");
            // response.setStatus(HttpStatus.BAD_REQUEST.value());
            // response.getWriter().write(new Gson().toJson(res));

            Map<Object, Object> responseData = new HashMap<>();
            responseData.put("error", HttpStatus.BAD_REQUEST + " No input file found");
            String jsonResponseData = new Gson().toJson(responseData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(jsonResponseData);
        }
    }

    private String getCurrentDate()
    {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }
    
    private String getCurrentDateWithSpaces()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    /**
     * Persist job execution to DB.
     *
     * @param jobEndPoint the job end point
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

    /**
     * Chopped.
     *
     * @param <T> the generic type
     * @param list the list
     * @param L the l
     * @return the list
     */
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
