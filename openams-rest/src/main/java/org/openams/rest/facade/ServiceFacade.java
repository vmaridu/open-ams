package org.openams.rest.facade;

import java.util.Collection;

import org.openams.rest.jpa.entity.Person;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFacade {

	private StaffService staffService;

	@Autowired
	public ServiceFacade(StaffService staffService) {
		this.staffService = staffService;
	}

	public Collection<Staff> getStaffs() {
		return staffService.getAll();
	}

	public Staff getStaff(String id) {
		return staffService.get(id);
	}

	public Person getStaffByUserName(String userName) {
		return staffService.getStaffByUserName(userName);
	}

}
