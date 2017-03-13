package org.openams.rest.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class PersonService extends BaseService<Person, String>  {

	private PersonRepository repository;

	@Autowired
	public PersonService(PersonRepository repository) {
		super(repository,Person::getId);
		this.repository = repository;
	}

	public Person getPersonByUserName(String userName){
		return repository.findByUserName(userName).stream().findFirst().orElseThrow(() -> new EntityNotFoundException());
	}

	public Person updateProfileWithUserAccountPreservation(Person person){
		Person prePerson = get(person.getId());
		person.setUser(prePerson.getUser());
		return repository.save(person);
	}

}
