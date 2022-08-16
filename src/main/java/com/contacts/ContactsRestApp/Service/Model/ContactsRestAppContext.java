package com.contacts.ContactsRestApp.Service.Model;

import java.io.Serializable;
import java.util.Date;

public class ContactsRestAppContext implements Serializable {

private static final long serialVersionUID = 3906169278470348749L;
	
	private Long person_id;
	private Long call_count;
	private String person_name;
	private String person_surname;
	private Long phone_number;
	
	public Long getPersonID() {
		return person_id;
	}
	public void setPersonID(Long person_id) {
		this.person_id = person_id;
	}
	public Long getCallCount() {
		return call_count;
	}
	public void setCallCount(Long call_count) {
		this.call_count = call_count;
	}
	public String getPersonName() {
		return person_name;
	}
	public void setPersonName(String person_name) {
		this.person_name = person_name;
	}
	public String getPersonSurname() {
		return person_surname;
	}
	public void setPersonSurname(String person_surname) {
		this.person_surname = person_surname;
	}
	public Long getPhoneNumber() {
		return phone_number;
	}
	public void setPhoneNumber(Long phone_number) {
		this.phone_number = phone_number;
	}
}



