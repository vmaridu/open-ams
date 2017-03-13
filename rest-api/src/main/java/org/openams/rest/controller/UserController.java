package org.openams.rest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.openams.rest.facade.ServiceFacade;
import org.openams.rest.jpa.entity.User;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//TODO:Only ADMIN should be able to access Create,Update,Delete,getAll
//TODO:Self User should be able to access get,changePassword Only
@Api(value = "User Controller", description = "Allows CRUD,Change Password Operations on User Account")
@RestController
@RequestMapping("/users")
public class UserController {

    private final ServiceFacade facade;

    @Autowired
    public UserController(ServiceFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "Creates User Account ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        facade.createUser(user);
        response.setHeader("Location", uriBuilder.path("/user/{id}") .buildAndExpand(user.getUserName()).toUriString());
    }

    @ApiOperation(value = "Gets User Account by UserName ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getByUserName(@PathVariable("userName") String userName) {
        return PresentationUtil.getPresentableUser(facade.get(User.class,userName));
    }

    @ApiOperation(value = "Gets All User Accounts ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> getAll() {
        return PresentationUtil.getPresentableUsers(facade.getAll(User.class));
    }

    @ApiOperation(value = "Updates User Account ; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@RequestBody User user,@PathVariable("userName") String userName) {
        user.setUserName(userName);
        facade.updateUser(user);
    }

    @ApiOperation(value = "Changes Users Password ; Allowed Roles [ANY-SELF]")
    @RequestMapping(value = "/{userName}/password", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@PathVariable("userName") String userName, @RequestBody String newPassword) {
        facade.updatePassword(userName, newPassword);
    }

    @ApiOperation(value = "Deletes User Account by UserName ; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("userName") String userName) {
        facade.delete(User.class,userName);
    }

}
