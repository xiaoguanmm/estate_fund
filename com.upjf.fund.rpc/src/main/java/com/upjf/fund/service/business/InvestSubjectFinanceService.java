package com.upjf.fund.service.business;

import java.util.Map;

import com.upjf.fund.base.Page;
import com.upjf.fund.dto.InvestSubjectFinanceVo;

/**
 * 投资主体财务管理服务接口
 * @author lixq
 * @date   2018年10月17日
 */
public interface InvestSubjectFinanceService {
	
	//分页获取投资主体财务信息
	Map<String, Object> getInvestSubjectFinanceListByPage(InvestSubjectFinanceVo investSubjectFinanceVo,Page page) throws Exception;
	
	
	//根据投资主体主键及项目主键获取投资主体相关的详细信息
	Map<String, Object> getSubjectFinanceDetail(String subjectPid,String projectPid) throws Exception;
	
	
	//分页获取投资主体(投资人)的附件列表
	Map<String, Object> getSubjectFileListByPage(String subjectPid,Page page) throws Exception;
	
	//分页加载投资主体付款信息
	Map<String, Object> getSubjectFinanceDetailListByPage(String subjectPid,Page page) throws Exception;
	
	//根据付款主键,获取当前该条件下对应的付款明细信息
	Map<String, Object> getSubjectPaymentDetailByPid(String subjectPayPid) throws Exception;
	
	//分页加载投资主体单条付款对应的附件信息列表
	Map<String, Object> getSubjectPaymentDetailFileList(String subjectPayPid,Page page) throws Exception;
	
	//分页加载投资主体回款信息
	Map<String, Object> getSubjectReceiveDetailListByPage(String subjectPid,Page page) throws Exception;
	
	//根据投资主体回款信息主键,获取该条主键下的回款明细
	Map<String, Object> getSubjectReturnDetailByPid(String subjectReturnPid) throws Exception;
	
	//分页加载投资主体单条付款对应的附件信息列表
	Map<String, Object> getSubjectReturnDetailFileList(String subjectReturnPid,Page page) throws Exception;

}
