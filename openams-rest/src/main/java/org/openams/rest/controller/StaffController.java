package org.openams.rest.controller;

import java.util.Collection;

import org.jsondoc.core.annotation.ApiAuthBasic;
import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.utils.PresentationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")

public class StaffController {

	private final ServiceFacade facade;

	@Autowired
	public StaffController(ServiceFacade facade) {
		this.facade = facade;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiAuthBasic(roles = {"ROLE_ADMIN"})
	//@RequestParam(required = false, value = "userName") String userName
	public Collection<Staff> get() {
		return PresentationUtil.getPresentableSatffs(facade.getStaffs());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiAuthBasic(roles = {"ROLE_ADMIN"})
	public Staff getById(@PathVariable("id") String id) {
		return PresentationUtil.getPresentableSatff(facade.getStaff(id));
	}


}
