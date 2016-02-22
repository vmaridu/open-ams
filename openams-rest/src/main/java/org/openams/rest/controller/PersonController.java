package org.openams.rest.controller;

import java.util.Collection;

import org.jsondoc.core.annotation.ApiAuthBasic;
import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")

public class PersonController {

	private final ServiceFacade facade;

	@Autowired
	public PersonController(ServiceFacade facade) {
		this.facade = facade;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiAuthBasic(roles = {"ROLE_ADMIN"})
	//@RequestParam(required = false, value = "userName") String userName
	public Collection<Person> get() {
		return facade.getPeople();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person getById(@PathVariable("id") String id) {
		return facade.getPerson(id);
	}
	
	
}
