package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.InvestManagePlan;
import com.upjf.fund.dto.Payment;
import com.upjf.fund.service.business.InvesterManageService;
import com.upjf.fund.service.impl.business.mapper.InvestPlanManageMapper;
import com.upjf.fund.service.impl.business.mapper.PaymentMapper;

@Service("investerManageService")
public class InvesterManageServiceImpl implements InvesterManageService {
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private InvestPlanManageMapper investPlanManageMapper;

	@Override
	public Payment getPaymentByPrimaryKey(String pid) {
		
		return this.paymentMapper.getPaymentByPrimaryKey(pid);
	}

	@Override
	@Transactional
	public int updatePaymentBySelective(Payment payment) {
		if(payment.getPayAmount()!=null){
			payment.setPayAmount(payment.getPayAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(payment.getReceiverAmount()!=null){
			payment.setReceiverAmount(payment.getReceiverAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		int result = this.paymentMapper.updateByPrimaryKeySelective(payment);
		if(EstateFundConstants.PAYMENT_TYPE_STOCKHOLDER.equals(payment.getPaymentType()) && EstateFundConstants.FINANCE_CONFIRM.equals(payment.getFinanceConfirmStatus())){
			this.investPlanManageMapper.updateReceiveredPrincipal(payment.getPrjId(),payment.getReceiverId(),payment.getContributiveId(),payment.getReceiverAmount());
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> getPaymentsByConditions(Payment payment,Integer offset,Integer pageRows) {
		
		return this.paymentMapper.getPaymentsByConditions(payment,offset,pageRows);
	}

	@Override
	public Integer countPaymentsByConditions(Payment payment) {
		
		return this.paymentMapper.countPaymentsByConditions(payment);
	}

	@Override
	public Map<String, Object> statisticsAmount(String paymentType) {
		
		return this.paymentMapper.statisticsAmount(paymentType);
	}

	@Override
	@Transactional
	public int insertPaymentBySelective(Payment payment) {
		if(payment.getPayAmount()!=null){
			payment.setPayAmount(payment.getPayAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(payment.getReceiverAmount()!=null){
			payment.setReceiverAmount(payment.getReceiverAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return this.paymentMapper.insertSelective(payment);
	}

	@Override
	@Transactional
	public int updatePaymentStatus(String pid, String status) {
		
		return this.paymentMapper.updatePaymentStatus(pid,status);
	}

	@Override
	public List<Map<String, Object>> queryStockPaymentInfoList(Map<String, String> condtions, Integer offset, Integer pageRows) {
		return this.paymentMapper.queryStockPaymentInfoList(condtions,offset,pageRows);
	}

	@Override
	public Integer countStockPaymentInfoList(Map<String, String> condtions) {
		return this.paymentMapper.countStockPaymentInfoList(condtions);
	}

	@Override
	public Map<String, Object> statisticsAmountByCondition(Map<String,String> conditions,String paymentType) {
		return this.paymentMapper.statisticsAmountByCondition(conditions,paymentType);
	}

	@Override
	public List<Map<String, Object>> queryPaymentList(String linkedPrjId) {
		return this.paymentMapper.queryPaymentList(linkedPrjId);
	}

	@Override
	public List<Payment> getPaymentByParams(Payment payment) {
		
		return this.paymentMapper.getPaymentByParams(payment);
	}

	@Override
	public InvestManagePlan getInvestManagePlanByParams(InvestManagePlan investManagePlan) {
		
		List<InvestManagePlan> list = this.investPlanManageMapper.getInvestManagePlanByParams(investManagePlan);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
