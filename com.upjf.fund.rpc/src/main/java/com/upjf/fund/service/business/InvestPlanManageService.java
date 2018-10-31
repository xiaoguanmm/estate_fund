package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.InvestManagePlan;


public interface InvestPlanManageService {

	/**
	 * 根据条件查询资管计划管理列表
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getInvestPlanManageByConditon(Map<String, String> condtions, Integer offset, Integer pageRows);

	/**
	 * 根据条件查询资管计划管理列表总条数
	 * @author zhangcai
	 * @param condtions
	 * @param offset
	 * @param pageRows
	 * @return int
	 */
	Integer countInvestPlanManageByConditon(Map<String, String> condtions);

	/**
	 * 插入资产计划管理表
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	int insertInvestPlanManageByPid(InvestManagePlan investPlanManage);

	/**
	 * 修改资产计划管理表
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	int updateInvestPlanManageByPid(InvestManagePlan investPlanManage);

	/**
	 * 根据主键查询 资产计划管理表
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	InvestManagePlan getInvestPlanManageByPrimaryKey(String investManagePlanPid);

	/**
	 * 根据资管计划Pid查询  投资主体列表
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	List<Map<String, Object>> queryInvestSubjectList(String investPlanManagePid, Integer offset, Integer pageRows);

	/**
	 * 根据资管计划Pid查询  投资主体列表数量
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	Integer countInvestSubjectList(String investPlanManagePid);

	/**
	 * 根据项目名称Pid、项目公司Pid、股东 PID 查询资管计划表，校验是否重复
	 * @param projectInfoPid
	 * @param businessPrjInfoPid
	 * @param stockholderInfoPid
	 * @return
	 * @date 2018.10.15
	 */
	InvestManagePlan getInvestPlanManageByAllPid(String projectInfoPid,String businessPrjInfoPid, String stockholderInfoPid);


}
