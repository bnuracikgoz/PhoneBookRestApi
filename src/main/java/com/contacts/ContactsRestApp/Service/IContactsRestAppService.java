package com.contacts.ContactsRestApp.Service;

import com.contacts.ContactsRestApp.Service.Model.ContactsRestAppContext;

import java.util.List;

import com.contacts.ContactsRestApp.Model.Contacts.ContactsRestApp;
public interface IContactsRestAppService {
	public Long save(ContactsRestAppContext contactsRestAppContext);
	public Long delete(Long person_id);
	public ContactsRestApp findWithPersonId (Long person_id);
	public Long update(ContactsRestAppContext contactsRestAppContext);
	public Long updateCallCount(ContactsRestAppContext contactsRestAppContext);
	public List<ContactsRestApp> searchContactsList(ContactsRestAppContext contactsRestAppContext);
}
