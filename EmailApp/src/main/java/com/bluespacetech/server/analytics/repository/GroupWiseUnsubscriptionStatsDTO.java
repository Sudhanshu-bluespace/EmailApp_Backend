package com.bluespacetech.server.analytics.repository;

public class GroupWiseUnsubscriptionStatsDTO {
	
	private String createdUser;
	private int unsubscribed;
	private int groupId;
	private String groupName;

	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public int getUnsubscribed() {
		return unsubscribed;
	}
	public void setUnsubscribed(int unsubscribed) {
		this.unsubscribed = unsubscribed;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	@Override
	public String toString() {
		return "GroupWiseUnsubscriptionStatsDTO [createdUser=" + createdUser + ", unsubscribed=" + unsubscribed
				+ ", groupId=" + groupId + ", groupName=" + groupName + "]";
	}
	
	

}
