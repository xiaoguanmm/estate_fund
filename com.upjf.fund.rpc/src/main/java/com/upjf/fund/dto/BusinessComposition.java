package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目业态组成信息实体类
 * @author lixq
 * @date 2018年9月26日
 */
public class BusinessComposition implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键id
     */
    private String pid;
    
    /**
     * 序列号
     */
    private String sortNum;
    
    /**
     * 业态组成名称
     */
    private String businessTypeName;

    /**
     * 业态组成,与字典详情表dict_detail主键相关联
     */
    private String businessType;

    /**
     * 可售建面
     */
    private BigDecimal saleArea;

    /**
     * 销售均价
     */
    private BigDecimal salePerMeter;

    /**
     * 对应业态建面
     */
    private BigDecimal compositionArea;

    /**
     * 货值
     */
    private BigDecimal projectValue;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 是否有效:0否,1是
     */
    private Integer status;

    /**
     * 创建人,与系统用户信息表user_info主键相关联
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人,与系统用户信息表user_info主键相关联
     */
    private String updateId;

    /**
     * 更新时间
     */
    private Date updateDate;

    
    

    
    
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public BigDecimal getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(BigDecimal saleArea) {
        this.saleArea = saleArea;
    }
    
    
    public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public BigDecimal getSalePerMeter() {
        return salePerMeter;
    }

    public void setSalePerMeter(BigDecimal salePerMeter) {
        this.salePerMeter = salePerMeter;
    }

    public BigDecimal getCompositionArea() {
        return compositionArea;
    }

    public void setCompositionArea(BigDecimal compositionArea) {
        this.compositionArea = compositionArea;
    }

    public BigDecimal getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(BigDecimal projectValue) {
        this.projectValue = projectValue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}