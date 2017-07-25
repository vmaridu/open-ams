package org.openams.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.Page;
import org.openams.rest.model.TestModel;
import org.openams.rest.model.TestScoreModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.service.impl.TestService;
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

@Api(value = "Test Controller", description = "Allows CRUD on Test & TestScores")
@RestController
@RequestMapping("/tests")
public class TestController {

	private final TestService service;

	@Autowired
	public TestController(TestService service) {
		this.service = service;
	}

	@ApiOperation(value = "Gets Test Details by Filter ; Allowed Roles [ADMIN|STAFF]")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", paramType = "query"),
			@ApiImplicitParam(name = "limit", paramType = "query"),
			@ApiImplicitParam(name = "sort", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<TestModel> getByFilter(@RequestParam(value = "filter", required = false) String filter,
			Pageable pageable) throws QueryParserException {
		if (StringUtils.isBlank(filter)) {
			return service.getTests(pageable);
		} else {
			return service.getTests(pageable, filter);
		}
	}

	@ApiOperation(value = "Gets Test Filter Config ; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/filter-config", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFilterConfig() {
		return service.getFilterConfig();
	}

	@ApiOperation(value = "Gets Test By ID ; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public TestModel getTest(@PathVariable("id") String id,
			@RequestParam(value = "expand", defaultValue = "false") boolean expand) {
		return service.getTest(id, expand);
	}

	@ApiOperation(value = "Creates Test; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTest(@RequestBody @Valid TestModel test, HttpServletResponse response) {
		TestModel createdTest = service.createTest(test);
		response.setHeader("Location", "/tests/" + createdTest.getId());
	}
	
	@ApiOperation(value = "Creates Test Score; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/{testId}/test-scores", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createTestScore(@PathVariable("testId") String testId, @RequestBody @Valid TestScoreModel testScore, HttpServletResponse response) {
		testScore.setTestId(testId);
		TestScoreModel createdTestScore = service.createTestScore(testScore);
		response.setHeader("Location", "/tests/" + testId + "/test-scores/" + createdTestScore.getId());
	}

	@ApiOperation(value = "Updates Test; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTest(@PathVariable("id") String id, @RequestBody @Valid TestModel test) {
		test.setId(id);
		service.updateTest(test);
	}
	
	@ApiOperation(value = "Updates Test Score; Allowed Roles [ADMIN|STAFF]")
	@RequestMapping(value = "/{testId}/test-scores/{testScoreId}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTestScore(@PathVariable("testId") String testId, @PathVariable("testScoreId") String testScoreId,
			@RequestBody @Valid TestScoreModel testScore, HttpServletResponse response) {
		testScore.setId(testScoreId);
		testScore.setTestId(testId);
		service.updateTestScore(testScore);
	}

	@ApiOperation(value = "Deletes Test; Allowed Roles [ADMIN]")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTest(@PathVariable("id") String id) {
		service.deleteTest(id);
	}

}
