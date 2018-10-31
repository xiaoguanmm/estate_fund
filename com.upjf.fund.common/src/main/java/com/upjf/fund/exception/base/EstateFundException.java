package com.upjf.fund.exception.base;

import org.apache.commons.lang.StringUtils;

import com.upjf.fund.constants.I18nConstants;

/**
 * 自定义异常基类
 * @author Administrator
 *
 */
public class EstateFundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模块
	 */
	private String  module;
	
	/**
	 * 异常code
	 */
	private I18nConstants code;
	
	/**
	 * 异常消息
	 */
	private String defaultMessage;

	public EstateFundException(String module, I18nConstants code, String defaultMessage) {
		this.module = module;
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	public EstateFundException(I18nConstants code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}
	
	public EstateFundException(I18nConstants code){
		this(code,null);
	}

	public String getModule() {
		return module;
	}

	public I18nConstants getCode() {
		return code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder("");
		if(StringUtils.isNotEmpty(this.module)){
			message.append(this.module);
			message.append("-");
		}
		if(this.code!=null){
			message.append(I18nConstants.getCode(this.code));
			message.append("-");
		}
		if(StringUtils.isEmpty(this.defaultMessage)){
			message.append(I18nConstants.getMessage(this.code));
		}else{
			message.append(this.defaultMessage);
		}
		return message.toString();
	}
	
	
	

}
