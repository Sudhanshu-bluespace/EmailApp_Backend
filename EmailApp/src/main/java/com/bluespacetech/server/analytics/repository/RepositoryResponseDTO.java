package com.bluespacetech.server.analytics.repository;

public class RepositoryResponseDTO {
	
	private long email_id;
	private String created_user;
	private String content;
	private String subject;
	private int reach;
	private int clicks;
	private int unsubscribes;
	private String sentOn;
	private double clickPercentage;
	private double unsubscribePercentage;
	
	public double getClickPercentage() {
		return clickPercentage;
	}
	public void setClickPercentage(double clickPercentage) {
		this.clickPercentage = clickPercentage;
	}
	public double getUnsubscribePercentage() {
		return unsubscribePercentage;
	}
	public void setUnsubscribePercentage(double unsubscribePercentage) {
		this.unsubscribePercentage = unsubscribePercentage;
	}
	public String getSentOn() {
		return sentOn;
	}
	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}
	public long getEmail_id() {
		return email_id;
	}
	public void setEmail_id(long email_id) {
		this.email_id = email_id;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	@Override
	public String toString() {
		return "RepositoryResponseDTO [email_id=" + email_id + ", created_user=" + created_user + ", content=" + content
				+ ", subject=" + subject + ", reach=" + reach + ", clicks=" + clicks + ", unsubscribes=" + unsubscribes
				+ ", sentOn="+sentOn+ ", clickPercentage="+clickPercentage+", unsubscribePercentage="+unsubscribePercentage+"]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getReach() {
		return reach;
	}
	public void setReach(int reach) {
		this.reach = reach;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getUnsubscribes() {
		return unsubscribes;
	}
	public void setUnsubscribes(int unsubscribes) {
		this.unsubscribes = unsubscribes;
	}

}
