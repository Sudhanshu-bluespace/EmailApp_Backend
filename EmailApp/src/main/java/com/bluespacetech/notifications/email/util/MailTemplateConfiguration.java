package com.bluespacetech.notifications.email.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"file:/opt/packages/Oracle/BluespaceMailer/config/mail_template.properties"})
public class MailTemplateConfiguration
{
  @Value("${footer.light-text}")
  private String footerLightText;
  @Value("${footer.dark-text}")
  private String footerDarkText;
  @Value("${mail.superadmins}")
  private String mailSuperAdmins;
  @Value("${mail.ignoreList}")
  private String ignoreList;
  @Value("${mail.prohibited_content}")
  private String prohibitedContent;
  
  public String getProhibitedContent()
  {
    return this.prohibitedContent;
  }
  
  public String getIgnoreList()
  {
    return this.ignoreList;
  }
  
  public void setIgnoreList(String ignoreList)
  {
    this.ignoreList = ignoreList;
  }
  
  public String getFooterLightText()
  {
    return this.footerLightText;
  }
  
  public void setFooterLightText(String footerLightText)
  {
    this.footerLightText = footerLightText;
  }
  
  public String getFooterDarkText()
  {
    return this.footerDarkText;
  }
  
  public void setFooterDarkText(String footerDarkText)
  {
    this.footerDarkText = footerDarkText;
  }
  
  public String getMailSuperAdmins()
  {
    return this.mailSuperAdmins;
  }
  
  public void setMailSuperAdmins(String mailSuperAdmins)
  {
    this.mailSuperAdmins = mailSuperAdmins;
  }
  
  public String toString()
  {
    return "MailTemplateConfiguration [footerLightText=" + this.footerLightText + ", footerDarkText=" + this.footerDarkText + ", mailSuperAdmins=" + this.mailSuperAdmins + ", ignoreList=" + this.ignoreList + "]";
  }
}
