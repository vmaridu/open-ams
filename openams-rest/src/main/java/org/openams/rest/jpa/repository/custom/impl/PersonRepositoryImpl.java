package org.openams.rest.jpa.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.repository.custom.PersonRepositoryCustom;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;
	
	
	@Override
	public Person findPerson(String id) {
		return entityManager.find(Person.class, id);
	}

}
