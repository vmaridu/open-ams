package org.openams.rest.controller;

import org.openams.rest.model.StudentModel;
import org.openams.rest.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Student Controller", description = "Allows CRUD,Change Password Operations on Student Account")
@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    
	@ApiOperation(value = "Gets Student Details by Roll Number ; Allowed Roles [ADMIN|ANY-SELF]")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StudentModel getByUserName(@RequestParam("rollNumber") String rollNumber) {
        return service.getStudentByRollNumber(rollNumber);
    }
}
