package org.openams.rest.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.repository.StaffRepository;
import org.openams.rest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff,String> implements StaffService {

	private StaffRepository repository;

	@Autowired
	public StaffServiceImpl(StaffRepository repository) {
		this.repository = repository;
	}

	@Override
	public JpaRepository<Staff, String> getRepository() {
		return repository;
	}

	@Override
	public Staff getStaffByUserName(String userName) {
		Staff staff = repository.findByUserName(userName).stream()
				.findFirst().orElseThrow(() -> new EntityNotFoundException());
		return staff;
	}



}
