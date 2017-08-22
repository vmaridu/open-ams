package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.model.Page;
import org.openams.rest.model.StudentModel;
import org.openams.rest.service.impl.StudentService;
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

@Api(value = "Student Controller", description = "Allows CRUD Operations on Students")
@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    
    @ApiOperation(value = "Gets Student Details by Filter ; Allowed Roles [ADMIN|STAFF]")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query"),
        @ApiImplicitParam(name = "limit", paramType = "query"),
        @ApiImplicitParam(name = "sort", paramType = "query")
     })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentModel> getStudentsByFilter(@RequestParam(value = "filter", required = false) String filter, 
    		Pageable pageable) throws ApplicationException {
	    	if(StringUtils.isBlank(filter)){
	    		return service.getStudents(pageable);
	    	}else{
	    		return service.getStudents(pageable, filter);
	    	}
    }
    
    @ApiOperation(value = "Gets Student Filter Config ; Allowed Roles [ADMIN|STAFF]")
    @RequestMapping(value = "/filter-config", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getFilterConfig(){
    		return service.getFilterConfig();
    }
    
    @ApiOperation(value = "Gets Student By ID ; Allowed Roles [ADMIN|STAFF|SELF]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StudentModel getStudent(@PathVariable("id") String id) throws ApplicationException {
    		return service.getStudent(id);
    }
    
    @ApiOperation(value = "Creates Student, Ignores user data; Allowed Roles [ANY]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody @Valid StudentModel student, HttpServletResponse response) throws ApplicationException {
    		StudentModel createdStudent = service.createStudent(student);
    		response.setHeader("Location", "/api/students/"+ createdStudent.getId());
    }
    
    @ApiOperation(value = "Updates Student, Ignores user data; Allowed Roles [ADMIN|SELF]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable("id") String id, @RequestBody @Valid StudentModel student) throws ApplicationException {
    		student.setId(id);
    		service.updateStudent(student);
    }
    
    @ApiOperation(value = "Deletes Student; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") String id) throws ApplicationException {
    		service.deleteStudent(id);
    }
    
    @ApiOperation(value = "Links Student with User Account; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{studentId}/user/{userName:.+}" , method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkStudentWithUser(@PathVariable("studentId") String studentId, @PathVariable("userName") String userName) throws ApplicationException {
    		service.linkStudentWithUser(studentId, userName);
    }
    

}
