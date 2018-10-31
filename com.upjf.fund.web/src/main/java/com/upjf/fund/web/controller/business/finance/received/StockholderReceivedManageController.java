package com.upjf.fund.web.controller.business.finance.received;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.dto.Received;
import com.upjf.fund.web.aspect.annotation.Log;
import com.upjf.fund.web.utils.file.ExcelUtils;

/**
 * 项目股东回款
 * @company upjf.com
 * @author guantong
 *
 */
@Controller
public class StockholderReceivedManageController extends ReceivedManageController {

	@RequestMapping("/toStockholderReceivedList")
	public String toStockholderReceivedList(){
		
		return STOCKHOLDER_RECEIVED_LIST;
	}
	
	@RequestMapping(value="/queryProjectStockholderPaybackList",produces = "application/json;charset=utf-8")
	public @ResponseBody String  queryProjectStockholderPaybackList(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> conditions = new HashMap<String,String>();
		conditions.put("prjId", request.getParameter("prjId")); //项目
		conditions.put("receiverId", request.getParameter("receiverId"));//回款公司
		conditions.put("receivedStatus", request.getParameter("receivedStatus"));//回款状态
		conditions.put("stockholderType", request.getParameter("stockholderType"));//股东类别
		List<Map<String, Object>> rows = this.receivedManageService.getStockholderPaybacksByConditions(conditions,getOffset(),getPageRows());
		Integer records = this.receivedManageService.countStockholderPaybacksByConditions(conditions);
		return putJsonData(rows, records);
	}
	
	@RequestMapping("toStockholderPaybackList")
	public ModelAndView toStockholderPaybackList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String corp_name = request.getParameter("corp_name");
		String prj_corp_name = request.getParameter("prj_corp_name");
		String project_name = request.getParameter("project_name");
		String sourceModule = request.getParameter("sourceModule");										//请求来源模块:trade表示为请求业务管理,用于控制显示共用的界面新增修改删除按钮
		if(StringUtils.isNotEmpty(corp_name)){
			try {
				corp_name = URLDecoder.decode(corp_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(prj_corp_name)){
			try {
				prj_corp_name = URLDecoder.decode(prj_corp_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(project_name)){
			try {
				project_name = URLDecoder.decode(project_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String project_id = request.getParameter("project_id");
		String receiver_id = request.getParameter("corp_id");
		Received received = new Received();
		received.setPrjId(project_id);
		received.setReceiverId(receiver_id);
		received.setReceivedType(EstateFundConstants.PAYBACK_TYPE_STOCKHOLDER);
		Map<String,Object> amountMap = this.receivedManageService.statisticsAmountByParmas(received);
		mv.addObject("amount", amountMap);
		String received_status = this.receivedManageService.getReceivedStatus(received);
		mv.addObject("received_status",received_status);
		String project_status = this.projectInfoService.getProjectStatusByProjectId(project_id);
		mv.addObject("project_status",project_status);
		mv.addObject("project_id", project_id);
		mv.addObject("corp_id", receiver_id);
		mv.addObject("business_prj_id", request.getParameter("business_prj_id"));
		mv.addObject("corporation_info_id", request.getParameter("corporation_info_id"));
		mv.addObject("corp_name", corp_name);
		mv.addObject("prj_corp_name", prj_corp_name);
		mv.addObject("project_name", project_name);
		mv.addObject("sourceModule", sourceModule);
		mv.setViewName(STOCKHOLDER_RECEIVED_HISTORY_LIST);
		return mv;
		
	}
	
	/**
	 * 查询项目股东回款历史记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryStockholderPaybackList",produces = "application/json;charset=utf-8")
	public @ResponseBody String queryStockholderPaybackList(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return queryHistoryPaybacksList(received, request, response);
	}
	
	/**
	 * 新增回款信息
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-项目股东回款",content="新增/修改 项目股东回款记录")
	@RequestMapping("/addStockholderPaybackRecord")
	public @ResponseBody ModelMap addStockholderPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return addPaybackRecord(received, request, response);
	}
	
	/**
	 * 查看项目股东回款附件列表
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryStockholderPaybackAccessoryList", produces = "application/json;charset=utf-8")
	protected @ResponseBody String queryStockholderPaybackAccessoryList(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return queryPaybackAccessoryList(pid, request, response);
	}
	
	/**
	 * 删除项目股东回款记录
	 * @param received
	 * @param request
	 * @param response
	 * @return
	 */
	@Log(module="财务管理-项目股东回款",content="删除项目股东回款记录")
	@RequestMapping("/delStockholderPaybackRecord")
	public @ResponseBody ModelMap delStockholderPaybackRecord(Received received,HttpServletRequest request,HttpServletResponse response){
		
		return delPaybackRecord(received, request, response);
	}
	
	/**
	 * 上传项目股东回款收款凭证
	 * @return
	 */
	@Log(module="财务管理-项目股东回款",content="上传股东回款收款凭证")
	@RequestMapping("/uploadStockholderPaybackAccessory")
	public @ResponseBody ModelMap uploadStockholderPaybackAccessory(String pid,HttpServletRequest request,HttpServletResponse response){
		
		return uploadPaybackAccessory(pid,EstateFundModelConstants.STOCKHOLDER_PAYBACK_ACCESSORY,EstateFundConstants.PAYMENT_ACCESSORY_PAY, request, response);
	}
	
	/**
	 * 导出项目股东回款记录列表
	 * @param request
	 * @param response
	 */
	@Log(module="财务管理-项目股东回款",content="导出项目股东回款记录列表")
	@RequestMapping("/exportStockholderPaybackData")
	public void exportStockholderPaybackData(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> conditions = new HashMap<String,String>();
		conditions.put("prjId", request.getParameter("prjId")); //项目
		conditions.put("receiverId", request.getParameter("receiverId"));//回款公司
		conditions.put("receivedStatus", request.getParameter("receivedStatus"));//回款状态
		conditions.put("stockholderType", request.getParameter("stockholderType"));//股东类别
		List<Map<String, Object>> rows = this.receivedManageService.getStockholderPaybacksByConditions(conditions,null,null);
		
		String[] title = {"项目名称","项目公司","股东类别","回款公司","注册资本(万元)","持股比例","投入本金(万元)	","应回利润(万元)","已回款本金","已回款利润","回款状态"};
		String[] filedNames = {"project_name","prj_corp_name","stockholder_type","corp_name","register_capital","hold_stock_ratio","pay_amount_total","received_profit","receiver_amount","profit","received_status"};
		String fileName = "项目股东回款.xls";
		try {
			ExcelUtils.exportDataToExcel(rows, title, filedNames, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出项目股东回款历史记录列表
	 * @param request
	 * @param response
	 */
	@Log(module="财务管理-项目股东回款",content="导出项目股东回款历史记录列表")
	@RequestMapping("/exportStockholderHistoryPaybackData")
	public void exportStockholderHistoryPaybackData(Received received, HttpServletRequest request, HttpServletResponse response){
		
		List<Map<String, Object>> rows = this.receivedManageService.getHistoryPaybacksByConditions(received,null,null);
		String[] title = {"回款时间","回款账号","回款本金(万元)","回款利润(万元)","项目名称","付款公司","付款账号","回款备注","回款录入人","回款录入时间"};
		String[] filedNames = {"receiver_date","receiver_account","receiver_amount","profit","project_name","prj_corp_name","pay_account","reveiver_remark","create_user","create_date"};
		String fileName = "项目股东回款历史.xls";
		try {
			ExcelUtils.exportDataToExcel(rows, title, filedNames, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
