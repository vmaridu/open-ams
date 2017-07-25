package org.openams.rest.service.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.openams.rest.jpa.entity.Fee;
import org.openams.rest.jpa.entity.Payment;
import org.openams.rest.jpa.repository.FeeRepository;
import org.openams.rest.jpa.repository.PaymentRepository;
import org.openams.rest.jpa.repository.custom.impl.RepositoryWrapper;
import org.openams.rest.model.FeeModel;
import org.openams.rest.model.Page;
import org.openams.rest.model.PaymentModel;
import org.openams.rest.queryparser.FeeQueryParser;
import org.openams.rest.queryparser.QueryParserException;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class FeeService {

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
	
	public Page<FeeModel> getFees(Pageable pageRequest) throws QueryParserException {
		org.springframework.data.domain.Page<Fee> fees = feeRepository.findAll(pageRequest);
		Function<Fee, FeeModel> contentMapper = (fee) -> mapper.map(fee, FeeModel.class);
		return PaginationUtil.toPageModel(fees, pageRequest, contentMapper);
	}

	public Page<FeeModel> getFees(Pageable pageRequest, String filter) throws QueryParserException {
		org.springframework.data.domain.Page<Fee> fees = feeRepository.findAll(parser.toPredicate(filter), pageRequest);
		Function<Fee, FeeModel> contentMapper = (fee) -> mapper.map(fee, FeeModel.class);
		return PaginationUtil.toPageModel(fees, pageRequest, contentMapper);
	}
	
	public Map<String,String> getFilterConfig(){
		return parser.getFilterConfig();
	}
	
	public FeeModel getFee(String id, boolean expand) {
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
		return feeModel;
	}
	
	public FeeModel createFee(FeeModel feeModel){
		Fee fee = mapper.map(feeModel, Fee.class);
		feeRepository.create(fee);
		FeeModel createdFeeModel = mapper.map(fee, FeeModel.class);
		return createdFeeModel;
	}
	
	public PaymentModel createPayment(PaymentModel paymentModel){
		Payment payment = mapper.map(paymentModel, Payment.class);
		paymentRepository.create(payment);
		PaymentModel createdPaymentModel =  mapper.map(paymentModel, PaymentModel.class);
		return createdPaymentModel;
	}
	
	public void updateFee(FeeModel feeModel){
		Fee exisitngFee = feeRepository.findOne(feeModel.getId());
		Fee fee = mapper.map(feeModel, Fee.class);
		fee.setPayments(exisitngFee.getPayments());
		feeRepository.update(fee);
	}
	
	public void updatePayment(PaymentModel paymentModel){
		Payment payment = mapper.map(paymentModel, Payment.class);
		paymentRepository.update(payment);
	}
	
	public void deleteFee(String id){
		feeRepository.delete(id);
	}
	
}
