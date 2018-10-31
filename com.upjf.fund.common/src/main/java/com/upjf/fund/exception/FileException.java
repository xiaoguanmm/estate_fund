package com.upjf.fund.exception;

import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.constants.I18nConstants;
import com.upjf.fund.exception.base.EstateFundException;

/**
 * file操作Exception
 * @author Administrator
 *
 */
public class FileException extends EstateFundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileException(I18nConstants code,String defaultMessage) {
		
		super(EstateFundModelConstants.SYSTEM, code, defaultMessage);
		
	}

}
