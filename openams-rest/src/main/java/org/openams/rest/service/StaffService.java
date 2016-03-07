package org.openams.rest.service;

import org.openams.rest.jpa.entity.Staff;

public interface StaffService extends BaseService<Staff, String>{

	public Staff getStaffByUserName(String userName);

}
