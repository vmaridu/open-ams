package org.openams.rest.facade;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.service.PersonService;
import org.openams.rest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFacade {

	private PersonService personService;
	private StaffService staffService;

	@Autowired
	public ServiceFacade(PersonService personService, StaffService staffService) {
		this.personService = personService;
		this.staffService = staffService;
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

	public Collection<Staff> getStaffs() {
		return staffService.getStaffs();
	}

	public Staff getStaff(String id) {
		return staffService.getStaff(id);
	}

	public Person getStaffByUserName(String userName) {
		return staffService.getStaffByUserName(userName);
	}

}
