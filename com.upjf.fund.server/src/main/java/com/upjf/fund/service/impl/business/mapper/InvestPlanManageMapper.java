package com.upjf.fund.service.impl.business.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.InvestManagePlan;

public interface InvestPlanManageMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(InvestManagePlan record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertInvestPlanManageByPid(InvestManagePlan investPlanManage);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    InvestManagePlan getInvestPlanManageByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateInvestPlanManageByPid(InvestManagePlan investPlanManage);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(InvestManagePlan record);

    /**
	 * 根据条件查询资管计划管理列表
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getInvestPlanManageByConditon(@Param("condtions")Map<String, String> condtions, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据条件查询资管计划管理列表总条数
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return int
	 */
	Integer countInvestPlanManageByConditon(@Param("condtions")Map<String, String> condtions);

	/**
	 * 根据项目名称Pid、项目公司Pid、股东 PID 查询资管计划表，校验是否重复
	 * @param projectInfoPid
	 * @param businessPrjInfoPid
	 * @param stockholderInfoPid
	 * @return
	 * @date 2018.10.15
	 */
	InvestManagePlan getInvestPlanManageByAllPid(@Param("projectInfoPid")String projectInfoPid,
			@Param("businessPrjInfoPid")String businessPrjInfoPid, @Param("stockholderInfoPid")String stockholderInfoPid);

	/**
	 * 更新资管计划已收回款字段
	 * @param prjId 项目id
	 * @param contributiveId 付款公司id(项目公司id)
	 * @param receiverId (收款公司(股东对应企业id))
	 */
	int updateReceivedPayback(@Param("prjId")String prjId, @Param("contributiveId")String contributiveId,@Param("receiverId")String receiverId);

	/**
	 * 更新资管计划已收本金字段
	 * @param prjId
	 * @param receiverId
	 * @param contributiveId
	 * @param receiverAmount
	 */
	int updateReceiveredPrincipal(@Param("prjId")String prjId, @Param("receiverId")String receiverId, @Param("contributiveId")String contributiveId, @Param("receiverAmount")BigDecimal receiverAmount);

	/**
	 * 根据条件获取资管计划信息
	 * @param investManagePlan
	 * @return
	 */
	List<InvestManagePlan> getInvestManagePlanByParams(@Param("investManagePlan")InvestManagePlan investManagePlan);

}