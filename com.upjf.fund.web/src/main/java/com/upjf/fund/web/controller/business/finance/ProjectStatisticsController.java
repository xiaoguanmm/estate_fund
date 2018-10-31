package com.upjf.fund.web.controller.business.finance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upjf.fund.service.business.ProjectStatisticsService;
import com.upjf.fund.service.business.ReportService;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.file.ExcelUtils;


/**
    * @ClassName: ProjectStatisticsController
    * @Description: 财务模块-项目信息统计等
    * @author wufujing
    * @date 2018年10月09日
    *
 */
@Controller
@RequestMapping("/finance")
public class ProjectStatisticsController extends BaseController{
	
	@Autowired
	private ProjectStatisticsService projectStatisticsService;
	@Autowired
	private ReportService reportService;
	
	/** 现金流管理列表页*/
	@RequestMapping(value="/cashFlowListPage")
	public String cashFlowListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		model.addAttribute("stockholderList",stockholderList);
		return "/finance/project_cash_flow";
	}
	
	/** 现金流管理列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportCashFlowList")
	public void exportCashFlowList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> cashList = null;
		try {
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String occurId = request.getParameter("occurId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("occurId", occurId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			Map<String,Object> resultMap  = projectStatisticsService.getCashList(params);
			
			if(resultMap != null){
				cashList = (List<Map<String,Object>>) resultMap.get("cashList");
				if(cashList != null && cashList.size() > 0){
					for (int i = 0; i < cashList.size(); i++) {
						Map<String,Object> itemMap = cashList.get(i);
						if(itemMap != null){
							String cash = "";//事项
							String type = String.valueOf(itemMap.get("type"));
							String stockholderName = String.valueOf(itemMap.get("stockholder_name"));;
							String occurName = String.valueOf(itemMap.get("occur_name"));;
							String receiverAmount = String.valueOf(itemMap.get("receiver_amount"));
							String totalAmount = String.valueOf(itemMap.get("total_amount"));
							String inAmount = "";
							String outAmount = "";
							if("1".equals(type)){
								cash = stockholderName +"  出资到 "+occurName;
								inAmount = "--";
								outAmount = receiverAmount;
							}else if("2".equals(type)){
								cash = occurName +"  出资到  "+stockholderName;
								inAmount = receiverAmount;
								outAmount = "--";
							}else if("3".equals(type)){
								cash = occurName+"  付费到  "+stockholderName;
								inAmount = receiverAmount;
								outAmount = "--";
							}else if("4".equals(type)){
								cash = stockholderName +"  付费到  "+occurName;
								inAmount = "--";
								outAmount = receiverAmount;
							}
							if(StringUtils.isBlank(inAmount) || "null".equals(inAmount)){
								inAmount = "0";
							}
							if(StringUtils.isBlank(outAmount) || "null".equals(outAmount)){
								outAmount = "0";
							}
							if(StringUtils.isBlank(totalAmount) || "null".equals(totalAmount)){
								totalAmount = "0";
							}
							itemMap.put("cash", cash);
							itemMap.put("in_amount", inAmount);
							itemMap.put("out_amount", outAmount);
							itemMap.put("total_amount", totalAmount);
						}
					}
					
					String[] title = {"项目名称", "有限合伙(项目股东)", "发生主体","事项", "账户收入(万元)","账户支出(万元)","现金余额(万元)","发生时间","备注"};
					String[] filedNames = {"project_name", "stockholder_name", "occur_name","cash", "in_amount","out_amount","total_amount","receiver_date","reveiver_remark"};
					String fileName = "现金流管理.xls";
					
					ExcelUtils.exportDataToExcel(cashList, title, filedNames, fileName, request, response);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 现金流管理列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cashFlowList")
	public void cashFlowList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> cashList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String occurId = request.getParameter("occurId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("occurId", occurId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getCashList(params);
			
			if(resultMap != null){
				cashList = (List<Map<String,Object>>) resultMap.get("cashList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, cashList, total,pageSize);
	}
	
	
	/** 项目现金情况列表页*/
	@RequestMapping(value="/projectCashListPage")
	public String projectCashListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.addAttribute("stockholderList",stockholderList);
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_cash";
	}
	
	/** 项目现金情况列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectCashList")
	public void projectCashList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectCashList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String projectProgress = request.getParameter("projectProgress");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("projectProgress", projectProgress);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectCashList(params);
			
			if(resultMap != null){
				projectCashList = (List<Map<String,Object>>) resultMap.get("projectCashList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectCashList, total,pageSize);
	}
	
	/** 项目现金情况列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectCashList")
	public void exportProjectCashList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> projectCashList = null;
		try {
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String projectProgress = request.getParameter("projectProgress");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(StringUtils.isNotBlank(startDate)){
				startDate = startDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(endDate)){
				endDate = endDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("projectProgress", projectProgress);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectCashList(params);
			
			if(resultMap != null){
				projectCashList = (List<Map<String,Object>>) resultMap.get("projectCashList");
				if(projectCashList != null && projectCashList.size() > 0){
					for (int i = 0; i < projectCashList.size(); i++) {
						Map<String,Object> itemMap = projectCashList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							BigDecimal needAmount = BigDecimal.ZERO;
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							needAmount = (itemMap.get("plan_amount") != null ? (BigDecimal)itemMap.get("plan_amount") : BigDecimal.ZERO).subtract(itemMap.get("actual_amount") != null ? (BigDecimal)itemMap.get("actual_amount") : BigDecimal.ZERO);
							
							itemMap.put("hold_stock_ratio", holdStockRatio);
							itemMap.put("need_amount", needAmount);
						}
					}
				}
				String[] title = {"项目名称", "出资主体", "出资主体投资规模（万元）","上市公司投资规模（万元）", "投资起始日","期限","投资方式","持股比例","项目现状","出资主体已投规模（万元）","待投规模（万元）","回款情况本金（万元）","回款情况收益（万元）"};
				String[] filedNames = {"project_name", "stockholder_name", "investor_scope_amount","contributive_amount", "invest_start_date","term","invest_type","hold_stock_ratio","project_progress_name","actual_amount","need_amount","receiver_amount","profit_amount"};
				String fileName = "项目现金管理.xls";
				
				ExcelUtils.exportDataToExcel(projectCashList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 项目现金基本指标列表页*/
	@RequestMapping(value="/projectIndicatorPage")
	public String projectIndicatorListPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_indicator";
	}
	
	/** 项目现金基本指标列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectIndicatorList")
	public void projectIndicatorList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectIndicatorList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String landGetWay = request.getParameter("landGetWay");
			String projectProgress = request.getParameter("projectProgress");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("landGetWay", landGetWay);
			params.put("projectProgress", projectProgress);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectIndicatorList(params);
			
			if(resultMap != null){
				projectIndicatorList = (List<Map<String,Object>>) resultMap.get("projectIndicatorList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectIndicatorList, total,pageSize);
	}
	
	/** 项目基本指标列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectIndicatorList")
	public void exportProjectIndicatorList(HttpServletRequest request,HttpServletResponse response){
		
	    List<Map<String,Object>> projectIndicatorList = null;
		try {
			
			String projectId = request.getParameter("projectId");
			String landGetWay = request.getParameter("landGetWay");
			String projectProgress = request.getParameter("projectProgress");

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("landGetWay", landGetWay);
			params.put("projectProgress", projectProgress);
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectIndicatorList(params);
			
			if(resultMap != null){
				projectIndicatorList = (List<Map<String,Object>>) resultMap.get("projectIndicatorList");
				if(projectIndicatorList != null && projectIndicatorList.size() > 0){
					for (int i = 0; i < projectIndicatorList.size(); i++) {
						Map<String,Object> itemMap = projectIndicatorList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							String projectCubageRate = String.valueOf(itemMap.get("project_cubage_rate"));
							
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							itemMap.put("hold_stock_ratio", holdStockRatio);
							
							
							if(StringUtils.isBlank(projectCubageRate) || "null".equals(projectCubageRate)){
								projectCubageRate = "0";
							}
							projectCubageRate = projectCubageRate +"%";
							
							itemMap.put("project_cubage_rate", projectCubageRate);
						}
					}
				}
				String[] title = {"项目名称", "更新单元范围面积（㎡）", "拟拆除建筑面积（㎡）","可开发建设用地（㎡）", "容积率","建筑面积（㎡）","计容可售建筑面积（㎡）","总货值（万元）","项目总投（万元）","出资主体","出资主体持股比例","出资主体投资规模（万元）","规划用地性质","土地获取方式","项目现状"};
				String[] filedNames = {"project_name", "update_range_per_area", "prepare_dismantle_area", "develop_build_area","project_cubage_rate","build_area","cubage_sale_area","all_price","project_all_put_into","stockholder_name","hold_stock_ratio","hold_stock_amount","project_land_quality","land_get_way_name","project_progress_name"};
				String fileName = "项目基本指标.xls";
				
				ExcelUtils.exportDataToExcel(projectIndicatorList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 项目投资收益测算列表页*/
	@RequestMapping(value="/projectProfitPage")
	public String projectProfitPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.addAttribute("stockholderList",stockholderList);
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_profit";
	}
	
	/** 项目投资收益测算列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectProfitList")
	public void projectProfitList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectProfitList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectProfitList(params);
			
			if(resultMap != null){
				projectProfitList = (List<Map<String,Object>>) resultMap.get("projectProfitList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectProfitList, total,pageSize);
	}
	
	/** 项目投资收益测算列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectProfitList")
	public void exportProjectProfitList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> projectProfitList = null;
		try {
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectProfitList(params);
			
			if(resultMap != null){
				projectProfitList = (List<Map<String,Object>>) resultMap.get("projectProfitList");
				if(projectProfitList != null && projectProfitList.size() > 0){
					for (int i = 0; i < projectProfitList.size(); i++) {
						Map<String,Object> itemMap = projectProfitList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							String expectEarningsRate = String.valueOf(itemMap.get("expect_earnings_rate"));
							String investorExpectEarningsRate= String.valueOf(itemMap.get("investor_expect_earnings_rate"));
							
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							itemMap.put("hold_stock_ratio", holdStockRatio);
							
							
							if(StringUtils.isBlank(expectEarningsRate) || "null".equals(expectEarningsRate)){
								expectEarningsRate = "0";
							}
							expectEarningsRate = expectEarningsRate +"%";
							itemMap.put("expect_earnings_rate", expectEarningsRate);
							
							if(StringUtils.isBlank(investorExpectEarningsRate) || "null".equals(investorExpectEarningsRate)){
								investorExpectEarningsRate = "0";
							}
							investorExpectEarningsRate = investorExpectEarningsRate +"%";
							itemMap.put("investor_expect_earnings_rate", investorExpectEarningsRate);
						}
					}
				}
				String[] title = {"项目名称", "出资主体", "出资主体投资规模(万元)","出资主体持股比例", "项目预期收益(万元)","项目预期收益率","出资主体投资收益(万元)","出资主体投资收益率"};
				String[] filedNames = {"project_name", "stockholder_name", "investor_scope_amount", "hold_stock_ratio","expect_earnings","expect_earnings_rate","investor_expect_earnings","investor_expect_earnings_rate"};
				String fileName = "项目投资收益.xls";
				
				ExcelUtils.exportDataToExcel(projectProfitList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 项目回款情况列表页*/
	@RequestMapping(value="/projectReturnPage")
	public String projectReturnPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.addAttribute("stockholderList",stockholderList);
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_return";
	}
	
	/** 项目回款情况列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectReturnList")
	public void projectReturnList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectReturnList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String investStartDate = request.getParameter("investStartDate");
			String investEndDate = request.getParameter("investEndDate");
			String returnStartDate = request.getParameter("returnStartDate");
			String returnEndDate = request.getParameter("returnEndDate");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}
			if(StringUtils.isNotBlank(investStartDate)){
				investStartDate = investStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(investEndDate)){
				investEndDate = investEndDate +" 23:59:59";
			}
			if(StringUtils.isNotBlank(returnStartDate)){
				returnStartDate = returnStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(returnEndDate)){
				returnEndDate = returnEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("investStartDate", investStartDate);
			params.put("investEndDate", investEndDate);
			params.put("returnStartDate", returnStartDate);
			params.put("returnEndDate", returnEndDate);
			
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectReturnList(params);
			
			if(resultMap != null){
				projectReturnList = (List<Map<String,Object>>) resultMap.get("projectReturnList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectReturnList, total,pageSize);
	}
	
	/** 项目回款情况列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectReturnList")
	public void exportprojectReturnList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> projectReturnList = null;
		try {
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String investStartDate = request.getParameter("investStartDate");
			String investEndDate = request.getParameter("investEndDate");
			String returnStartDate = request.getParameter("returnStartDate");
			String returnEndDate = request.getParameter("returnEndDate");
			if(StringUtils.isNotBlank(investStartDate)){
				investStartDate = investStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(investEndDate)){
				investEndDate = investEndDate +" 23:59:59";
			}
			if(StringUtils.isNotBlank(returnStartDate)){
				returnStartDate = returnStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(returnEndDate)){
				returnEndDate = returnEndDate +" 23:59:59";
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("investStartDate", investStartDate);
			params.put("investEndDate", investEndDate);
			params.put("returnStartDate", returnStartDate);
			params.put("returnEndDate", returnEndDate);
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectReturnList(params);
			
			if(resultMap != null){
				projectReturnList = (List<Map<String,Object>>) resultMap.get("projectReturnList");
				if(projectReturnList != null && projectReturnList.size() > 0){
					for (int i = 0; i < projectReturnList.size(); i++) {
						Map<String,Object> itemMap = projectReturnList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							itemMap.put("hold_stock_ratio", holdStockRatio);
							
						}
					}
				}
				String[] title = {"项目名称", "出资主体", "出资主体投资规模（万元）","投资起始日", "期限","投资方式","出资主体持股比例","回款时间","回款金额（合计）"};
				String[] filedNames = {"project_name", "stockholder_name", "investor_scope_amount", "invest_start_date","term","invest_type","hold_stock_ratio","receiver_date_year","total_receiver_amount"};
				String fileName = "项目回款情况.xls";
				
				ExcelUtils.exportDataToExcel(projectReturnList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 项目投资进展列表页*/
	@RequestMapping(value="/projectInvestmentProgressPage")
	public String projectInvestmentProgressPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.addAttribute("stockholderList",stockholderList);
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_investment_progress";
	}
	
	/** 项目投资进展列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectInvestmentProgressList")
	public void projectInvestmentProgressList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectInvestmentProgressList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String projectProgress = request.getParameter("projectProgress");
			String investStartDate = request.getParameter("investStartDate");
			String investEndDate = request.getParameter("investEndDate");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}

			if(StringUtils.isNotBlank(investStartDate)){
				investStartDate = investStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(investEndDate)){
				investEndDate = investEndDate +" 23:59:59";
			}
			
			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("projectProgress", projectProgress);
			params.put("investStartDate", investStartDate);
			params.put("investEndDate", investEndDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectInvestmentProgressList(params);
			
			if(resultMap != null){
				projectInvestmentProgressList = (List<Map<String,Object>>) resultMap.get("projectInvestmentProgressList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectInvestmentProgressList, total,pageSize);
	}
	
	/** 项目投资进展列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectInvestmentProgressList")
	public void exportProjectInvestmentProgressList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> projectInvestmentProgressList = null;
		try {
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			String projectProgress = request.getParameter("projectProgress");
			String investStartDate = request.getParameter("investStartDate");
			String investEndDate = request.getParameter("investEndDate");

			if(StringUtils.isNotBlank(investStartDate)){
				investStartDate = investStartDate +" 00:00:00";
			}
			
			if(StringUtils.isNotBlank(investEndDate)){
				investEndDate = investEndDate +" 23:59:59";
			}
			
			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("projectProgress", projectProgress);
			params.put("investStartDate", investStartDate);
			params.put("investEndDate", investEndDate);
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectInvestmentProgressList(params);
			
			if(resultMap != null){
				projectInvestmentProgressList = (List<Map<String,Object>>) resultMap.get("projectInvestmentProgressList");
				if(projectInvestmentProgressList != null && projectInvestmentProgressList.size() > 0){
					for (int i = 0; i < projectInvestmentProgressList.size(); i++) {
						Map<String,Object> itemMap = projectInvestmentProgressList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							String expectEarningsRate = String.valueOf(itemMap.get("expect_earnings_rate"));
							String investorExpectEarningsRate= String.valueOf(itemMap.get("investor_expect_earnings_rate"));
							
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							itemMap.put("hold_stock_ratio", holdStockRatio);
							
							if(StringUtils.isBlank(expectEarningsRate) || "null".equals(expectEarningsRate)){
								expectEarningsRate = "0";
							}
							expectEarningsRate = expectEarningsRate +"%";
							itemMap.put("expect_earnings_rate", expectEarningsRate);
							
							if(StringUtils.isBlank(investorExpectEarningsRate) || "null".equals(investorExpectEarningsRate)){
								investorExpectEarningsRate = "0";
							}
							investorExpectEarningsRate = investorExpectEarningsRate +"%";
							itemMap.put("investor_expect_earnings_rate", investorExpectEarningsRate);
							
							BigDecimal needAmount = (itemMap.get("plan_amount") != null ? (BigDecimal)itemMap.get("plan_amount") : BigDecimal.ZERO).subtract(itemMap.get("actual_amount") != null ? (BigDecimal)itemMap.get("actual_amount") : BigDecimal.ZERO);
							itemMap.put("need_amount", needAmount);
						}
					}
				}
				String[] title = {"项目名称", "出资主体", "出资主体投资规模（万元）","上市公司投资规模（万元）", "项目总投（万元）","投资起始日","期限(月)","持股比例","总货值（万元）","项目预期收益(万元)", "项目预期收益率", "出资主体投资收益（万元）","出资主体投资收益率", "出资主体已投规模（万元）","待投规模（万元）","回款情况本金（万元）","回款情况收益（万元）","项目现状"};
				String[] filedNames = {"project_name", "stockholder_name", "investor_scope_amount", "contributive_amount","project_all_put_into","invest_start_date","term","hold_stock_ratio","all_price","expect_earnings","expect_earnings_rate","investor_expect_earnings","investor_expect_earnings_rate","actual_amount","need_amount","receiver_amount","profit_amount","project_progress_name"};
				String fileName = "项目投资进展.xls";
				
				ExcelUtils.exportDataToExcel(projectInvestmentProgressList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/** 项目情况（总表）列表页*/
	@RequestMapping(value="/projectSituationTotalPage")
	public String projectSituationTotalPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		List<Map<String,Object>> stockholderList = projectStatisticsService.getStockholderList();
		model.addAttribute("stockholderList",stockholderList);
		List<Map<String,Object>> projectList = reportService.getProjectList();
		model.addAttribute("projectList",projectList);
		
		return "/finance/project_situation_total";
	}
	
	/** 项目情况（总表）列表数据*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projectSituationTotalList")
	public void projectSituationTotalList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<Map<String,Object>> projectSituationTotalList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = projectStatisticsService.getProjectSituationTotalList(params);
			
			if(resultMap != null){
				projectSituationTotalList = (List<Map<String,Object>>) resultMap.get("projectSituationTotalList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, projectSituationTotalList, total,pageSize);
	}
	
	/** 项目情况（总表）列表数据导出*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportProjectSituationTotalList")
	public void exportProjectSituationTotalList(HttpServletRequest request,HttpServletResponse response){
	    
	    List<Map<String,Object>> projectSituationTotalList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String projectId = request.getParameter("projectId");
			String stockholderId = request.getParameter("stockholderId");

			Map<String,String> params = new HashMap<String, String>();
			params.put("projectId", projectId);
			params.put("stockholderId", stockholderId);
			Map<String,Object> resultMap  = projectStatisticsService.getProjectSituationTotalList(params);
			
			if(resultMap != null){
				projectSituationTotalList = (List<Map<String,Object>>) resultMap.get("projectSituationTotalList");
				if(projectSituationTotalList != null && projectSituationTotalList.size() > 0){
					for (int i = 0; i < projectSituationTotalList.size(); i++) {
						Map<String,Object> itemMap = projectSituationTotalList.get(i);
						if(itemMap != null){
							String holdStockRatio = String.valueOf(itemMap.get("hold_stock_ratio"));
							String expectEarningsRate = String.valueOf(itemMap.get("expect_earnings_rate"));
							String investorExpectEarningsRate= String.valueOf(itemMap.get("investor_expect_earnings_rate"));
							
							if(StringUtils.isBlank(holdStockRatio) || "null".equals(holdStockRatio)){
								holdStockRatio = "0";
							}
							holdStockRatio = holdStockRatio +"%";
							itemMap.put("hold_stock_ratio", holdStockRatio);
							
							if(StringUtils.isBlank(expectEarningsRate) || "null".equals(expectEarningsRate)){
								expectEarningsRate = "0";
							}
							expectEarningsRate = expectEarningsRate +"%";
							itemMap.put("expect_earnings_rate", expectEarningsRate);
							
							if(StringUtils.isBlank(investorExpectEarningsRate) || "null".equals(investorExpectEarningsRate)){
								investorExpectEarningsRate = "0";
							}
							investorExpectEarningsRate = investorExpectEarningsRate +"%";
							itemMap.put("investor_expect_earnings_rate", investorExpectEarningsRate);
						}
					}
				}
				String[] title = {"项目名称", "项目总投（万元）", "项目总货值（万元）","项目预期收益（万元）", "项目预期收益率","出资主体","持股比例","投资方式","上市公司出资规模（万元）","出资主体投资收益（万元）","出资主体投资收益率"};
				String[] filedNames = {"project_name", "project_all_put_into", "all_price", "expect_earnings","expect_earnings_rate","stockholder_name","hold_stock_ratio","invest_type","contributive_amount","investor_expect_earnings","investor_expect_earnings_rate"};
				String fileName = "项目投资进展.xls";
				
				ExcelUtils.exportDataToExcel(projectSituationTotalList, title, filedNames, fileName, request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
