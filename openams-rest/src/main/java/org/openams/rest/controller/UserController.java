package org.openams.rest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthBasic;
import org.jsondoc.core.annotation.ApiMethod;
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

@Api(name = "User Controller", description = "Allows CRUD Operations on User")
@ApiAuthBasic(roles = {"ROLE_ADMIN"})
@RestController
@RequestMapping("/users")
public class UserController {

	private final ServiceFacade facade;

	@Autowired
	public UserController(ServiceFacade facade) {
		this.facade = facade;
	}

	@ApiMethod(description = "Creates User Account")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody User user, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
		facade.createUser(user);
		response.setHeader("Location", uriBuilder.path("/user/{id}") .buildAndExpand(user.getUserName()).toUriString());
	}

	@ApiMethod(description = "Gets User Account by UserName")
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User getByUserName(@PathVariable("userName") String userName) {
		return PresentationUtil.getPresentableUser(facade.getUser(userName));
	}

	@ApiMethod(description = "Gets All User Accounts")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection<User> getAll() {
		return PresentationUtil.getPresentableUsers(facade.getUsers());
	}

	@ApiMethod(description = "Updates User Account")
	@RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@RequestBody User user,@PathVariable("userName") String userName) {
		facade.updateUser(user,userName);
	}

	@ApiMethod(description = "Changes User Password by UserName (Accepts New Password as Body)")
	@RequestMapping(value = "/{userName}/password", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void changePassword(@PathVariable("userName") String userName, @RequestBody String newPassword) {
		facade.updatePassword(userName, newPassword);
	}

	@ApiMethod(description = "Deletes User Account by UserName")
	@RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable("userName") String userName) {
		facade.deleteUser(userName);
	}

}
