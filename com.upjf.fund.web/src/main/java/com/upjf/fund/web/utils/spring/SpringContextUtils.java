package com.upjf.fund.web.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring 工具类
 * @company upjf.com
 * @author guantong
 *
 */
public class SpringContextUtils implements ApplicationContextAware {
	 private static ApplicationContext applicationContext=null; 
	 
	 public void setApplicationContext(ApplicationContext applicationContext)
	            throws BeansException {
		 SpringContextUtils.applicationContext = applicationContext;
	    }
	 
	 public static ApplicationContext getApplicationContext(){
		 return applicationContext;
		 }
	 
	 /**
	  * getBeanByName
	  * @param name
	  * @return
	  * @throws BeansException
	  */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
        return  (T) getApplicationContext().getBean(name);
    }
	/**
	 * getBeanByClass
	 * @param t
	 * @return
	 */
	public static <T> T getBean(Class<T> t) throws BeansException {
		return (T) getApplicationContext().getBean(t);
	}

}
