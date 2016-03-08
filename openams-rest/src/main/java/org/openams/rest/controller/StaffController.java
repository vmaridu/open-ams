package org.openams.rest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthBasic;
import org.jsondoc.core.annotation.ApiMethod;
import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Staff;
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

@Api(name = "Staff Controller", description = "Allows CRUD Operations on Staff")
@ApiAuthBasic(roles = {"ROLE_ADMIN"})
@RestController
@RequestMapping("/staff")
public class StaffController {

	private final ServiceFacade facade;

	@Autowired
	public StaffController(ServiceFacade facade) {
		this.facade = facade;
	}

	//TODO:Do not allow user to add Role if not Admin
	@ApiMethod(description = "Creates Staff Account")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Staff staff, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
		facade.createStaff(staff);
		response.setHeader("Location", uriBuilder.path("/staff/{id}") .buildAndExpand(staff.getId()).toUriString());
	}


	@ApiMethod(description = "Gets All Staff Accounts")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection<Staff> get() {
		return PresentationUtil.getPresentableSatffs(facade.getStaffs());
	}

	@ApiMethod(description = "Gets Staff Account by Staff ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Staff getById(@PathVariable("id") String id) {
		return PresentationUtil.getPresentableSatff(facade.getStaff(id));
	}

	//TODO:Do not allow user to change Role if not Admin
	@ApiMethod(description = "Updates Staff Account")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@RequestBody Staff staff,@PathVariable("id") String id) {
		facade.updateStaff(staff,id);
	}

	@ApiMethod(description = "Deletes Staff Account by Staff ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable("id") String id) {
		facade.deleteUser(id);
	}

}
