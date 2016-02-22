package org.openams.rest.service.impl;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.repository.PersonRepository;
import org.openams.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository repository;

	@Autowired
	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public Collection<Person> getPeople() {
		Collection<Person> people = repository.findAll();
		if(people.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return people;
	}

	@Override
	public Person getPerson(String id) {
		Person person = Optional.ofNullable(repository.findPerson(id))
				.orElseThrow(() -> new EntityNotFoundException());
		return person;
	}

	@Override
	public Person getPersonByUserName(String userName) {
		Person person = repository.findByUserName(userName).stream()
				.findFirst().orElseThrow(() -> new EntityNotFoundException());
		return person;
	}

}
