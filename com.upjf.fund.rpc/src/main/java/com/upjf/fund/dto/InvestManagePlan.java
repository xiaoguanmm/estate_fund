package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvestManagePlan implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 项目表id
     */
    private String prjId;

    /**
     * 企业项目关系表id
     */
    private String businessPrjInfoId;

    /**
     * 股东表id
     */
    private String stockholderId;

    /**
     * 投资起始日期
     */
    private Date investStartDate;

    /**
     * 投资方式
     */
    private String investType;

    /**
     * 期限
     */
    private Integer term;

    /**
     * 预计出资规模
     */
    private BigDecimal expectInvestAmount;

    /**
     * 预计总回款
     */
    private BigDecimal expectAllReceiverAccount;

    /**
     * 实际出资金额
     */
    private BigDecimal realityInvestAmount;

    /**
     * 已收本金
     */
    private BigDecimal receiveredPrincipal;

    /**
     * 已收回款
     */
    private BigDecimal receiveredPayback;

    /**
     * 客户经理
     */
    private String customerManager;

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
     * 联系电话
     */
    private String phone;
    
    /**
     * 状态(0：无效；1：有效)
     */
    private String status;
    
    /**
     * 应回利润（万元）
     */
    private BigDecimal returnedProfit;

    /**
     * invest_manage_plan
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
     * 项目表id
     * @return prj_id 项目表id
     */
    public String getPrjId() {
        return prjId;
    }

    /**
     * 项目表id
     * @param prjId 项目表id
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId == null ? null : prjId.trim();
    }

    /**
     * 企业项目关系表id
     * @return business_prj_info_id 企业项目关系表id
     */
    public String getBusinessPrjInfoId() {
        return businessPrjInfoId;
    }

    /**
     * 企业项目关系表id
     * @param businessPrjInfoId 企业项目关系表id
     */
    public void setBusinessPrjInfoId(String businessPrjInfoId) {
        this.businessPrjInfoId = businessPrjInfoId == null ? null : businessPrjInfoId.trim();
    }

    /**
     * 股东表id
     * @return stockholder_id 股东表id
     */
    public String getStockholderId() {
        return stockholderId;
    }

    /**
     * 股东表id
     * @param stockholderId 股东表id
     */
    public void setStockholderId(String stockholderId) {
        this.stockholderId = stockholderId == null ? null : stockholderId.trim();
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
     * 投资方式
     * @return invest_type 投资方式
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 投资方式
     * @param investType 投资方式
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 期限
     * @return term 期限
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * 期限
     * @param term 期限
     */
    public void setTerm(Integer term) {
        this.term = term;
    }

    /**
     * 预计出资规模
     * @return expect_invest_amount 预计出资规模
     */
    public BigDecimal getExpectInvestAmount() {
        return expectInvestAmount;
    }

    /**
     * 预计出资规模
     * @param expectInvestAmount 预计出资规模
     */
    public void setExpectInvestAmount(BigDecimal expectInvestAmount) {
        this.expectInvestAmount = expectInvestAmount;
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
     * 实际出资金额
     * @return reality_invest_amount 实际出资金额
     */
    public BigDecimal getRealityInvestAmount() {
        return realityInvestAmount;
    }

    /**
     * 实际出资金额
     * @param realityInvestAmount 实际出资金额
     */
    public void setRealityInvestAmount(BigDecimal realityInvestAmount) {
        this.realityInvestAmount = realityInvestAmount;
    }

    /**
     * 已收本金
     * @return receivered_principal 已收本金
     */
    public BigDecimal getReceiveredPrincipal() {
        return receiveredPrincipal;
    }

    /**
     * 已收本金
     * @param receiveredPrincipal 已收本金
     */
    public void setReceiveredPrincipal(BigDecimal receiveredPrincipal) {
        this.receiveredPrincipal = receiveredPrincipal;
    }

    /**
     * 已收回款
     * @return receivered_payback 已收回款
     */
    public BigDecimal getReceiveredPayback() {
        return receiveredPayback;
    }

    /**
     * 已收回款
     * @param receiveredPayback 已收回款
     */
    public void setReceiveredPayback(BigDecimal receiveredPayback) {
        this.receiveredPayback = receiveredPayback;
    }

    /**
     * 客户经理
     * @return customer_manager 客户经理
     */
    public String getCustomerManager() {
        return customerManager;
    }

    /**
     * 客户经理
     * @param customerManager 客户经理
     */
    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager == null ? null : customerManager.trim();
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

    /**
     * 联系电话
     * @return phone 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getReturnedProfit() {
		return returnedProfit;
	}

	public void setReturnedProfit(BigDecimal returnedProfit) {
		this.returnedProfit = returnedProfit;
	}

}