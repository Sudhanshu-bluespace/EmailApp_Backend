package com.bluespacetech.security.dao;

public class PendingAccountApprovalsDTO {
	
	private long serialNo;
	private long id;
	private String name;
	private String email;
	private String registrationRequestDate;
	private String status;
	
	
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistrationRequestDate() {
		return registrationRequestDate;
	}
	public void setRegistrationRequestDate(String registrationRequestDate) {
		this.registrationRequestDate = registrationRequestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "PendingAccountApprovalsDTO [id=" + id + ", name=" + name + ", email=" + email
				+ ", registrationRequestDate=" + registrationRequestDate + ", status=" + status + "]";
	}
}
