package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.Payment;

public interface PaymentMapper {
    /**
     *
     * @mbg.generated 2018-09-26
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-26
     */
    int insert(Payment record);

    /**
     *
     * @mbg.generated 2018-09-26
     */
    int insertSelective(Payment record);

    /**
     *
     * @mbg.generated 2018-09-26
     */
    Payment selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-26
     */
    int updateByPrimaryKeySelective(Payment record);

    /**
     *
     * @mbg.generated 2018-09-26
     */
    int updateByPrimaryKey(Payment record);

    /**
     * 根据条件获取付款信息列表
     * @param payment
     * @param pageRows 
     * @param offset 
     * @return
     */
	List<Map<String, Object>> getPaymentsByConditions(@Param("payment")Payment payment, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件统计付款信息
	 * @param payment
	 * @return
	 */
	Integer countPaymentsByConditions(@Param("payment")Payment payment);

	/**
	 * 统计收付款金额
	 * @param paymentType 
	 * @return
	 */
	Map<String, Object> statisticsAmount(@Param("paymentType")String paymentType);

	/**
	 * 根据主键获取付款信息
	 * @param pid
	 * @return
	 */
	Payment getPaymentByPrimaryKey(@Param("pid")String pid);
	
	/**
	 * 更新付款表状态
	 * @param pid
	 * @param status
	 * @return
	 */
	int updatePaymentStatus(@Param("pid")String pid, @Param("status")String status);

	/**
	 * 查看股东付款请求信息列表
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return
	 */
	List<Map<String, Object>> queryStockPaymentInfoList(@Param("condtions")Map<String, String> condtions, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 查看股东付款请求信息列表数量
	 * @author zhangcai
	 * @param condtions
	 * @return
	 */
	Integer countStockPaymentInfoList(@Param("condtions")Map<String, String> condtions);

	/**
	 * 统计应付实付金额
	 * @author zhangcai
	 * @param conditions 查询条件
	 * @param paymentType 
	 * @return
	 */
	Map<String, Object> statisticsAmountByCondition(@Param("conditions")Map<String,String> conditions,@Param("paymentType")String paymentType);
	
	
	/**
	 * 根据付款主键获取该主键对应的相关信息及关联信息
	 * @author  lixq 
	 * @param   payMentPid
	 * @return  Map<String,Object>  
	 * @date    2018年10月23日
	 */
	Map<String, Object> getStockHolderPayByPid(String payMentPid);
	
	
	/**
	 * 批量删除股东付款请求
	 * @author  lixq 
	 * @param   payMentPids
	 * @return  int  
	 * @date    2018年10月23日
	 */
	int delStockHolderPayByPids(@Param("idList") List<String> payMentPids);

	/**
	 * 根据prj_id 查询付款表，只要有数据，则不让更新关联项目
	 * @author zhangcai
	 * @param linkedPrjId
	 * @return
	 */
	List<Map<String, Object>> queryPaymentList(@Param("prjId") String linkedPrjId);

	/**
	 * 根据条件获取payment
	 * @param payment
	 * @return
	 */
	List<Payment> getPaymentByParams(@Param("payment")Payment payment);
}