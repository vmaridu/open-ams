package org.openams.rest.controller;

import org.openams.rest.exception.ApplicationException;
import org.openams.rest.model.AttendanceByModel;
import org.openams.rest.model.EnrollmentAttendanceReportModel;
import org.openams.rest.service.impl.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Attendance Controller", description = "Attendance Bulk Submit and Reports")
@RestController
@RequestMapping("/attendances")
public class AttendanceController {

	private final AttendanceService service;

    @Autowired
    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @ApiOperation(value = "Bulk Submits Attendance ; Allowed Roles [ADMIN|STAFF]")
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bulkSubmit(@RequestBody AttendanceByModel bulkAttendance) throws ApplicationException{
    	service.submitBulkAttendance(bulkAttendance);
    }
    
    

}
