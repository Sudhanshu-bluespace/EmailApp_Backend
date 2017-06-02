package com.bluespacetech.contact.fileupload.dto;

public class ContactUploadDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String group;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "ContactUploadDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", group="
				+ group + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}
