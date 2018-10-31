package com.upjf.fund.web.controller.system;

import java.net.URLDecoder;
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

import com.upjf.fund.dto.LogInfo;
import com.upjf.fund.service.system.LogInfoService;
import com.upjf.fund.web.controller.base.BaseController;


/**
    * @ClassName: LogInfoController
    * @Description: 系统操作日志
    * @author wufujing
    * @date 2018年5月31日
    *
 */
@Controller
@RequestMapping("/system")
public class LogInfoController extends BaseController{
	
	@Autowired
	private LogInfoService logInfoService;
	
	/** 系统操作日志列表页*/
	@RequestMapping(value="/logInfoPage")
	public String logInfoPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		return "/system/logInfo";
	}
	
	/** 系统操作日志列表页*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/logInfoList")
	public void logInfoList(HttpServletRequest request,HttpServletResponse response){
		
	    int total = 0;
	    int pageSize = 10;
	    int offset = 0;
	    
	    List<LogInfo> logInfoList = null;
		try {
			String page = request.getParameter("page");//分页插件返回页码
			String rows = request.getParameter("rows");//分页插件返回每页总数
			
			String operatorName = request.getParameter("operatorName");
			String moduleName = request.getParameter("moduleName");
			String remark = request.getParameter("remark");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			if(!StringUtils.isBlank(operatorName)){
				operatorName = URLDecoder.decode(operatorName, "UTF-8");
			}
			if(!StringUtils.isBlank(moduleName)){
				moduleName = URLDecoder.decode(moduleName, "UTF-8");
			}
			if(!StringUtils.isBlank(remark)){
				remark = URLDecoder.decode(remark, "UTF-8");
			}
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(rows)) {
				offset = ((Integer.valueOf(page) > 0 ? Integer.valueOf(page) : 1) - 1) * Integer.valueOf(rows);
				pageSize = Integer.valueOf(rows);
			}

			Map<String,String> params = new HashMap<String, String>();
			params.put("operatorName", operatorName);
			params.put("moduleName", moduleName);
			params.put("remark", remark);
			params.put("beginDate", beginDate);
			params.put("endDate", endDate);
			params.put("offset", String.valueOf(offset));
			params.put("pageSize", String.valueOf(pageSize));
			
			Map<String,Object> resultMap  = logInfoService.getLogInfoListPage(params);
			
			if(resultMap != null){
				logInfoList = (List<LogInfo>) resultMap.get("logInfoList");
				total = (int) resultMap.get("total");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	    // 输出
	    outPutPage(response, logInfoList, total,pageSize);
	}
	
	/** 获取系统操作日志详细*/
	@RequestMapping(value="/getLogInfo")
	public void getLogInfo(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			String logInfoId = request.getParameter("logInfoId");
			if(!StringUtils.isBlank(logInfoId)){
				
				LogInfo logInfo  = logInfoService.getLogInfo(logInfoId);
				if(logInfo != null){
					resultMap.put("code", "0");
					resultMap.put("msg","获取系统操作日志详情成功！");
					resultMap.put("logInfo", logInfo);
				}else{
					resultMap.put("code", "1");
					resultMap.put("msg","获取系统操作日志详情失败！");
				}
			}else{
				resultMap.put("code", "1");
            	resultMap.put("msg","数据异常，缺少系统操作日志ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "1");
			resultMap.put("msg","操作失败！");
		}
		outPutJson(resultMap, response);
	}
	
	
}
