package org.openams.rest.service;

import java.util.Collection;

import org.openams.rest.jpa.entity.Staff;

public interface StaffService {

	public Collection<Staff> getStaffs();

	public Staff getStaff(String id);

	public Staff getStaffByUserName(String userName);

}
