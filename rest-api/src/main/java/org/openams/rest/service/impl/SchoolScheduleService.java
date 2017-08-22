package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.SchoolSchedule;
import org.openams.rest.jpa.repository.SchoolScheduleRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.SchoolScheduleModel;
import org.openams.rest.queryparser.SchoolScheduleQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SchoolScheduleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolScheduleService.class);
	
	private RepositoryWrapper<SchoolSchedule, String> schoolScheduleRepository;
	private Mapper mapper;
	private SchoolScheduleQueryParser parser;

	@Autowired
	public SchoolScheduleService(SchoolScheduleRepository schoolScheduleRepository, Mapper mapper, SchoolScheduleQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.schoolScheduleRepository = new RepositoryWrapper<SchoolSchedule, String>(schoolScheduleRepository, (SchoolSchedule::getId));
	}
	
	public Page<SchoolScheduleModel> getSchoolSchedules(Pageable pageRequest)  throws ApplicationException {
		LOGGER.info("Processing Get SchoolSchedules Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<SchoolSchedule> schoolSchedules = schoolScheduleRepository.findAll(pageRequest);
		Function<SchoolSchedule, SchoolScheduleModel> contentMapper = (schoolSchedule) -> mapper.map(schoolSchedule, SchoolScheduleModel.class);
		Page<SchoolScheduleModel> result = PaginationUtil.toPageModel(schoolSchedules, pageRequest, contentMapper);
		LOGGER.info("Processed Get SchoolSchedules Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<SchoolScheduleModel> getSchoolSchedules(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get SchoolSchedules Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<SchoolSchedule> schoolSchedules = schoolScheduleRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<SchoolSchedule, SchoolScheduleModel> contentMapper = (schoolSchedule) -> mapper.map(schoolSchedule, SchoolScheduleModel.class);
		Page<SchoolScheduleModel> result = PaginationUtil.toPageModel(schoolSchedules, pageRequest, contentMapper);
		LOGGER.info("Processed Get SchoolSchedules Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public SchoolScheduleModel getSchoolSchedule(String id) throws ApplicationException {
		LOGGER.info("Processing Get SchoolSchedule By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		SchoolSchedule schoolSchedule = schoolScheduleRepository.findOne(id);
		SchoolScheduleModel schoolScheduleModel = mapper.map(schoolSchedule, SchoolScheduleModel.class);
		LOGGER.info("Processed Get SchoolSchedule By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return schoolScheduleModel;
	}
	
	public SchoolScheduleModel createSchoolSchedule(SchoolScheduleModel schoolScheduleModel) throws ApplicationException {
		LOGGER.info("Processing Create SchoolSchedule Request ...; TX_ID ({})", getTxId());
		SchoolSchedule schoolSchedule = mapper.map(schoolScheduleModel, SchoolSchedule.class);
		schoolScheduleRepository.create(schoolSchedule);
		SchoolScheduleModel createdSchoolScheduleModel = mapper.map(schoolSchedule, SchoolScheduleModel.class);
		LOGGER.info("Processed Create SchoolSchedule Request; TX_ID ({}) createdID ({})", getTxId(), createdSchoolScheduleModel.getId());
		return createdSchoolScheduleModel;
	}
	
	public void updateSchoolSchedule(SchoolScheduleModel schoolScheduleModel) throws ApplicationException {
		LOGGER.info("Processing Update SchoolSchedule Request ...; TX_ID ({}) ID ({})", getTxId(), schoolScheduleModel.getId());
		SchoolSchedule schoolSchedule = mapper.map(schoolScheduleModel, SchoolSchedule.class);
		schoolScheduleRepository.update(schoolSchedule);
		LOGGER.info("Processed Update SchoolSchedule Request; TX_ID ({}) ID ({})", getTxId(), schoolScheduleModel.getId());
	}
	
	public void deleteSchoolSchedule(String id) throws ApplicationException {
		LOGGER.info("Processing Delete SchoolSchedule Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		schoolScheduleRepository.delete(id);
		LOGGER.info("Processed Delete SchoolSchedule Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
