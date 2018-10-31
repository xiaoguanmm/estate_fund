package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.upjf.fund.dto.StockholderInfo;

public interface StockholderInfoMapper {
    /**
     *
     * @mbg.generated 2018-09-14
     */
    int deleteByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insert(StockholderInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int insertSelective(StockholderInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    StockholderInfo selectByPrimaryKey(String pid);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKeySelective(StockholderInfo record);

    /**
     *
     * @mbg.generated 2018-09-14
     */
    int updateByPrimaryKey(StockholderInfo record);

    /**
	 * 查询项目公司股东信息index页列表
	 * @author zhangcai
     * @param businessPrjInfoId 
     * @param projectInfoPid 
     * @param projectInfoPid 
	 * @param offset
	 * @param pageRows
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> queryProjCompanyStockholderList(@Param("businessPrjInfoId") String businessPrjInfoId,
			@Param("projectInfoPid") String projectInfoPid, @Param("offset") Integer offset, @Param("pageRows") Integer pageRows);
	
	/**
	 * 查询项目公司股东信息index页列表条数
	 * @author zhangcai
	 * @param businessPrjInfoId 
	 * @param projectInfoPid 
	 * @param projectInfoPid 
	 * @return int
	 */
	Integer countProjCompanyStockholderList(@Param("businessPrjInfoId") String businessPrjInfoId, @Param("projectInfoPid") String projectInfoPid);

	/**
	 * 选中的股东PID（即企业Pid）、项目公司Pid 
	 * @author zhangcai
	 * @param corpId
	 * @param businessPrjInfoId 
	 * @param prjId 
	 * @return
	 */
	StockholderInfo getStockholderByCorpId(@Param("businessPrjInfoId") String businessPrjInfoId, @Param("corpId") String corpId, @Param("prjId")String prjId);

	/**
	 * 逻辑删除股东表
	 * @author zhangcai
	 * @param pids
	 * @return
	 */
	int delStockholderInfoByPids(Map<String,Object> paramsMap);

	/**
	 * 根据项目公司pid异步加载股东信息
	 * @author zhangcai
	 * @param  projectInfoPid 
	 * @param  businessPrjInfoPid
	 * @param  type 
	 * @return
	 */
	List<Map<String, Object>> getStockholderPidByBusinessPrjPid(@Param("projectInfoPid") String projectInfoPid, @Param("businessPrjInfoPid") String businessPrjInfoPid);
	
	
	
	/**
	 * 根据项目公司主键及项目主键,查询获取有效状态下的股东信息
	 * @author  lixq 
	 * @param   projectCompanyPid
	 * @param   projectId
	 * @return  List<Map<String,Object>>  
	 * @date    2018年10月22日
	 */
	List<Map<String,Object>> getStockHolderByProjectComPanyPid(@Param("projectCompanyPid") String projectCompanyPid,@Param("projectId") String projectId);

	/**
	 * 根据项目公司主键及项目主键,查询获取有效状态下的股东信息
	 * @author  zhangcai 
	 * @param   projectCompanyPid
	 * @param   projectId
	 * @return  List<String>  
	 * @date    2018年10月22日
	 */
	List<String> queryStockholderInfo(@Param("businessPrjPid") String businessPrjPid,@Param("prjId") String projectInfoPid);
	
	
	/**
	 * 根据项目公司主键,查询获取项目公司主键为空的股东信息 
	 * @author  lixq 
	 * @param   businessPrjPid
	 * @return  List<String>  
	 * @date    2018年10月26日
	 */
	List<String> getStockholderInfoOfNullPrjId(@Param("businessPrjPid") String businessPrjPid);

	
	
	/**
	 * 批量更新股东表
	 * @author  zhangcai 
	 * @param   paramsMap
	 * @return
	 * @date    2018年10月23日
	 */
	int updateStockholderInfoByPids(@Param("paramsMap") Map<String, Object> paramsMap);
	
	/**
	 * 根据项目名称加载股东
	 * @author zhangcai
	 * @param projectInfoPid
	 * @return
	 */
	List<Map<String, Object>> getStockInfoByPrjId(@Param("projectInfoPid") String projectInfoPid);
}