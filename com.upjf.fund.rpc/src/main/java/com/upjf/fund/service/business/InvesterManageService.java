package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.InvestManagePlan;
import com.upjf.fund.dto.Payment;

/**
 * 出资管理(项目股东出资审核管理，投资主体出资管理，投资人出资管理)
 * @company upjf.com
 * @author guantong
 *
 */
public interface InvesterManageService {
	
	/**
	 * 根据主键获取付款信息
	 * @param pid
	 * @return
	 */
	public Payment getPaymentByPrimaryKey(String pid);

	/**
	 * 根据实体内容更新付款信息
	 * @param payment
	 */
	public int updatePaymentBySelective(Payment payment);

	/**
	 * 根据条件获取付款信息列表
	 * @param payment
	 * @param pageRows 
	 * @param offset 
	 * @return
	 */
	public List<Map<String, Object>> getPaymentsByConditions(Payment payment, Integer offset, Integer pageRows);

	/**
	 * 根据条件统计付款信息
	 * @param payment
	 * @return
	 */
	public Integer countPaymentsByConditions(Payment payment);

	/**
	 * 统计收付款金额
	 * @param paymentTypeStockholder 
	 * @return
	 */
	public Map<String, Object> statisticsAmount(String paymentTypeStockholder);

	/**
	 * 新增付款信息
	 * @param payment
	 * @return
	 */
	public int insertPaymentBySelective(Payment payment);

	/**
	 * 更新付款表状态
	 * @param pid
	 * @param status
	 * @return
	 */
	public int updatePaymentStatus(String pid, String status);

	/**
	 * 查看股东付款请求信息列表
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	public List<Map<String, Object>> queryStockPaymentInfoList(Map<String, String> condtions, Integer offset, Integer pageRows);

	/**
	 * 查看股东付款请求信息列表数量
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	public Integer countStockPaymentInfoList(Map<String, String> condtions);

	/**
	 * 统计应付实付金额
	 * @author zhangcai
	 * @param conditions 查询条件
	 * @param paymentTypeStockholder 
	 * @return
	 */
	public Map<String, Object> statisticsAmountByCondition(Map<String,String> conditions,String paymentTypeStockholder);

	/**
	 * 根据prj_id 查询付款表，只要有数据，则不让更新关联项目
	 * @param linkedPrjId
	 * @return
	 */
	public List<Map<String, Object>> queryPaymentList(String linkedPrjId);

	/**
	 * 根据条件获取payment
	 * @param payment
	 * @return
	 */
	public List<Payment> getPaymentByParams(Payment payment);

	/**
	 * 根据条件获取资管计划信息
	 * @param imp
	 * @return
	 */
	public InvestManagePlan getInvestManagePlanByParams(InvestManagePlan investManagePlan);

}
