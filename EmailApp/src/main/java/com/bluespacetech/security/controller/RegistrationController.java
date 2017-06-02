package com.bluespacetech.security.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespacetech.security.OnRegistrationCompleteEvent;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
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
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;

	private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<BaseResponseDAO> welcomeNewRegistration() {
		return new ResponseEntity<BaseResponseDAO>(getWelcomeResponse(), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerNewUser(@RequestBody Map<String, Object> registrationDetails, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// BaseResponseDAO response = null;
		HttpStatus status = null;
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		if (registrationDetails == null || registrationDetails.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().println("{ \"error\": \" Blank form received \"}");
		}

		String userName = (String) registrationDetails.get("username");
		String emailAdress = (String)registrationDetails.get("email");

		UserAccount userDetails = userAccountRepository.findUserAccountByUsername(userName);
		if (userDetails != null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().println("{ \"error\": \" Username is already taken \"}");
		} 
		else 
		{
			UserAccount userDetailsByEmail = userAccountRepository.findUserAccountByEmail(emailAdress);
			if (userDetailsByEmail != null) 
			{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getOutputStream().println("{ \"error\": \" Email is already taken \"}");
			} 
			else 
			{

				String password = (String) registrationDetails.get("password");
				String confirmPassword = (String) registrationDetails.get("confirmPassword");
				String email = (String) registrationDetails.get("email");

				LOGGER.debug("input as received: " + userName + " | " + password + " | " + confirmPassword);

				if (userName == null || userName.trim().isEmpty()) {
					LOGGER.debug("user blank");
					getInvalidInputResponse("userName cannot be blank");
					status = HttpStatus.BAD_REQUEST;
				} else if (password == null || password.trim().isEmpty()) {
					LOGGER.debug("password blank");
					getInvalidInputResponse("Password cannot be blank");
					status = HttpStatus.BAD_REQUEST;
				} else if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
					LOGGER.debug("conf password blank");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getOutputStream().println("{ \"error\": \" Confirm password cannot be left blank \"}");
				} else if (email == null || email.trim().isEmpty()) {
					LOGGER.debug("email is null no match");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getOutputStream().println("{ \"error\": \" Email cannot be blank \"}");
				} else if (!password.equals(confirmPassword)) {
					LOGGER.debug("password no match");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getOutputStream().println("{ \"error\": \" Passwords do no match \"}");
				} else {

					LOGGER.debug("Preparing to persist the user");
					UserAccount userAccount = new UserAccount();
					userAccount.setUsername(userName);
					String encodedPassword = userAccountService.getEncodedPassword(password);
					LOGGER.debug("Raw password = " + userAccount.getPassword());
					LOGGER.debug("Password to be saved = " + encodedPassword);

					userAccount.setPassword(encodedPassword);
					userAccount.setEmail(email);
					userAccount.setActive(false);
					userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_ADMIN);
					UserAccount retrievedUser = userAccountService.save(userAccount);

					try {
						URL url = new URL(request.getRequestURL().toString());
						String host  = url.getHost();
					    String scheme = url.getProtocol();
					    int port = url.getPort();
					    URI uri = new URI(scheme,null,host,port,null,null,null);
						LOGGER.info("Captured Server Url : "+uri.toString());
						OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(retrievedUser,
								request.getLocale(), uri.toString());
						LOGGER.debug("Event : " + event);
						eventPublisher.publishEvent(event);
						response.setStatus(HttpServletResponse.SC_OK);
						response.getOutputStream().println("{ \"success\": \" Verification email sent \"}");
					} catch (Exception me) {
						LOGGER.error("verification link sending failed: " + me.getMessage()+", Rolling back previous transaction");
						me.printStackTrace();
						verificationTokenRepository.delete(verificationTokenRepository.findTokenByUser(retrievedUser));
						System.out.println("Deleted token");
						userAccountRepository.delete(retrievedUser);
						System.out.println("Deleted user");
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						response.getOutputStream().println("{ \"error\": \" Failed to send verification email \"}");
					}
				}
			}
		}

		LOGGER.debug("Return status : " + status);
	}

	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration(WebRequest request, @RequestParam("token") String token) {

		// Locale locale = request.getLocale();
		LOGGER.info("Activing profile..");
		VerificationToken verificationToken = userAccountService.getVerificationToken(token);
		if (verificationToken == null) {
			String message = "invalid token (null)";
			throw new IllegalArgumentException(message);
		}

		UserAccount user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			LOGGER.error("user expired already on " + verificationToken.getExpiryDate().getTime());
			throw new IllegalArgumentException(
					"user expired already on " + verificationToken.getExpiryDate().getTime());

		}

		user.setActive(true);
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		userAccountService.save(user);
		LOGGER.info("Profile activated successfully.. you can now login..");
		return "Account activated successully. You can now login";
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

	private BaseResponseDAO getSuccessResponse(String message) {
		BaseResponseDAO response = new BaseResponseDAO();
		response.setResponseCode(200);
		response.setResponseMessage(message);
		return response;
	}

}
