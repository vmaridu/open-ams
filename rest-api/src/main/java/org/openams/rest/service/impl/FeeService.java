package org.openams.rest.service.impl;

import static org.openams.rest.utils.LogUtils.getTxId;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.exception.ApplicationException;
import org.openams.rest.jpa.entity.Fee;
import org.openams.rest.jpa.entity.Payment;
import org.openams.rest.jpa.repository.FeeRepository;
import org.openams.rest.jpa.repository.PaymentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.FeeModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.PaymentModel;
import org.openams.rest.queryparser.FeeQueryParser;
import org.openams.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class FeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeeService.class);
	
	private RepositoryWrapper<Fee, String> feeRepository;
	private RepositoryWrapper<Payment, String> paymentRepository;
	private Mapper mapper;
	private FeeQueryParser parser;

	@Autowired
	public FeeService(FeeRepository feeRepository,PaymentRepository paymentRepository, Mapper mapper, FeeQueryParser parser) {
		this.mapper = mapper;
		this.parser = parser;
		this.feeRepository = new RepositoryWrapper<Fee, String>(feeRepository, (Fee::getId));
		this.paymentRepository = new RepositoryWrapper<Payment, String>(paymentRepository, (Payment::getId));
	}
	
	public Page<FeeModel> getFees(Pageable pageRequest) throws ApplicationException {
		LOGGER.info("Processing Get Fees Request ...; TX_ID ({}) pageNumber ({}) pageSize ({})", getTxId(), pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Fee> fees = feeRepository.findAll(pageRequest);
		Function<Fee, FeeModel> contentMapper = (fee) -> mapper.map(fee, FeeModel.class);
		Page<FeeModel> result = PaginationUtil.toPageModel(fees, pageRequest, contentMapper);
		LOGGER.info("Processed Get Fees Request; TX_ID ({}) total ({}) size ({})", getTxId(), result.getTotal(), result.getSize());
		return result;
	}

	public Page<FeeModel> getFees(Pageable pageRequest, String filter) throws ApplicationException {
		LOGGER.info("Processing Get Fees Request ...; TX_ID ({}) filter ({}) pageNumber ({}) pageSize ({})", getTxId(), filter, pageRequest.getPageNumber(), pageRequest.getPageSize());
		org.springframework.data.domain.Page<Fee> fees = feeRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Fee, FeeModel> contentMapper = (fee) -> mapper.map(fee, FeeModel.class);
		Page<FeeModel> result = PaginationUtil.toPageModel(fees, pageRequest, contentMapper);
		LOGGER.info("Processed Get Fees Request; TX_ID ({}) filter ({}) total ({}) size ({})", getTxId(), filter, result.getTotal(), result.getSize());
		return result;
	}
	
	public Map<String,String> getFilterConfig() {
		LOGGER.info("Processing Get FilterConfig Request ...; TX_ID ({})", getTxId());
		Map<String,String> result =  parser.getFilterConfig();
		LOGGER.info("Processed Get FilterConfig Request; TX_ID ({})", getTxId());
		return result;
	}
	
	public FeeModel getFee(String id, boolean expand) throws ApplicationException {
		LOGGER.info("Processing Get Fee By ID Request ...; TX_ID ({}) ID ({}) expand ({})", getTxId(), id, expand);
		Fee fee = feeRepository.findOne(id);
		FeeModel feeModel = mapper.map(fee, FeeModel.class);
		feeModel.setStudentInfo(Optional.ofNullable(fee.getStudent().getFName()).orElse("") 
												+ ";" + Optional.ofNullable(fee.getStudent().getMName() ).orElse("") 
												+ ";" + Optional.ofNullable(fee.getStudent().getLName()).orElse(""));
		if(expand){
			feeModel.setPayments(new LinkedHashSet<>());
			if(fee.getPayments() != null){
				fee.getPayments().stream().forEach(p -> {
					PaymentModel paymentModel = mapper.map(p, PaymentModel.class, "expandPayment");
					feeModel.getPayments().add(paymentModel);
				});
			}
		}
		LOGGER.info("Processed Get Fee By ID Request; TX_ID ({}) ID ({}) expand ({})", getTxId(), id, expand);
		return feeModel;
	}
	
	public FeeModel createFee(FeeModel feeModel) throws ApplicationException {
		LOGGER.info("Processing Create Fee Request ...; TX_ID ({})", getTxId());
		Fee fee = mapper.map(feeModel, Fee.class);
		feeRepository.create(fee);
		FeeModel createdFeeModel = mapper.map(fee, FeeModel.class);
		LOGGER.info("Processed Create Fee Request; TX_ID ({}) createdID ({})", getTxId(), createdFeeModel.getId());
		return createdFeeModel;
	}
	
	public PaymentModel createPayment(PaymentModel paymentModel) throws ApplicationException {
		LOGGER.info("Processing Create Payment Request ...; TX_ID ({}) feeId ({})", getTxId(), paymentModel.getFeeId());
		Payment payment = mapper.map(paymentModel, Payment.class);
		paymentRepository.create(payment);
		PaymentModel createdPaymentModel =  mapper.map(paymentModel, PaymentModel.class);
		LOGGER.info("Processed Create Payment Request; TX_ID ({}) feeId ({}) createdID ({})", getTxId(), paymentModel.getFeeId(), createdPaymentModel.getId());
		return createdPaymentModel;
	}
	
	public void updateFee(FeeModel feeModel) throws ApplicationException {
		LOGGER.info("Processing Update Fee Request ...; TX_ID ({}) ID ({})", getTxId(), feeModel.getId());
		Fee exisitngFee = feeRepository.findOne(feeModel.getId());
		Fee fee = mapper.map(feeModel, Fee.class);
		fee.setPayments(exisitngFee.getPayments());
		feeRepository.update(fee);
		LOGGER.info("Processed Update Fee Request; TX_ID ({}) ID ({})", getTxId(), feeModel.getId());
	}
	
	public void updatePayment(PaymentModel paymentModel) throws ApplicationException {
		LOGGER.info("Processing Update Payment Request ...; TX_ID ({}) feeId ({}) ID ({})", getTxId(), paymentModel.getFeeId(), paymentModel.getId());
		Payment payment = mapper.map(paymentModel, Payment.class);
		paymentRepository.update(payment);
		LOGGER.info("Processed Update Payment Request; TX_ID ({}) feeId ({}) ID ({})", getTxId(), paymentModel.getFeeId(), paymentModel.getId());
	}
	
	public void deleteFee(String id) throws ApplicationException {
		LOGGER.info("Processing Delete Payment Request ...; TX_ID ({}) ID ({})", getTxId(), id);
		feeRepository.delete(id);
		LOGGER.info("Processed Delete Payment Request; TX_ID ({}) ID ({})", getTxId(), id);
	}
	
}
