package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.Page;
import org.openams.rest.model.StaffModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.StaffService;
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

@Api(value = "Staff Controller", description = "Allows CRUD,Change Password Operations on Staff")
@RestController
@RequestMapping("/staff")
public class StaffController {

	private final StaffService service;

    @Autowired
    public StaffController(StaffService service) {
        this.service = service;
    }

    
    @ApiOperation(value = "Gets Staff Details by Filter ; Allowed Roles [ADMIN]")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query"),
        @ApiImplicitParam(name = "limit", paramType = "query"),
        @ApiImplicitParam(name = "sort", paramType = "query")
     })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<StaffModel> getByFilter(@RequestParam(value = "filter", required = false) String filter, 
    		Pageable pageable) throws QueryParserException {
    	if(StringUtils.isBlank(filter)){
    		return service.getStaff(pageable);
    	}else{
    		return service.getStaff(pageable, filter);
    	}
    }
    
    @ApiOperation(value = "Gets Staff Filter Config ; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/filter-config", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getFilterConfig(){
    	return service.getFilterConfig();
    }
    
    @ApiOperation(value = "Gets Staff By ID ; Allowed Roles [ADMIN|SELF]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StaffModel getStaff(@PathVariable("id") String id){
    	return service.getStaff(id);
    }
    
    @ApiOperation(value = "Creates Staff, Ignores user data; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid StaffModel staff, HttpServletResponse response) {
    	StaffModel createdStaff = service.createStaff(staff);
    	response.setHeader("Location", "/staff/"+ createdStaff.getId());
    }
    
    @ApiOperation(value = "Updates Staff, Ignores user data; Allowed Roles [ADMIN|SELF]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid StaffModel staff) {
    	staff.setId(id);
    	service.updateStaff(staff);
    }
    
    @ApiOperation(value = "Deletes Staff; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
    	service.deleteStaff(id);
    }
    
    @ApiOperation(value = "Links Staff with User Account; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{staffId}/user/{userName:.+}" , method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkStudentWithUser(@PathVariable("staffId") String staffId, @PathVariable("userName") String userName ) {
    	service.linkStaffWithUser(staffId, userName);
    }

}
