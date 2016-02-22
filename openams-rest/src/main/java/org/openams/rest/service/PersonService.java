package org.openams.rest.service;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;

public interface PersonService {

	public Collection<Person> getPeople();
	
	public Person getPerson(String id);
	
	public Person getPersonByUserName(String userName);

}
