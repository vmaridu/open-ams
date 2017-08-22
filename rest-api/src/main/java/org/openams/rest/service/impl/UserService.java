package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.ProvisionableUserModel;
import org.openams.rest.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService   {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
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
	
	public UserModel get(String userName) throws ApplicationException {
		LOGGER.info("Processing Get User By userName Request ...; TX_ID ({}) userName ({})", getTxId(), userName);
		User user = repository.findOne(userName);
		UserModel userModel = mapper.map(user, UserModel.class);
		userModel.setRoles(user.getRoles().stream().map(Role :: getName).collect(Collectors.toList()));
		LOGGER.info("Processed Get User By userName Request; TX_ID ({}) userName ({})", getTxId(), userName);
		return userModel;
	}
	
	public void changePassword(String userName, String oldPassword, String newPassword) throws ApplicationException {
		LOGGER.info("Processing changePassword Request ...; TX_ID ({}) userName ({})", getTxId(), userName);
		User user = repository.findOne(userName);
		
		if (!encoder.matches(oldPassword, user.getPassword())){
			throw new AccessDeniedException("Old Password doesn't match");
		}
		
		user.setPassword(encoder.encode(newPassword));
		repository.update(user);
		LOGGER.info("Processed changePassword Request; TX_ID ({}) userName ({})", getTxId(), userName);
	}
	
	public void create(ProvisionableUserModel userModel) throws ApplicationException {
		LOGGER.info("Processing Create User Request ...; TX_ID ({}) userName ({}) roles ({})", getTxId(), userModel.getUserName(), userModel.getRoles());
		repository.create(toUser(userModel));
		LOGGER.info("Processed Create User Request; TX_ID ({}) userName ({}) roles ({})", getTxId(), userModel.getUserName(), userModel.getRoles());
	}
	
	public void update(ProvisionableUserModel userModel) throws ApplicationException {
		LOGGER.info("Processing Update User Request ...; TX_ID ({}) userName ({}) roles ({})", getTxId(), userModel.getUserName(), userModel.getRoles());
		repository.update(toUser(userModel));
		LOGGER.info("Processed Update User Request; TX_ID ({}) userName ({}) roles ({})", getTxId(), userModel.getUserName(), userModel.getRoles());
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
