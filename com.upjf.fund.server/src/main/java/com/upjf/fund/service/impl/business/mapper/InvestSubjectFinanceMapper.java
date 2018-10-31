package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.InvestSubjectFinanceVo;

/**
 * 投资主体财务管理接口
 * @author lixq
 * @date   2018年10月17日
 */
public interface InvestSubjectFinanceMapper {
    
	
	
	 /**
	  * 分页获取投资主体财务信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<InvestSubjectFinanceVo>  
	  * @date    2018年10月17日
	  */
	 List<InvestSubjectFinanceVo> getInvestSubjectFinanceListByPage(Map<String,Object> queryParams);
	
	 
	 /**
	  * 获取满足条件的命中总记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date    2018年10月17日
	  */
	 int getTotalCount(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 根据项目主键及投资主体主键获取投资主体信息
	  * @author  lixq 
	  * @param   paramsMap
	  * @return  InvestSubject  
	  * @date    2018年10月17日
	  */
	 InvestSubjectFinanceVo getInvestSubjectByPidAndProjectPid(Map<String, Object> paramsMap);
	 
	 
	 
	 /**
	  * 分页加载投资主体付款信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月18日
	  */
	 List<Map<String, Object>> getSubjectFinanceDetailListByPage(Map<String,Object> queryParams);
	 
	 
	 
	 /**
	  * 获取满足条件的投资主体付款信息明细记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date    2018年10月18日
	  */
	 int getSubjectFinanceDetailTotalCount(Map<String,Object> queryParams);
	 
	 
	 
	 /**
	  * 根据投资主体主键获取到投资主体的应付总金额及实付总金额
	  * @author  lixq 
	  * @param   subjectPid
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月18日
	  */
	 Map<String, Object> getAmountOfPrePayAndActualPay(String subjectPid);
	 
	 
	 
	 /**
	  * 根据付款主键,获取当前该条件下对应的付款明细信息
	  * @author  lixq 
	  * @param   subjectPayPid
	  * @return  Map<String,Object>  
	  * @date    2018年10月19日
	  */
	 Map<String, Object> getSubjectPaymentDetailByPid(String subjectPayPid);
	 
	 
	 /**
	  * 分页加载投资主体回款信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月19日
	  */
	 List<Map<String, Object>> getSubjectReceiveDetailListByPage(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 获取满足条件的投资主体回款信息明细记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date    2018年10月18日
	  */
	 int getSubjectReceiveDetailListTotalCount(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 根据投资主体主键获取到投资主体的回款本金合计及回款利润合计
	  * @author  lixq 
	  * @param   subjectPid
	  * @return  Map<String,Object>
	  * @date    2018年10月18日
	  */
	 Map<String, Object> getReceiverAmountAndProfit(String subjectPid);
	 
	 
	 /**
	  * 根据投资主体回款信息主键,获取该条主键下的回款明细
	  * @author  lixq 
	  * @param   subjectPayPid
	  * @return  Map<String,Object>  
	  * @date    2018年10月19日
	  */
	 Map<String, Object> getSubjectReturnDetailByPid(String subjectReturnPid);
	 
	 
}