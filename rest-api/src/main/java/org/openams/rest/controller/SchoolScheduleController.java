package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.Page;
import org.openams.rest.model.SchoolScheduleModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.SchoolScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "SchoolSchedule Controller", description = "Allows CRUD on SchoolSchedule")
@RestController
@RequestMapping("/school-schedules")
public class SchoolScheduleController {

	private final SchoolScheduleService service;

	@Autowired
	public SchoolScheduleController(SchoolScheduleService service) {
		this.service = service;
	}

	@ApiOperation(value = "Gets SchoolSchedule Details by Filter ; Allowed Roles [ADMIN|STAFF]")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", paramType = "query"),
			@ApiImplicitParam(name = "limit", paramType = "query"),
			@ApiImplicitParam(name = "sort", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<SchoolScheduleModel> getByFilter(@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable) throws QueryParserException {
		if (StringUtils.isBlank(filter)) {
			return service.getSchoolSchedules(pageable);
		} else {
			return service.getSchoolSchedules(pageable, filter);
		}
	}

	@ApiOperation(value = "Gets SchoolSchedule Filter Config ; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/filter-config", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFilterConfig() {
		return service.getFilterConfig();
	}

	@ApiOperation(value = "Gets SchoolSchedule By ID ; Allowed Roles [ADMIN|SELF]")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public SchoolScheduleModel getSchoolScheduleModel(@PathVariable("id") String id) {
		return service.getSchoolSchedule(id);
	}

	@ApiOperation(value = "Creates SchoolSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid SchoolScheduleModel schoolSchedule, HttpServletResponse response) {
		SchoolScheduleModel createdSchoolScheduleModel = service.createSchoolSchedule(schoolSchedule);
		response.setHeader("Location", "/school-schedules/" + createdSchoolScheduleModel.getId());
	}

	@ApiOperation(value = "Updates SchoolSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") String id, @RequestBody @Valid SchoolScheduleModel schoolSchedule) {
		schoolSchedule.setId(id);
		service.updateSchoolSchedule(schoolSchedule);
	}

	@ApiOperation(value = "Deletes SchoolSchedule; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		service.deleteSchoolSchedule(id);
	}

}
