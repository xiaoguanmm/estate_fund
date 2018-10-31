package com.upjf.fund.service.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.service.business.ProjectStatisticsService;
import com.upjf.fund.service.impl.business.mapper.ProjectStatisticsMapper;


 /**
 * @ClassName: ProjectStatisticsServiceImpl
 * @Description: 财务模块-项目相关信息统计实现类
 * @author wufujing
 * @date 2018年10月9日
 */
@Service("projectStatisticsService")
public class ProjectStatisticsServiceImpl implements ProjectStatisticsService{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProjectStatisticsMapper projectStatisticsMapper ;
	/**
    * @Title: getCashList
    * @Description: 查询现金流管理列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getCashList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getCashListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> cashList = projectStatisticsMapper.getCashList(paramMap);
				resultMap.put("cashList",cashList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}
	
	/**
    * @Title: getProjectCashList
    * @Description: 查询项目现金情况列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectCashList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectCashListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectCashList = projectStatisticsMapper.getProjectCashList(paramMap);
				resultMap.put("projectCashList",projectCashList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}
	
	
	/**
    * @Title: getStockholderList
    * @Description: 查询项目资金情况--投资主体（股东）信息下拉框数据
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public List<Map<String,Object>> getStockholderList(){
		
		return projectStatisticsMapper.getStockholderList();
	}
	
	/**
    * @Title: getProjectIndicatorList
    * @Description: 查询项目基本指标列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectIndicatorList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectIndicatorListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectIndicatorList = projectStatisticsMapper.getProjectIndicatorList(paramMap);
				resultMap.put("projectIndicatorList",projectIndicatorList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}
	
	/**
    * @Title: getProjectProfitList
    * @Description: 查询项目投资收益测算列表数据  
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectProfitList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectProfitListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectProfitList = projectStatisticsMapper.getProjectProfitList(paramMap);
				resultMap.put("projectProfitList",projectProfitList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}	

	
	/**
    * @Title: getProjectReturnList
    * @Description: 查询项目回款情况列表数据  
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectReturnList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectReturnListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectReturnList = projectStatisticsMapper.getProjectReturnList(paramMap);
				resultMap.put("projectReturnList",projectReturnList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}
	
	/**
    * @Title: getProjectInvestmentProgressList
    * @Description: 查询项目投资进展列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectInvestmentProgressList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectInvestmentProgressListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectInvestmentProgressList = projectStatisticsMapper.getProjectInvestmentProgressList(paramMap);
				resultMap.put("projectInvestmentProgressList",projectInvestmentProgressList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}	
	
	
	/**
    * @Title: getProjectSituationTotalList
    * @Description: 查询项目情况（总表）列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectSituationTotalList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = projectStatisticsMapper.getProjectSituationTotalListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectSituationTotalList = projectStatisticsMapper.getProjectSituationTotalList(paramMap);
				resultMap.put("projectSituationTotalList",projectSituationTotalList);
			}
			resultMap.put("total", count);
			resultMap.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("total", 0);
			resultMap.put("code", "1");
		}
		return resultMap;
	}	
}
