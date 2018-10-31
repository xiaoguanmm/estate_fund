package com.upjf.fund.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * servletUtils
 * @company upjf.com
 * @author guantong
 *
 */
public class ServletUtils {
	
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		
		return getRequestAttributes().getRequest();
	}
	
	/**
	 * 获取response
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		
		return getRequestAttributes().getResponse();
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		
		return getRequest().getSession();
	}
	
	
	public static ServletRequestAttributes getRequestAttributes(){
		
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
