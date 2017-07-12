package org.openams.rest.service.impl;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.StaffRepository;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.StaffModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.queryparser.StaffQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffService {

	private RepositoryWrapper<Staff, String> staffRepository;
	private RepositoryWrapper<User, String> userRepository;
	private Mapper mapper;
	private StaffQueryParser parser;

	@Autowired
	public StaffService(StaffRepository staffRepository, UserRepository userRepository, Mapper mapper, StaffQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.staffRepository = new RepositoryWrapper<Staff, String>(staffRepository, (Staff::getId));
		this.userRepository  = new RepositoryWrapper<User, String>(userRepository, (User::getUserName));
	}
	
	public Page<StaffModel> getStaff(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<Staff> staff = staffRepository.findAll(pageRequest);
		Function<Staff, StaffModel> contentMapper = (staffer) -> mapper.map(staffer, StaffModel.class);
		return PaginationUtil.toPageModel(staff, pageRequest, contentMapper);
	}

	public Page<StaffModel> getStaff(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Staff> staff = staffRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Staff, StaffModel> contentMapper = (staffer) -> mapper.map(staffer, StaffModel.class);
		return PaginationUtil.toPageModel(staff, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public StaffModel getStaff(String id) {
		Staff staff = staffRepository.findOne(id);
		StaffModel staffModel = mapper.map(staff, StaffModel.class);
		return staffModel;
	}
	
	public StaffModel createStaff(StaffModel staffModel){
		Staff staff = mapper.map(staffModel, Staff.class);
		staffRepository.create(staff);
		StaffModel createdStaffModel = mapper.map(staff, StaffModel.class);
		return createdStaffModel;
	}
	
	public void updateStaff(StaffModel staffModel){
		Staff staff = mapper.map(staffModel, Staff.class);
		Staff existingStaff = staffRepository.findOne(staff.getId());
		staff.setUser(existingStaff.getUser());
		staffRepository.update(staff);
	}
	
	public void deleteStaff(String id){
		staffRepository.delete(id);
	}
	
	public void linkStaffWithUser(String staffId, String userName){
		Staff staff = staffRepository.findOne(staffId);
		User user = userRepository.findOne(userName);
		staff.setUser(user);
		staffRepository.update(staff);
	}
	
}
