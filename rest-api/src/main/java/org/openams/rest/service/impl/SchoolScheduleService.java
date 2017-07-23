package org.openams.rest.service.impl;

import java.util.Map;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.SchoolSchedule;
import org.openams.rest.jpa.repository.SchoolScheduleRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.SchoolScheduleModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.queryparser.SchoolScheduleQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SchoolScheduleService {

	private RepositoryWrapper<SchoolSchedule, String> schoolScheduleRepository;
	private Mapper mapper;
	private SchoolScheduleQueryParser parser;

	@Autowired
	public SchoolScheduleService(SchoolScheduleRepository schoolScheduleRepository, Mapper mapper, SchoolScheduleQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.schoolScheduleRepository = new RepositoryWrapper<SchoolSchedule, String>(schoolScheduleRepository, (SchoolSchedule::getId));
	}
	
	public Page<SchoolScheduleModel> getSchoolSchedules(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<SchoolSchedule> schoolSchedules = schoolScheduleRepository.findAll(pageRequest);
		Function<SchoolSchedule, SchoolScheduleModel> contentMapper = (schoolSchedule) -> mapper.map(schoolSchedule, SchoolScheduleModel.class);
		return PaginationUtil.toPageModel(schoolSchedules, pageRequest, contentMapper);
	}

	public Page<SchoolScheduleModel> getSchoolSchedules(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<SchoolSchedule> schoolSchedules = schoolScheduleRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<SchoolSchedule, SchoolScheduleModel> contentMapper = (schoolSchedule) -> mapper.map(schoolSchedule, SchoolScheduleModel.class);
		return PaginationUtil.toPageModel(schoolSchedules, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public SchoolScheduleModel getSchoolSchedule(String id) {
		SchoolSchedule schoolSchedule = schoolScheduleRepository.findOne(id);
		SchoolScheduleModel schoolScheduleModel = mapper.map(schoolSchedule, SchoolScheduleModel.class);
		return schoolScheduleModel;
	}
	
	public SchoolScheduleModel createSchoolSchedule(SchoolScheduleModel schoolScheduleModel){
		SchoolSchedule schoolSchedule = mapper.map(schoolScheduleModel, SchoolSchedule.class);
		schoolScheduleRepository.create(schoolSchedule);
		SchoolScheduleModel createdSchoolScheduleModel = mapper.map(schoolSchedule, SchoolScheduleModel.class);
		return createdSchoolScheduleModel;
	}
	
	public void updateSchoolSchedule(SchoolScheduleModel schoolScheduleModel){
		SchoolSchedule schoolSchedule = mapper.map(schoolScheduleModel, SchoolSchedule.class);
		schoolScheduleRepository.update(schoolSchedule);
	}
	
	public void deleteSchoolSchedule(String id){
		schoolScheduleRepository.delete(id);
	}
	
}
