package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目预算附件信息实体类
 * @author lixq
 * @date 2018年10月11日
 */
public class ProjectBudgetAccessory implements Serializable {
	

    private static final long serialVersionUID = 1L;
    
    
    /**
     * 主键id
     */
    private String pid;

    /**
     * 所属项目预算信息,与项目预算表project_budget主键相关联
     */
    private String prjBudgetId;

    /**
     * 关联文件,与文件表esate_fund_file主键相关联
     */
    private String fileId;

    /**
     * 是否有效:1是,否0
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人,与系统用户信息表user_info主键相关联
     */
    private String createId;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人,与系统用户信息表user_info主键相关联
     */
    private String updateId;

    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPrjBudgetId() {
        return prjBudgetId;
    }

    public void setPrjBudgetId(String prjBudgetId) {
        this.prjBudgetId = prjBudgetId == null ? null : prjBudgetId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
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