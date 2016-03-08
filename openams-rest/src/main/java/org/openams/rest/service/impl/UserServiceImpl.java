package org.openams.rest.service.impl;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public JpaRepository<User, String> getRepository() {
		return repository;
	}

	@Override
	public User create(User user, String userName) {
		if (StringUtils.isNotBlank(userName) && repository.exists(userName)) {
			throw new EntityExistsException();
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return repository.save(user);
	}


	@Override
	public User update(User user, String userName) {
		if (StringUtils.isNotBlank(userName) && !repository.exists(userName)) {
			throw new EntityNotFoundException();
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public User updatePassword(String userName, String newPassword) {
		User user = get(userName);
		user.setPassword(newPassword);
		return update(user,user.getUserName());
	}



}
