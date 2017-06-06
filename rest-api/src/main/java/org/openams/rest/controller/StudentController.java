package org.openams.rest.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Student Controller", description = "Allows CRUD,Change Password Operations on Student Account")
@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @ApiOperation(value = "Gets Student Details by Filter ; Allowed Roles [ADMIN]")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query"),
        @ApiImplicitParam(name = "limit", paramType = "query"),
        @ApiImplicitParam(name = "sort", paramType = "query")
     })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentModel> getByFilter(@RequestParam(value = "filter", required = false) String filter, 
    		Pageable pageable) throws QueryParserException {
    	if(StringUtils.isBlank(filter)){
    		return service.getStudents(pageable);
    	}else{
    		return service.getStudents(pageable, filter);
    	}
    }
    
    @ApiOperation(value = "Gets Student Filter Config ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.GET, path = "/filter-config")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getFilterConfig(){
    	return service.getFilterConfig();
    }
    

}
