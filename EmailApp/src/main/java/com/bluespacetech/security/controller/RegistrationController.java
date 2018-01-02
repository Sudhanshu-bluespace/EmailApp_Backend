package com.bluespacetech.security.controller;

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
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@CrossOrigin
@RequestMapping({"/new"})
public class RegistrationController
{
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  UserAccountRepository userAccountRepository;
  @Autowired
  AccountApprovalRepository accountApprovalRepository;
  @Autowired
  CompanyRegistrationRepository companyRegRepo;
  @Autowired
  ApplicationEventPublisher eventPublisher;
  @Autowired
  MailTemplateConfiguration mailTemplateConfiguration;
  @Autowired
  EmailHandler emailHandler;
  @Autowired
  VerificationTokenRepository verificationTokenRepository;
  @Autowired
  CountryRepository countryRepository;
  @Autowired
  StateRepository stateRepository;
  @Autowired
  CityRepository cityRepository;
  private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class.getName());
  
  @RequestMapping(value={"/feedData"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void feedCountryList()
  {
    populateCountryList();
    populateStateList();
    populateCityList();
  }
  
  private void populateCountryList()
  {
    List<Country> countries = this.countryRepository.findAll();
    try
    {
      List<String> countriesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/countries.csv", new String[0]));
      if ((countries == null) || (countries.isEmpty()))
      {
        for (String record : countriesToFeed)
        {
          String[] splitData = record.split("\\,");
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
            LOGGER.error("Invalid record format in countries.csv : " + splitData + ". check for missing commas..");
          }
        }
        this.countryRepository.save(countries);
        
        LOGGER.info("Countries populated successfully");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private void populateStateList()
  {
    List<State> states = this.stateRepository.findAll();
    try
    {
      List<String> statesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/states.csv", new String[0]));
      if ((states == null) || (states.isEmpty()))
      {
        for (String record : statesToFeed)
        {
          String[] splitData = record.split("\\,");
          if (splitData.length == 2)
          {
            State state = new State();
            state.setName(splitData[0].trim());
            Country country = (Country)this.countryRepository.findOne(Long.valueOf(Long.parseLong(splitData[1].trim())));
            state.setCountry(country);
            states.add(state);
          }
          else
          {
            LOGGER.error("Invalid record format in states.csv : " + splitData + ". check for missing commas..");
          }
        }
        this.stateRepository.save(states);
      }
      LOGGER.info("States populated successfully");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private void populateCityList()
  {
    List<City> cities = this.cityRepository.findAll();
    try
    {
      List<String> citiesToFeed = Files.readAllLines(Paths.get("/opt/packages/Oracle/BluespaceMailer/data/cities.csv", new String[0]));
      if ((cities == null) || (cities.isEmpty()))
      {
        for (String record : citiesToFeed)
        {
          String[] splitData = record.split("\\,");
          if (splitData.length == 2)
          {
            City city = new City();
            city.setName(splitData[0].trim());
            State state = (State)this.stateRepository.findOne(Long.valueOf(Long.parseLong(splitData[1].trim())));
            city.setState(state);
            cities.add(city);
          }
          else
          {
            LOGGER.error("Invalid record format in cities.csv : " + splitData + ". check for missing commas..");
          }
        }
        this.cityRepository.save(cities);
      }
      LOGGER.info("Cities populated successfully");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  @RequestMapping(value={""}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ResponseEntity<BaseResponseDAO> welcomeNewRegistration()
  {
    return new ResponseEntity(getWelcomeResponse(), HttpStatus.OK);
  }
  
  @GetMapping(value={"/getCountries"}, produces={"application/json"})
  public List<Country> getCountries()
  {
    return this.countryRepository.findAll();
  }
  
  @PostMapping(value={"/getStatesFromCountry/{fullName}"}, produces={"application/json"})
  public List<State> getStates(@PathVariable("fullName") String fullName)
  {
    Country country = this.countryRepository.findByFullNameIgnoreCase(fullName);
    return this.stateRepository.findByCountry(country);
  }
  
  @PostMapping(value={"/getCitiesFromState/{name}"}, produces={"application/json"})
  public List<City> getCities(@PathVariable("name") String name)
  {
    State state = this.stateRepository.findByName(name);
    System.out.println("Found state : " + state);
    List<City> cities = this.cityRepository.findByState(state);
    System.out.println("Found cities : " + cities);
    return cities;
  }
  
  @RequestMapping(value={"/register"}, consumes={"application/json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  public void registerNewUser(@RequestBody Map<String, Object> registrationDetails, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      HttpStatus status = null;
      response.setContentType("application/json");
      if ((registrationDetails == null) || (registrationDetails.isEmpty()))
      {
        response.setStatus(400);
        response.getOutputStream().println("{ \"error\": \" Blank form received \"}");
      }
      String userName = (String)registrationDetails.get("username");
      String emailAdress = (String)registrationDetails.get("email");
      String companyName = (String)registrationDetails.get("companyName");
      String phoneNumber = (String)registrationDetails.get("phone");
      String addressLine1 = (String)registrationDetails.get("addressLine1");
      String addressLine2 = (String)registrationDetails.get("addressLine2");
      String firstName = (String)registrationDetails.get("firstName");
      String middleName = (String)registrationDetails.get("middleName");
      String lastName = (String)registrationDetails.get("lastName");
      String country = (String)registrationDetails.get("country");
      String state = (String)registrationDetails.get("state");
      String city = (String)registrationDetails.get("city");
      String zipcode = (String)registrationDetails.get("zipcode");
      String federalId = (String)registrationDetails.get("federalId");
      
      UserAccount userDetails = this.userAccountRepository.findUserAccountByUsername(userName);
      if (userDetails != null)
      {
        response.setStatus(400);
        response.getOutputStream().println("{ \"error\": \" Username is already taken \"}");
        return;
      }
      UserAccount userDetailsByEmail = this.userAccountRepository.findUserAccountByEmail(emailAdress);
      if (userDetailsByEmail != null)
      {
        response.setStatus(400);
        response.getOutputStream().println("{ \"error\": \" Email is already taken \"}");
        return;
      }
      String password = (String)registrationDetails.get("password");
      String confirmPassword = (String)registrationDetails.get("confirmPassword");
      
      LOGGER.debug("input as received: " + userName + " | " + password + " | " + confirmPassword + " | " + companyName + " | " + phoneNumber);
      if (!password.equals(confirmPassword))
      {
        LOGGER.debug("password no match");
        response.setStatus(400);
        response.getOutputStream().println("{ \"error\": \" Passwords do no match \"}");
        return;
      }
      LOGGER.debug("Preparing to create the user account");
      
      CompanyRegistration company = this.companyRegRepo.findCompanyRegistrationByCompanyNameIgnoreCase(companyName);
      if (company == null)
      {
        LOGGER.info("Company is not yet Registered with us. Creating a new record to be sent for approval");
        
        CompanyRegistration record = new CompanyRegistration();
        record.setCompanyName(companyName);
        record.setDescription("To be Updated");
        company = (CompanyRegistration)this.companyRegRepo.save(record);
      }
      UserAccount userAccount = new UserAccount();
      userAccount.setUsername(userName);
      String encodedPassword = this.userAccountService.getEncodedPassword(password);
      LOGGER.debug("Raw password = " + userAccount.getPassword());
      LOGGER.debug("Password to be saved = " + encodedPassword);
      
      userAccount.setPassword(encodedPassword);
      userAccount.setEmail(emailAdress);
      userAccount.setActive(false);
      userAccount.setFirstName(firstName);
      userAccount.setMiddleName(middleName);
      userAccount.setLastName(lastName);
      userAccount.setVerifiedByAdmin(false);
      userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_EMPLOYEE);
      userAccount.setPhoneNumber(phoneNumber);
      userAccount.setCompanyRegistration(company);
      userAccount.setCity(city);
      userAccount.setState(state);
      userAccount.setCountry(country);
      userAccount.setAddressLine1(addressLine1);
      userAccount.setAddressLine2(addressLine2);
      userAccount.setFederalId((federalId == null) || (federalId.trim().isEmpty()) ? "-" : federalId);
      userAccount.setZipCode(zipcode);
      userAccount.setAutoRenew(false);
      
      UserAccount retrievedUser = this.userAccountService.save(userAccount);
      
      AccountApproval approval = new AccountApproval();
      approval.setStatus("PENDING");
      approval.setIdPendingApproval(retrievedUser.getId());
      approval.setEmail(retrievedUser.getEmail());
      
      UserAccount adminAccount = this.userAccountRepository.findUserAccountByEmail(this.mailTemplateConfiguration.getMailSuperAdmins().trim());
      approval.setUserAccount(adminAccount);
      
      this.accountApprovalRepository.save(approval);
      LOGGER.info("Pending Approval stats saved successfully");
      
      AccountCreationEmail mail = new AccountCreationEmail();
      mail.setMailTo(this.mailTemplateConfiguration.getMailSuperAdmins());
      mail.setMailFrom("<no-reply>@hireswing.com");
      mail.setMailSubject("New User Accout Created");
      
      Map<String, Object> model = new HashMap();
      model.put("userName", retrievedUser.getUsername());
      model.put("signature", "www.hireswing.com");
      mail.setModel(model);
      
      this.emailHandler.sendEmailToAdmin(mail);
      
      LOGGER.debug("Return status : " + status);
      response.setStatus(200);
      response.getOutputStream().println("{ \"Success\": \" Account creation successful. \"}");
    }
    catch (IOException ex)
    {
      response.setStatus(500);
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
  
  @RequestMapping(value={"/regitrationConfirm"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void confirmRegistration(WebRequest request, @RequestParam("token") String token, HttpServletResponse response)
    throws IOException
  {
    LOGGER.info("Activing profile..");
    VerificationToken verificationToken = this.userAccountService.getVerificationToken(token);
    if (verificationToken == null)
    {
      response.setStatus(400);
      response.getOutputStream().println("{ \"Error\": \" Invalid token (null) \"}");
    }
    UserAccount user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0L)
    {
      LOGGER.error("user expired already on " + verificationToken.getExpiryDate().getTime());
      response.setStatus(400);
      response.getOutputStream().println("{ \"Error\": \" user expired already on " + verificationToken
        .getExpiryDate().getTime() + " \"}");
    }
    user.setActive(true);
    user.setAccountExpired(false);
    user.setAccountLocked(false);
    this.userAccountService.save(user);
    LOGGER.info("Profile activated successfully.. you can now login..");
    response.setStatus(200);
    response.getOutputStream().println("{ \"Success\": \" Account acivated successfully. You can now login \"}");
  }
  
  private BaseResponseDAO getWelcomeResponse()
  {
    BaseResponseDAO response = new BaseResponseDAO();
    response.setResponseCode(200L);
    response.setResponseMessage("New Registration Controller");
    return response;
  }
}
