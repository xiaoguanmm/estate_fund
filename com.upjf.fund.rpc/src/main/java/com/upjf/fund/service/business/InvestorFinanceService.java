package com.upjf.fund.service.business;

import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.InvestSubjectFinanceVo;

/**
 * 投资人财务管理服务接口
 * @author lixq
 * @date   2018年10月17日
 */
public interface InvestorFinanceService {
	
	//分页获取投资人财务信息
	Map<String, Object> getInvestorFinanceListByPage(InvestSubjectFinanceVo investSubjectFinanceVo,Page page) throws Exception;
	
	
	//根据投资人主键获取投资人相关的详细信息
	Map<String, Object> getInvestorFinanceDetail(String investorPid) throws Exception;
	
	
	//分页加载投资人付款信息
	Map<String, Object> getInvestorFinanceDetailListByPage(String investorPid,Page page) throws Exception;
	
	//分页加载投资人回款信息
	Map<String, Object> getInvestorReceiveDetailListByPage(String investorPid,Page page) throws Exception;
}
