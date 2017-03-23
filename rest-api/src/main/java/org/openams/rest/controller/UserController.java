package org.openams.rest.controller;

import org.openams.rest.jpa.entity.User;
import org.openams.rest.model.UserModel;
import org.openams.rest.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//TODO:Only ADMIN should be able to access Create,Update,Delete,getAll
//TODO:Self User should be able to access get,changePassword Only
@Api(value = "User Controller", description = "Allows CRUD,Change Password Operations on User Account")
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    
	@ApiOperation(value = "Gets User Account by UserName ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserModel getByUserName(@PathVariable("userName") String userName) {
        return service.get(userName);
    }

	
}
