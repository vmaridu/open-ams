package org.openams.rest.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffService extends BaseService<Staff,String> {

	private StaffRepository repository;

	@Autowired
	public StaffService(StaffRepository repository) {
		super(repository,Staff::getId);
		this.repository = repository;
	}

	public Staff getStaffByUserName(String userName) {
		Staff staff = repository.findByUserName(userName).stream().findFirst().orElseThrow(() -> new EntityNotFoundException());
		return staff;
	}


}
