package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockholderInfo implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 企业表id
     */
    private String corpId;

    /**
     * 企业项目关系表id
     */
    private String businessPrjInfoId;

    /**
     * 股东类别
     */
    private String stockholderType;

    /**
     * 注册资本
     */
    private BigDecimal registerCapital;

    /**
     * 股东往来
     */
    private BigDecimal stockholderContacts;

    /**
     * 持股比例
     */
    private BigDecimal holdStockRatio;

    /**
     * 股权确认状态
     */
    private String stockRightsStatus;

    /**
     * 股权确认状态备注
     */
    private String stockRightsRemark;

    /**
     * 实际出资总额
     */
    private BigDecimal realityInvestTotal;

    /**
     * 已收回款总额
     */
    private BigDecimal receivedPaybackTotal;

    /**
     * 已收本金总额
     */
    private BigDecimal receivedPrincipalTotal;

    /**
     * 备注
     */
    private String remark;

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
     * 股权确认日期
     */
    private Date confirmDate;
    
    /**
     * 股权确认人
     */
    private String confirmId;
    
    /**
     * 状态(0：无效；1：有效)
     */
    private String status;
    
    /**
     * stockholder_info
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 所属项目id
     */
    private String prjId;

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
     * 企业表id
     * @return corp_id 企业表id
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 企业表id
     * @param corpId 企业表id
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
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
     * 股东类别
     * @return stockholder_type 股东类别
     */
    public String getStockholderType() {
        return stockholderType;
    }

    /**
     * 股东类别
     * @param stockholderType 股东类别
     */
    public void setStockholderType(String stockholderType) {
        this.stockholderType = stockholderType == null ? null : stockholderType.trim();
    }

    /**
     * 注册资本
     * @return register_capital 注册资本
     */
    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    /**
     * 注册资本
     * @param registerCapital 注册资本
     */
    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    /**
     * 股东往来
     * @return stockholder_contacts 股东往来
     */
    public BigDecimal getStockholderContacts() {
        return stockholderContacts;
    }

    /**
     * 股东往来
     * @param stockholderContacts 股东往来
     */
    public void setStockholderContacts(BigDecimal stockholderContacts) {
        this.stockholderContacts = stockholderContacts;
    }

    /**
     * 持股比例
     * @return hold_stock_ratio 持股比例
     */
    public BigDecimal getHoldStockRatio() {
        return holdStockRatio;
    }

    /**
     * 持股比例
     * @param holdStockRatio 持股比例
     */
    public void setHoldStockRatio(BigDecimal holdStockRatio) {
        this.holdStockRatio = holdStockRatio;
    }

    /**
     * 股权确认状态
     * @return stock_rights_status 股权确认状态
     */
    public String getStockRightsStatus() {
        return stockRightsStatus;
    }

    /**
     * 股权确认状态
     * @param stockRightsStatus 股权确认状态
     */
    public void setStockRightsStatus(String stockRightsStatus) {
        this.stockRightsStatus = stockRightsStatus == null ? null : stockRightsStatus.trim();
    }

    /**
     * 股权确认状态备注
     * @return stock_rights_remark 股权确认状态备注
     */
    public String getStockRightsRemark() {
        return stockRightsRemark;
    }

    /**
     * 股权确认状态备注
     * @param stockRightsRemark 股权确认状态备注
     */
    public void setStockRightsRemark(String stockRightsRemark) {
        this.stockRightsRemark = stockRightsRemark == null ? null : stockRightsRemark.trim();
    }

    /**
     * 实际出资总额
     * @return reality_invest_total 实际出资总额
     */
    public BigDecimal getRealityInvestTotal() {
        return realityInvestTotal;
    }

    /**
     * 实际出资总额
     * @param realityInvestTotal 实际出资总额
     */
    public void setRealityInvestTotal(BigDecimal realityInvestTotal) {
        this.realityInvestTotal = realityInvestTotal;
    }

    /**
     * 已收回款总额
     * @return received_payback_total 已收回款总额
     */
    public BigDecimal getReceivedPaybackTotal() {
        return receivedPaybackTotal;
    }

    /**
     * 已收回款总额
     * @param receivedPaybackTotal 已收回款总额
     */
    public void setReceivedPaybackTotal(BigDecimal receivedPaybackTotal) {
        this.receivedPaybackTotal = receivedPaybackTotal;
    }

    /**
     * 已收本金总额
     * @return received_principal_total 已收本金总额
     */
    public BigDecimal getReceivedPrincipalTotal() {
        return receivedPrincipalTotal;
    }

    /**
     * 已收本金总额
     * @param receivedPrincipalTotal 已收本金总额
     */
    public void setReceivedPrincipalTotal(BigDecimal receivedPrincipalTotal) {
        this.receivedPrincipalTotal = receivedPrincipalTotal;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrjId() {
		return prjId;
	}

	public void setPrjId(String prjId) {
		this.prjId = prjId;
	}
}