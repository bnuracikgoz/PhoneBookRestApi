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
	
	//Kayýtlarý listeleyen servisi baðladým.
	public List<ContactsRestApp> getAllContactsList() {
		return contactsRestAppRepository.getAllContactsList();
	}
	//Gönderilen parametreye ait personel kayýtlarýný döndüren servis
	public ContactsRestApp findWithPersonId(Long person_id) {
		return contactsRestAppRepository.findWithPersonId(person_id);
	}
	
	//Search için yazýlan servis
	public List<ContactsRestApp> searchContactsList(ContactsRestAppContext contactsRestAppContext) {
		return contactsRestAppRepository.searchContactsList(contactsRestAppContext.getPersonName());
	}
	
	@Transactional
	public Long save(ContactsRestAppContext contactsRestAppContext) {
		Long maxId;
		try {
			 maxId = contactsRestAppRepository.findMaxId() + 1;

		}
		/* Veritabaný boþ olduðunda findMaxId() fonksiyonu catche düþüyor. Bu nedenle ilk deðer atamasý 1 olarak set edildi.
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
	
	//Güncelleme için servis içerisinde sql sorgularý ayný süreç içerisinde iþlemlerini gerçekleþtirildi.
	@Transactional
	public Long update(ContactsRestAppContext contactsRestAppContext) {

		//Girilen id ye ait personel kayýtlarýný çektim
		ContactsRestApp contactsRestApp = contactsRestAppRepository.findWithPersonId(contactsRestAppContext.getPersonID());
		if(contactsRestApp == null)
		{
			throw new RuntimeException(contactsRestAppContext.getPersonID() + " ID not found in DB!");
		}
		//Yeni gönderilen deðerleri eski kayýtlarýn yerine kayýt ettim.
		contactsRestApp.setCallCount(contactsRestAppContext.getCallCount());
		contactsRestApp.setPersonName(contactsRestAppContext.getPersonName());
		contactsRestApp.setPersonSurname(contactsRestAppContext.getPersonSurname());
		contactsRestApp.setPhoneNumber(contactsRestAppContext.getPhoneNumber());
		
		contactsRestAppRepository.save(contactsRestApp);
		return contactsRestApp.getPersonID();
	}
	//Gönderilen parametredeki kayýtlarý silen servis baðlantýsý
	@Transactional
	public Long delete(Long person_id) {
		
		contactsRestAppRepository.deleteById(person_id);
		return person_id;
	}
	//Arama sayýsýný güncellleyen servis
	@Transactional
	public Long updateCallCount(ContactsRestAppContext contactsRestAppContext) {

		ContactsRestApp contactsRestApp = contactsRestAppRepository.findWithPersonId(contactsRestAppContext.getPersonID());
		if(contactsRestApp == null)
		{
			throw new RuntimeException(contactsRestAppContext.getPersonID() + " ID not found in DB!");
		}
		//Arama sayýsýný çektiðim kayýtýn aramasayýsý +1 olacak þekilde kayýt edildi.
		contactsRestApp.setCallCount(contactsRestAppContext.getCallCount()+1);
		contactsRestApp.setPersonName(contactsRestAppContext.getPersonName());
		contactsRestApp.setPersonSurname(contactsRestAppContext.getPersonSurname());
		contactsRestApp.setPhoneNumber(contactsRestAppContext.getPhoneNumber());
		contactsRestAppRepository.save(contactsRestApp);
		return contactsRestApp.getPersonID();
	}
	//Tüm kayýtlarý silen servis
	@Transactional
	public String deleteAllContacts() {

		String text="Success deleteAllTable";
		contactsRestAppRepository.deleteAll();
		return text;
	}

}
