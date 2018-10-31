package com.upjf.fund.web.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.upjf.fund.dto.LogInfo;
import com.upjf.fund.dto.UserInfo;
import com.upjf.fund.service.system.LogInfoService;
import com.upjf.fund.utils.IPUtils;
import com.upjf.fund.utils.UuidGenerator;
import com.upjf.fund.web.aspect.annotation.Log;
import com.upjf.fund.web.controller.base.BaseController;
import com.upjf.fund.web.utils.ServletUtils;

/**
 * 日志处理切面
 * @company upjf.com
 * @author guantong
 * 说明:@EnableAsync 使用异步进行日志的处理
 *
 */
@Component
@Aspect
@EnableAsync
public class LogAspect {
	
	@Autowired
	private LogInfoService logInfoService;
	
	/**
	 * 织入点
	 */
	@Pointcut("@annotation(com.upjf.fund.web.aspect.annotation.Log)")
	public void logPointCut(){
		
	}
	
	/**
	 * 方法返回前执行
	 * @param joinPoint
	 */
	@AfterReturning(pointcut="logPointCut()")
	public void afterReturning(JoinPoint joinPoint){
		UserInfo userInfo = BaseController.getUserInfo();
		if(userInfo == null){
			return ;
		}
		/*处理日志*/
		handleLog(joinPoint);
	}

	/***
	 * 处理日志(操作日志) 异步处理
	 * @param joinPoint
	 */
	@Async
	private void handleLog(JoinPoint joinPoint) {
		Log log = getAnnotationLog(joinPoint);
		if(log==null){
			return ;
		}
		HttpServletRequest request = ServletUtils.getRequest();
		LogInfo logInfo = new LogInfo();
        logInfo.setPid(UuidGenerator.getUuidGenerator());
        logInfo.setOperatorId(BaseController.getUserInfo().getCreateId());
        logInfo.setUri(request.getRequestURI());
        logInfo.setOperateDate(new Date());
        logInfo.setIp(IPUtils.getClientIP(ServletUtils.getRequest()));
        logInfo.setModuleName(log.module());
        logInfo.setRemark(log.content());
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

	/**
	 * 获取@Log参数
	 * @param joinPoint
	 * @return
	 */
	private Log getAnnotationLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null){
            return method.getAnnotation(Log.class);
        }
        return null;
	}

}
