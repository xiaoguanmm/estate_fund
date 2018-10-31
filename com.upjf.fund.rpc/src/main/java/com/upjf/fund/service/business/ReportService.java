package com.upjf.fund.service.business;

import java.util.List;
import java.util.Map;


 /**
 * @ClassName: ReportService
 * @Description: 报表统计
 * @author wufujing
 * @date 2018年10月9日
 *
 */

public interface ReportService {

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
	List<Map<String,Object>> getInvestCompanyList(String investType);
			
	/**
    * @Title: getProjectInOutList
    * @Description: 项目投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutCompanyList
    * @Description: 项目投入产出-股东公司统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutCompanyList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutInvestList
    * @Description: 项目投入产出-股东公司投资统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutInvestList(Map<String,String> paramMap);
	
	/**
    * @Title: getProjectInOutRepayList
    * @Description: 项目投入产出-股东公司回报统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getProjectInOutRepayList(Map<String,String> paramMap);
	
	/**
    * @Title: getStockholderInOutList
    * @Description: 项目股东投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getStockholderInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutList
    * @Description: 投资主体、投资人投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestInOutList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestSubInOutCompanyList
    * @Description: 投资主体投入产出-项目投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestSubInOutCompanyList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestorInOutCompanyList
    * @Description: 投资人投入产出-项目投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestorInOutCompanyList(Map<String,String> paramMap);

	/**
    * @Title: getInvestInOutInvestList
    * @Description: 投资主体、投资人投资统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestInOutInvestList(Map<String,String> paramMap);
	
	/**
    * @Title: getInvestInOutRepayList
    * @Description: 投资主体、投资人回报统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	Map<String,Object> getInvestInOutRepayList(Map<String,String> paramMap);
}
