package org.openams.rest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.utils.PresentationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final ServiceFacade facade;

	@Autowired
	public StudentController(ServiceFacade facade) {
		this.facade = facade;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Student student, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
		facade.create(Student.class, student);
		response.setHeader("Location", uriBuilder.path("/students/{id}") .buildAndExpand(student.getId()).toUriString());
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection<Student> get() {
		return PresentationUtil.getPresentableStudents(facade.getAll(Student.class));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Student getById(@PathVariable("id") String id) {
		return PresentationUtil.getPresentableStudent(facade.get(Student.class, id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@RequestBody Student student, @PathVariable("id") String id) {
		student.setId(id);
		facade.update(Student.class, student);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable("id") String id) {
		facade.delete(Student.class, id);
	}

}
