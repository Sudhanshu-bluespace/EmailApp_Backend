package com.bluespacetech.security.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

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

import com.bluespacetech.security.OnRegistrationCompleteEvent;
import com.bluespacetech.security.dao.BaseResponseDAO;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.VerificationToken;
import com.bluespacetech.security.service.UserAccountService;

@RestController
@CrossOrigin
@RequestMapping("/new")
public class RegistrationController {

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<BaseResponseDAO> welcomeNewRegistration() {
		return new ResponseEntity<BaseResponseDAO>(getWelcomeResponse(), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", consumes=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDAO> registerNewUser(@RequestBody Map<String, Object> registrationDetails,
			WebRequest request) {
		BaseResponseDAO response = null;
		HttpStatus status = null;
		if (registrationDetails == null || registrationDetails.isEmpty()) {
			response = getInvalidInputResponse("No input Supplied");
			status = HttpStatus.BAD_REQUEST;
		}

		String userName = (String) registrationDetails.get("userName");
		String password = (String) registrationDetails.get("password");
		String confirmPassword = (String) registrationDetails.get("confirmPassword");
		String email = (String) registrationDetails.get("emailAddress");
		
		System.out.println("input as received: "+userName+" | "+password+" | "+confirmPassword);

		if (userName == null || userName.trim().isEmpty()) {
			System.out.println("user blank");
			getInvalidInputResponse("userName cannot be blank");
			status = HttpStatus.BAD_REQUEST;
		} else if (password == null || password.trim().isEmpty()) {
			System.out.println("password blank");
			getInvalidInputResponse("Password cannot be blank");
			status = HttpStatus.BAD_REQUEST;
		} else if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
			System.out.println("conf password blank");
			response = getInvalidInputResponse("Confirm Password cannot be blank");
			status = HttpStatus.BAD_REQUEST;
		} else if (email==null||email.trim().isEmpty()) {
			System.out.println("email is null no match");
			response = getInvalidInputResponse("email cannot be null");
			status = HttpStatus.BAD_REQUEST;
		}
	 else if (!password.equals(confirmPassword)) {
		System.out.println("password no match");
		response = getInvalidInputResponse("The two passwords do not match");
		status = HttpStatus.BAD_REQUEST;
	}else {

			System.out.println("Preparing to persist the user");
			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(userName);
			userAccount.setPassword(password);
			userAccount.setEmail(email);
			userAccount.setActive(false);
			UserAccount retrievedUser = userAccountService.save(userAccount);
			
		    try {
		        String appUrl = request.getContextPath();
		        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(retrievedUser, request.getLocale(), appUrl);
		        System.out.println("Event : "+event);
		        eventPublisher.publishEvent(event);
				status = HttpStatus.OK;
				response = getSuccessResponse("Verification email sent successully.");
		    } catch (Exception me) {
				System.out.println("verification link sending failed: "+me.getMessage());
				response = getInvalidInputResponse("verification link sending failed");
				status = HttpStatus.FAILED_DEPENDENCY;
		    }
		}

		System.out.println("Return status : "+status);
		return new ResponseEntity<BaseResponseDAO>(response, status);
	}
	
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration
	  (WebRequest request, @RequestParam("token") String token) {
	  
	    Locale locale = request.getLocale();
	    System.out.println("Activing profile.."); 
	    VerificationToken verificationToken = userAccountService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = "invalid token";
	        return message+"redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	     
	    UserAccount user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setActive(true); 
	    userAccountService.save(user); 
	    System.out.println("Profile activated successfully.. you can now login..");
	    return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
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
