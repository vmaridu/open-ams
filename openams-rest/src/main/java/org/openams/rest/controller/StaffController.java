
package org.openams.rest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Staff;
import org.openams.rest.utils.PresentationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//TODO:Only ADMIN should be able to access Create,Update,Delete,getAll
//TODO:Self User should be able to access get
@Api(value = "Staff Controller", description = "Allows CRUD Operations on Staff User Account")
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final ServiceFacade facade;

    @Autowired
    public StaffController(ServiceFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "Creates Staff Profile ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Staff staff, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        facade.create(Staff.class,staff);
        response.setHeader("Location", uriBuilder.path("/staff/{id}") .buildAndExpand(staff.getId()).toUriString());
    }

    @ApiOperation(value = "Gets All Staff Profiles ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Collection<Staff> get() {
        return PresentationUtil.getPresentableSatffs(facade.getAll(Staff.class));
    }

    @ApiOperation(value = "Gets Staff Profile by Staff ID ; Allowed Roles [ADMIN|STAFF-SELF]")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Staff getById(@PathVariable("id") String id) {
        return PresentationUtil.getPresentableSatff(facade.get(Staff.class,id));
    }

    @ApiOperation(value = "Updates Staff Profile; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody Staff staff, @PathVariable("id") String id) {
        staff.setId(id);
        facade.update(Staff.class,staff);
    }

    @ApiOperation(value = "Deletes Staff Profile by Staff ID ; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") String id) {
        facade.delete(Staff.class,id);
    }

}
