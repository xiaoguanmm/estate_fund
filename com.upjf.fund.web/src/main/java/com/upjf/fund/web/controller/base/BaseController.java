package com.upjf.fund.web.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upjf.fund.constants.EstateFundConstants;
import com.upjf.fund.dto.LogInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.system.LogInfoService;
import com.upjf.fund.utils.IPUtils;
import com.upjf.fund.utils.UuidGenerator;

public class BaseController {
	
	@Autowired
	private LogInfoService logInfoService;
	
	/**当前页*/
	private String page;
	/**偏移量*/
	private Integer offset;
	/**每页显示数量*/
	private Integer pageRows;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Map<Object,Object> jsonMap = new HashMap<Object,Object>();
	
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	      binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/** 基于@ExceptionHandler异常处理 */  
	@ExceptionHandler
	@ResponseBody
	public Object exp(HttpServletRequest request, Exception e) {
		Map<String, Object> result = initResult();
		//权限异常
		if("org.apache.shiro.authz.UnauthorizedException".equals(e.getClass().getName())){
			result.put("code", 400);
			result.put("msg", "没有操作权限！");
		}else{
			result.put(EstateFundConstants.RESULT_SUCCESS_KEY, false);
			result.put(EstateFundConstants.RESULT_CODE_KEY, 400);
			result.put(EstateFundConstants.RESULT_MSG_KEY, e.getMessage());
			request.setAttribute("ex", e);
			logger.error("系统运行出错！", e);
		}
		return result;
	}
	
	/**
	 * @Description: 初始化返回操作结果
	 * @return 返回成功操作结果
	 * @author: dulin
	 * @date: 2017年9月12日 上午11:51:29
	 */
	protected Map<String, Object> initResult() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(EstateFundConstants.RESULT_SUCCESS_KEY, true);
		result.put(EstateFundConstants.RESULT_CODE_KEY, 200);
		result.put(EstateFundConstants.RESULT_MSG_KEY, "操作成功");
		return result;
	}
    
    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public com.upjf.fund.dto.UserInfo getCurrUser(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
    	return userInfo;
    }
	
   	
	public void outPutJson(Map<String, Object> returnMap, HttpServletResponse response) {
		try {
			String retrunJson = JSON.toJSONString(returnMap);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(retrunJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
   /**
    * 分页输出
    *@author:liangyanjun
    *@time:2016年7月11日下午3:39:02
    *@param response
    *@param list
    *@param total
    *@param pageSize
    */
    protected void outPutPage(HttpServletResponse response, List<?> list, int total,int pageSize) {
       Map<String, Object> resultMap = new HashMap<String, Object>();
       resultMap.put("rows", list);
       int i = total / pageSize;
       double j = Double.parseDouble(total + "") / pageSize;
       if (j > i) {
          i++;
       }
       resultMap.put("total", i);
       resultMap.put("records", total);
       outPutJson(resultMap, response);
    }
    
    /**
     * 分页输出
     *@author:liangyanjun
     *@time:2016年7月11日下午3:39:02
     *@param response
     *@param list
     *@param total
     *@param pageSize
     */
     protected void outPutPage(HttpServletResponse response, List<?> list, int total,int pageSize,Map<String,Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", list);
        int i = total / pageSize;
        double j = Double.parseDouble(total + "") / pageSize;
        if (j > i) {
           i++;
        }
        resultMap.put("total", i);
        resultMap.put("records", total);
        resultMap.put("paramMap", paramMap);
        outPutJson(resultMap, response);
     }
    
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model,HttpServletRequest request) {
		/*对分页查询初始化分页信息*/
		String url = request.getRequestURI();
		String regex = ".*/query[a-z0-9A-Z_]*[$(List)]";
		if(url.matches(regex)){
			Integer offset = 0;
			Integer pageRows = EstateFundConstants.DEFAULT_PAGE_ROWS;
			String page = request.getParameter("page"); // 取得当前页数,jqgrid自身的参数
			String rows = request.getParameter("rows"); // 取得每页显示行数,jqgrid自身的参数
			if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(rows)) {
				offset = (Integer.valueOf(page) - 1) * Integer.valueOf(rows);
				pageRows = Integer.valueOf(rows);
			}
			setPage(page);
			setOffset(offset);
			setPageRows(pageRows);
		}
		
	}
	
	public String putJsonData(Object rows,int records){
		int totalPages = records % pageRows == 0 ? records / pageRows : records	/ pageRows + 1;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", totalPages);
		resultMap.put("page", StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
		resultMap.put("records", records);
		resultMap.put("rows", rows);
		
		return JSONObject.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss");
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getPageRows() {
		return pageRows;
	}

	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}
	
	/**
	 * 登录用户信息
	 * @return
	 */
	public static UserInfo getUserInfo(){
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo)subject.getPrincipal();
		return userInfo;
	}

    /**
     * 记录用户操作日志<br>
     * 
     * @param content
     *       操作内容
     * @param moduleName
     *       模块名
     */
    protected void addLogInfo(HttpServletRequest request,String moduleName,String remark) {
        LogInfo logInfo = new LogInfo();
        logInfo.setPid(UuidGenerator.getUuidGenerator());
        logInfo.setOperatorId(getUserInfo().getPid());
        logInfo.setModuleName(moduleName);
        logInfo.setUri(request.getRequestURI());
        logInfo.setOperateDate(new Date());
        logInfo.setIp(IPUtils.getClientIP(request));
        logInfo.setRemark(remark);
        //涉及密码操作的请求，参数中必须清除密码项,如要清除其他的参数项在此添加
    	Map<String, String[]>  parameterMap= request.getParameterMap();
    	Map<String, String[]>  parameterMapTemp = new HashMap<String, String[]>(parameterMap);
    	if(parameterMapTemp.containsKey("oripassword")){
    		parameterMapTemp.put("oripassword", null);
    	}
    	if(parameterMapTemp.containsKey("repassword")){
    		parameterMapTemp.put("repassword", null);
    	}
    	if(parameterMapTemp.containsKey("password")){
    		parameterMapTemp.put("password", null);
    	}

    	logInfo.setParamValue(JSON.toJSONString(parameterMapTemp));
        
        logInfoService.addLogInfo(logInfo);
    }
}
