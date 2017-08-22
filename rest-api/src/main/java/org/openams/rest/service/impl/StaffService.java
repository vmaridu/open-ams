package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.repository.StaffRepository;
import org.openams.rest.jpa.repository.UserRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.StaffModel;
import org.openams.rest.queryparser.StaffQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StaffService.class);
	
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
	
	public Page<StaffModel> getStaff(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get Staff Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Staff> staff = staffRepository.findAll(pageRequest);
		Function<Staff, StaffModel> contentMapper = (staffer) -> mapper.map(staffer, StaffModel.class);
		Page<StaffModel> result = PaginationUtil.toPageModel(staff, pageRequest, contentMapper);
		LOGGER.info("Processed Get Staff Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<StaffModel> getStaff(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get Staff Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Staff> staff = staffRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Staff, StaffModel> contentMapper = (staffer) -> mapper.map(staffer, StaffModel.class);
		Page<StaffModel> result = PaginationUtil.toPageModel(staff, pageRequest, contentMapper);
		LOGGER.info("Processed Get Staff Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public StaffModel getStaff(String id) throws ApplicationException {
		LOGGER.info("Processing Get staff By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		Staff staff = staffRepository.findOne(id);
		StaffModel staffModel = mapper.map(staff, StaffModel.class);
		LOGGER.info("Processed Get staff By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return staffModel;
	}
	
	public StaffModel createStaff(StaffModel staffModel) throws ApplicationException {
		LOGGER.info("Processing Create staff Request ...; TX_ID ({})", getTxId());
		Staff staff = mapper.map(staffModel, Staff.class);
		staffRepository.create(staff);
		StaffModel createdStaffModel = mapper.map(staff, StaffModel.class);
		LOGGER.info("Processed Create staff Request; TX_ID ({}) createdID ({})", getTxId(), staffModel.getId());
		return createdStaffModel;
	}
	
	public void updateStaff(StaffModel staffModel) throws ApplicationException {
		LOGGER.info("Processing Update staff Request ...; TX_ID ({}) ID ({})", getTxId(), staffModel.getId());
		Staff staff = mapper.map(staffModel, Staff.class);
		Staff existingStaff = staffRepository.findOne(staff.getId());
		staff.setUser(existingStaff.getUser());
		staffRepository.update(staff);
		LOGGER.info("Processed Update staff Request; TX_ID ({}) ID ({})", getTxId(), staffModel.getId());
	}
	
	public void deleteStaff(String id) throws ApplicationException {
		LOGGER.info("Processing Delete staff Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		staffRepository.delete(id);
		LOGGER.info("Processed Delete staff Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
	public void linkStaffWithUser(String staffId, String userName) throws ApplicationException {
		LOGGER.info("Processing Link Staff with User Request ...; TX_ID ({}) staffId ({}) userName ({})", getTxId(), staffId, userName);
		Staff staff = staffRepository.findOne(staffId);
		User user = userRepository.findOne(userName);
		staff.setUser(user);
		staffRepository.update(staff);
		LOGGER.info("Processed Link Staff with User Request; TX_ID ({}) staffId ({}) userName ({})", getTxId(), staffId, userName);
	}
	
}
