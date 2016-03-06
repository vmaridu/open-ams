package org.openams.rest.service.impl;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.repository.StaffRepository;
import org.openams.rest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {

	private final StaffRepository repository;

	@Autowired
	public StaffServiceImpl(StaffRepository repository) {
		this.repository = repository;
	}

	@Override
	public Collection<Staff> getStaffs() {
		Collection<Staff> staffs = repository.findAll();
		if(staffs.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return staffs;
	}

	@Override
	public Staff getStaff(String id) {
		Staff staff = Optional.ofNullable(repository.findOne(id))
				.orElseThrow(() -> new EntityNotFoundException());
		return staff;
	}

	@Override
	public Staff getStaffByUserName(String userName) {
		Staff staff = repository.findByUserName(userName).stream()
				.findFirst().orElseThrow(() -> new EntityNotFoundException());
		return staff;
	}

}
