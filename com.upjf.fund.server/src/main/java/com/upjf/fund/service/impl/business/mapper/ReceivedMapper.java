package com.upjf.fund.service.impl.business.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.Received;

public interface ReceivedMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(Received record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(Received record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    Received selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(Received record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(Received record);

    /**
     * 根据条件获取股东回款记录汇总
     * @param conditions
     * @param offset
     * @param pageRows
     * @return
     */
	List<Map<String, Object>> getStockholderPaybacksByConditions(@Param("params")Map<String, String> params, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计股东回款记录总数
	 * @param conditions
	 * @return
	 */
	Integer countStockholderPaybacksByConditions(@Param("params")Map<String, String> params);

	/**
	 * 查询回款历史记录
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getHistoryPaybacksByConditions(@Param("received")Received received, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 统计回款记录记录
	 * @param received
	 * @return
	 */
	Integer countHistoryPaybacksByConditions(@Param("received")Received received);

	/**
	 * 根据条件查询投资主体回款记录
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getInvestSubjectPaybacksByConditions(@Param("received")Received received, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计投资主体回款总数
	 * @param received
	 * @return
	 */
	Integer countInvestSubjectPaybacksByConditions(@Param("received")Received received);

	/**
	 * 统计回款合计
	 * @param receivedType
	 * @return
	 */
	Map<String, Object> statisticsAmount(@Param("receivedType")String receivedType);

	/**
	 * 根据条件统计回款合计
	 * @param prjId
	 * @param receivedId
	 * @param receivedType
	 * @return
	 */
	Map<String, Object> statisticsAmountByParmas(@Param("received")Received received);

	/**
	 * 根据条件查询投资人回款记录
	 * @param received
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getInvestorPaybacksByConditions(@Param("received")Received received, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计投资人回款总数
	 * @param received
	 * @return
	 */
	Integer countInvestorPaybacksByConditions(@Param("received")Received received);

	/**
	 * 更新回款表状态
	 * @param pid
	 * @param statusDelete
	 * @return
	 */
	int updatePaybackRecordStatus(@Param("pid")String pid, @Param("status")String status);

	/**
	 * 根据prj_id 查询回款表，只要有数据，则不让更新关联项目
	 * @param linkedPrjId
	 * @return
	 */
	List<Map<String, Object>> queryReceivedList(@Param("prjId") String linkedPrjId);

	/**
	 * 获取回款状态
	 * @param received
	 * @return
	 */
	String getReceivedStatus(@Param("received")Received received);

	/**
	 * 获取最近一次的回款日期
	 * @param received
	 * @return
	 */
	Date getLastReceivedDate(@Param("received")Received received);
	
}