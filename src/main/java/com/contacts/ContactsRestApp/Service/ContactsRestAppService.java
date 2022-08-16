package com.contacts.ContactsRestApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.contacts.ContactsRestApp.Model.Contacts.ContactsRestApp;
import com.contacts.ContactsRestApp.Repository.ContactsRestAppRepository;
import com.contacts.ContactsRestApp.Service.Model.ContactsRestAppContext;

@Service
public class ContactsRestAppService implements IContactsRestAppService {
	
	@Autowired
	private ContactsRestAppRepository contactsRestAppRepository;
	
	//Kay�tlar� listeleyen servisi ba�lad�m.
	public List<ContactsRestApp> getAllContactsList() {
		return contactsRestAppRepository.getAllContactsList();
	}
	//G�nderilen parametreye ait personel kay�tlar�n� d�nd�ren servis
	public ContactsRestApp findWithPersonId(Long person_id) {
		return contactsRestAppRepository.findWithPersonId(person_id);
	}
	
	//Search i�in yaz�lan servis
	public List<ContactsRestApp> searchContactsList(ContactsRestAppContext contactsRestAppContext) {
		return contactsRestAppRepository.searchContactsList(contactsRestAppContext.getPersonName());
	}
	
	@Transactional
	public Long save(ContactsRestAppContext contactsRestAppContext) {
		Long maxId;
		try {
			 maxId = contactsRestAppRepository.findMaxId() + 1;

		}
		/* Veritaban� bo� oldu�unda findMaxId() fonksiyonu catche d���yor. Bu nedenle ilk de�er atamas� 1 olarak set edildi.
		 * 
		 */
		catch(Exception e){ 
			maxId=(long) 1;
		}


		ContactsRestApp contactsRestApp= new ContactsRestApp();

		contactsRestApp.setPersonID(maxId);
		contactsRestApp.setCallCount(contactsRestAppContext.getCallCount());
		contactsRestApp.setPersonName(contactsRestAppContext.getPersonName());
		contactsRestApp.setPersonSurname(contactsRestAppContext.getPersonSurname());
		contactsRestApp.setPhoneNumber(contactsRestAppContext.getPhoneNumber());
		contactsRestApp = contactsRestAppRepository.save(contactsRestApp);
		/*
		if(books.getPersonID() > 0) {
			throw new RuntimeException("CUSTOM ERROR FOR ROLLBACK!");
		}
		*/
		return contactsRestApp.getPersonID();
	}
	
	//G�ncelleme i�in servis i�erisinde sql sorgular� ayn� s�re� i�erisinde i�lemlerini ger�ekle�tirildi.
	@Transactional
	public Long update(ContactsRestAppContext contactsRestAppContext) {

		//Girilen id ye ait personel kay�tlar�n� �ektim
		ContactsRestApp contactsRestApp = contactsRestAppRepository.findWithPersonId(contactsRestAppContext.getPersonID());
		if(contactsRestApp == null)
		{
			throw new RuntimeException(contactsRestAppContext.getPersonID() + " ID not found in DB!");
		}
		//Yeni g�nderilen de�erleri eski kay�tlar�n yerine kay�t ettim.
		contactsRestApp.setCallCount(contactsRestAppContext.getCallCount());
		contactsRestApp.setPersonName(contactsRestAppContext.getPersonName());
		contactsRestApp.setPersonSurname(contactsRestAppContext.getPersonSurname());
		contactsRestApp.setPhoneNumber(contactsRestAppContext.getPhoneNumber());
		
		contactsRestAppRepository.save(contactsRestApp);
		return contactsRestApp.getPersonID();
	}
	//G�nderilen parametredeki kay�tlar� silen servis ba�lant�s�
	@Transactional
	public Long delete(Long person_id) {
		
		contactsRestAppRepository.deleteById(person_id);
		return person_id;
	}
	//Arama say�s�n� g�ncellleyen servis
	@Transactional
	public Long updateCallCount(ContactsRestAppContext contactsRestAppContext) {

		ContactsRestApp contactsRestApp = contactsRestAppRepository.findWithPersonId(contactsRestAppContext.getPersonID());
		if(contactsRestApp == null)
		{
			throw new RuntimeException(contactsRestAppContext.getPersonID() + " ID not found in DB!");
		}
		//Arama say�s�n� �ekti�im kay�t�n aramasay�s� +1 olacak �ekilde kay�t edildi.
		contactsRestApp.setCallCount(contactsRestAppContext.getCallCount()+1);
		contactsRestApp.setPersonName(contactsRestAppContext.getPersonName());
		contactsRestApp.setPersonSurname(contactsRestAppContext.getPersonSurname());
		contactsRestApp.setPhoneNumber(contactsRestAppContext.getPhoneNumber());
		contactsRestAppRepository.save(contactsRestApp);
		return contactsRestApp.getPersonID();
	}
	//T�m kay�tlar� silen servis
	@Transactional
	public String deleteAllContacts() {

		String text="Success deleteAllTable";
		contactsRestAppRepository.deleteAll();
		return text;
	}

}
