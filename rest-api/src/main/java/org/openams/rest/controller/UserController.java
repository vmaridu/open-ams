package org.openams.rest.controller;

import static org.openams.rest.utils.Constants.DEFAULT_LIMIT;
import static org.openams.rest.utils.Constants.DEFAULT_PAGE;

import javax.validation.Valid;

import org.openams.rest.model.Page;
import org.openams.rest.model.PasswordModel;
import org.openams.rest.model.UserModel;
import org.openams.rest.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation(value = "Gets Paginated Collection of All Users ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<UserModel> getAll(@RequestParam(name = "page", required = false , defaultValue = DEFAULT_PAGE) int pageIndex,
    							  @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_LIMIT) int limit ) {
        return service.getAll(pageIndex, limit);
    }
    
    @ApiOperation(value = "Change PASSWORD ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}/password", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void chnagePassword(@PathVariable("userName") String userName, @RequestBody @Valid PasswordModel password) {
    	service.changePassword(userName, password.getOldPassword(), password.getNewPassword());
    }
    
	@ApiOperation(value = "Gets User Account by UserName ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserModel getByUserName(@PathVariable("userName") String userName) {
        return service.get(userName);
    }

}
