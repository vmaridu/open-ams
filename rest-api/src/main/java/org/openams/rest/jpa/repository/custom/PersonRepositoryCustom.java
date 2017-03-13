package org.openams.rest.jpa.repository.custom;

import org.openams.rest.jpa.entity.Person;
                
public interface PersonRepositoryCustom {

	public Person findPerson(String id);
}
