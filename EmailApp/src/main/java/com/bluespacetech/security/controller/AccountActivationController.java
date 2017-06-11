package com.bluespacetech.security.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.security.OnRegistrationCompleteEvent;
import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;
import com.bluespacetech.security.model.AccountApproval;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.repository.AccountApprovalRepository;
import com.bluespacetech.security.repository.AccountApprovalRepositoryCustom;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountActivationController {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	MailTemplateConfiguration mailTemplateConfiguration;
	
	@Autowired
	AccountApprovalRepositoryCustom accountApprovalRepository;
	
	@Autowired
	AccountApprovalRepository accountApprovalRepo;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());
	
	@PostMapping(value="approveRequest")
	public void verifyUserAccount(@RequestParam("id")String id,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		UserAccount retrievedUser = null;
		try {
			
			AccountApproval repo = accountApprovalRepo.findAccountApprovalByIdPendingApproval(Long.valueOf(id));
			repo.setStatus("APPROVED");
			repo = accountApprovalRepo.save(repo);
			retrievedUser = userAccountRepository.findUserAccountById(repo.getIdPendingApproval().longValue());
			retrievedUser.setVerifiedByAdmin(true);
			
			retrievedUser = userAccountRepository.save(retrievedUser);
			
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
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().println("{ \"error\": \" Failed to send verification email \"}");
		}
	}
	
	@PostMapping(value="getPendingApprovals",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PendingAccountApprovalsDTO> getPendingApprovals(@RequestParam("userName") String userName)
	{
		List<PendingAccountApprovalsDTO> pendingApprovalList = 
				accountApprovalRepository.getPendingApprovals(userName);
		System.out.println("response : "+pendingApprovalList);
		return pendingApprovalList;
	}
}
