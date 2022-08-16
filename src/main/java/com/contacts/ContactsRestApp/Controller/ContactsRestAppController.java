package com.contacts.ContactsRestApp.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.contacts.ContactsRestApp.Model.Contacts.ContactsRestApp;
import com.contacts.ContactsRestApp.Repository.ContactsRestAppRepository;
import com.contacts.ContactsRestApp.Service.ContactsRestAppService;
import com.contacts.ContactsRestApp.Service.Model.ContactsRestAppContext;

import org.springframework.http.HttpStatus;

@RestController
//Cors hatasi icin annotation
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class ContactsRestAppController {

	@Autowired
	private ContactsRestAppService contactsRestAppService;

	// Rehberdeki tüm kayýtlarý listeleyen servis
	@RequestMapping(value = "/getAllContactsList",method = RequestMethod.GET)
	public List<ContactsRestApp> getAllContactsList() {
		
		return contactsRestAppService.getAllContactsList();
	}
	
	//Rehbere yeni kayýt eklerken kullanýlacak servis
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public Long save(@RequestBody ContactsRestAppContext contactsRestAppContext) {	
		return contactsRestAppService.save(contactsRestAppContext);
	}
	
	//Seçilen kayýtýn silinmesi için yazýlan servis
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/contacts/{person_id}", method = RequestMethod.DELETE)
	public Long delete(@PathVariable("person_id") Long person_id) {
		return contactsRestAppService.delete(person_id);
	}
	
	//Tüm Kayýtlarý Sýfýrlayan Servis
	@RequestMapping(value = "/deleteAllContacts", method = RequestMethod.DELETE)
	public String deleteAllContacts() {
		
		return contactsRestAppService.deleteAllContacts();
	
	}
	
	//Rehberdeki kiþileri güncelleyen servis
	@RequestMapping(value = "/contactsUpdate", method = RequestMethod.PUT)
	public Long update(@RequestBody ContactsRestAppContext contactsRestAppContext) {
		return contactsRestAppService.update(contactsRestAppContext);
	}
	
	//Her arama sonrasý callCount parametresini bir arttýran servis(Bu sayede sýk görüþülenlerin listenebilmesi için her aramada güncelleme yapýlacak.)
	@RequestMapping(value = "/contactsUpdateCallCount", method = RequestMethod.PUT)
	public Long updateCallCount(@RequestBody ContactsRestAppContext contactsRestAppContext) {
		return contactsRestAppService.updateCallCount(contactsRestAppContext);
	}
	
	// Rehberde tek parametre ile hem telefon hem isim aramasý yapan servis
	@RequestMapping(value = "/contactsSearch", method = RequestMethod.POST)
	public List<ContactsRestApp> searchContactsList(@RequestBody ContactsRestAppContext contactsRestAppContext) {
		return contactsRestAppService.searchContactsList(contactsRestAppContext);
	}
	      
}

