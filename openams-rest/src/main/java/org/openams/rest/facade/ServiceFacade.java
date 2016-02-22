package org.openams.rest.facade;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFacade {

	private PersonService personService;

	@Autowired
	public ServiceFacade(PersonService personService) {
		this.personService = personService;
	}
	
	public Collection<Person> getPeople() {
		return personService.getPeople();
	}

	public Person getPerson(String id) {
		return personService.getPerson(id);
	}

	public Person getPersonByUserName(String userName) {
		return personService.getPersonByUserName(userName);
	}
	
}
