package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.InvestSubject;

public interface InvestSubjectMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(InvestSubject record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertInvestSubject(InvestSubject investSubject);

    /**
     * 查询有效的投资主体
     * @mbg.generated 2018-09-14
     */
    InvestSubject getInvestSubjectByPrimaryKey(String pid);

    /**
     * 更新投资主体表
     * @mbg.generated 2018-09-14
     */
    int updateInvestSubjectByPrimaryKey(InvestSubject investSubject);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(InvestSubject record);

    /**
	 * 根据资管计划Pid查询  投资主体列表
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	List<Map<String, Object>> queryInvestSubjectList(@Param("investPlanManagePid")String investPlanManagePid, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 根据资管计划Pid查询  投资主体列表数量
	 * @author zhangcai
	 * @param investPlanManage
	 * @return
	 * @date 2018.10.11
	 */
	Integer countInvestSubjectList(@Param("investPlanManagePid")String investPlanManagePid);

	/**
	 * 根据资产管理计划id、投资主体主键查询    投资主体表
	 * @author zhangcai
	 * @param investPlanManagePid
	 * @param investSubjectName 
	 * @return
	 * @date 2018.10.15
	 */
	InvestSubject getInvestSubjectByInvestPlanPidAndPid(@Param("investPlanManageId")String investPlanManagePid, @Param("investSubjectId")String investSubjectName);

	/**
	 * 通过条件查询投资人业务管理列表
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.19
	 */
	List<Map<String, Object>> queryInvestorBusinessManageList(@Param("condtions") Map<String, String> condtions, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 通过条件查询投资人业务管理列表总条数
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.19
	 */
	Integer countInvestorBusinessManageList(@Param("condtions") Map<String, String> condtions);

	/**
	 * 通过资管计划Pid（investPlanManagePid）、投资主体Pid（investSubjectPid）查询新增投资人时所需字段
	 * @author zhangcai
	 * @param investPlanManagePid
	 * @param investSubjectPid
	 * @return
	 * @date 2018.10.22
	 */
	Map<String, Object> queryInvestSubjectAndPrjInfo(@Param("investPlanManagePid") String investPlanManagePid, @Param("investSubjectPid")String investSubjectPid);

	/**
	 * 根据资产管理计划pid、投资主体pid、投资人名称查询    投资主体表,校验唯一性
	 * 同一个资管计划、投资主体下投资人Pid不能重复
	 * @author zhangcai
	 * @param investPlanManagePid
	 * @param investSubjectPid 
	 * @param investorCorPid
	 * @return
	 * @date 2018.10.22
	 */
	InvestSubject getInvestorByInvestPlanPidAndInvestPid(@Param("investPlanManageId") String investPlanManagePid, @Param("investSubjectPid")String investSubjectPid,
			@Param("investorCorPid") String investorCorPid);

	/**
	 * 删除投资主体、投资人（可批量删除）
	 * @author zhangcai
	 * @param paramsMap
	 * @return
	 * @date 2018.10.25
	 */
	int deleteInvestSubjectByPids(Map<String, Object> paramsMap);

	/**
	 * 查询所有投资主体业务管理列表
	 * @author zhangcai
	 * @param conditions
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.26
	 */
	List<Map<String, Object>> queryInvestSubjectBusinessManageList(@Param("conditions") Map<String, String> conditions, @Param("offset")Integer offset, @Param("pageRows")Integer pageRows);

	/**
	 * 查询所有投资主体业务管理列表数量
	 * @author zhangcai
	 * @param conditions
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.26
	 */
	Integer countInvestSubjectBusinessManageList(@Param("conditions") Map<String, String> conditions);
	
}