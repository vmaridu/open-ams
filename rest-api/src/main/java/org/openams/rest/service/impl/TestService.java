package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.Test;
import org.openams.rest.jpa.entity.TestScore;
import org.openams.rest.jpa.repository.TestRepository;
import org.openams.rest.jpa.repository.TestScoreRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.Page;
import org.openams.rest.model.TestModel;
import org.openams.rest.model.TestScoreModel;
import org.openams.rest.queryparser.TestQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
	
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
	
	public Page<TestModel> getTests(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get Tests Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Test> tests = testRepository.findAll(pageRequest);
		Function<Test, TestModel> contentMapper = (test) -> mapper.map(test, TestModel.class);
		Page<TestModel> result = PaginationUtil.toPageModel(tests, pageRequest, contentMapper);
		LOGGER.info("Processed Get Tests Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<TestModel> getTests(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get Tests Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Test> tests = testRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Test, TestModel> contentMapper = (test) -> mapper.map(test, TestModel.class);
		Page<TestModel> result = PaginationUtil.toPageModel(tests, pageRequest, contentMapper);
		LOGGER.info("Processed Get Tests Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig(){
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public TestModel getTest(String id, boolean expand) throws ApplicationException {
		LOGGER.info("Processing Get Test By ID Request ...; TX_ID ({}) ID ({}) expand ({})", getTxId(), id, expand);
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
		LOGGER.info("Processed Get Test By ID Request; TX_ID ({}) ID ({}) expand ({})", getTxId(), id, expand);
		return testModel;
	}
	
	public TestModel createTest(TestModel testModel) throws ApplicationException {
		LOGGER.info("Processing Create Test Request ...; TX_ID ({})", getTxId());
		Test test = mapper.map(testModel, Test.class);
		testRepository.create(test);
		TestModel createdTestModel = mapper.map(test, TestModel.class);
		LOGGER.info("Processed Create Test Request; TX_ID ({}) createdID ({})", getTxId(), testModel.getId());
		return createdTestModel;
	}
	
	public TestScoreModel createTestScore(TestScoreModel testScoreModel) throws ApplicationException {
		LOGGER.info("Processing Create TestScore Request ...; TX_ID ({}) testId ({})", getTxId(), testScoreModel.getTestId());
		TestScore testScore = mapper.map(testScoreModel, TestScore.class);
		testScoreRepository.create(testScore);
		TestScoreModel createdTestScoreModel =  mapper.map(testScore, TestScoreModel.class);
		LOGGER.info("Processed Create TestScore Request; TX_ID ({}) testId ({}) createdID ({})", getTxId(), testScoreModel.getTestId(), testScoreModel.getId());
		return createdTestScoreModel;
	}
	
	public void updateTest(TestModel testModel) throws ApplicationException {
		LOGGER.info("Processing Update Test Request ...; TX_ID ({}) ID ({})", getTxId(), testModel.getId());
		Test exisitngTest = testRepository.findOne(testModel.getId());
		Test test = mapper.map(testModel, Test.class);
		test.setTestScores(exisitngTest.getTestScores());
		testRepository.update(test);
		LOGGER.info("Processed Update Test Request; TX_ID ({}) ID ({})", getTxId(), testModel.getId());
	}
	
	public void updateTestScore(TestScoreModel testScoreModel) throws ApplicationException {
		LOGGER.info("Processing Update TestScore Request ...; TX_ID ({}) feeId ({}) ID ({})", getTxId(), testScoreModel.getTestId(), testScoreModel.getId());
		TestScore testScore = mapper.map(testScoreModel, TestScore.class);
		testScoreRepository.update(testScore);
		LOGGER.info("Processed Update TestScore Request; TX_ID ({}) feeId ({}) ID ({})", getTxId(), testScoreModel.getTestId(), testScoreModel.getId());
	}
	
	public void deleteTest(String id) throws ApplicationException {
		LOGGER.info("Processing Delete Test Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		testRepository.delete(id);
		LOGGER.info("Processed Delete Test Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
