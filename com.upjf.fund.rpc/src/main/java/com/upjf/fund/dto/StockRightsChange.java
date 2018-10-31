package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockRightsChange implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 股东表id
     */
    private String stockholderId;

    /**
     * 企业表id
     */
    private String corpId;

    /**
     * 项目表id
     */
    private String prjId;

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
     * 备注
     */
    private String remark;

    /**
     * 创建日期（申请变更时间）
     */
    private Date createDate;

    /**
     * 创建人（申请变更人）
     */
    private String createId;

    /**
     * 出资期数
     */
    private Short term;

    /**
     * 实际出资
     */
    private BigDecimal actualFunding;
    
    /**
     * 股权变更日期（股权变更确认时间）
     */
    private Date changeDate;

    /**
     * 股权变更人（股权变更确认人）
     */
    private String changeId;

    /**
     * stock_rights_change
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
     * 出资期数
     * @return term 出资期数
     */
    public Short getTerm() {
        return term;
    }

    /**
     * 出资期数
     * @param term 出资期数
     */
    public void setTerm(Short term) {
        this.term = term;
    }

    /**
     * 实际出资
     * @return actual_funding 实际出资
     */
    public BigDecimal getActualFunding() {
        return actualFunding;
    }

    /**
     * 实际出资
     * @param actualFunding 实际出资
     */
    public void setActualFunding(BigDecimal actualFunding) {
        this.actualFunding = actualFunding;
    }

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
}