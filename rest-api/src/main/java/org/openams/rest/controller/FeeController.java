package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.model.FeeModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.PaymentModel;
import org.openams.rest.service.impl.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Fee Controller", description = "Allows CRUD on Fee & Payments")
@RestController
@RequestMapping("/api/fees")
public class FeeController {

	private final FeeService service;

	@Autowired
	public FeeController(FeeService service) {
		this.service = service;
	}

	@ApiOperation(value = "Gets Fee Details by Filter ; Allowed Roles [ADMIN]")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", paramType = "query"),
			@ApiImplicitParam(name = "limit", paramType = "query"),
			@ApiImplicitParam(name = "sort", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<FeeModel> getByFilter(@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable) throws ApplicationException {
		if (StringUtils.isBlank(filter)) {
			return service.getFees(pageable);
		} else {
			return service.getFees(pageable, filter);
		}
	}

	@ApiOperation(value = "Gets Fee Filter Config ; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/filter-config", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFilterConfig() {
		return service.getFilterConfig();
	}

	@ApiOperation(value = "Gets Fee By ID ; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public FeeModel getTest(@PathVariable("id") String id,
			@RequestParam(value = "expand", defaultValue = "false") boolean expand) throws ApplicationException {
		return service.getFee(id, expand);
	}

	@ApiOperation(value = "Creates Test; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTest(@RequestBody @Valid FeeModel fee, HttpServletResponse response) throws ApplicationException {
		FeeModel createdFee = service.createFee(fee);
		response.setHeader("Location", "/api/fees/" + createdFee.getId());
	}
	
	@ApiOperation(value = "Creates Payment; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{feeId}/payments", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTestScore(@PathVariable("feeId") String feeId, @RequestBody @Valid PaymentModel payment, HttpServletResponse response) throws ApplicationException {
		payment.setFeeId(feeId);
		PaymentModel createdPayment = service.createPayment(payment);
		response.setHeader("Location", "/api/fees/" + feeId + "/payments/" + createdPayment.getId());
	}

	@ApiOperation(value = "Updates Fee; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateFee(@PathVariable("id") String id, @RequestBody @Valid FeeModel fee) throws ApplicationException {
		fee.setId(id);
		service.updateFee(fee);
	}
	
	@ApiOperation(value = "Updates Payment; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{feeId}/payments/{paymentId}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTestScore(@PathVariable("feeId") String feeId, @PathVariable("paymentId") String paymentId,
			@RequestBody @Valid PaymentModel payment, HttpServletResponse response) throws ApplicationException {
		payment.setId(paymentId);
		payment.setFeeId(feeId);
		service.updatePayment(payment);
	}

	@ApiOperation(value = "Deletes Fee; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFee(@PathVariable("id") String id) throws ApplicationException {
		service.deleteFee(id);
	}

}
