package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目合同附件实体类
 * @author lixq
 * @date 2018年10月10日
 */
public class ProjectContract implements Serializable {
	
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
     * 关联的文件主键,与文件信息表estate_fund_file表主键相关联
     */
    private String fileId;

    /**
     * 合同名称
     */
    private String contractName;

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
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