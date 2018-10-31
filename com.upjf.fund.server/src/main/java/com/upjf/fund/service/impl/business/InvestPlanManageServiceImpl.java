package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.InvestManagePlan;
import com.upjf.fund.service.business.InvestPlanManageService;
import com.upjf.fund.service.impl.business.mapper.InvestPlanManageMapper;
import com.upjf.fund.service.impl.business.mapper.InvestSubjectMapper;


@Service("investPlanManageService")
public class InvestPlanManageServiceImpl implements InvestPlanManageService {
	
	@Autowired
	private InvestPlanManageMapper investPlanManageMapper;
	
	@Autowired
	private InvestSubjectMapper investSubjectMapper;

	@Override
	public List<Map<String, Object>> getInvestPlanManageByConditon(
			Map<String, String> condtions, Integer offset, Integer pageRows) {
		return this.investPlanManageMapper.getInvestPlanManageByConditon(condtions,offset,pageRows);
	}

	@Override
	public Integer countInvestPlanManageByConditon(Map<String, String> condtions) {
		return this.investPlanManageMapper.countInvestPlanManageByConditon(condtions);
	}

	@Override
	@Transactional
	public int insertInvestPlanManageByPid(InvestManagePlan investPlanManage) {
		if(investPlanManage.getExpectInvestAmount()!=null){
			investPlanManage.setExpectInvestAmount(investPlanManage.getExpectInvestAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getExpectAllReceiverAccount()!=null){
			investPlanManage.setExpectAllReceiverAccount(investPlanManage.getExpectAllReceiverAccount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getRealityInvestAmount()!=null){
			investPlanManage.setRealityInvestAmount(investPlanManage.getRealityInvestAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReceiveredPrincipal()!=null){
			investPlanManage.setReceiveredPrincipal(investPlanManage.getReceiveredPrincipal().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReceiveredPayback()!=null){
			investPlanManage.setReceiveredPayback(investPlanManage.getReceiveredPayback().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReturnedProfit()!=null){
			investPlanManage.setReturnedProfit(investPlanManage.getReturnedProfit().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return this.investPlanManageMapper.insertInvestPlanManageByPid(investPlanManage);
	}

	@Override
	@Transactional
	public int updateInvestPlanManageByPid(InvestManagePlan investPlanManage) {
		if(investPlanManage.getExpectInvestAmount()!=null){
			investPlanManage.setExpectInvestAmount(investPlanManage.getExpectInvestAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getExpectAllReceiverAccount()!=null){
			investPlanManage.setExpectAllReceiverAccount(investPlanManage.getExpectAllReceiverAccount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getRealityInvestAmount()!=null){
			investPlanManage.setRealityInvestAmount(investPlanManage.getRealityInvestAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReceiveredPrincipal()!=null){
			investPlanManage.setReceiveredPrincipal(investPlanManage.getReceiveredPrincipal().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReceiveredPayback()!=null){
			investPlanManage.setReceiveredPayback(investPlanManage.getReceiveredPayback().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(investPlanManage.getReturnedProfit()!=null){
			investPlanManage.setReturnedProfit(investPlanManage.getReturnedProfit().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return this.investPlanManageMapper.updateInvestPlanManageByPid(investPlanManage);
	}

	@Override
	public InvestManagePlan getInvestPlanManageByPrimaryKey(String investManagePlanPid) {
		InvestManagePlan investPlanManage = this.investPlanManageMapper.getInvestPlanManageByPrimaryKey(investManagePlanPid);
		// 预计出资规模  转化为 万元 为单位
		BigDecimal expectInvestAmount = investPlanManage.getExpectInvestAmount();
		if(expectInvestAmount != null) {
			investPlanManage.setExpectInvestAmount(expectInvestAmount.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		// 预计总回款  转化为 万元 为单位
		BigDecimal expectAllReceiverAccount = investPlanManage.getExpectAllReceiverAccount();
		if(expectAllReceiverAccount != null) {
			investPlanManage.setExpectAllReceiverAccount(expectAllReceiverAccount.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		// 实际出资金额  转化为 万元 为单位
		BigDecimal realityInvestAmount = investPlanManage.getRealityInvestAmount();
		if(realityInvestAmount != null) {
			investPlanManage.setRealityInvestAmount(realityInvestAmount.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		// 已收本金  转化为 万元 为单位
		BigDecimal receiveredPrincipal = investPlanManage.getReceiveredPrincipal();
		if(receiveredPrincipal != null) {
			investPlanManage.setReceiveredPrincipal(receiveredPrincipal.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		// 已收回款  转化为 万元 为单位
		BigDecimal receiveredPayback = investPlanManage.getReceiveredPayback();
		if(receiveredPayback != null) {
			investPlanManage.setReceiveredPayback(receiveredPayback.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		// 应回利润  转化为 万元 为单位
		BigDecimal returnedProfit = investPlanManage.getReturnedProfit();
		if(returnedProfit != null) {
			investPlanManage.setReturnedProfit(returnedProfit.divide(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		return investPlanManage;
	}

	@Override
	public List<Map<String, Object>> queryInvestSubjectList(String investPlanManagePid, Integer offset, Integer pageRows) {
		return this.investSubjectMapper.queryInvestSubjectList(investPlanManagePid,offset,pageRows);
	}

	@Override
	public Integer countInvestSubjectList(String investPlanManagePid) {
		return this.investSubjectMapper.countInvestSubjectList(investPlanManagePid);
	}

	@Override
	public InvestManagePlan getInvestPlanManageByAllPid(String projectInfoPid,String businessPrjInfoPid, String stockholderInfoPid) {
		return this.investPlanManageMapper.getInvestPlanManageByAllPid(projectInfoPid,businessPrjInfoPid,stockholderInfoPid);
	}
	
	
	
	
	


}
