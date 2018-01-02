package com.bluespacetech.security.controller;

import com.bluespacetech.notifications.email.util.MailTemplateConfiguration;
import com.bluespacetech.security.OnRegistrationCompleteEvent;
import com.bluespacetech.security.dao.PendingAccountApprovalsDTO;
import com.bluespacetech.security.model.AccountApproval;
import com.bluespacetech.security.model.CompanyRegistration;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.repository.AccountApprovalRepository;
import com.bluespacetech.security.repository.AccountApprovalRepositoryCustom;
import com.bluespacetech.security.repository.CompanyRegistrationRepository;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.VerificationTokenRepository;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({"/accounts"})
public class AccountActivationController
{
  @Autowired
  UserAccountRepository userAccountRepository;
  @Autowired
  ApplicationEventPublisher eventPublisher;
  @Autowired
  MailTemplateConfiguration mailTemplateConfiguration;
  @Autowired
  AccountApprovalRepositoryCustom accountApprovalRepository;
  @Autowired
  CompanyRegistrationRepository companyRegistrationRepo;
  @Autowired
  AccountApprovalRepository accountApprovalRepo;
  @Autowired
  VerificationTokenRepository verificationTokenRepository;
  private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class.getName());
  
  @PostMapping({"approveRequest"})
  public void verifyUserAccount(@RequestParam("id") String id, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    UserAccount retrievedUser = null;
    try
    {
      AccountApproval repo = this.accountApprovalRepo.findAccountApprovalByIdPendingApproval(Long.valueOf(id));
      if ("APPROVE".equalsIgnoreCase(type)) {
        repo.setStatus("APPROVED");
      } else if ("REJECT".equalsIgnoreCase(type)) {
        repo.setStatus("REJECTED");
      } else if ("HOLD".equalsIgnoreCase(type)) {
        repo.setStatus("ON HOLD");
      }
      repo = (AccountApproval)this.accountApprovalRepo.save(repo);
      retrievedUser = this.userAccountRepository.findUserAccountById(repo.getIdPendingApproval().longValue());
      if ("APPROVE".equalsIgnoreCase(type))
      {
        retrievedUser.setVerifiedByAdmin(true);
        String companyName = retrievedUser.getCompanyRegistration().getCompanyName();
        
        CompanyRegistration company = this.companyRegistrationRepo.findCompanyRegistrationByCompanyNameIgnoreCase(companyName);
        company.setApproved(true);
        this.companyRegistrationRepo.save(company);
        LOGGER.info("Approved Company Registration and updated records in DB successfully");
        retrievedUser = (UserAccount)this.userAccountRepository.save(retrievedUser);
      }
      URL url = new URL(request.getRequestURL().toString());
      String host = url.getHost();
      String scheme = url.getProtocol();
      int port = url.getPort();
      URI uri = new URI(scheme, null, host, port, null, null, null);
      LOGGER.info("Captured Server Url : " + uri.toString());
      
      OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(retrievedUser, request.getLocale(), uri.toString(), false, type);
      LOGGER.debug("Event : " + event);
      this.eventPublisher.publishEvent(event);
      response.setStatus(200);
      response.getOutputStream().println("{ \"success\": \" Verification email sent \"}");
    }
    catch (Exception me)
    {
      LOGGER.error("verification link sending failed: " + me
        .getMessage() + ", Rolling back previous transaction");
      me.printStackTrace();
      this.verificationTokenRepository.delete(this.verificationTokenRepository.findTokenByUser(retrievedUser));
      response.setStatus(400);
      response.getOutputStream().println("{ \"error\": \" Failed to send verification email \"}");
    }
  }
  
  @PostMapping(value={"getPendingApprovals"}, produces={"application/json"})
  public List<PendingAccountApprovalsDTO> getPendingApprovals(@RequestParam("userName") String userName)
  {
    List<PendingAccountApprovalsDTO> pendingApprovalList = this.accountApprovalRepository.getPendingApprovals(userName);
    LOGGER.info("response : " + pendingApprovalList);
    return pendingApprovalList;
  }
}
