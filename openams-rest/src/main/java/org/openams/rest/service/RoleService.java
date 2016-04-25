package org.openams.rest.service;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RoleService extends BaseService<Role, Integer>  {

	private RoleRepository repository;

	@Autowired
	public RoleService(RoleRepository repository) {
		super(repository,Role::getId);
		this.repository = repository;
	}



}
