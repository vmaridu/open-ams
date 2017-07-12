package org.openams.rest.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.openams.rest.model.Page;
import org.openams.rest.model.PasswordModel;
import org.openams.rest.model.ProvisionableUserModel;
import org.openams.rest.model.UserModel;
import org.openams.rest.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "User Controller", description = "Allows CRUD,Change Password Operations on Users")
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Gets Paginated Collection of All Users ; Allowed Roles [ADMIN]")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query"),
        @ApiImplicitParam(name = "limit", paramType = "query"),
        @ApiImplicitParam(name = "sort", paramType = "query")
     })
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<UserModel> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }
    
	@ApiOperation(value = "Gets User Account by UserName ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserModel getByUserName(@PathVariable("userName") String userName) {
        return service.get(userName);
    }
	
    @ApiOperation(value = "Change PASSWORD ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(value = "/{userName}/password", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void chnagePassword(@PathVariable("userName") String userName, @RequestBody @Valid PasswordModel password) {
    	service.changePassword(userName, password.getOldPassword(), password.getNewPassword());
    }
    
    @ApiOperation(value = "Creates User; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ProvisionableUserModel user, HttpServletResponse response) {
    	service.create(user);
    	response.setHeader("Location", "/users/"+ user.getUserName());
    }
    
    @ApiOperation(value = "Updates User; Allowed Roles [ADMIN]")
    @RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("userName") String userName, @RequestBody @Valid ProvisionableUserModel user) {
    	user.setUserName(userName);
    	service.update(user);
    }

}
