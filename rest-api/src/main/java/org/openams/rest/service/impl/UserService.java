package org.openams.rest.service.impl;

import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.UserModel;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	public Page<UserModel> getAll(int pageIndex, int limit) {
		Pageable pageRequest = PaginationUtil.getPageRequest(pageIndex, limit);
		org.springframework.data.domain.Page<User> page = repository.findAll(pageRequest);
		Function<User, UserModel> contentMapper = (user) -> mapper.map(user, UserModel.class);
		return PaginationUtil.toPageModel(page, pageRequest, contentMapper);
	}
	
	public UserModel get(String userName) {
		User user = repository.findOne(userName);
		UserModel userModel = mapper.map(user, UserModel.class);
		userModel.setRoles(user.getRoles().stream().map(Role :: getName).collect(Collectors.toList()));
		return userModel;
	}
	
	public void changePassword(String userName, String oldPassword, String newPassword){
		User user = repository.findOne(userName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if (!encoder.matches(oldPassword, user.getPassword())){
			throw new AccessDeniedException("Old Password doesn't match");
		}
		
		user.setPassword(encoder.encode(newPassword));
		repository.update(user);
	}
}
