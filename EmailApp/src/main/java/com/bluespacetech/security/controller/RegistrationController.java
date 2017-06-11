package com.bluespacetech.security.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.notifications.email.util.EmailHandler;
import com.bluespacetech.notifications.email.util.EmailUtils;
import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.AccountApproval;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.repository.AccountApprovalRepository;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import com.bluespacetech.security.service.UserAccountService;

@RestController
@CrossOrigin
@RequestMapping("/new")
public class RegistrationController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	AccountApprovalRepository accountApprovalRepository;

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	MailTemplateConfiguration mailTemplateConfiguration;
	
	@Autowired
	EmailHandler emailHandler;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;

	private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<BaseResponseDAO> welcomeNewRegistration() {
		return new ResponseEntity<BaseResponseDAO>(getWelcomeResponse(), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerNewUser(@RequestBody Map<String, Object> registrationDetails, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpStatus status = null;
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			if (registrationDetails == null || registrationDetails.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getOutputStream().println("{ \"error\": \" Blank form received \"}");
			}

			String userName = (String) registrationDetails.get("username");
			String emailAdress = (String) registrationDetails.get("email");
			String companyName = (String) registrationDetails.get("companyName");
			String phoneNumber = (String) registrationDetails.get("phone");

			UserAccount userDetails = userAccountRepository.findUserAccountByUsername(userName);
			if (userDetails != null) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getOutputStream().println("{ \"error\": \" Username is already taken \"}");
				return;
			} else {
				UserAccount userDetailsByEmail = userAccountRepository.findUserAccountByEmail(emailAdress);
				if (userDetailsByEmail != null) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getOutputStream().println("{ \"error\": \" Email is already taken \"}");
					return;
				} else {

					String password = (String) registrationDetails.get("password");
					String confirmPassword = (String) registrationDetails.get("confirmPassword");

					LOGGER.debug("input as received: " + userName + " | " + password + " | " + confirmPassword + " | "+companyName+ " | "+phoneNumber);

					if (!password.equals(confirmPassword)) {
						LOGGER.debug("password no match");
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						response.getOutputStream().println("{ \"error\": \" Passwords do no match \"}");
						return;
					} else {

						LOGGER.debug("Preparing to persist the user");
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
						userAccount.setCompanyName(companyName);
						userAccount.setPhoneNumber(phoneNumber);
						UserAccount retrievedUser = userAccountService.save(userAccount);
						
						AccountApproval approval = new AccountApproval();
						approval.setStatus("PENDING");
						approval.setIdPendingApproval(retrievedUser.getId());
						approval.setEmail(retrievedUser.getEmail());
						
						UserAccount adminAccount = userAccountRepository.findUserAccountByEmail(mailTemplateConfiguration.getMailSuperAdmins().trim());
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
		} catch (IOException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			try {
				response.getOutputStream().println("{ \"Error\": \" Internal Server Error. " + ex.getMessage() + "\"}");
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
				return;
			}
		}
	}

	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public void confirmRegistration(WebRequest request, @RequestParam("token") String token,HttpServletResponse response) throws IOException {

		// Locale locale = request.getLocale();
		LOGGER.info("Activing profile..");
		VerificationToken verificationToken = userAccountService.getVerificationToken(token);
		if (verificationToken == null) {
			//String message = "invalid token (null)";
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().println("{ \"Error\": \" Invalid token (null) \"}");
		}

		UserAccount user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			LOGGER.error("user expired already on " + verificationToken.getExpiryDate().getTime());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().println("{ \"Error\": \" user expired already on " + verificationToken.getExpiryDate().getTime()+" \"}");

		}

		user.setActive(true);
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		userAccountService.save(user);
		LOGGER.info("Profile activated successfully.. you can now login..");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getOutputStream().println("{ \"Success\": \" Account acivated successfully. You can now login \"}");
	}

	private BaseResponseDAO getInvalidInputResponse(String message) {
		BaseResponseDAO response = new BaseResponseDAO();
		response.setErrorCode(408);
		response.setErrorMessage(message);
		return response;
	}

	private BaseResponseDAO getWelcomeResponse() {
		BaseResponseDAO response = new BaseResponseDAO();
		response.setResponseCode(200);
		response.setResponseMessage("New Registration Controller");
		return response;
	}

}
