package com.upjf.fund.constants;

/**
 * 模块常量
 * @author Administrator
 *
 */
public interface EstateFundModelConstants {
	
	/**
	 * 业务管理模块
	 */
	public static final String TRADE = "TRADE_MODULE";
	
	/**
	 * 财务管理模块
	 */
	public static final String FINANCE = "FINANCE_MODULE";
	
	/**
	 * 统计报表模块
	 */
	public static final String COUNT = "COUNT_MODULE";
	
	/**
	 * 系统管理模块
	 */
	public static final String SYSTEM = "SYSTEM_MODULE";
	
	/************************上传附件模块******************************/
	
	/**项目股东出资付款凭证*/
	public static final String STOCKHOLDER_INVEST_PAYMENT_ACCESSORY="stockholderInvest";
	/**投资主体付款凭证*/
	public static final String INVEST_SUBJECT_PAYMENT_ACCESSORY="investSubjectInvest";
	/**投资人付款凭证*/
	public static final String INVESTOR_INVEST_PAYMENT_ACCESSORY="InvestorInvest";
	
	/**项目公司管理---新增项目公司---项目公司合同附件*/
	public static final String PROJ_COMP_CONTRACT_ACCESSORY="projCompContractAccessory";
	
	/**项目公司管理---新增项目公司---项目公司股东信息---新增、修改股东公司---上传股东附件*/
	public static final String STOCKHOLDER_ACCESSORY="stockholderAccessory";
	
	/**项目公司管理---新增项目公司---项目公司股东信息---股权变更---上传股权变更附件*/
	public static final String STOCK_RIGHTS_CHANGE_ACCESSORY="StockRightsChangeAccessory";
	
	/**资管计划管理---新增资管计划---新增投资主体---上传投资主体附件*/
	public static final String ADD_INVEST_SUBJECT_ACCESSORY="addInvestSubjectAccessory";
	
	/**资管计划管理---新增资管计划---新增投资人---上传投资人附件*/
	public static final String ADD_INVESTOR_ACCESSORY="addInvestorAccessory";
	
	/**项目股东回款付款凭证*/
	public static final String STOCKHOLDER_PAYBACK_ACCESSORY="stockholderPayback";
	/**投资主体回款付款凭证*/
	public static final String INVEST_SUBJECT_PAYBACK_ACCESSORY="investSubjectPayback";
	/**投资人回款付款凭证*/
	public static final String INVESTOR_PAYBACK_ACCESSORY="InvestorPayback";
	
	

}
