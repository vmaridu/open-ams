package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.CourseSchedule;
import org.openams.rest.jpa.repository.CourseScheduleRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.CourseScheduleModel;
import org.openams.rest.model.Page;
import org.openams.rest.queryparser.CourseScheduleQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CourseScheduleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseScheduleService.class);
	
	private RepositoryWrapper<CourseSchedule, String> courseScheduleRepository;
	private Mapper mapper;
	private CourseScheduleQueryParser parser;

	@Autowired
	public CourseScheduleService(CourseScheduleRepository courseScheduleRepository, Mapper mapper, CourseScheduleQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.courseScheduleRepository = new RepositoryWrapper<CourseSchedule, String>(courseScheduleRepository, (CourseSchedule::getId));
	}
	
	public Page<CourseScheduleModel> getCourseSchedules(Pageable pageRequest) throws ApplicationException {
    	LOGGER.info("Processing Get CourseSchedules Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<CourseSchedule> courseSchedules = courseScheduleRepository.findAll(pageRequest);
		Function<CourseSchedule, CourseScheduleModel> contentMapper = (courseSchedule) -> mapper.map(courseSchedule, CourseScheduleModel.class);
		Page<CourseScheduleModel> result = PaginationUtil.toPageModel(courseSchedules, pageRequest, contentMapper);
		LOGGER.info("Processed Get CourseSchedules Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<CourseScheduleModel> getCourseSchedules(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get CourseSchedules Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<CourseSchedule> courseSchedules = courseScheduleRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<CourseSchedule, CourseScheduleModel> contentMapper = (courseSchedule) -> mapper.map(courseSchedule, CourseScheduleModel.class);
		Page<CourseScheduleModel> result = PaginationUtil.toPageModel(courseSchedules, pageRequest, contentMapper);
		LOGGER.info("Processed Get CourseSchedules Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public CourseScheduleModel getCourseSchedule(String id) throws ApplicationException{
		LOGGER.info("Processing Get CourseSchedule By ID Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		CourseSchedule courseSchedule = courseScheduleRepository.findOne(id);
		CourseScheduleModel courseScheduleModel = mapper.map(courseSchedule, CourseScheduleModel.class);
		LOGGER.info("Processed Get CourseSchedule By ID Request; TX_ID ({}) ID ({})", getTxId(), id);
		return courseScheduleModel;
	}
	
	public CourseScheduleModel createCourseSchedule(CourseScheduleModel courseScheduleModel) throws ApplicationException{
		LOGGER.info("Processing Create CourseSchedule Request ...; TX_ID ({})", getTxId());
		CourseSchedule courseSchedule = mapper.map(courseScheduleModel, CourseSchedule.class);
		courseScheduleRepository.create(courseSchedule);
		CourseScheduleModel createdCourseScheduleModel = mapper.map(courseSchedule, CourseScheduleModel.class);
		LOGGER.info("Processed Create CourseSchedule Request; TX_ID ({}) createdID ({})", getTxId(), createdCourseScheduleModel.getId());
		return createdCourseScheduleModel;
	}
	
	public void updateCourseSchedule(CourseScheduleModel courseScheduleModel) throws ApplicationException{
		LOGGER.info("Processing Update CourseSchedule Request ...; TX_ID ({}) ID ({})", getTxId(), courseScheduleModel.getId());
		CourseSchedule courseSchedule = mapper.map(courseScheduleModel, CourseSchedule.class);
		courseScheduleRepository.update(courseSchedule);
		LOGGER.info("Processed Update CourseSchedule Request; TX_ID ({}) ID ({})", getTxId(), courseScheduleModel.getId());
	}
	
	public void deleteCourseSchedule(String id) throws ApplicationException{
		LOGGER.info("Processing Delete CourseSchedule Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		courseScheduleRepository.delete(id);
		LOGGER.info("Processed Delete CourseSchedule Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
