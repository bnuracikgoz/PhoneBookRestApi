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

//Burada crudResository sayesinde veritaban�nda �ekece�imiz alanlar� filitreleyebildi�imiz queryler mevcut.
@Repository
public interface ContactsRestAppRepository extends CrudRepository<ContactsRestApp, Long> {

	//Tablodaki t�m kay�tlar� d�nd�r�r.
	@Query(value = "SELECT * FROM contacts ",nativeQuery=true)
	public List<ContactsRestApp> getAllContactsList();
	
	//Tablodaki max id yi d�nd�r�r.(Kay�teklerken id yi +1 ile tabloya kay�t ettim.)
	@Query(value = "SELECT MAX(person_id) FROM contacts ",nativeQuery=true)
	public Long findMaxId();

	//G�nderilen parametreye sahip id si olan ki�inin kay�tlar�n� d�nd�r�r.
	@Query(value = "SELECT * FROM contacts  WHERE person_id = :person_id",nativeQuery=true)
	public ContactsRestApp findWithPersonId(@Param("person_id") Long person_id);
	
	//Search i�in person_name ve phone_number alanlar�nda g�nderilen parametreyi ar�yor.
	@Query(value = "SELECT * FROM contacts WHERE person_name like :person_name% or cast(phone_number as text) like :person_name%", nativeQuery = true)
	public List<ContactsRestApp> searchContactsList(@Param("person_name") String person_name );
	
}
