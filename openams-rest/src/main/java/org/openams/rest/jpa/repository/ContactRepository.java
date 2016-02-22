package org.openams.rest.jpa.repository;

import org.openams.rest.jpa.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,String>{

	
}
