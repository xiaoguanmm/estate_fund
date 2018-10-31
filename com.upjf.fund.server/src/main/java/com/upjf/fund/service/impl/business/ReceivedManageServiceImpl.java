package com.upjf.fund.service.impl.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.Received;
import com.upjf.fund.service.business.ReceivedManageService;
import com.upjf.fund.service.impl.business.mapper.InvestPlanManageMapper;
import com.upjf.fund.service.impl.business.mapper.ReceivedMapper;

@Service("receivedManageService")
public class ReceivedManageServiceImpl implements ReceivedManageService {
	
	@Autowired
	private ReceivedMapper receivedMapper;
	
	@Autowired
	private InvestPlanManageMapper investPlanManageMapper;

	@Override
	public List<Map<String, Object>> getStockholderPaybacksByConditions(Map<String, String> params, Integer offset, Integer pageRows) {
		
		return this.receivedMapper.getStockholderPaybacksByConditions(params,offset,pageRows);
	}

	@Override
	public Integer countStockholderPaybacksByConditions(Map<String, String> params) {
		
		return this.receivedMapper.countStockholderPaybacksByConditions(params);
	}

	@Override
	public List<Map<String, Object>> getHistoryPaybacksByConditions(Received received, Integer offset, Integer pageRows) {
		
		return this.receivedMapper.getHistoryPaybacksByConditions(received,offset,pageRows);
	}

	@Override
	public Integer countHistoryPaybacksByConditions(Received received) {
		
		return this.receivedMapper.countHistoryPaybacksByConditions(received);
	}

	@Override
	@Transactional
	public int updateReceivedBySelective(Received received) {
		if(received.getReceiverAmount()!=null){
			received.setReceiverAmount(received.getReceiverAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(received.getProfit()!=null){
			received.setProfit(received.getProfit().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		int result = this.receivedMapper.updateByPrimaryKeySelective(received);
		/*如果是项目股东回款，需要同时更新资管计划表已收回款字段*/
		if(EstateFundConstants.PAYBACK_TYPE_STOCKHOLDER.equals(received.getReceivedType())){
			this.investPlanManageMapper.updateReceivedPayback(received.getPrjId(),received.getContributiveId(),received.getReceiverId());
		}
		return result;
	}

	@Override
	@Transactional
	public int insertPaymentBySelective(Received received) {
		if(received.getReceiverAmount()!=null){
			received.setReceiverAmount(received.getReceiverAmount().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		if(received.getProfit()!=null){
			received.setProfit(received.getProfit().multiply(new BigDecimal(EstateFundConstants.TEN_THOUSAND)));
		}
		int result = this.receivedMapper.insertSelective(received);
		/*如果是项目股东回款，需要同时更新资管计划表已收回款字段*/
		if(EstateFundConstants.PAYBACK_TYPE_STOCKHOLDER.equals(received.getReceivedType())){
			this.investPlanManageMapper.updateReceivedPayback(received.getPrjId(),received.getContributiveId(),received.getReceiverId());
			
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getInvestSubjectPaybacksByConditions(Received received, Integer offset, Integer pageRows) {
		
		return this.receivedMapper.getInvestSubjectPaybacksByConditions(received,offset,pageRows);
	}

	@Override
	public Integer countInvestSubjectPaybacksByConditions(Received received) {
		
		return this.receivedMapper.countInvestSubjectPaybacksByConditions(received);
	}

	@Override
	public Map<String, Object> statisticsAmount(String paybackType) {
		
		return this.receivedMapper.statisticsAmount(paybackType);
	}

	@Override
	public Map<String, Object> statisticsAmountByParmas(Received received) {
		
		return this.receivedMapper.statisticsAmountByParmas(received);
	}

	@Override
	public List<Map<String, Object>> getInvestorPaybacksByConditions(Received received, Integer offset, Integer pageRows) {
		
		return this.receivedMapper.getInvestorPaybacksByConditions(received,offset,pageRows);
	}

	@Override
	public Integer countInvestorPaybacksByConditions(Received received) {
		
		return this.receivedMapper.countInvestorPaybacksByConditions(received);
	}

	@Override
	@Transactional
	public int updatePaybackRecordStatus(String pid, String status) {
		Received received = this.receivedMapper.selectByPrimaryKey(pid);
		int result = this.receivedMapper.updatePaybackRecordStatus(pid,status);
		/*如果是项目股东回款，需要同时更新资管计划表已收回款字段*/
		if(EstateFundConstants.PAYBACK_TYPE_STOCKHOLDER.equals(received.getReceivedType())){
			this.investPlanManageMapper.updateReceivedPayback(received.getPrjId(),received.getContributiveId(),received.getReceiverId());
			
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryReceivedList(String linkedPrjId) {
		return this.receivedMapper.queryReceivedList(linkedPrjId);
	}

	@Override
	public String getReceivedStatus(Received received) {
		
		return this.receivedMapper.getReceivedStatus(received);
	}

	@Override
	public Date getLastReceivedDate(Received received) {
		
		return this.receivedMapper.getLastReceivedDate(received);
	}

}
