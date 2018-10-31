package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Received implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 项目表id
     */
    private String prjId;
    
    /**
     * 项目股东(投资人利润分配，需要填充该字段(关联资管计划表股东字段))
     */
    private String stockholderId;

    /**
     * 收款公司(关联公司表id)
     */
    private String receiverId;

    /**
     * 回款类别(1:股东 2:投资主体 3:投资人)
     */
    private String receivedType;

    /**
     * 出资股东（出资主体、出资人）(关联公司表id)
     */
    private String contributiveId;

    /**
     * 收款银行(关联企业银行表id)
     */
    private String receiverBankId;

    /**
     * 付款银行(关联企业银行表id)
     */
    private String payBankId;

    /**
     * 收款账户
     */
    private String receiverAccount;

    /**
     * 付款账户
     */
    private String payAccount;

    /**
     * 收款金额
     */
    private BigDecimal receiverAmount;

    /**
     * 回款利润
     */
    private BigDecimal profit;

    /**
     * 收款日期
     */
    private Date receiverDate;

    /**
     * 收款备注
     */
    private String reveiverRemark;

    /**
     * 回款状态(1:已完结 2:未完结 3：未回款)
     */
    private String receivedStatus;

    /**
     * 已回本金总额
     */
    private BigDecimal receivedPrincipalTotal;

    /**
     * 已回利润总和
     */
    private BigDecimal receivedProfitTotal;
    
    /**
     * 回款状态(1:有效 0:无效)
     */
    private String status;

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

    private Date paybackDateStart;
    
    private Date paybackDateEnd;
    
    /**
     * received
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
     * 项目股东(投资人利润分配，需要填充该字段(关联资管计划表股东字段))
     * @return 项目股东(投资人利润分配，需要填充该字段(关联资管计划表股东字段))
     */
    public String getStockholderId() {
		return stockholderId;
	}

    /**
     * 项目股东(投资人利润分配，需要填充该字段(关联资管计划表股东字段))
     * @param stockholderCorpId 项目股东(投资人利润分配，需要填充该字段(关联资管计划表股东字段))
     */
	public void setStockholderId(String stockholderId) {
		this.stockholderId = stockholderId;
	}

	/**
     * 收款公司(关联公司表id)
     * @return receiver_id 收款公司(关联公司表id)
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * 收款公司(关联公司表id)
     * @param receiverId 收款公司(关联公司表id)
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    /**
     * 回款类别(1:股东 2:投资主体 3:投资人)
     * @return received_type 回款类别(1:股东 2:投资主体 3:投资人)
     */
    public String getReceivedType() {
        return receivedType;
    }

    /**
     * 回款类别(1:股东 2:投资主体 3:投资人)
     * @param receivedType 回款类别(1:股东 2:投资主体 3:投资人)
     */
    public void setReceivedType(String receivedType) {
        this.receivedType = receivedType == null ? null : receivedType.trim();
    }

    /**
     * 出资股东（出资主体、出资人）(关联公司表id)
     * @return contributive_id 出资股东（出资主体、出资人）(关联公司表id)
     */
    public String getContributiveId() {
        return contributiveId;
    }

    /**
     * 出资股东（出资主体、出资人）(关联公司表id)
     * @param contributiveId 出资股东（出资主体、出资人）(关联公司表id)
     */
    public void setContributiveId(String contributiveId) {
        this.contributiveId = contributiveId == null ? null : contributiveId.trim();
    }

    /**
     * 收款银行(关联企业银行表id)
     * @return receiver_bank_id 收款银行(关联企业银行表id)
     */
    public String getReceiverBankId() {
        return receiverBankId;
    }

    /**
     * 收款银行(关联企业银行表id)
     * @param receiverBankId 收款银行(关联企业银行表id)
     */
    public void setReceiverBankId(String receiverBankId) {
        this.receiverBankId = receiverBankId == null ? null : receiverBankId.trim();
    }

    /**
     * 付款银行(关联企业银行表id)
     * @return pay_bank_id 付款银行(关联企业银行表id)
     */
    public String getPayBankId() {
        return payBankId;
    }

    /**
     * 付款银行(关联企业银行表id)
     * @param payBankId 付款银行(关联企业银行表id)
     */
    public void setPayBankId(String payBankId) {
        this.payBankId = payBankId == null ? null : payBankId.trim();
    }

    /**
     * 收款账户
     * @return receiver_account 收款账户
     */
    public String getReceiverAccount() {
        return receiverAccount;
    }

    /**
     * 收款账户
     * @param receiverAccount 收款账户
     */
    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    /**
     * 付款账户
     * @return pay_account 付款账户
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * 付款账户
     * @param payAccount 付款账户
     */
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    /**
     * 收款金额
     * @return receiver_amount 收款金额
     */
    public BigDecimal getReceiverAmount() {
        return receiverAmount;
    }

    /**
     * 收款金额
     * @param receiverAmount 收款金额
     */
    public void setReceiverAmount(BigDecimal receiverAmount) {
        this.receiverAmount = receiverAmount;
    }

    /**
     * 回款利润
     * @return profit 回款利润
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 回款利润
     * @param profit 回款利润
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 收款日期
     * @return receiver_date 收款日期
     */
    public Date getReceiverDate() {
        return receiverDate;
    }

    /**
     * 收款日期
     * @param receiverDate 收款日期
     */
    public void setReceiverDate(Date receiverDate) {
        this.receiverDate = receiverDate;
    }

    /**
     * 收款备注
     * @return reveiver_remark 收款备注
     */
    public String getReveiverRemark() {
        return reveiverRemark;
    }

    /**
     * 收款备注
     * @param reveiverRemark 收款备注
     */
    public void setReveiverRemark(String reveiverRemark) {
        this.reveiverRemark = reveiverRemark == null ? null : reveiverRemark.trim();
    }

    /**
     * 回款状态(1:已完结 2:未完结 3：未回款)
     * @return received_status 回款状态(1:已完结 2:未完结 3：未回款)
     */
    public String getReceivedStatus() {
        return receivedStatus;
    }

    /**
     * 回款状态(1:已完结 2:未完结 3：未回款)
     * @param receivedStatus 回款状态(1:已完结 2:未完结 3：未回款)
     */
    public void setReceivedStatus(String receivedStatus) {
        this.receivedStatus = receivedStatus == null ? null : receivedStatus.trim();
    }

    /**
     * 已回本金总额
     * @return received_principal_total 已回本金总额
     */
    public BigDecimal getReceivedPrincipalTotal() {
        return receivedPrincipalTotal;
    }

    /**
     * 已回本金总额
     * @param receivedPrincipalTotal 已回本金总额
     */
    public void setReceivedPrincipalTotal(BigDecimal receivedPrincipalTotal) {
        this.receivedPrincipalTotal = receivedPrincipalTotal;
    }

    /**
     * 已回利润总和
     * @return received_profit_total 已回利润总和
     */
    public BigDecimal getReceivedProfitTotal() {
        return receivedProfitTotal;
    }

    /**
     * 已回利润总和
     * @param receivedProfitTotal 已回利润总和
     */
    public void setReceivedProfitTotal(BigDecimal receivedProfitTotal) {
        this.receivedProfitTotal = receivedProfitTotal;
    }
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getPaybackDateStart() {
		return paybackDateStart;
	}

	public void setPaybackDateStart(Date paybackDateStart) {
		this.paybackDateStart = paybackDateStart;
	}

	public Date getPaybackDateEnd() {
		return paybackDateEnd;
	}

	public void setPaybackDateEnd(Date paybackDateEnd) {
		this.paybackDateEnd = paybackDateEnd;
	}
    
    
}