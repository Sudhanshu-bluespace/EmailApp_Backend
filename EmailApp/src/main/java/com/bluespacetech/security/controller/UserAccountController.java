package com.bluespacetech.security.controller;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.OnRegistrationCompleteEvent;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.exceptions.UserAccountDoesNotExistException;
import com.bluespacetech.security.model.CompanyRegistration;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.repository.CompanyRegistrationRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import com.bluespacetech.security.resources.UserAccountChangePasswordResource;
import com.bluespacetech.security.resources.UserAccountResource;
import com.bluespacetech.security.resources.assembler.UserAccountResourceAssembler;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import com.bluespacetech.security.service.BlueSpaceTechUserAccountService;
import com.bluespacetech.security.service.UserGroupService;

/**
 * The Class UserAccountController.
 * @author Sudhanshu Tripathy
 */
@RestController
@RequestMapping("/userAccounts")
public class UserAccountController extends AbstractBaseController
{

    /** The blue space tech user account service. */
    @Autowired
    BlueSpaceTechUserAccountService blueSpaceTechUserAccountService;

    /** The user group service. */
    @Autowired
    UserGroupService userGroupService;

    /** The contact service. */
    @Autowired
    ContactService contactService;

    /** The event publisher. */
    @Autowired
    ApplicationEventPublisher eventPublisher;

    /** The company registration repository. */
    @Autowired
    CompanyRegistrationRepository companyRegistrationRepository;

    /** The verification token repository. */
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(UserAccountController.class.getName());

    /**
     * Retrieve All Financial Years.
     *
     * @return the user accounts
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAccountResource>> getUserAccounts()
    {
        final List<UserAccount> userAccounts = blueSpaceTechUserAccountService.getAllUserAccounts();

        final List<UserAccountResource> userAccountResources = new UserAccountResourceAssembler()
                .toResources(userAccounts);
        return new ResponseEntity<List<UserAccountResource>>(userAccountResources, HttpStatus.OK);
    }

    /**
     * Gets the company list.
     *
     * @return the company list
     */
    @RequestMapping(value = "/getCompanyList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyRegistration>> getCompanyList()
    {
        final List<CompanyRegistration> companyList = companyRegistrationRepository.findAll();
        return new ResponseEntity<List<CompanyRegistration>>(companyList, HttpStatus.OK);
    }

    /**
     * Retrieve Financial year by Id.
     *
     * @param id id of Financial year to be retrieved.
     * @return the user account by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccountResource> getUserAccountById(@PathVariable final Long id)
    {
        final UserAccount userAccount = blueSpaceTechUserAccountService.getUserAccountById(id);
        if (userAccount == null)
        {
            throw new UserAccountDoesNotExistException("Supplied Financial year is invalid.");
        }

        final List<Long> userGroupIds = new ArrayList<Long>();

        for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups())
        {
            userGroupIds.add(userAccountUserGroup.getUserGroupId());
        }
        final List<UserGroup> userGroups = userGroupService.getUserGroupByIds(userGroupIds);
        final Map<Long, UserGroup> userGroupsMap = new HashMap<Long, UserGroup>();
        for (final UserGroup userGroup : userGroups)
        {
            userGroupsMap.put(userGroup.getId(), userGroup);
        }
        final UserAccountResource userAccountResource = new UserAccountResourceAssembler()
                .toCompleteResource(userAccount, userGroupsMap);
        return new ResponseEntity<UserAccountResource>(userAccountResource, HttpStatus.OK);

    }

    /**
     * Retrieve Financial year by Id.
     *
     * @param userAccountSearchCriteria the user account search criteria
     * @return the user accounts
     * @throws BusinessException the business exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAccountResource>> getUserAccounts(
            @RequestBody final UserAccountSearchCriteria userAccountSearchCriteria) throws BusinessException
    {

        final List<UserAccount> userAccounts = blueSpaceTechUserAccountService
                .findUserAccountsBySearchCriteria(userAccountSearchCriteria);

        List<UserAccountResource> userAccountResources = new ArrayList<UserAccountResource>();
        if (userAccounts != null)
        {
            userAccountResources = new UserAccountResourceAssembler().toResources(userAccounts);
        }
        return new ResponseEntity<List<UserAccountResource>>(userAccountResources, HttpStatus.OK);
    }

    /**
     * Creates the.
     *
     * @param userAccountResource the user account resource
     * @param request the request
     * @return the response entity
     * @throws BusinessException the business exception
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody final UserAccountResource userAccountResource,
            HttpServletRequest request) throws BusinessException
    {

        final UserAccount userAccount = UserAccountResourceAssembler.getUserAccountFromResource(userAccountResource);
        CompanyRegistration reg = companyRegistrationRepository
                .findCompanyRegistrationByCompanyNameIgnoreCase(userAccountResource.getCompanyName());
        if (reg == null)
        {
            CompanyRegistration reg1 = new CompanyRegistration();
            reg1.setCompanyName(userAccountResource.getCompanyName());
            reg1.setApproved(true);
            reg1.setDescription(userAccountResource.getCompanyName());
            reg = companyRegistrationRepository.save(reg1);
        }

        userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_USER);
        userAccount.setCompanyRegistration(reg);
        Map<UserAccount,String> retrievedUserWithdecryptedPassword = blueSpaceTechUserAccountService.createUserAccount(userAccount);
        UserAccount user=null;
        String password = null;
        for(UserAccount acc : retrievedUserWithdecryptedPassword.keySet())
        {
            user=acc;
            password=retrievedUserWithdecryptedPassword.get(acc);
            user.setPassword(password);
        }

        try
        {
            /*URL url = new URL(request.getRequestURL().toString());
            String host = url.getHost();
            String scheme = url.getProtocol();
            int port = url.getPort();
            URI uri = new URI(scheme, null, host, port, null, null, null);
            LOGGER.info("Captured Server Url : " + uri.toString());*/
            OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(user, request.getLocale(),
                    null, true);
            LOGGER.debug("Event : " + event);
            eventPublisher.publishEvent(event);
            LOGGER.info("Sent Account Creation Email to user successfully");
            return new ResponseEntity<Void>(HttpStatus.OK);

        }
        catch (Exception me)
        {
            LOGGER.error("Account creation email sending failed: " + me.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update.
     *
     * @param id the id
     * @param userAccountResource the user account resource
     * @return the response entity
     * @throws BusinessException the business exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable final Long id,
            @RequestBody final UserAccountResource userAccountResource) throws BusinessException
    {

        // Get existing Financial Year
        final UserAccount currentUserAccount = blueSpaceTechUserAccountService.getUserAccountById(id);
        if (currentUserAccount == null)
        {
            throw new UserAccountDoesNotExistException("Supplied Financial year is invalid.");
        }
        if (!currentUserAccount.getVersion().equals(userAccountResource.getVersion()))
        {
            throw new BusinessException("Stale Financial Year. Please update.");
        }
        // Extract the Financial Year from Resource
        final UserAccount sourceUserAccount = UserAccountResourceAssembler
                .getUserAccountFromResource(userAccountResource);

        // Copy changes from source to destination including version so that if
        // some one updated the branch then JPA will throw exception
        UserAccountResourceAssembler.copyUserAccountInto(sourceUserAccount, currentUserAccount);
        blueSpaceTechUserAccountService.updateUserAccount(currentUserAccount);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Update password.
     *
     * @param userAccountChangePasswordResource the user account change password resource
     * @return the response entity
     * @throws BusinessException the business exception
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassword(
            @RequestBody final UserAccountChangePasswordResource userAccountChangePasswordResource)
            throws BusinessException
    {
        blueSpaceTechUserAccountService.changePasswordUserAccount(userAccountChangePasswordResource.getOldPassword(),
                userAccountChangePasswordResource.getNewPassword(),
                userAccountChangePasswordResource.getConfirmPassword());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Long id)
    {
        System.out.println("deleting user account with id "+id);
        blueSpaceTechUserAccountService.deleteUserAccount(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Handle user account not found exception.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(UserAccountDoesNotExistException.class)
    ResponseEntity<String> handleUserAccountNotFoundException(final Exception e)
    {
        return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
