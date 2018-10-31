package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 本类为投资主体财务管理信息包装类,用于进入投资主体列表相关字段数据封装.
 * @author lixq
 * @date 2018年10月17日
 */
public class InvestSubjectFinanceVo implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * 投资主体主键
	 */
	private String subjectPid;
	
	/**
	 * 楼盘中文名称
	 */
	private String buildName;
	
	/**
	 * 出资类别中文名称
	 */
	private String contributiveTypeName;
	
	/**
	 * 项目中文名称
	 */
	private String projectName;
	
	/**
	 * 预计出资额
	 */
	private BigDecimal expectContributiveAmount;
	
	/**
	 * 持股比列
	 */
	private BigDecimal holdStockRate;
	
	/**
	 * 期数
	 */
	private Integer payTerm;
	
	/**
	 * 分红方式中文名
	 */
	private String dividendTypeName;
	
	/**
	 * 年化率
	 */
	private BigDecimal annualizedInterestRate;
	
	/**
	 * 预计总回款
	 */
	private BigDecimal expectAllReceiverAccount;
	
	/**
	 * 实际出资额
	 */
	private BigDecimal totalRealPayAmount;
	
	/**
	 * 已收本金
	 */
	private BigDecimal hasReceiveAmount;
	
	/**
	 * 已收利润
	 */
	private BigDecimal hasReceiveProfit;
	
	/**
	 * 出资类型主键,与字典详情表dict_detail主键关联
	 */
	private String contributiveType;
	
	/**
	 * 投资主体名称关联主键,关联企业信息表corporation_info主键
	 */
	private String investSubjectId;
	
	
	/**
	 * 投资人名称关联主键,关联企业信息表corporation_info主键
	 */
	private String investorPid;
	
	/**
	 * 所属项目主键
	 */
	private String projectPid;
	
	/**
	 * 投资主体中文名
	 */
	private String corporationName;
	
	/**
	 * 股东中文名
	 */
	private String stockHolderName;
	
	/**
	 * 项目公司中文名
	 */
	private String projectCompany;
	
	/**
	 * 是否为汇联上市公司:1是,0否
	 */
	private String isHuilianCorp;
	
	/**
	 * 级别:1优先,2劣后
	 */
	private String level;
	
	/**
	 * 投资起始日期
	 */
	private Date investStartDate;
	
	/**
	 * 预期收益率
	 */
	private BigDecimal expectIncomeRate;
	
	/**
	 * 预期收益
	 */
	private BigDecimal expectIncome;
	
	/**
	 * 实际收益率
	 */
	private BigDecimal realYieldRate;
	
	
	
	public String getSubjectPid() {
		return subjectPid;
	}

	public void setSubjectPid(String subjectPid) {
		this.subjectPid = subjectPid;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getContributiveTypeName() {
		return contributiveTypeName;
	}

	public void setContributiveTypeName(String contributiveTypeName) {
		this.contributiveTypeName = contributiveTypeName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getExpectContributiveAmount() {
		return expectContributiveAmount;
	}

	public void setExpectContributiveAmount(BigDecimal expectContributiveAmount) {
		this.expectContributiveAmount = expectContributiveAmount;
	}

	public BigDecimal getHoldStockRate() {
		return holdStockRate;
	}

	public void setHoldStockRate(BigDecimal holdStockRate) {
		this.holdStockRate = holdStockRate;
	}

	public Integer getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(Integer payTerm) {
		this.payTerm = payTerm;
	}

	public String getDividendTypeName() {
		return dividendTypeName;
	}

	public void setDividendTypeName(String dividendTypeName) {
		this.dividendTypeName = dividendTypeName;
	}

	public BigDecimal getAnnualizedInterestRate() {
		return annualizedInterestRate;
	}

	public void setAnnualizedInterestRate(BigDecimal annualizedInterestRate) {
		this.annualizedInterestRate = annualizedInterestRate;
	}

	public BigDecimal getExpectAllReceiverAccount() {
		return expectAllReceiverAccount;
	}

	public void setExpectAllReceiverAccount(BigDecimal expectAllReceiverAccount) {
		this.expectAllReceiverAccount = expectAllReceiverAccount;
	}

	public BigDecimal getTotalRealPayAmount() {
		return totalRealPayAmount;
	}

	public void setTotalRealPayAmount(BigDecimal totalRealPayAmount) {
		this.totalRealPayAmount = totalRealPayAmount;
	}

	public BigDecimal getHasReceiveAmount() {
		return hasReceiveAmount;
	}

	public void setHasReceiveAmount(BigDecimal hasReceiveAmount) {
		this.hasReceiveAmount = hasReceiveAmount;
	}

	public BigDecimal getHasReceiveProfit() {
		return hasReceiveProfit;
	}

	public void setHasReceiveProfit(BigDecimal hasReceiveProfit) {
		this.hasReceiveProfit = hasReceiveProfit;
	}

	public String getContributiveType() {
		return contributiveType;
	}

	public void setContributiveType(String contributiveType) {
		this.contributiveType = contributiveType;
	}

	public String getInvestSubjectId() {
		return investSubjectId;
	}

	public void setInvestSubjectId(String investSubjectId) {
		this.investSubjectId = investSubjectId;
	}

	public String getProjectPid() {
		return projectPid;
	}

	public void setProjectPid(String projectPid) {
		this.projectPid = projectPid;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getStockHolderName() {
		return stockHolderName;
	}

	public void setStockHolderName(String stockHolderName) {
		this.stockHolderName = stockHolderName;
	}

	public String getProjectCompany() {
		return projectCompany;
	}

	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}

	public String getIsHuilianCorp() {
		return isHuilianCorp;
	}

	public void setIsHuilianCorp(String isHuilianCorp) {
		this.isHuilianCorp = isHuilianCorp;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getInvestStartDate() {
		return investStartDate;
	}

	public void setInvestStartDate(Date investStartDate) {
		this.investStartDate = investStartDate;
	}

	public BigDecimal getExpectIncomeRate() {
		return expectIncomeRate;
	}

	public void setExpectIncomeRate(BigDecimal expectIncomeRate) {
		this.expectIncomeRate = expectIncomeRate;
	}

	public BigDecimal getExpectIncome() {
		return expectIncome;
	}

	public void setExpectIncome(BigDecimal expectIncome) {
		this.expectIncome = expectIncome;
	}

	public BigDecimal getRealYieldRate() {
		return realYieldRate;
	}

	public void setRealYieldRate(BigDecimal realYieldRate) {
		this.realYieldRate = realYieldRate;
	}

	public String getInvestorPid() {
		return investorPid;
	}

	public void setInvestorPid(String investorPid) {
		this.investorPid = investorPid;
	}
	
	
}