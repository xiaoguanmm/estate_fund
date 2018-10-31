package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目进度信息实体类
 * @author lixq
 * @date 2018年9月29日
 */
public class ProjectProgress implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键
     */
    private String pid;


    /**
     * 排序号
     */
    private String sortNum;

    /**
     * 项目进度状态名称
     */
    private String projectProgressName;
    
    /**
     * 项目进度附件文件名称
     */
    private String proProgressFileName;
    
    /**
     * 所属项目,与项目信息表project_info主键相关联
     */
    private String prjId;

    /**
     * 预期完成收地时间
     */
    private Date expectResumptionDate;

    /**
     * 实际完成收地时间
     */
    private Date realityResumptionDate;

    /**
     * 预期开始施工日期
     */
    private Date expectConstructionDate;

    /**
     * 实际开始施工日期
     */
    private Date realityConstructionDate;

    /**
     * 预期取得预售证日期
     */
    private Date expectSaleCertifyDate;

    /**
     * 实际取得预售证日期
     */
    private Date realitySaleCertifyDate;

    /**
     * 预期清算日期
     */
    private Date expectLiquidationDate;

    /**
     * 实际清算日期
     */
    private Date realityLiquidationDate;

    /**
     * 项目进度状态,与字典详情表dict_detail主键相关联
     */
    private String projectProgress;
    
    /**
     * 是否取得预售证:0否,1是
     */
    private Integer acquireSaleCertify;

    /**
     * 已推货值
     */
    private BigDecimal hasPushValue;

    /**
     * 证载建面,与字典详情表dict_detail主键相关联
     */
    private String certBuildArea;

    /**
     * 剩余推盘货值
     */
    private BigDecimal surplusPubshValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效:0否,1是
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
    
    /**
     * 更新人名称
     */
    private String updateName;

    
    

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
    
    public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getProjectProgressName() {
		return projectProgressName;
	}

	public void setProjectProgressName(String projectProgressName) {
		this.projectProgressName = projectProgressName;
	}
	
	public String getProProgressFileName() {
		return proProgressFileName;
	}

	public void setProProgressFileName(String proProgressFileName) {
		this.proProgressFileName = proProgressFileName;
	}

	public Date getExpectResumptionDate() {
        return expectResumptionDate;
    }

    public void setExpectResumptionDate(Date expectResumptionDate) {
        this.expectResumptionDate = expectResumptionDate;
    }

    public Date getRealityResumptionDate() {
        return realityResumptionDate;
    }

    public void setRealityResumptionDate(Date realityResumptionDate) {
        this.realityResumptionDate = realityResumptionDate;
    }

    public Date getExpectConstructionDate() {
        return expectConstructionDate;
    }

    public void setExpectConstructionDate(Date expectConstructionDate) {
        this.expectConstructionDate = expectConstructionDate;
    }

    public Date getRealityConstructionDate() {
        return realityConstructionDate;
    }

    public void setRealityConstructionDate(Date realityConstructionDate) {
        this.realityConstructionDate = realityConstructionDate;
    }

    public Date getExpectSaleCertifyDate() {
        return expectSaleCertifyDate;
    }

    public void setExpectSaleCertifyDate(Date expectSaleCertifyDate) {
        this.expectSaleCertifyDate = expectSaleCertifyDate;
    }

    public Date getRealitySaleCertifyDate() {
        return realitySaleCertifyDate;
    }

    public void setRealitySaleCertifyDate(Date realitySaleCertifyDate) {
        this.realitySaleCertifyDate = realitySaleCertifyDate;
    }

    public Date getExpectLiquidationDate() {
        return expectLiquidationDate;
    }

    public void setExpectLiquidationDate(Date expectLiquidationDate) {
        this.expectLiquidationDate = expectLiquidationDate;
    }

    public Date getRealityLiquidationDate() {
        return realityLiquidationDate;
    }

    public void setRealityLiquidationDate(Date realityLiquidationDate) {
        this.realityLiquidationDate = realityLiquidationDate;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress == null ? null : projectProgress.trim();
    }

    public Integer getAcquireSaleCertify() {
        return acquireSaleCertify;
    }

    public void setAcquireSaleCertify(Integer acquireSaleCertify) {
        this.acquireSaleCertify = acquireSaleCertify;
    }

    public BigDecimal getHasPushValue() {
        return hasPushValue;
    }

    public void setHasPushValue(BigDecimal hasPushValue) {
        this.hasPushValue = hasPushValue;
    }

    public String getCertBuildArea() {
        return certBuildArea;
    }

    public void setCertBuildArea(String certBuildArea) {
        this.certBuildArea = certBuildArea == null ? null : certBuildArea.trim();
    }

    public BigDecimal getSurplusPubshValue() {
        return surplusPubshValue;
    }

    public void setSurplusPubshValue(BigDecimal surplusPubshValue) {
        this.surplusPubshValue = surplusPubshValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
    
}