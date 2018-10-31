package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

/**
 * 投资人财务管理接口
 * @author lixq
 * @date   2018年10月17日
 */
public interface InvestorFinanceMapper {
    
	
	
	 /**
	  * 分页获取投资人财务信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月24日
	  */
	 List<Map<String,Object>> getInvestorFinanceListByPage(Map<String,Object> queryParams);
	
	 
	 /**
	  * 获取满足条件的命中总记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date    2018年10月17日
	  */
	 int getTotalCount(Map<String,Object> queryParams);
	 
	 
	 
	 /**
	  * 根据投资人主键获取有效状态先的相关投资人信息 
	  * @author  lixq 
	  * @param   investorPid
	  * @return  Map<String,Object>  
	  * @date    2018年10月24日
	  */
	 Map<String,Object> getInvestorFinanceDetail(String investorPid);
	 
	 
	 /**
	  * 分页获取投资人付款信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月29日
	  */
	 List<Map<String, Object>> getInvestorFinanceDetailListByPage(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 获取投资人的总应付款和总实付款
	  * @author  lixq 
	  * @param   queryParams
	  * @return  Map<String,Object>  
	  * @date    2018年10月29日
	  */
	 Map<String, Object> getAmountOfPrePayAndActualPay(Map<String,Object> queryParams);
	 
	 
	 
	 /**
	  * 获取投资人付款信息总记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date 2018年10月29日
	  */
	 int getInvestorFinanceDetailTotalCount(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 分页获取投资人回款信息
	  * @author  lixq 
	  * @param   queryParams
	  * @return  List<Map<String,Object>>  
	  * @date    2018年10月29日
	  */
	 List<Map<String, Object>> getInvestorReceiveDetailListByPage(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 获取投资人的回款本金合计和回款利润合计
	  * @author  lixq 
	  * @param   queryParams
	  * @return  Map<String,Object>  
	  * @date    2018年10月29日
	  */
	 Map<String, Object> getReceiverAmountAndProfit(Map<String,Object> queryParams);
	 
	 
	 /**
	  * 获取投资人回款信息总记录数
	  * @author  lixq 
	  * @param   queryParams
	  * @return  int  
	  * @date    2018年10月29日
	  */
	 int getInvestorReceiveDetailTotalCount(Map<String,Object> queryParams);
	 
}