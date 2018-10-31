package com.upjf.fund.service.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.Received;

/**
 * 回款管理(项目股东回款，投资主体利润分配，投资人利润分配)
 * @company upjf.com
 * @author guantong
 *
 */
public interface ReceivedManageService {

	/**
	 * 根据条件获取股东回款记录汇总
	 * @param conditions
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getStockholderPaybacksByConditions(Map<String, String> params, Integer offset, Integer pageRows);

	/**
	 * 根据条件统计股东回款记录总数
	 * @param conditions
	 * @return
	 */
	Integer countStockholderPaybacksByConditions(Map<String, String> params);

	/**
	 * 查询回款历史记录
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getHistoryPaybacksByConditions(Received received, Integer offset, Integer pageRows);

	/**
	 * 统计回款历史记录
	 * @param received
	 * @return
	 */
	Integer countHistoryPaybacksByConditions(Received received);

	/**
	 * 新增回款信息
	 * @param received
	 * @return
	 */
	int updateReceivedBySelective(Received received);

	/**
	 * 新增回款信息
	 * @param received
	 * @return
	 */
	int insertPaymentBySelective(Received received);

	/**
	 * 根据条件获取投资主体回款记录汇总
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectPaybacksByConditions(Received received, Integer offset, Integer pageRows);

	/**
	 * 根据条件统计投资主体回款记录总数
	 * @param received
	 * @return
	 */
	Integer countInvestSubjectPaybacksByConditions(Received received);

	/**
	 * 统计回款合计
	 * @param paybackTypeInvestSubject
	 * @return
	 */
	Map<String, Object> statisticsAmount(String paybackType);

	/**
	 * 根据条件统计回款合计
	 * @param received
	 * @return
	 */
	Map<String, Object> statisticsAmountByParmas(Received received);
	
	/**
	 * 根据条件获取投资人回款记录汇总
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getInvestorPaybacksByConditions(Received received, Integer offset, Integer pageRows);
	
	/**
	 * 根据条件统计投资人回款记录总数
	 * @param received
	 * @return
	 */
	Integer countInvestorPaybacksByConditions(Received received);

	/**
	 * 更改回款记录状态
	 * @param pid
	 * @param status
	 * @return
	 */
	int updatePaybackRecordStatus(String pid, String status);

	/**
	 * 根据prj_id 查询回款表，只要有数据，则不让更新关联项目
	 * @param linkedPrjId
	 * @return
	 */
	List<Map<String, Object>> queryReceivedList(String linkedPrjId);

	/**
	 * 获取回款状态
	 * @param received
	 * @return
	 */
	String getReceivedStatus(Received received);

	/**
	 * 获取最近一次的回款日期
	 * @param received
	 * @return
	 */
	Date getLastReceivedDate(Received received);


}
