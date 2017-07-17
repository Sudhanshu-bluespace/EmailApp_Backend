/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluespacetech.common.util.CommonUtilCache;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.exceptions.InvalidUploadFileTypeException;
import com.bluespacetech.core.exceptions.MaximumFileSizeExceededException;
import com.bluespacetech.core.exceptions.UploadedFileDataCorruptException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.repository.GroupRepository;
import com.bluespacetech.group.service.GroupService;

/**
 * The Class ContactController.
 *
 * @author pradeep created date 30-Jan-2015
 * @author Sudhanshu Tripathy
 */
@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController
{

    /** The contact service. */
    @Autowired
    ContactService contactService;

    /** The contact repository. */
    @Autowired
    private ContactRepository contactRepository;

    /** The group service. */
    @Autowired
    GroupService groupService;

    /** The group repository. */
    @Autowired
    GroupRepository groupRepository;

    /** The job launcher. */
    @Autowired
    private JobLauncher jobLauncher;

    /** The job. */
    @Autowired
    @Qualifier("uploadContactJob")
    private Job job;

    /** The env. */
    @Autowired
    private Environment env;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);

    /**
     * Creates the.
     *
     * @param contact
     *            the contact
     * @return the response entity
     * @throws BusinessException
     *             the business exception
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody final Contact contact)
    {
        try
        {
            for (final ContactGroup contactGroup : contact.getContactGroups())
            {
                contactGroup.setContact(contact);
                contactGroup.getGroup().setContactGroups(contact.getContactGroups());
            }
            contactService.createContact(contact);
            return new ResponseEntity<String>(HttpStatus.OK);

        }
        catch (BusinessException ex)
        {
            return new ResponseEntity<String>(String.format("{\"Error\":\"%s\"}", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update.
     *
     * @param id
     *            the id
     * @param contact
     *            the contact
     * @return the response entity
     * @throws BusinessException
     *             the business exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> update(@PathVariable final Long id, @RequestBody final Contact contact)
            throws BusinessException
    {

        final Contact currentContact = contactService.getContactById(id);
        if (currentContact == null)
        {
            LOGGER.warn("Supplied Contact does not exist.: " + id);
            throw new BusinessException("Supplied Contact does not exist.");
        }
        if (!currentContact.getVersion().equals(contact.getVersion()))
        {
            throw new BusinessException("Stale Contact. Please update.");
        }

        for (final ContactGroup contactGroup : contact.getContactGroups())
        {
            contactGroup.setContact(contact);
            contactGroup.getGroup().setContactGroups(contact.getContactGroups());
        }

        final Contact contactUpdated = contactService.updateContact(contact);
        
        contactUpdated.getContactGroups().stream().forEach(contactGroup -> {
            contactGroup.setContact(null);
        });
        return new ResponseEntity<Contact>(contactUpdated, HttpStatus.OK);
    }

    /**
     * Retrieve Financial year by Id.
     *
     * @param id
     *            id of Financial year to be retrieved.
     * @return the contact by id
     * @throws BusinessException
     *             the business exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContactById(@PathVariable final Long id) throws BusinessException
    {
        final Contact contact = contactService.getContactById(id);
        if (contact == null)
        {
            throw new BusinessException("Supplied Contact ID is invalid.");
        }
        contact.getContactGroups().stream().forEach(contactGroup -> {
            contactGroup.setContact(null);
        });
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    /**
     * Retrieve All Financial Years.
     *
     * @return the contacts
     */
    @PostMapping(value = "/getAllByCreatedUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getContactsByCreatedUser(@RequestParam("username") String username)
    {
        final List<Contact> contacts = contactService.findByCreatedUser(username);
        contacts.stream().forEach(contact -> {
            contact.getContactGroups().stream().forEach(contactGroup -> {
                contactGroup.setContact(null);
            });
        });
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    /**
     * Retrieve All Financial Years.
     *
     * @param contactSearchCriteria
     *            the contact search criteria
     * @return the contacts by search criteria
     */
    @RequestMapping(value = "/searchCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getContactsBySearchCriteria(
            @RequestBody final ContactSearchCriteria contactSearchCriteria)
    {
        if (contactSearchCriteria.getFirstName() != null)
        {
            contactSearchCriteria.setFirstName(contactSearchCriteria.getFirstName().trim());
            if (contactSearchCriteria.getFirstName().trim().equals(""))
            {
                contactSearchCriteria.setFirstName(null);
            }
        }
        if (contactSearchCriteria.getLastName() != null)
        {
            contactSearchCriteria.setLastName(contactSearchCriteria.getLastName().trim());
            if (contactSearchCriteria.getLastName().trim().equals(""))
            {
                contactSearchCriteria.setLastName(null);
            }
        }
        if (contactSearchCriteria.getEmail() != null)
        {
            contactSearchCriteria.setEmail(contactSearchCriteria.getEmail().trim());
            if (contactSearchCriteria.getEmail().trim().equals(""))
            {
                contactSearchCriteria.setEmail(null);
            }
        }
        final List<Contact> contacts = contactService.findBySearchCriteria(contactSearchCriteria);
        contacts.stream().forEach(contact -> {
            contact.setCreationDate(null);
            contact.setLastUpdatedDate(null);
            contact.setCreatedUser(null);
            contact.setLastUpdatedUser(null);
            contact.setResourceObjectId(null);
            contact.getContactGroups().stream().forEach(contactGroup -> {
                contactGroup.setContact(null);
                contactGroup.getGroup().setCreatedUser(null);
                contactGroup.getGroup().setLastUpdatedDate(null);
                contactGroup.getGroup().setCreatedUser(null);
                contactGroup.getGroup().setLastUpdatedUser(null);
                contactGroup.getGroup().setComments(null);
            });
        });
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id
     *            the id
     * @return the response entity
     * @throws BusinessException
     *             the business exception
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException
    {
        contactService.deleteContact(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Handle contact not found exception.
     *
     * @param e
     *            the e
     * @return the response entity
     */
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<String> handleContactNotFoundException(final Exception e)
    {
        return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Creates the contacts.
     *
     * @return the response entity
     * @throws BusinessException
     *             the business exception
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/createContacts", method = RequestMethod.GET)
    public ResponseEntity<Void> createContacts() throws BusinessException
    {

        final ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (Long i = 0L; i < 5000L; i++)
        {
            final Contact contact1 = new Contact();
            contact1.setFirstName("contact" + i);
            contact1.setLastName("lastname" + i);
            contact1.setEmail("kpgoud533@gmail.com");

            final Group group1 = groupService.getGroupById(1L);

            final ContactGroup contactGroup1 = new ContactGroup();
            contactGroup1.setContact(contact1);
            contactGroup1.setGroup(group1);
            contactGroup1.setActive(true);
            contactGroup1.setUnSubscribed(false);

            contact1.getContactGroups().add(contactGroup1);

            contacts.add(contact1);
            if (i % 1000 == 0)
            {
                contactRepository.save(contacts);
                contacts.clear();
            }

        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Handle file upload.
     *
     * @param request
     *            the request
     * @param res
     *            the res
     * @throws UploadedFileDataCorruptException
     *             the uploaded file data corrupt exception
     * @throws JobExecutionAlreadyRunningException
     *             the job execution already running exception
     * @throws JobRestartException
     *             the job restart exception
     * @throws JobInstanceAlreadyCompleteException
     *             the job instance already complete exception
     * @throws JobParametersInvalidException
     *             the job parameters invalid exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @RequestMapping(value = "/bulkupload", method = RequestMethod.POST)
    public void handleFileUpload(MultipartHttpServletRequest request, HttpServletResponse res)
            throws UploadedFileDataCorruptException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException, IOException
    {

        Iterator<String> iterator = request.getFileNames();
        MultipartFile multipartFile = null;
        String message = null;

        if (!iterator.hasNext())
        {
            message = "No files selected for upload";
            LOGGER.warn(message);
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            res.getOutputStream().print(message);
        }
        try
        {

            while (iterator.hasNext())
            {
                multipartFile = request.getFile(iterator.next());
                // do something with the file.....
                if (!multipartFile.isEmpty())
                {

                    File origFile = multipartToFile(multipartFile);
                    String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                    if (extension.equalsIgnoreCase("csv"))
                    {

                        long size = multipartFile.getSize();

                        LOGGER.debug("Uploading file of size : " + size);
                        if (multipartFile.getSize() > (20 * 1024 * 1024))
                        {
                            message = " File size exceeds 20MB. Only files less than 20Mb in size are allowed..";
                            LOGGER.error(message);
                            throw new MaximumFileSizeExceededException(message);
                        }
                        try
                        {
                            List<String> content = Files.readAllLines(origFile.toPath());
                            for (String data : content)
                            {
                                if(data.trim().isEmpty())
                                {
                                    continue;
                                }
                                String[] values = data.split(",");
                                if (values.length != 4)
                                {
                                    Files.deleteIfExists(origFile.toPath());
                                    message = "File data does not match the expected column format [firstname,lastname,email,group]";
                                    LOGGER.error(message);
                                    throw new UploadedFileDataCorruptException(message);
                                }
                                else
                                {
                                    if (values[3].contains(";"))
                                    {
                                        for (String grpName : values[3].split(";"))
                                        {
                                            createNewGroup(grpName.trim());
                                        }
                                    }
                                    else
                                    {
                                        Group group = groupRepository.findByName(values[3]);
                                        if (group == null)
                                        {
                                            createNewGroup(values[3]);
                                        }
                                    }
                                }
                            }

                            final Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
                            List<Group> groupList = groupRepository.findAll();

                            CommonUtilCache.getGroupNameToGroupMap().putAll(createGroupNameToGroupMap(groupList));

                            String tempFilePath = saveFileToTempLocation(multipartFile);
                            LOGGER.info("Temporary file path: " + tempFilePath);
                            jobParametersMap.put("file", new JobParameter(tempFilePath));
                            jobParametersMap.put("dateAndTime", new JobParameter(new Date()));

                            JobExecution execution = jobLauncher.run(job, new JobParameters(jobParametersMap));
                            LOGGER.info("Job Execution Status : " + execution.getExitStatus());

                            if ("FAILED".equalsIgnoreCase(execution.getExitStatus().getExitCode()))
                            {
                                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                List<Throwable> exceptions = execution.getAllFailureExceptions();
                                StringBuilder sb = new StringBuilder("Error: ");
                                for (Throwable ex : exceptions)
                                {
                                    if (ex.getMessage().contains("ConstraintViolationException")
                                            || ex.getMessage().contains("DataIntegrityViolationException"))
                                    {
                                        res.setStatus(HttpServletResponse.SC_CONFLICT);
                                        sb.append("Failed to upload contact: Contact already exists");
                                    }
                                    else
                                    {
                                        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                        sb.append(ex.getMessage()).append(" | ");
                                    }
                                }

                                LOGGER.error(sb.toString());
                                res.getOutputStream().print(sb.toString());
                            }
                            else
                            {
                                res.setStatus(HttpServletResponse.SC_OK);
                                message = "Contacts uploaded successfully";
                                LOGGER.info(message);
                                res.getOutputStream().print(message);
                            }
                        }
                        catch (Exception ex)
                        {
                            if (ex instanceof UploadedFileDataCorruptException)
                            {
                                res.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                                LOGGER.error(ex.fillInStackTrace());
                                res.getOutputStream().print(ex.getMessage());
                            }
                            else
                            {
                                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                LOGGER.error(ex.fillInStackTrace());
                                res.getOutputStream().print("Internal Server Error: " + ex.getMessage());
                            }
                        }
                    }
                    else
                    {
                        throw new InvalidUploadFileTypeException(
                                "Invalid File type: Only csv files are permitted for this operation");
                    }
                }
                else
                {
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    message = "Blank or no input file selected";
                    LOGGER.warn(message);
                    res.getOutputStream().print(message);
                }
            }
        }
        catch (InvalidUploadFileTypeException ex)
        {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.error(ex.getMessage());
            res.getOutputStream().print(ex.getMessage());
        }
        catch (MaximumFileSizeExceededException ex)
        {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.error(ex.getMessage());
            res.getOutputStream().print(ex.getMessage());
        }
    }

    /**
     * Creates the new group.
     *
     * @param name
     *            the name
     */
    private void createNewGroup(String name)
    {
        if (name != null && !name.trim().isEmpty())
        {
            Group newGroup = new Group();
            newGroup.setName(name);
            newGroup.setComments("Description for " + name);
            groupRepository.save(newGroup);
        }
    }

    /**
     * Creates the group name to group map.
     *
     * @param groupList
     *            the group list
     * @return the map
     */
    private Map<String, Group> createGroupNameToGroupMap(List<Group> groupList)
    {
        Map<String, Group> groupNameToGroupMap = new HashMap<>();
        for (Group group : groupList)
        {
            groupNameToGroupMap.put(group.getName(), group);
        }

        return groupNameToGroupMap;
    }

    /**
     * Multipart to file.
     *
     * @param multipart
     *            the multipart
     * @return the file
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private File multipartToFile(MultipartFile multipart) throws IOException
    {
        String tempFilePath = saveFileToTempLocation(multipart);
        return new File(tempFilePath);
    }

    /**
     * Save file to temp location.
     *
     * @param multipartFile
     *            the multipart file
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private String saveFileToTempLocation(MultipartFile multipartFile) throws IOException
    {
        File tempFile = new File(env.getProperty("mutipart.location"), multipartFile.getOriginalFilename());
        FileCopyUtils.copy(multipartFile.getBytes(), tempFile);
        return tempFile.getAbsolutePath();
    }

}
