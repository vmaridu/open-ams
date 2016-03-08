package org.openams.rest.service;

import org.openams.rest.jpa.entity.User;


public interface UserService extends BaseService<User, String>{

	public User updatePassword(String userName,String newPassword);

}
