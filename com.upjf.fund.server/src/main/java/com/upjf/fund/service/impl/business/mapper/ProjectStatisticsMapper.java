package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;
/**
* @ClassName: ProjectStatisticsMapper
* @Description: 财务模块-项目相关信息统计
* @author wufujing
* @date 2018年10月9日
*
*/
public interface ProjectStatisticsMapper {

	/**
    * @Title: getCashList
    * @Description: 查询现金流管理列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getCashList(Map<String,String> paramMap);
	
	/**
    * @Title: getCashListCount
    * @Description: 查询现金流管理列表总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getCashListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectCashList
    * @Description: 查询项目现金情况列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectCashList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectCashListCount
    * @Description: 查询项目现金情况列表总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectCashListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getStockholderList
    * @Description: 查询项目资金情况--投资主体（股东）信息下拉框数据
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getStockholderList();
	
	
	/**
    * @Title: getProjectIndicatorList
    * @Description: 查询项目基本指标列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectIndicatorList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectIndicatorListCount
    * @Description: 查询项目基本指标列表总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectIndicatorListCount(Map<String,String> paramMap);
	
	
	/**
    * @Title: getProjectIndicatorList
    * @Description: 查询项目投资收益测算列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectProfitList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectIndicatorListCount
    * @Description: 查询项目投资收益测算列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectProfitListCount(Map<String,String> paramMap);	
	
	/**
    * @Title: getProjectReturnList
    * @Description: 查询项目回款情况列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectReturnList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectReturnListCount
    * @Description: 查询项目回款情况列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectReturnListCount(Map<String,String> paramMap);	
	
	/**
    * @Title: getProjectReturnList
    * @Description: 查询项目投资进展列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectInvestmentProgressList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectReturnListCount
    * @Description: 查询项目投资进展列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectInvestmentProgressListCount(Map<String,String> paramMap);	
	
	
	/**
    * @Title: getProjectSituationTotalList
    * @Description: 查询项目情况（总表）列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectSituationTotalList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectSituationTotalListCount
    * @Description: 查询项目情况（总表）列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectSituationTotalListCount(Map<String,String> paramMap);	
	
}