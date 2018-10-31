package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;

import com.upjf.fund.dto.EstateFundFile;
import com.upjf.fund.dto.InvestSubject;



public interface InvestSubjectService {

	/**
	 * 插入投资主体表
	 * @author zhangcai
	 * @param investSubject
	 * @return
	 * @date 2018.10.15
	 */
	int insertInvestSubject(InvestSubject investSubject);

	/**
	 * 根据主键查询  投资主体表
	 * @author zhangcai
	 * @param investSubjectPid
	 * @return
	 * @date 2018.10.15
	 */
	InvestSubject getInvestSubjectByPrimaryKey(String investSubjectPid);

	/**
	 * 根据资产管理计划id、投资主体主键查询    投资主体表
	 * @author zhangcai
	 * @param investPlanManagePid
	 * @param investSubjectPid 
	 * @return
	 * @date 2018.10.15
	 */
	InvestSubject getInvestSubjectByInvestPlanPidAndPid(String investPlanManagePid, String investSubjectPid);

	/**
	 * 修改   投资主体表
	 * @author zhangcai
	 * @param investSubject
	 * @return
	 * @date 2018.10.15
	 */
	int updateInvestSubjectByPrimaryKey(InvestSubject investSubject);

	/**
	 * 批量插入投资主体附件表
	 * @author zhangcai
	 * @param accessoryType
	 * @param files
	 * @param stockholderPid
	 * @param loginUserId
	 * @return
	 * @date 2018.10.15
	 */
	int batchInsertInvestSubjectAccessory(String accessoryType,List<EstateFundFile> files,String investSubjectPid, String loginUserId);

	/**
	 * 通过subjectId查询投资主体附件表
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.15
	 */
	List<Map<String, Object>> getInvestSubjectAccessoryByInvestSubjectId(String investSubjectPid, Integer offset, Integer pageRows);

	/**
	 * 通过subjectId查询投资主体附件表总条数
	 * @author zhangcai
	 * @param investSubjectPid
	 * @return
	 * @date 2018.10.15
	 */
	Integer countInvestSubjectAccessoryByInvestSubjectId(String investSubjectPid);

	/**
	 * 通过条件查询投资人业务管理列表
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.19
	 */
	List<Map<String, Object>> queryInvestorBusinessManageList(Map<String, String> condtions, Integer offset, Integer pageRows);

	/**
	 * 通过条件查询投资人业务管理列表总条数
	 * @author zhangcai
	 * @param investSubjectPid
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.19
	 */
	Integer countInvestorBusinessManageList(Map<String, String> condtions);

	/**
	 * 通过资管计划Pid（investPlanManagePid）、投资主体Pid（investSubjectPid）查询新增投资人时所需字段
	 * @author zhangcai
	 * @param investPlanManagePid
	 * @param investSubjectPid
	 * @return
	 * @date 2018.10.22
	 */
	Map<String, Object> queryInvestSubjectAndPrjInfo(String investPlanManagePid, String investSubjectPid);

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
	InvestSubject getInvestorByInvestPlanPidAndInvestPid(String investPlanManagePid, String investSubjectPid,String investorCorPid);

	/**
	 * 删除投资主体、投资人（可批量删除）
	 * @author zhangcai
	 * @param pids
	 * @param curPid
	 * @param investType
	 * @return
	 * @date 2018.10.25
	 */
	int deleteInvestSubjectByPids(List<String> pids, String curPid, String investType);

	/**
	 * 查询所有投资主体业务管理列表
	 * @author zhangcai
	 * @param conditions
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.26
	 */
	List<Map<String, Object>> queryInvestSubjectBusinessManageList(Map<String, String> conditions, Integer offset, Integer pageRows);

	/**
	 * 查询所有投资主体业务管理列表数量
	 * @author zhangcai
	 * @param conditions
	 * @param offset
	 * @param pageRows
	 * @return
	 * @date 2018.10.26
	 */
	Integer countInvestSubjectBusinessManageList(Map<String, String> conditions);


}
