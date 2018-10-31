package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目预算实体类
 * @author lixq
 * @date 2018年10月11日
 */
public class ProjectBudget implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键id
     */
    private String pid;

    /**
     * 所属项目,与项目信息表project_info主键相关联
     */
    private String prjId;

    /**
     * 预算名称
     */
    private String budgetName;

    /**
     * 项目整体利润预算
     */
    private BigDecimal projectProfitBudget;

    /**
     * 预算日期
     */
    private Date budgetDate;

    /**
     * 预算依据
     */
    private String budgetGist;

    /**
     * 是否有效:1是,0否
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人,与系统用户信息表user_info主键相关联
     */
    private String createId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人,与系统用户信息表user_info主键相关联
     */
    private String updateId;

   
    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
        this.prjId = prjId == null ? null : prjId.trim();
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName == null ? null : budgetName.trim();
    }

    public BigDecimal getProjectProfitBudget() {
        return projectProfitBudget;
    }

    public void setProjectProfitBudget(BigDecimal projectProfitBudget) {
        this.projectProfitBudget = projectProfitBudget;
    }

    public Date getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(Date budgetDate) {
        this.budgetDate = budgetDate;
    }

    public String getBudgetGist() {
        return budgetGist;
    }

    public void setBudgetGist(String budgetGist) {
        this.budgetGist = budgetGist == null ? null : budgetGist.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }
}