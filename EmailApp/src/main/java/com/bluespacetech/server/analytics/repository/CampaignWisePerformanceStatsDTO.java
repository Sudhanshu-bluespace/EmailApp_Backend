package com.bluespacetech.server.analytics.repository;

public class CampaignWisePerformanceStatsDTO {

	private int unsubscribes;
	private int group_id;
	private long email_id;
	private String campaignName;
	private String groupName;
	private int clicks;
	private int totalReach;
	private String createdUser;
	private String subject;
	private String content;
	private String sentOn;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSentOn() {
		return sentOn;
	}
	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public int getUnsubscribes() {
		return unsubscribes;
	}
	public void setUnsubscribes(int unsubscribes) {
		this.unsubscribes = unsubscribes;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public long getEmail_id() {
		return email_id;
	}
	public void setEmail_id(long email_id) {
		this.email_id = email_id;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getTotalReach() {
		return totalReach;
	}
	public void setTotalReach(int totalReach) {
		this.totalReach = totalReach;
	}
	
	@Override
	public String toString() {
		return "CampaignWisePerformanceStats [unsubscribes=" + unsubscribes + ", group_id=" + group_id + ", email_id="
				+ email_id + ", campaignName=" + campaignName + ", groupName=" + groupName + ", clicks=" + clicks
				+ ", totalReach=" + totalReach + "]";
	}

	
}
