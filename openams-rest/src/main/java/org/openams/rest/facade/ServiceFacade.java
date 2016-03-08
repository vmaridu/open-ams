package org.openams.rest.facade;

import java.util.Collection;

import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.service.StaffService;
import org.openams.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFacade {

	private UserService userService;
	private StaffService staffService;

	@Autowired
	public ServiceFacade(UserService userService,StaffService staffService) {
		this.userService = userService;
		this.staffService = staffService;
	}

	public Collection<User> getUsers() {
		return userService.getAll();
	}

	public User getUser(String userName) {
		return userService.get(userName);
	}

	public User createUser(User user) {
		return userService.create(user, user.getUserName());
	}

	public User updateUser(User user, String userName) {
		return userService.update(user, userName);
	}

	public void deleteUser(String userName) {
		userService.delete(userName);
	}

	public User updatePassword(String userName,String newPassword) {
		return userService.updatePassword(userName,newPassword);
	}

	public Collection<Staff> getStaffs() {
		return staffService.getAll();
	}

	public Staff getStaff(String id) {
		return staffService.get(id);
	}

	public Staff getStaffByUserName(String userName) {
		return staffService.getStaffByUserName(userName);
	}


	public Staff createStaff(Staff staff) {
		return staffService.create(staff, staff.getId());
	}

	public Staff updateStaff(Staff staff, String id) {
		return staffService.update(staff, id);
	}

	public void deleteStaff(String id) {
		staffService.delete(id);
	}


}
