package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.CourseModel;
import org.openams.rest.model.Page;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.CourseService;
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

@Api(value = "Course Controller", description = "Allows CRUD on Course")
@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    
    @ApiOperation(value = "Gets Course Details by Filter ; Allowed Roles [ADMIN|STAFF]")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query"),
        @ApiImplicitParam(name = "limit", paramType = "query"),
        @ApiImplicitParam(name = "sort", paramType = "query")
     })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<CourseModel> getByFilter(@RequestParam(value = "filter", required = false) String filter, 
    		Pageable pageable) throws QueryParserException {
	    	if(StringUtils.isBlank(filter)){
	    		return service.getCourses(pageable);
	    	}else{
	    		return service.getCourses(pageable, filter);
	    	}
    }
    
    @ApiOperation(value = "Gets Course Filter Config ; Allowed Roles [ADMIN|STAFF]")
    @RequestMapping(value = "/filter-config", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getFilterConfig(){
    		return service.getFilterConfig();
    }
    
    @ApiOperation(value = "Gets Course By ID ; Allowed Roles [ADMIN|SELF]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CourseModel getCourse(@PathVariable("id") String id){
    		return service.getCourse(id);
    }
    
    @ApiOperation(value = "Creates Course; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CourseModel course, HttpServletResponse response) {
    		CourseModel createdCourse = service.createCourse(course);
    		response.setHeader("Location", "/courses/"+ createdCourse.getId());
    }
    
    @ApiOperation(value = "Updates Course; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid CourseModel course) {
    		course.setId(id);
    		service.updateCourse(course);
    }
    
    @ApiOperation(value = "Deletes Course; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
    		service.deleteCourse(id);
    }
    

}
