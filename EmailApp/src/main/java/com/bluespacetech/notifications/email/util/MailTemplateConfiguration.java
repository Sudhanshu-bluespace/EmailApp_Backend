package com.bluespacetech.notifications.email.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * The Class MailTemplateConfiguration.
 * @author Sudhanshu Tripathy
 */
@Configuration
@PropertySource("file:/opt/packages/Oracle/BluespaceMailer/config/mail_template.properties")
public class MailTemplateConfiguration
{

    /** The footer light text. */
    @Value("${footer.light-text}")
    private String footerLightText;

    /** The footer dark text. */
    @Value("${footer.dark-text}")
    private String footerDarkText;

    /** The mail super admins. */
    @Value("${mail.superadmins}")
    private String mailSuperAdmins;

    /**
     * Gets the footer light text.
     *
     * @return the footer light text
     */
    public String getFooterLightText()
    {
        return footerLightText;
    }

    /**
     * Sets the footer light text.
     *
     * @param footerLightText the new footer light text
     */
    public void setFooterLightText(String footerLightText)
    {
        this.footerLightText = footerLightText;
    }

    /**
     * Gets the footer dark text.
     *
     * @return the footer dark text
     */
    public String getFooterDarkText()
    {
        return footerDarkText;
    }

    /**
     * Sets the footer dark text.
     *
     * @param footerDarkText the new footer dark text
     */
    public void setFooterDarkText(String footerDarkText)
    {
        this.footerDarkText = footerDarkText;
    }

    /**
     * Gets the mail super admins.
     *
     * @return the mail super admins
     */
    public String getMailSuperAdmins()
    {
        return mailSuperAdmins;
    }

    /**
     * Sets the mail super admins.
     *
     * @param mailSuperAdmins the new mail super admins
     */
    public void setMailSuperAdmins(String mailSuperAdmins)
    {
        this.mailSuperAdmins = mailSuperAdmins;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "MailTemplateConfiguration [footerLightText=" + footerLightText + ", footerDarkText=" + footerDarkText
                + ", mailSuperAdmins=" + mailSuperAdmins + "]";
    }

}
