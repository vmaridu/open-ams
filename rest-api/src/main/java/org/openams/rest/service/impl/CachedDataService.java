package org.openams.rest.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.HashBiMap;

@Service
public class CachedDataService {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public CachedDataService(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
	
	@Transactional
	@Cacheable(value = "expirableCache", key = "'roles'")
	public HashBiMap<String,String> getRoleNametoIdMap(){
		return HashBiMap.create(roleRepository.findAll().stream().collect(Collectors.toMap(Role :: getName, Role :: getId)));
	}
}
