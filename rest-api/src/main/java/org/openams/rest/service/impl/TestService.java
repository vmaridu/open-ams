package org.openams.rest.service.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Test;
import org.openams.rest.jpa.entity.TestScore;
import org.openams.rest.jpa.repository.TestRepository;
import org.openams.rest.jpa.repository.TestScoreRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.TestModel;
import org.openams.rest.model.TestScoreModel;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.queryparser.TestQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TestService {

	private RepositoryWrapper<Test, String> testRepository;
	private RepositoryWrapper<TestScore, String> testScoreRepository;
	private Mapper mapper;
	private TestQueryParser parser;

	@Autowired
	public TestService(TestRepository testRepository,TestScoreRepository testScoreRepository, Mapper mapper, TestQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.testRepository = new RepositoryWrapper<Test, String>(testRepository, (Test::getId));
		this.testScoreRepository = new RepositoryWrapper<TestScore, String>(testScoreRepository, (TestScore::getId));
	}
	
	public Page<TestModel> getTests(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<Test> tests = testRepository.findAll(pageRequest);
		Function<Test, TestModel> contentMapper = (test) -> mapper.map(test, TestModel.class);
		return PaginationUtil.toPageModel(tests, pageRequest, contentMapper);
	}

	public Page<TestModel> getTests(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Test> tests = testRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Test, TestModel> contentMapper = (test) -> mapper.map(test, TestModel.class);
		return PaginationUtil.toPageModel(tests, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public TestModel getTest(String id, boolean expand) {
		Test test = testRepository.findOne(id);
		TestModel testModel = mapper.map(test, TestModel.class);
		if(expand){
			testModel.setTestScores(new LinkedHashSet<>());
			if(test.getTestScores() != null){
				test.getTestScores().stream().forEach(ts -> {
					TestScoreModel testScoreModel = mapper.map(ts, TestScoreModel.class, "expandTestScore");
					testScoreModel.setStudentInfo(Optional.ofNullable(ts.getPerson().getFName()).orElse("") 
												+ ";" + Optional.ofNullable(ts.getPerson().getMName() ).orElse("") 
												+ ";" + Optional.ofNullable(ts.getPerson().getLName()).orElse("") );
					testModel.getTestScores().add(testScoreModel);
				});
			}
		}
		return testModel;
	}
	
	public TestModel createTest(TestModel testModel){
		Test test = mapper.map(testModel, Test.class);
		testRepository.create(test);
		TestModel createdTestModel = mapper.map(test, TestModel.class);
		return createdTestModel;
	}
	
	public TestScoreModel createTestScore(TestScoreModel testScoreModel){
		TestScore testScore = mapper.map(testScoreModel, TestScore.class);
		testScoreRepository.create(testScore);
		TestScoreModel createdTestScoreModel =  mapper.map(testScore, TestScoreModel.class);
		return createdTestScoreModel;
	}
	
	public void updateTest(TestModel testModel){
		Test exisitngTest = testRepository.findOne(testModel.getId());
		Test test = mapper.map(testModel, Test.class);
		test.setTestScores(exisitngTest.getTestScores());
		testRepository.update(test);
	}
	
	public void updateTestScore(TestScoreModel testScoreModel){
		TestScore testScore = mapper.map(testScoreModel, TestScore.class);
		testScoreRepository.update(testScore);
	}
	
	public void deleteTest(String id){
		testRepository.delete(id);
	}
	
}
