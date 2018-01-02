package com.bluespacetech.notifications.email.controller;

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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/emails"})
@CrossOrigin
public class EmailController
{
  @Autowired
  private EmailService emailService;
  @Autowired
  private JobExecutionRepository jobExecutionRepository;
  @Autowired
  private Consumer consumer;
  private Path reportsFilePath;
  private static final Logger LOGGER = LogManager.getLogger(EmailController.class);
  @Autowired
  @Qualifier("contactGroupEmailJob")
  private Job contactGroupEmailJob;
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public void job(@RequestBody EmailVO emailVO, HttpServletRequest request, HttpServletResponse response)
  {
    Email email = null;
    try
    {
      if (!emailVO.isPersonalizedEmail()) {
        email = this.emailService.createEmail(emailVO);
      }
      String createdDate = new SimpleDateFormat("yyyyMMddHHmmss").format(email.getCreationDate());
      String requestId = "RQ_" + email.getCreatedUser() + "_" + createdDate;
      
      String emailBody = emailVO.getMessage();
      for (String blocked : CommonUtilCache.getProhibitedContentList()) {
        if (emailBody.toLowerCase().contains(blocked.toLowerCase()))
        {
          LOGGER.error("Found Prohibited content in email body. The campaign cannot be triggered.");
          response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
          response.getOutputStream().println("{ \"Error\": \"Email campaign has been blocked as it was found to contain prohibited content. \"}");
        }
      }
      String queryGetContacts = QueryStringConstants.getQuery_QUERY_FIND_CONTACTS();
      
      Long emailId = Long.valueOf(0L);
      if (email != null) {
        emailId = email.getId();
      }
      if (emailVO.getGroupId() != null)
      {
        LOGGER.info("Running job for single group..");
        
        queryGetContacts = queryGetContacts + " AND CG.GROUP_ID = " + emailVO.getGroupId();
      }
      else if ((emailVO.getGroupIdList() != null) && (!emailVO.getGroupIdList().isEmpty()))
      {
        StringBuilder queryAppender = new StringBuilder();
        for (Long groupId : emailVO.getGroupIdList()) {
          queryAppender.append(groupId).append(",");
        }
        queryGetContacts = queryGetContacts + " AND CG.GROUP_ID IN (" + queryAppender.substring(0, queryAppender.lastIndexOf(",")) + ")";
        LOGGER.debug("Final Email Query : " + queryGetContacts);
      }
      List<EmailContactGroupVO> recipientList = this.emailService.getEmailContactGroups(queryGetContacts, emailVO, emailId);
      
      FileSystemResource reportsDir = new FileSystemResource("/opt/packages/Oracle/BluespaceMailer/reports/emailCampaigns");
      if (!reportsDir.exists()) {
        try
        {
          Files.createDirectory(Paths.get(reportsDir.getPath(), new String[0]), new FileAttribute[0]);
        }
        catch (IOException ex)
        {
          LOGGER.error("Failed to create new reports directory " + reportsDir.getPath() + ", reports will not be generated for this campaign");
        }
      }
      String fileName = email.getCreatedUser() + "_" + getCurrentDate() + "_" + emailId + ".csv";
      this.reportsFilePath = Paths.get(reportsDir.getPath(), new String[] { fileName });
      try
      {
        Files.createFile(this.reportsFilePath, new FileAttribute[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(",,,,,").append("Generated Report for Campaign").append(",").append(email.getSubject())
          .append(System.lineSeparator()).append(",,,,,").append("Campaign Date : ").append(",")
          .append(getCurrentDateWithSpaces()).append(System.lineSeparator()).append(",,,,,")
          .append("Generated Campaign ID : ").append(",").append(email.getId())
          .append(System.lineSeparator()).append(",,,,,").append("Campaign Sender username").append(",")
          .append(email.getCreatedUser()).append(System.lineSeparator()).append(System.lineSeparator())
          .append(System.lineSeparator())
          .append("JOB_ID,BATCH_ID,REQUEST_ID,CONTACT_EMAIL,FIRST_NAME,LAST_NAME,SEND_EMAIL_STATUS,COMMENTS")
          .append(System.lineSeparator());
        
        Files.write(this.reportsFilePath, sb.toString().getBytes(), new OpenOption[] { StandardOpenOption.TRUNCATE_EXISTING });
      }
      catch (IOException ex)
      {
        LOGGER.error("Failed to create new reports file " + fileName + " in " + reportsDir.getPath() + ", reports will not be generated for this campaign");
      }
      if (recipientList == null)
      {
        LOGGER.warn("No recipients found");
      }
      else if (recipientList.size() >= 1000)
      {
        List<List<EmailContactGroupVO>> splitList = chopped(recipientList, 1000);
        
        JobProcessingPriorityBlockingQueue queueInstance = JobProcessingPriorityBlockingQueue.getQueueInstance();
        
        DelegatingSecurityContextExecutorService securedExecutorService = null;
        long i = 0L;
        for (List<EmailContactGroupVO> internalList : splitList)
        {
          i += 1L;
          EmailJobEndpoint endpoint = new EmailJobEndpoint();
          endpoint.getEmailContactGroupList().addAll(internalList);
          endpoint.setBatchId(String.valueOf(i));
          endpoint.setRequestId(requestId);
          endpoint.setCampaignName(emailVO.getSubject());
          endpoint.setSender(ViewUtil.getPrincipal());
          endpoint.setRequestUrl(request.getRequestURL().toString());
          endpoint.setReportsFilePath(this.reportsFilePath);
          
          Producer producer = new Producer(endpoint, queueInstance, this.jobExecutionRepository);
          
          securedExecutorService = new DelegatingSecurityContextExecutorService(Executors.newFixedThreadPool(splitList.size()), ViewUtil.gtecurityContext());
          
          persistJobExecutionToDB(endpoint);
          securedExecutorService.submit(producer);
        }
        if (splitList.size() > 8)
        {
          LOGGER.info("Maintaing a ratio of producers :consumers = 4:1 for batches greater than 8");
          for (int j = 0; j < splitList.size() / 4; j++) {
            securedExecutorService.submit(this.consumer);
          }
        }
        else
        {
          LOGGER.info("Based on the batch size, one consumer should handle the entire production load..");
          securedExecutorService.submit(this.consumer);
        }
        securedExecutorService.shutdown();
      }
      else
      {
        DelegatingSecurityContextExecutorService securedExecutorService = new DelegatingSecurityContextExecutorService(Executors.newFixedThreadPool(2), ViewUtil.gtecurityContext());
        
        JobProcessingPriorityBlockingQueue queueInstance = JobProcessingPriorityBlockingQueue.getQueueInstance();
        EmailJobEndpoint endpoint = new EmailJobEndpoint();
        endpoint.getEmailContactGroupList().addAll(recipientList);
        endpoint.setBatchId("1");
        endpoint.setRequestId(requestId);
        endpoint.setCampaignName(emailVO.getSubject());
        endpoint.setSender(ViewUtil.getPrincipal());
        endpoint.setRequestUrl(request.getRequestURL().toString());
        endpoint.setReportsFilePath(this.reportsFilePath);
        
        Producer producer = new Producer(endpoint, queueInstance, this.jobExecutionRepository);
        
        persistJobExecutionToDB(endpoint);
        securedExecutorService.submit(producer);
        securedExecutorService.submit(this.consumer);
        securedExecutorService.shutdown();
      }
    }
    catch (Exception e)
    {
      LOGGER.error("Error caught in controller : " + e.getMessage() + " Rolling back email creation entry");
      try
      {
        this.emailService.deleteEmail(email);
        LOGGER.info("Email record rolled back successfully, Deleting reports file if already generated..");
        try
        {
          Files.deleteIfExists(this.reportsFilePath);
        }
        catch (IOException e1)
        {
          LOGGER.warn("Failed to delete reports file " + this.reportsFilePath + ", please delete manually");
        }
      }
      catch (BusinessException e1)
      {
        LOGGER.error("Failed to rollback transaction, reason : [" + e1.getMessage() + "]");
      }
      throw new RuntimeException(e);
    }
  }
  
  @PutMapping(value={"/attachFile"}, produces={"application/json"})
  public void attachFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id, HttpServletResponse response, HttpServletRequest request)
    throws IOException
  {
    FileAttachResponse res = new FileAttachResponse();
    if (!file.isEmpty())
    {
      String fileFilterRegex = "^(.*\\.(exe|mp3|mp4|java|js|ts|zip|7z|gz|tar|bin|bat|mkv|avi|3gp|aac|wmv|sh)$)?[^.]*$";
      Pattern patternFileFilter = Pattern.compile(fileFilterRegex);
      Matcher matcher = patternFileFilter.matcher(file.getOriginalFilename());
      response.setContentType("application/json");
      if (matcher.find())
      {
        LOGGER.info("Restricted File detected - match = [" + matcher.group() + "]" + ", attachment will not be allowed..");
        
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        
        Map<Object, Object> responseData = new HashMap();
        responseData.put("error", "Files of type (" + ext + ") cannot be sent as attachment due to security reasons");
        
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
          
          res.setLink(link);
          
          response.getWriter().write(new Gson().toJson(res));
        }
        catch (Exception e)
        {
          Map<Object, Object> responseData = new HashMap();
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
      Map<Object, Object> responseData = new HashMap();
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
    this.jobExecutionRepository.save(entity);
    LOGGER.info("Batch entry for " + jobEndPoint.getRequestId() + "|" + jobEndPoint.getBatchId() + " saved successfully");
  }
  
  <T> List<List<T>> chopped(List<T> list, int L)
  {
    List<List<T>> parts = new ArrayList();
    int N = list.size();
    for (int i = 0; i < N; i += L) {
      parts.add(new ArrayList(list.subList(i, Math.min(N, i + L))));
    }
    return parts;
  }
  
  @ExceptionHandler({BusinessException.class})
  ResponseEntity<String> handleContactNotFoundException(Exception e)
  {
    return new ResponseEntity(String.format("{\"reason\":\"%s\"}", new Object[] { e.getMessage() }), HttpStatus.NOT_FOUND);
  }
}
