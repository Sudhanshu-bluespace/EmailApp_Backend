package com.bluespacetech.notifications.email.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("file:/opt/packages/Oracle/BluespaceMailer/config/mail_template.properties")
public class MailTemplateConfiguration {

		@Value("${footer.light-text}")
		private String footerLightText;
		@Value("${footer.dark-text}")
		private String footerDarkText;
		@Value("${mail.superadmins}")
		private String mailSuperAdmins;

		public String getFooterLightText() {
			return footerLightText;
		}

		public void setFooterLightText(String footerLightText) {
			this.footerLightText = footerLightText;
		}

		public String getFooterDarkText() {
			return footerDarkText;
		}

		public void setFooterDarkText(String footerDarkText) {
			this.footerDarkText = footerDarkText;
		}

		public String getMailSuperAdmins() {
			return mailSuperAdmins;
		}

		public void setMailSuperAdmins(String mailSuperAdmins) {
			this.mailSuperAdmins = mailSuperAdmins;
		}

		@Override
		public String toString() {
			return "MailTemplateConfiguration [footerLightText=" + footerLightText + ", footerDarkText="
					+ footerDarkText + ", mailSuperAdmins=" + mailSuperAdmins + "]";
		}
		
}
