package com.contacts.ContactsRestApp.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.contacts.ContactsRestApp.Model.Contacts.ContactsRestApp;
import com.contacts.ContactsRestApp.Service.Model.ContactsRestAppContext;

//Burada crudResository sayesinde veritabanýnda çekeceðimiz alanlarý filitreleyebildiðimiz queryler mevcut.
@Repository
public interface ContactsRestAppRepository extends CrudRepository<ContactsRestApp, Long> {

	//Tablodaki tüm kayýtlarý döndürür.
	@Query(value = "SELECT * FROM contacts ",nativeQuery=true)
	public List<ContactsRestApp> getAllContactsList();
	
	//Tablodaki max id yi döndürür.(Kayýteklerken id yi +1 ile tabloya kayýt ettim.)
	@Query(value = "SELECT MAX(person_id) FROM contacts ",nativeQuery=true)
	public Long findMaxId();

	//Gönderilen parametreye sahip id si olan kiþinin kayýtlarýný döndürür.
	@Query(value = "SELECT * FROM contacts  WHERE person_id = :person_id",nativeQuery=true)
	public ContactsRestApp findWithPersonId(@Param("person_id") Long person_id);
	
	//Search için person_name ve phone_number alanlarýnda gönderilen parametreyi arýyor.
	@Query(value = "SELECT * FROM contacts WHERE person_name like :person_name% or cast(phone_number as text) like :person_name%", nativeQuery = true)
	public List<ContactsRestApp> searchContactsList(@Param("person_name") String person_name );
	
}
