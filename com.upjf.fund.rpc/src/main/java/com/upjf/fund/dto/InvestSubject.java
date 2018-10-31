package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvestSubject implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 所属项目pid
     */
    private String prjId;

    /**
     * 所属项目公司pid
     */
    private String prjCorpId;

    /**
     * 投资类型(1为投资主体，2为投资人)
     */
    private String investType;

    /**
     * 投资主体pid(当投资类型为投资人时，写入投资主体pid到该字段)
     */
    private String parentId;

    /**
     * 资产管理计划id
     */
    private String investManangeId;

    /**
     * 楼盘名称
     */
    private String buildName;

    /**
     * 出资类别
     */
    private String contributiveType;

    /**
     * 预计出资金额
     */
    private BigDecimal expectContributiveAmount;

    /**
     * 投资主体名称(企业表id)
     */
    private String investSubjectId;

    /**
     * 持股比例
     */
    private BigDecimal holdStockRate;

    /**
     * 是否为汇联上市公司
     */
    private String isHuilianCorp;

    /**
     * 分红方式
     */
    private String dividendType;

    /**
     * 级别
     */
    private String level;

    /**
     * 预计总回款
     */
    private BigDecimal expectAllReceiverAccount;

    /**
     * 出资期数
     */
    private Integer term;

    /**
     * 预期收益
     */
    private BigDecimal expectIncome;

    /**
     * 年化利率
     */
    private BigDecimal annualizedInterestRate;

    /**
     * 预期收益率
     */
    private BigDecimal expectIncomeRate;

    /**
     * 投资起始日期
     */
    private Date investStartDate;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updateId;
    
    /**
     * 状态
     */
    private String status;

    /**
     * invest_subject
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     * @return pid 主键id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 主键id
     * @param pid 主键id
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 所属项目pid
     * @return prj_id 所属项目pid
     */
    public String getPrjId() {
        return prjId;
    }

    /**
     * 所属项目pid
     * @param prjId 所属项目pid
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId == null ? null : prjId.trim();
    }

    /**
     * 所属项目公司pid
     * @return prj_corp_id 所属项目公司pid
     */
    public String getPrjCorpId() {
        return prjCorpId;
    }

    /**
     * 所属项目公司pid
     * @param prjCorpId 所属项目公司pid
     */
    public void setPrjCorpId(String prjCorpId) {
        this.prjCorpId = prjCorpId == null ? null : prjCorpId.trim();
    }

    /**
     * 投资类型(1为投资主体，2为投资人)
     * @return invest_type 投资类型(1为投资主体，2为投资人)
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 投资类型(1为投资主体，2为投资人)
     * @param investType 投资类型(1为投资主体，2为投资人)
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 投资主体pid(当投资类型为投资人时，写入投资主体pid到该字段)
     * @return parent_id 投资主体pid(当投资类型为投资人时，写入投资主体pid到该字段)
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 投资主体pid(当投资类型为投资人时，写入投资主体pid到该字段)
     * @param parentId 投资主体pid(当投资类型为投资人时，写入投资主体pid到该字段)
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 资产管理计划id
     * @return invest_manange_id 资产管理计划id
     */
    public String getInvestManangeId() {
        return investManangeId;
    }

    /**
     * 资产管理计划id
     * @param investManangeId 资产管理计划id
     */
    public void setInvestManangeId(String investManangeId) {
        this.investManangeId = investManangeId == null ? null : investManangeId.trim();
    }

    /**
     * 楼盘名称
     * @return build_name 楼盘名称
     */
    public String getBuildName() {
        return buildName;
    }

    /**
     * 楼盘名称
     * @param buildName 楼盘名称
     */
    public void setBuildName(String buildName) {
        this.buildName = buildName == null ? null : buildName.trim();
    }

    /**
     * 出资类别
     * @return contributive_type 出资类别
     */
    public String getContributiveType() {
        return contributiveType;
    }

    /**
     * 出资类别
     * @param contributiveType 出资类别
     */
    public void setContributiveType(String contributiveType) {
        this.contributiveType = contributiveType == null ? null : contributiveType.trim();
    }

    /**
     * 预计出资金额
     * @return expect_contributive_amount 预计出资金额
     */
    public BigDecimal getExpectContributiveAmount() {
        return expectContributiveAmount;
    }

    /**
     * 预计出资金额
     * @param expectContributiveAmount 预计出资金额
     */
    public void setExpectContributiveAmount(BigDecimal expectContributiveAmount) {
        this.expectContributiveAmount = expectContributiveAmount;
    }

    /**
     * 投资主体名称(企业表id)
     * @return invest_subject_id 投资主体名称(企业表id)
     */
    public String getInvestSubjectId() {
        return investSubjectId;
    }

    /**
     * 投资主体名称(企业表id)
     * @param investSubjectId 投资主体名称(企业表id)
     */
    public void setInvestSubjectId(String investSubjectId) {
        this.investSubjectId = investSubjectId == null ? null : investSubjectId.trim();
    }

    /**
     * 持股比例
     * @return hold_stock_rate 持股比例
     */
    public BigDecimal getHoldStockRate() {
        return holdStockRate;
    }

    /**
     * 持股比例
     * @param holdStockRate 持股比例
     */
    public void setHoldStockRate(BigDecimal holdStockRate) {
        this.holdStockRate = holdStockRate;
    }

    /**
     * 是否为汇联上市公司
     * @return is_huilian_corp 是否为汇联上市公司
     */
    public String getIsHuilianCorp() {
        return isHuilianCorp;
    }

    /**
     * 是否为汇联上市公司
     * @param isHuilianCorp 是否为汇联上市公司
     */
    public void setIsHuilianCorp(String isHuilianCorp) {
        this.isHuilianCorp = isHuilianCorp == null ? null : isHuilianCorp.trim();
    }

    /**
     * 分红方式
     * @return dividend_type 分红方式
     */
    public String getDividendType() {
        return dividendType;
    }

    /**
     * 分红方式
     * @param dividendType 分红方式
     */
    public void setDividendType(String dividendType) {
        this.dividendType = dividendType == null ? null : dividendType.trim();
    }

    /**
     * 级别
     * @return level 级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * 级别
     * @param level 级别
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 预计总回款
     * @return expect_all_receiver_account 预计总回款
     */
    public BigDecimal getExpectAllReceiverAccount() {
        return expectAllReceiverAccount;
    }

    /**
     * 预计总回款
     * @param expectAllReceiverAccount 预计总回款
     */
    public void setExpectAllReceiverAccount(BigDecimal expectAllReceiverAccount) {
        this.expectAllReceiverAccount = expectAllReceiverAccount;
    }

    /**
     * 出资期数
     * @return term 出资期数
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * 出资期数
     * @param term 出资期数
     */
    public void setTerm(Integer term) {
        this.term = term;
    }

    /**
     * 预期收益
     * @return expect_income 预期收益
     */
    public BigDecimal getExpectIncome() {
        return expectIncome;
    }

    /**
     * 预期收益
     * @param expectIncome 预期收益
     */
    public void setExpectIncome(BigDecimal expectIncome) {
        this.expectIncome = expectIncome;
    }

    /**
     * 年化利率
     * @return annualized_interest_rate 年化利率
     */
    public BigDecimal getAnnualizedInterestRate() {
        return annualizedInterestRate;
    }

    /**
     * 年化利率
     * @param annualizedInterestRate 年化利率
     */
    public void setAnnualizedInterestRate(BigDecimal annualizedInterestRate) {
        this.annualizedInterestRate = annualizedInterestRate;
    }

    /**
     * 预期收益率
     * @return expect_income_rate 预期收益率
     */
    public BigDecimal getExpectIncomeRate() {
        return expectIncomeRate;
    }

    /**
     * 预期收益率
     * @param expectIncomeRate 预期收益率
     */
    public void setExpectIncomeRate(BigDecimal expectIncomeRate) {
        this.expectIncomeRate = expectIncomeRate;
    }

    /**
     * 投资起始日期
     * @return invest_start_date 投资起始日期
     */
    public Date getInvestStartDate() {
        return investStartDate;
    }

    /**
     * 投资起始日期
     * @param investStartDate 投资起始日期
     */
    public void setInvestStartDate(Date investStartDate) {
        this.investStartDate = investStartDate;
    }

    /**
     * 创建日期
     * @return create_date 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建人
     * @return create_id 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人
     * @param createId 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 修改日期
     * @return update_date 修改日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 修改日期
     * @param updateDate 修改日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 修改人
     * @return update_id 修改人
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 修改人
     * @param updateId 修改人
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}