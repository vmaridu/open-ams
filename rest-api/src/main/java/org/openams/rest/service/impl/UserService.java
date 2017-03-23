package org.openams.rest.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
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
	private Mapper mapper;

	@Autowired
	public UserService(UserRepository repository, Mapper mapper) {
		this.mapper = mapper;
		this.repository = new RepositoryWrapper<User,String>(repository, (User :: getUserName));
	}

	public UserModel get(String userName) {
		User user = repository.get(userName);
		UserModel userModel = mapper.map(user, UserModel.class);
		userModel.setRoles(user.getRoles().stream().map(Role :: getName).collect(Collectors.toList()));
		return userModel;
	}
}
