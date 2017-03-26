package org.openams.rest.controller;

import java.util.Collection;

import org.openams.rest.service.impl.CachedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Role Controller", description = "Allows Read Operations on Role Resources")
@RestController
@RequestMapping("/roles")
public class RoleController {

	private final CachedDataService service;

    @Autowired
    public RoleController(CachedDataService service) {
        this.service = service;
    }

    @ApiOperation(value = "Gets All available Roles ; Allowed Roles [ADMIN]")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Collection<String> getAll() {
        return service.getRoleNametoIdMap().keySet();
    }
    

}
