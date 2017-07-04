package com.bluespacetech.security.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.core.model.City;
import com.bluespacetech.core.model.Country;
import com.bluespacetech.core.model.State;
import com.bluespacetech.core.repository.CityRepository;
import com.bluespacetech.core.repository.CountryRepository;
import com.bluespacetech.core.repository.StateRepository;
import com.bluespacetech.notifications.email.util.EmailHandler;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.AccountApproval;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.CompanyRegistration;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.AccountApprovalRepository;
import com.bluespacetech.security.repository.CompanyRegistrationRepository;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import com.bluespacetech.security.service.UserAccountService;

/**
 * The Class RegistrationController.
 * 
 * @author Sudhanshu Tripathy created date 17-April-2017
 */
@RestController
@CrossOrigin
@RequestMapping("/new")
public class RegistrationController
{

    /** The user account service. */
    @Autowired
    private UserAccountService userAccountService;

    /** The user account repository. */
    @Autowired
    UserAccountRepository userAccountRepository;

    /** The account approval repository. */
    @Autowired
    AccountApprovalRepository accountApprovalRepository;

    /** The company reg repo. */
    @Autowired
    CompanyRegistrationRepository companyRegRepo;

    /** The event publisher. */
    @Autowired
    ApplicationEventPublisher eventPublisher;

    /** The mail template configuration. */
    @Autowired
    MailTemplateConfiguration mailTemplateConfiguration;

    /** The email handler. */
    @Autowired
    EmailHandler emailHandler;

    /** The verification token repository. */
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class.getName());

    @RequestMapping(value = "/feedData", method = RequestMethod.GET)
    public void feedCountryList()
    {
        populateCountryList();
        populateStateList();
        populateCityList();
    }

    private void populateCountryList()
    {
        List<Country> countries = countryRepository.findAll();
        List<String> countriesToFeed;
        try
        {
            countriesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/countries.csv"));

            if (countries == null || countries.isEmpty())
            {
                for (String record : countriesToFeed)
                {
                    String[] splitData = record.split("\\,");
                    {
                        if (splitData.length == 3)
                        {
                            Country country = new Country();
                            country.setShortName(splitData[0].trim());
                            country.setFullName(splitData[1].trim());
                            country.setIsdCode(splitData[2].trim());
                            countries.add(country);
                        }
                        else
                        {
                            LOGGER.error("Invalid record format in countries.csv : " + splitData
                                    + ". check for missing commas..");
                        }
                    }
                }

                countryRepository.save(countries);

                LOGGER.info("Countries populated successfully");
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void populateStateList()
    {
        List<State> states = stateRepository.findAll();
        List<String> statesToFeed;
        try
        {
            statesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/states.csv"));

            if (states == null || states.isEmpty())
            {
                for (String record : statesToFeed)
                {
                    String[] splitData = record.split("\\,");
                    {
                        if (splitData.length == 2)
                        {
                            State state = new State();
                            state.setName(splitData[0].trim());
                            Country country = countryRepository.findOne(Long.parseLong(splitData[1].trim()));
                            state.setCountry(country);
                            states.add(state);
                        }
                        else
                        {
                            LOGGER.error("Invalid record format in states.csv : " + splitData
                                    + ". check for missing commas..");
                        }
                    }
                }

                stateRepository.save(states);
            }
            LOGGER.info("States populated successfully");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void populateCityList()
    {
        List<City> cities = cityRepository.findAll();
        List<String> citiesToFeed;
        try
        {
            citiesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/cities.csv"));

            if (cities == null || cities.isEmpty())
            {
                for (String record : citiesToFeed)
                {
                    String[] splitData = record.split("\\,");
                    {
                        if (splitData.length == 2)
                        {
                            City city = new City();
                            city.setName(splitData[0].trim());
                            State state = stateRepository.findOne(Long.parseLong(splitData[1].trim()));
                            city.setState(state);
                            cities.add(city);
                            // cityRepository.save(city);
                        }
                        else
                        {
                            LOGGER.error("Invalid record format in cities.csv : " + splitData
                                    + ". check for missing commas..");
                        }
                    }
                }

                cityRepository.save(cities);
            }
            LOGGER.info("Cities populated successfully");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Welcome new registration.
     *
     * @return the response entity
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BaseResponseDAO> welcomeNewRegistration()
    {
        return new ResponseEntity<BaseResponseDAO>(getWelcomeResponse(), HttpStatus.OK);
    }
    
    @GetMapping(value="/getCountries",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getCountries()
    {
        return countryRepository.findAll();
    }
    
    @PostMapping(value="/getStatesFromCountry/{fullName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<State> getStates(@PathVariable("fullName")String fullName)
    {
        Country country = countryRepository.findByFullNameIgnoreCase(fullName);
        return stateRepository.findByCountry(country);
    }
    
    @PostMapping(value="/getCitiesFromState/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities(@PathVariable("name")String name)
    {
        State state = stateRepository.findByName(name);
        System.out.println("Found state : "+state);
        List<City> cities =  cityRepository.findByState(state);
        System.out.println("Found cities : "+cities);
        return cities;
    }

    /**
     * Register new user.
     *
     * @param registrationDetails the registration details
     * @param request the request
     * @param response the response
     */
    @RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerNewUser(@RequestBody Map<String, Object> registrationDetails, HttpServletRequest request,
            HttpServletResponse response)
    {

        try
        {
            HttpStatus status = null;
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            if (registrationDetails == null || registrationDetails.isEmpty())
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getOutputStream().println("{ \"error\": \" Blank form received \"}");
            }

            String userName = (String) registrationDetails.get("username");
            String emailAdress = (String) registrationDetails.get("email");
            String companyName = (String) registrationDetails.get("companyName");
            String phoneNumber = (String) registrationDetails.get("phone");
            String address = (String) registrationDetails.get("address");
            String street = (String) registrationDetails.get("street");
            String country = (String) registrationDetails.get("country");
            String state = (String) registrationDetails.get("state");
            String city = (String) registrationDetails.get("city");
            String zipcode = (String) registrationDetails.get("zipcode");
            String federalId = (String) registrationDetails.get("federalId");
            
            System.out.println("params : "+address+" | "+street+" | "+country+" | "+state+" | "+city);

            UserAccount userDetails = userAccountRepository.findUserAccountByUsername(userName);
            if (userDetails != null)
            {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getOutputStream().println("{ \"error\": \" Username is already taken \"}");
                return;
            }
            else
            {
                UserAccount userDetailsByEmail = userAccountRepository.findUserAccountByEmail(emailAdress);
                if (userDetailsByEmail != null)
                {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getOutputStream().println("{ \"error\": \" Email is already taken \"}");
                    return;
                }
                else
                {

                    String password = (String) registrationDetails.get("password");
                    String confirmPassword = (String) registrationDetails.get("confirmPassword");

                    LOGGER.debug("input as received: " + userName + " | " + password + " | " + confirmPassword + " | "
                            + companyName + " | " + phoneNumber);

                    if (!password.equals(confirmPassword))
                    {
                        LOGGER.debug("password no match");
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getOutputStream().println("{ \"error\": \" Passwords do no match \"}");
                        return;
                    }
                    else
                    {

                        LOGGER.debug("Preparing to create the user account");

                        CompanyRegistration company = companyRegRepo
                                .findCompanyRegistrationByCompanyNameIgnoreCase(companyName);
                        if (company == null)
                        {
                            LOGGER.info(
                                    "Company is not yet Registered with us. Creating a new record to be sent for approval");
                            CompanyRegistration record = new CompanyRegistration();
                            record.setCompanyName(companyName);
                            record.setDescription("To be Updated");
                            company = companyRegRepo.save(record);
                        }
                        UserAccount userAccount = new UserAccount();
                        userAccount.setUsername(userName);
                        String encodedPassword = userAccountService.getEncodedPassword(password);
                        LOGGER.debug("Raw password = " + userAccount.getPassword());
                        LOGGER.debug("Password to be saved = " + encodedPassword);

                        userAccount.setPassword(encodedPassword);
                        userAccount.setEmail(emailAdress);
                        userAccount.setActive(false);
                        userAccount.setVerifiedByAdmin(false);
                        userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_EMPLOYEE);
                        userAccount.setPhoneNumber(phoneNumber);
                        userAccount.setCompanyRegistration(company);
                        userAccount.setCity(city);
                        userAccount.setState(state);
                        userAccount.setCountry(country);
                        userAccount.setAddress(address);
                        userAccount.setStreet(street);
                        userAccount.setFederalId(federalId);
                        userAccount.setZipCode(zipcode);
                        userAccount.setAutoRenew(false);
                        
                        UserAccount retrievedUser = userAccountService.save(userAccount);

                        AccountApproval approval = new AccountApproval();
                        approval.setStatus("PENDING");
                        approval.setIdPendingApproval(retrievedUser.getId());
                        approval.setEmail(retrievedUser.getEmail());

                        UserAccount adminAccount = userAccountRepository
                                .findUserAccountByEmail(mailTemplateConfiguration.getMailSuperAdmins().trim());
                        approval.setUserAccount(adminAccount);

                        accountApprovalRepository.save(approval);
                        LOGGER.info("Pending Approval stats saved successfully");

                        AccountCreationEmail mail = new AccountCreationEmail();
                        mail.setMailTo(mailTemplateConfiguration.getMailSuperAdmins());
                        mail.setMailFrom("<no-reply>@hireswing.net");
                        mail.setMailSubject("New User Accout Created");

                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("userName", retrievedUser.getUsername());
                        model.put("signature", "www.hireswing.net");
                        mail.setModel(model);

                        emailHandler.sendEmailToAdmin(mail);
                    }
                }
            }

            LOGGER.debug("Return status : " + status);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println("{ \"Success\": \" Account creation successful. \"}");
        }
        catch (IOException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try
            {
                response.getOutputStream().println("{ \"Error\": \" Internal Server Error. " + ex.getMessage() + "\"}");
            }
            catch (IOException e)
            {
                LOGGER.error(e.getMessage());
                return;
            }
        }
    }

    /**
     * Confirm registration.
     *
     * @param request the request
     * @param token the token
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public void confirmRegistration(WebRequest request, @RequestParam("token") String token,
            HttpServletResponse response) throws IOException
    {

        // Locale locale = request.getLocale();
        LOGGER.info("Activing profile..");
        VerificationToken verificationToken = userAccountService.getVerificationToken(token);
        if (verificationToken == null)
        {
            // String message = "invalid token (null)";
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getOutputStream().println("{ \"Error\": \" Invalid token (null) \"}");
        }

        UserAccount user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
        {
            LOGGER.error("user expired already on " + verificationToken.getExpiryDate().getTime());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getOutputStream().println(
                    "{ \"Error\": \" user expired already on " + verificationToken.getExpiryDate().getTime() + " \"}");

        }

        user.setActive(true);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        userAccountService.save(user);
        LOGGER.info("Profile activated successfully.. you can now login..");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println("{ \"Success\": \" Account acivated successfully. You can now login \"}");
    }

    /**
     * Gets the welcome response.
     *
     * @return the welcome response
     */
    private BaseResponseDAO getWelcomeResponse()
    {
        BaseResponseDAO response = new BaseResponseDAO();
        response.setResponseCode(200);
        response.setResponseMessage("New Registration Controller");
        return response;
    }

}
