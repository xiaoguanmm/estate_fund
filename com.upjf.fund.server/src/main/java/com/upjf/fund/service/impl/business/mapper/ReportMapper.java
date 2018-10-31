package com.upjf.fund.service.impl.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReportMapper {

	/**
    * @Title: getProjectList
    * @Description: 项目信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectList();
		
	/**
    * @Title: getProjectCompanyList
    * @Description: 项目公司信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectCompanyList();
	
	/**
    * @Title: getInvestCompanyList
    * @Description: 投资人、投资主体信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestCompanyList(@Param("investType") String investType);
		
	/**
    * @Title: getCashList
    * @Description: 项目投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getCashListCount
    * @Description: 项目投入产出统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectInOutListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutCompanyList
    * @Description: 项目投入产出-股东公司统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectInOutCompanyList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutCompanyListCount
    * @Description: 项目投入产出-股东公司统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getProjectInOutCompanyListCount(Map<String,String> paramMap);	
	
	/**
    * @Title: getProjectInOutInvestList
    * @Description: 项目投入产出-股东公司投资统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectInOutInvestList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutCompanyListCount
    * @Description: 项目投入产出-股东公司投资统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutInvestListCount(Map<String,String> paramMap);	
	
	
	/**
    * @Title: getProjectInOutRepayList
    * @Description: 项目投入产出-股东公司回报统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getProjectInOutRepayList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutRepayListCount
    * @Description: 项目投入产出-股东公司回报统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutRepayListCount(Map<String,String> paramMap);	
	
	
	/**
    * @Title: getStockholderInOutList
    * @Description: 项目股东投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getStockholderInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getStockholderInOutListCount
    * @Description: 项目股东投入产出统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getStockholderInOutListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutList
    * @Description: 投资主体、投资人投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getStockholderInOutListCount
    * @Description: 投资主体、投资人投入产出统计列表数据 总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getInvestInOutListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestSubInOutCompanyList
    * @Description: 投资主体投入产出-项目投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestSubInOutCompanyList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestSubInOutCompanyListCount
    * @Description: 投资主体投入产出-项目投入产出统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getInvestSubInOutCompanyListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestorInOutCompanyList
    * @Description: 投资人投入产出-项目投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestorInOutCompanyList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestorInOutCompanyListCount
    * @Description: 投资人投入产出-项目投入产出统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Integer getInvestorInOutCompanyListCount(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutInvestList
    * @Description: 投资主体、投资人投资统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestInOutInvestList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutInvestListCount
    * @Description: 投资主体、投资人投资统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestInOutInvestListCount(Map<String,String> paramMap);	
	
	
	/**
    * @Title: getInvestInOutRepayList
    * @Description: 投资主体、投资人回报统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	List<Map<String,Object>> getInvestInOutRepayList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutRepayListCount
    * @Description: 投资主体、投资人回报统计列表数据总数
    * @param paramMap
    * @return int
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestInOutRepayListCount(Map<String,String> paramMap);	
}