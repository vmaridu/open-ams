package org.openams.rest.service.impl;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.repository.RoleRepository;
import org.openams.rest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	private RoleRepository repository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super(repository,Role::getId);
		this.repository = repository;
	}



}
