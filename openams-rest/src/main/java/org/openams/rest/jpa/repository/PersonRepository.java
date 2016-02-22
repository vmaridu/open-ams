package org.openams.rest.jpa.repository;

import java.util.List;

import org.openams.rest.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer>{

	List<Person> findByLName(String lName);
	
}
