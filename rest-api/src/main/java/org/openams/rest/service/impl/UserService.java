package org.openams.rest.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService   {

	private RepositoryWrapper<User,String> repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = new RepositoryWrapper<User,String>(repository, (User :: getUserName));
	}

	public UserModel get(String userName) {
		User user = repository.get(userName);
		UserModel userModel = new UserModel();
		userModel.setUserName(user.getUserName());
		userModel.setStatus(user.getStatus());
		userModel.setEmail(user.getEMail());
		userModel.setRoles(user.getRoles().stream().map(Role :: getName).collect(Collectors.toList()));
		return userModel;
	}



}
