package com.upjf.fund.service.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upjf.fund.service.business.ReportService;
import com.upjf.fund.service.impl.business.mapper.ReportMapper;


 /**
 * @ClassName: ReportServiceImpl
 * @Description: 报表统计等查询实现类
 * @author wufujing
 * @date 2018年10月9日
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReportMapper reportMapper ;
	
	/**
    * @Title: getProjectList
    * @Description: 项目信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public List<Map<String,Object>> getProjectList(){
		return reportMapper.getProjectList();
	}
		
	/**
    * @Title: getProjectCompanyList
    * @Description: 项目公司信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public List<Map<String,Object>> getProjectCompanyList(){
		return reportMapper.getProjectCompanyList();
	}
	
	/**
    * @Title: getInvestCompanyList
    * @Description: 投资人、投资主体信息下拉框数据
    * @return List<Map<String,Object>>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public List<Map<String,Object>> getInvestCompanyList(String investType){
		return reportMapper.getInvestCompanyList(investType);
	}
		
	/**
    * @Title: getProjectInOutList
    * @Description: 项目投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectInOutList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getProjectInOutListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectInOutList= reportMapper.getProjectInOutList(paramMap);
				resultMap.put("projectInOutList",projectInOutList);
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
    * @Title: getProjectInOutCompanyList
    * @Description: 项目投入产出-股东公司统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectInOutCompanyList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getProjectInOutCompanyListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> projectInOutCompanyList= reportMapper.getProjectInOutCompanyList(paramMap);
				resultMap.put("projectInOutCompanyList",projectInOutCompanyList);
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
    * @Title: getProjectInOutInvestList
    * @Description: 项目投入产出-股东公司投资统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectInOutInvestList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,Object> countMap = reportMapper.getProjectInOutInvestListCount(paramMap);
			int count = countMap != null ? Integer.parseInt(countMap.get("count")+"") : 0;
			if(count > 0 ){
				String totalAmount = countMap != null ? countMap.get("pay_amount")+"" : "0";
				resultMap.put("totalAmount",totalAmount);
				
				List<Map<String,Object>> projectInOutInvestList= reportMapper.getProjectInOutInvestList(paramMap);
				resultMap.put("projectInOutInvestList",projectInOutInvestList);
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
    * @Title: getProjectInOutRepayList
    * @Description: 项目投入产出-股东公司回报统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getProjectInOutRepayList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,Object> countMap = reportMapper.getProjectInOutRepayListCount(paramMap);
			int count = countMap != null ? Integer.parseInt(countMap.get("count")+"") : 0;
			if(count > 0 ){
				String totalAmount = countMap != null ? countMap.get("receiver_amount")+"" : "0";
				resultMap.put("totalAmount",totalAmount);
				
				List<Map<String,Object>> projectInOutRepayList= reportMapper.getProjectInOutRepayList(paramMap);
				resultMap.put("projectInOutRepayList",projectInOutRepayList);
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
    * @Title: getStockholderInOutList
    * @Description: 项目股东投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getStockholderInOutList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getStockholderInOutListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> stockholderInOutList= reportMapper.getStockholderInOutList(paramMap);
				resultMap.put("stockholderInOutList",stockholderInOutList);
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
    * @Title: getInvestInOutList
    * @Description: 投资主体、投资人投入产出统计列表数据 
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getInvestInOutList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getInvestInOutListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> investInOutList= reportMapper.getInvestInOutList(paramMap);
				resultMap.put("investInOutList",investInOutList);
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
    * @Title: getInvestSubInOutCompanyList
    * @Description: 投资主体投入产出-项目投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getInvestSubInOutCompanyList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getInvestSubInOutCompanyListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> investSubInOutCompanyList= reportMapper.getInvestSubInOutCompanyList(paramMap);
				resultMap.put("investSubInOutCompanyList",investSubInOutCompanyList);
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
    * @Title: getInvestorInOutCompanyList
    * @Description: 投资人投入产出-项目投入产出统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getInvestorInOutCompanyList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			int count = reportMapper.getInvestorInOutCompanyListCount(paramMap);
			if(count > 0 ){
				List<Map<String,Object>> investorInOutCompanyList= reportMapper.getInvestorInOutCompanyList(paramMap);
				resultMap.put("investorInOutCompanyList",investorInOutCompanyList);
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
    * @Title: getInvestInOutInvestList
    * @Description: 投资主体、投资人投资统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getInvestInOutInvestList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,Object> countMap = reportMapper.getInvestInOutInvestListCount(paramMap);
			int count = countMap != null ? Integer.parseInt(countMap.get("count")+"") : 0;
			if(count > 0 ){
				String totalAmount = countMap != null ? countMap.get("pay_amount")+"" : "0";
				resultMap.put("totalAmount",totalAmount);
				
				List<Map<String,Object>> investInOutInvestList= reportMapper.getInvestInOutInvestList(paramMap);
				resultMap.put("investInOutInvestList",investInOutInvestList);
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
    * @Title: getInvestInOutRepayList
    * @Description: 投资主体、投资人回报统计列表数据
    * @param paramMap
    * @return Map<String,Object>    返回类型
    * @author wufujing
    * @date 2018年10月9日
    * @throws
	 */
	public Map<String,Object> getInvestInOutRepayList(Map<String,String> paramMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String,Object> countMap = reportMapper.getInvestInOutRepayListCount(paramMap);
			int count = countMap != null ? Integer.parseInt(countMap.get("count")+"") : 0;
			if(count > 0 ){
				String totalAmount = countMap != null ? countMap.get("receiver_amount")+"" : "0";
				resultMap.put("totalAmount",totalAmount);
				
				List<Map<String,Object>> investInOutRepayList= reportMapper.getInvestInOutRepayList(paramMap);
				resultMap.put("investInOutRepayList",investInOutRepayList);
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
