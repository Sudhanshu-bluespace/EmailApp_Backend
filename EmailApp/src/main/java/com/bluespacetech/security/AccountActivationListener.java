package com.bluespacetech.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.bluespacetech.notifications.email.util.EmailHandler;
import com.bluespacetech.security.model.AccountCreationEmail;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.service.UserAccountService;

/**
 * The listener interface for receiving accountActivation events. The class that is interested in processing a accountActivation event implements this interface, and the object created with that class
 * is registered with a component using the component's <code>addAccountActivationListener<code> method. When the accountActivation event occurs, that object's appropriate method is invoked.
 *
 * @see AccountActivationEvent
 * @author Sudhanshu Tripathy
 */
@Component
public class AccountActivationListener implements ApplicationListener<OnRegistrationCompleteEvent>
{

    /** The service. */
    @Autowired
    private UserAccountService service;

    /** The email handler. */
    @Autowired
    private EmailHandler emailHandler;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(AccountActivationListener.class.getName());

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event)
    {
        this.confirmRegistration(event);
    }

    /**
     * Confirm registration.
     *
     * @param event the event
     */
    private void confirmRegistration(OnRegistrationCompleteEvent event)
    {
        UserAccount user = event.getUser();
        LOGGER.debug("Listening to the publish token event...");

        String recipientAddress = user.getEmail();
        String subject = "Bluespace Mailer Registration | Account Created";

        AccountCreationEmail mail = new AccountCreationEmail();
        mail.setMailTo(recipientAddress);
        mail.setMailFrom("bluespaceadmin@gmail.com");
        mail.setMailSubject(subject);
        if (!event.isAccountCreatedByAdmin())
        {
            LOGGER.info("INternal Account creation by Admin. Skiiping token creation and verification link");
            String token = UUID.randomUUID().toString();
            service.createVerificationToken(user, token);
            String confirmationUrl = event.getAppUrl() + "/new/regitrationConfirm?token=" + token;
            mail.setVerificationUrl(confirmationUrl);
            mail.setUnsubscribeUrl(event.getAppUrl() + "/unsubscribe?email=" + recipientAddress);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", user.getUsername());
        model.put("signature", "www.bluespacemail.com");
        mail.setModel(model);

        try
        {
            if (event.isAccountCreatedByAdmin())
            {
                emailHandler.sendAccountCreationEmail(mail, event.getUser().getUsername(),
                        event.getUser().getPassword());
            }
            else if ("APPROVE".equalsIgnoreCase(event.getRequestType()))
            {
                emailHandler.sendVerificationEmail(mail);
            }
            else if ("HOLD".equalsIgnoreCase(event.getRequestType()))
            {
                emailHandler.sendAccountCreationOnHoldEmail(mail, event.getUser().getUsername(),
                        event.getUser().getPassword());
            }
            else if ("REJECT".equalsIgnoreCase(event.getRequestType()))
            {
                emailHandler.sendAccountCreationRejectedEmail(mail, event.getUser().getUsername(),
                        event.getUser().getPassword());
            }
        }
        catch (IOException e)
        {
            String message = "Failed to send verification/account creation email";
            LOGGER.error(message + ", reason: " + e.getMessage());
            throw new RuntimeException(message);
        }
    }
}
