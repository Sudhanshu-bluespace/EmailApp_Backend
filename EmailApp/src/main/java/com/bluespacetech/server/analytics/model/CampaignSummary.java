/**
 * 
 */
package com.bluespacetech.server.analytics.model;

/**
 * @author sudhanshu
 *
 */
public class CampaignSummary {
	
	private String campaignName;
	private String totalNumberOfEmails;
	private int readCount;
	private int bounceCount;
	
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getTotalNumberOfEmails() {
		return totalNumberOfEmails;
	}
	public void setTotalNumberOfEmails(String totalNumberOfEmails) {
		this.totalNumberOfEmails = totalNumberOfEmails;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getBounceCount() {
		return bounceCount;
	}
	public void setBounceCount(int bounceCount) {
		this.bounceCount = bounceCount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String subject;
	private String description;

}
