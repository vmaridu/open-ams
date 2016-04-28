package org.openams.rest.controller;

import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//TODO:ADMIN|ANY-SELF should be able to access Get and Update
@Api(value = "Profile Controller", description = "Allows Update Operation on Profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ServiceFacade facade;

    @Autowired
    public ProfileController(ServiceFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "Updates Person Profile (Preserves associated User Accoount) ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProfile(@RequestBody Person person,@PathVariable("userName") String id) {
    	person.setId(id);
        facade.updateProfileWithUserAccountPreservation(person);
    }

}
