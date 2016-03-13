package org.openams.rest.facade;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.NotImplementedException;
import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.Student;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.service.BaseService;
import org.openams.rest.service.RoleService;
import org.openams.rest.service.StaffService;
import org.openams.rest.service.StudentService;
import org.openams.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO:Revisit Generic Handling CRUD Methods & serviceRegistry
@Component
public class ServiceFacade {

	private UserService userService;
	private StaffService staffService;
	private StudentService studentService;
	private RoleService roleService;
	private Map<Class<?>,BaseService<?,?>> serviceRegistry;

	@Autowired
	public ServiceFacade(UserService userService,StaffService staffService,StudentService studentService,RoleService roleService) {
		this.userService = userService;
		this.staffService = staffService;
		this.studentService = studentService;
		this.roleService = roleService;

		serviceRegistry = new HashMap<Class<?>, BaseService<?,?>>();
		serviceRegistry.put(User.class, userService);
		serviceRegistry.put(Staff.class, staffService);
		serviceRegistry.put(Student.class, studentService);
		serviceRegistry.put(Role.class, roleService);
	}

	private <T> BaseService<T,?> getService(Class<T> modelType){
		return (BaseService<T, ?>) Optional.ofNullable(serviceRegistry.get(modelType)).orElseThrow(() ->  new NotImplementedException("No Service Avaible For the modelType"));
	}

	public <T> Collection<T> getAll(Class<T> modelType){
		return getService(modelType).getAll();
	}

	public <T,K> T get(Class<T> modelType, K k){
		return ((BaseService<T, K>)getService(modelType)).get(k);
	}

	public <T> T create(Class<T> modelType, T t){
		return getService(modelType).create(t);
	}

	public <T> T update(Class<T> modelType, T t){
		return getService(modelType).update(t);
	}

	public <T,K> void delete(Class<T> modelType, K k){
		((BaseService<T, K>)getService(modelType)).delete(k);
	}

	public User createUser(User user) {
		Optional.ofNullable(user.getRoles()).map(roles -> areValidRoles(roles)).ifPresent(areValidRoles -> {
			if(!areValidRoles) throw new EntityNotFoundException();
		});
		return userService.create(user);
	}

	public User updateUser(User user) {
		Optional.ofNullable(user.getRoles()).map(roles -> areValidRoles(roles)).ifPresent(areValidRoles -> {
			if(!areValidRoles) throw new EntityNotFoundException();
		});
		return userService.update(user);
	}

	public boolean areValidRoles(Collection<Role> roles){
		Collection<Integer> availbleRoleIds = getAll(Role.class).stream().map(Role :: getId).collect(Collectors.toList());
		return availbleRoleIds.containsAll(roles.stream().map(Role :: getId).collect(Collectors.toList()));
	}

	public void updatePassword(String userName, String newPassword) {
		userService.updatePassword(userName, newPassword);
	}


}
