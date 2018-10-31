package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Payment implements Serializable {
    /**
     * 主键id
     */
    private String pid;
    
    /**
     * 付款类型(为哪种角色付款):1为股东，2为投资主体，3为投资人
     */
    private String paymentType;

    /**
     * 项目股东(投资主体或投资人出资管理时，需要填充该字段(关联资管计划表股东字段))
     */
    private String stockholderId;

    /**
     * 项目表id
     */
    private String prjId;

    /**
     * 收款公司(关联公司表id)
     */
    private String receiverId;

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
     * 付款期数
     */
    private Integer payTerm;

    /**
     * 付款金额
     */
    private BigDecimal payAmount;

    /**
     * 付款时间
     */
    private Date payDate;

    /**
     * 付款备注
     */
    private String payRemark;

    /**
     * 收款金额
     */
    private BigDecimal receiverAmount;

    /**
     * 收款日期
     */
    private Date receiverDate;

    /**
     * 收款备注
     */
    private String reveiverRemark;

    /**
     * 财务确认状态
     */
    private String financeConfirmStatus;

    /**
     * 出资操作人
     */
    private String investorOp;

    /**
     * 出资操作时间
     */
    private Date investorOpDate;

    /**
     * 出资确认人
     */
    private String investorConfirm;

    /**
     * 出资确认时间
     */
    private Date investorConfirmDate;
    
    /**
     * 付款状态(1:有效 0无效)
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
    
    private Date payDateStart;
    
    private Date payDateEnd;
    
    private Date receiverDateStart;
    
    private Date receiverDateEnd;
    
    /**
     * 入口类型（仅针对 从资管计划页面查看投资主体付款信息  accessType=1）
     */
    private String accessType;
    
    /**
     * 资管计划Pid
     */
    private String investPlanManagePid;
    
    /**
     * 投资主体Pid
     */
    private String investSubjectPid;

    /**
     * payment
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
    
    
    public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
     *  项目股东(投资主体或投资人出资管理时，需要填充该字段(关联资管计划表股东字段))
     * @return  项目股东(投资主体或投资人出资管理时，需要填充该字段(关联资管计划表股东字段))
     */
    public String getStockholderId() {
        return stockholderId;
    }

    /**
     *  项目股东(投资主体或投资人出资管理时，需要填充该字段(关联资管计划表股东字段))
     * @param stockholderId  项目股东(投资主体或投资人出资管理时，需要填充该字段(关联资管计划表股东字段))
     */
    public void setStockholderId(String stockholderId) {
        this.stockholderId = stockholderId == null ? null : stockholderId.trim();
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
     * 付款期数
     * @return pay_term 付款期数
     */
    public Integer getPayTerm() {
        return payTerm;
    }

    /**
     * 付款期数
     * @param payTerm 付款期数
     */
    public void setPayTerm(Integer payTerm) {
        this.payTerm = payTerm;
    }

    /**
     * 付款金额
     * @return pay_amount 付款金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 付款金额
     * @param payAmount 付款金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 付款时间
     * @return pay_date 付款时间
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 付款时间
     * @param payDate 付款时间
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 付款备注
     * @return pay_remark 付款备注
     */
    public String getPayRemark() {
        return payRemark;
    }

    /**
     * 付款备注
     * @param payRemark 付款备注
     */
    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark == null ? null : payRemark.trim();
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
     * 财务确认状态
     * @return finance_confirm_status 财务确认状态
     */
    public String getFinanceConfirmStatus() {
        return financeConfirmStatus;
    }

    /**
     * 财务确认状态
     * @param financeConfirmStatus 财务确认状态
     */
    public void setFinanceConfirmStatus(String financeConfirmStatus) {
        this.financeConfirmStatus = financeConfirmStatus == null ? null : financeConfirmStatus.trim();
    }

    /**
     * 出资操作人
     * @return investor_op 出资操作人
     */
    public String getInvestorOp() {
        return investorOp;
    }

    /**
     * 出资操作人
     * @param investorOp 出资操作人
     */
    public void setInvestorOp(String investorOp) {
        this.investorOp = investorOp == null ? null : investorOp.trim();
    }

    /**
     * 出资操作时间
     * @return investor_op_date 出资操作时间
     */
    public Date getInvestorOpDate() {
        return investorOpDate;
    }

    /**
     * 出资操作时间
     * @param investorOpDate 出资操作时间
     */
    public void setInvestorOpDate(Date investorOpDate) {
        this.investorOpDate = investorOpDate;
    }

    /**
     * 出资确认人
     * @return investor_confirm 出资确认人
     */
    public String getInvestorConfirm() {
        return investorConfirm;
    }

    /**
     * 出资确认人
     * @param investorConfirm 出资确认人
     */
    public void setInvestorConfirm(String investorConfirm) {
        this.investorConfirm = investorConfirm == null ? null : investorConfirm.trim();
    }

    /**
     * 出资确认时间
     * @return investor_confirm_date 出资确认时间
     */
    public Date getInvestorConfirmDate() {
        return investorConfirmDate;
    }

    /**
     * 出资确认时间
     * @param investorConfirmDate 出资确认时间
     */
    public void setInvestorConfirmDate(Date investorConfirmDate) {
        this.investorConfirmDate = investorConfirmDate;
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

	public Date getPayDateStart() {
		return payDateStart;
	}

	public void setPayDateStart(Date payDateStart) {
		this.payDateStart = payDateStart;
	}

	public Date getPayDateEnd() {
		return payDateEnd;
	}

	public void setPayDateEnd(Date payDateEnd) {
		this.payDateEnd = payDateEnd;
	}

	public Date getReceiverDateStart() {
		return receiverDateStart;
	}

	public void setReceiverDateStart(Date receiverDateStart) {
		this.receiverDateStart = receiverDateStart;
	}

	public Date getReceiverDateEnd() {
		return receiverDateEnd;
	}

	public void setReceiverDateEnd(Date receiverDateEnd) {
		this.receiverDateEnd = receiverDateEnd;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getInvestPlanManagePid() {
		return investPlanManagePid;
	}

	public void setInvestPlanManagePid(String investPlanManagePid) {
		this.investPlanManagePid = investPlanManagePid;
	}

	public String getInvestSubjectPid() {
		return investSubjectPid;
	}

	public void setInvestSubjectPid(String investSubjectPid) {
		this.investSubjectPid = investSubjectPid;
	}
    
}