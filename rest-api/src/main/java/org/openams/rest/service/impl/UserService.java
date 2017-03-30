package org.openams.rest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.ProvisionableUserModel;
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
	private CachedDataService dataService;
	private Mapper mapper;
	BCryptPasswordEncoder encoder;

	@Autowired
	public UserService(UserRepository repository, CachedDataService dataService, Mapper mapper) {
		this.mapper = mapper;
		this.encoder = new BCryptPasswordEncoder();
		this.repository = new RepositoryWrapper<User,String>(repository, (User :: getUserName));
		this.dataService = dataService;
	}

	public Page<UserModel> getAll(int pageIndex, int limit, String sort) {
		Pageable pageRequest = PaginationUtil.getPageRequest(pageIndex, limit, sort);
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
		
		if (!encoder.matches(oldPassword, user.getPassword())){
			throw new AccessDeniedException("Old Password doesn't match");
		}
		
		user.setPassword(encoder.encode(newPassword));
		repository.update(user);
	}
	
	public void create(ProvisionableUserModel userModel){
		repository.create(toUser(userModel));
	}
	
	public void update(ProvisionableUserModel userModel){
		repository.update(toUser(userModel));
	}
	
	public User toUser(ProvisionableUserModel userModel){
		User user = mapper.map(userModel, User.class);
		user.setPassword(encoder.encode(userModel.getPassword()));
		Map<String,String> roleNametoIdmap = dataService.getRoleNametoIdMap();
		List<Role> roles = userModel.getRoles().stream().filter(roleName -> roleNametoIdmap.containsKey(roleName))
				   							   .map(roleName -> roleNametoIdmap.get(roleName))
				   							   .map(roleId -> new Role(roleId))
				   							   .collect(Collectors.toList());
		user.setRoles(roles);
		user.setLastAccessDtt(new Date());
		user.setCredentialsExpireDtt(null);
		user.setAccountExpireDtt(null);
		return user;
	}
}
