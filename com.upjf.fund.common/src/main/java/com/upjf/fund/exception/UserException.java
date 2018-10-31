package com.upjf.fund.exception;

import com.upjf.fund.constants.EstateFundModelConstants;
import com.upjf.fund.constants.I18nConstants;
import com.upjf.fund.exception.base.EstateFundException;

public class UserException extends EstateFundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(I18nConstants code) {
		
		super(EstateFundModelConstants.SYSTEM,code,null);
		
	}

}
