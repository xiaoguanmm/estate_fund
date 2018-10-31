package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.PaymentAccessory;

public interface PaymentAccessoryMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(PaymentAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(PaymentAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    PaymentAccessory selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(PaymentAccessory record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(PaymentAccessory record);

    /**
     * 批量插入付款附件表
     * @param accessoryList
     * @return
     */
	int batchInsertPaymentAccessory(@Param("accessoryList")List<PaymentAccessory> accessoryList);

	/**
	 * 根据paymentId获取付款附件信息
	 * @param pid
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> getPaymentAccessoriesByPaymentId(@Param("paymentId")String paymentId, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);
	/**
	 * 根据paymentId统计付款附件信息
	 * @param pid
	 * @return
	 */
	Integer countPaymentAccessoriesByPaymentId(@Param("paymentId")String paymentId);
}