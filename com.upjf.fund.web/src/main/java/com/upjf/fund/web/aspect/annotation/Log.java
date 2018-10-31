package com.upjf.fund.web.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统操作日志注解
 * @company upjf.com
 * @author guantong
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	
	/**
	 * 模块名称
	 * @return
	 */
	public String module() default "";
	
	/**
	 * 具体操作内容
	 * @return
	 */
	public String content() default "";

}
