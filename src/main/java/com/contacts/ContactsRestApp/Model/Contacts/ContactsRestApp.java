package com.contacts.ContactsRestApp.Model.Contacts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contacts")
public class ContactsRestApp implements Serializable {
	 
		private static final long serialVersionUID = -82439648328404424L;

		@Id
		@org.springframework.data.annotation.Id
		/*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
		@Column(name="person_id")
		private Long person_id;
		
		@Column(name="call_count")
		private Long call_count;

		@Column(name="person_name")
		private String person_name;
		
		@Column(name="person_surname")
		private String person_surname;
		
		@Column(name="phone_number")
		private Long phone_number;

	    public ContactsRestApp() {
	        super();
	    }
	    
	    public ContactsRestApp(Long person_id,Long call_count, String person_name, String person_surname, Long phone_number) {
	        super();
	        this.person_id = person_id;
	        this.call_count = call_count;
	        this.person_name = person_name;
	        this.person_surname = person_surname;
	        this.phone_number = phone_number;
	    }
		public void setPersonID(Long person_id) {
			this.person_id = person_id;
		}
		public Long getPersonID() {
			return person_id;
		}
		public void setCallCount(Long call_count) {
			this.call_count = call_count;
		}
		public Long getCallCount() {
			return call_count;
		}

		public void setPersonName(String person_name) {
			this.person_name = person_name;
		}
		public String getPersonName() {
			return person_name;
		}
		public void setPersonSurname(String person_surname) {
			this.person_surname = person_surname;
		}
		public String getPersonSurname() {
			return person_surname;
		}
		public void setPhoneNumber(Long phone_number) {
			this.phone_number = phone_number;
		}
		public Long getPhoneNumber() {
			return phone_number;
		}
		
		//Tüm alanlarýn içeriklerinin ayný olup olmadýðý kontrol eder.
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
			result = prime * result + ((call_count == null) ? 0 : call_count.hashCode());
			result = prime * result + ((person_name == null) ? 0 : person_name.hashCode());
			result = prime * result + ((person_surname == null) ? 0 : person_surname.hashCode());
			result = prime * result + ((phone_number == null) ? 0 : phone_number.hashCode());
			return result;
		}
		//iki nesnenin ayný deðerlere sahip olup olmadýðýný doðrulamak için hashCode ile birlikte çalýþýr.  
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			ContactsRestApp other = (ContactsRestApp) obj;
			
			if (person_id == null) {
				if (other.person_id != null)
					return false;
				
			} else if (!person_id.equals(other.person_id))
				return false;
			
			if (call_count == null) {
				if (other.call_count != null)
					return false;
			} else if (!call_count.equals(other.call_count))
				return false;
			
			if (person_name == null) {
				if (other.person_name != null)
					return false;
			} else if (!person_name.equals(other.person_name))
				return false;
			
			if (person_surname == null) {
				if (other.person_surname != null)
					return false;
			} else if (!person_surname.equals(other.person_surname))
				return false;
			
			if (phone_number == null) {
				if (other.phone_number != null)
					return false;
			} else if (!phone_number.equals(other.phone_number))
				return false;
			
			return true;
		}
}
