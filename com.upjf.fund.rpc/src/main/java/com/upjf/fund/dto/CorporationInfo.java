package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业信息实体类
 * @author lixq
 * @date 2018年9月19日
 */
public class CorporationInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private String pid;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 名称简称
     */
    private String simpleName;

    /**
     * 组织机构代码证
     */
    private String orgCodeCert;

    /**
     * 营业执照号码
     */
    private String businessLicenceCode;

    /**
     * 法人代表
     */
    private String legalRepresentative;

    /**
     * 注册资本
     */
    private BigDecimal registerCapital;

    /**
     * 客户经理
     */
    private String customerManager;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 公司性质,与字典详情表dict_detail主键相关联
     */
    private String corpQuality;
    
    /**
     * 公司性质中文名
     */
    private String corpQualityName;

    /**
     * 是否有效:0否,1是,默认为有效
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人,与系统用户表user_info主键相关联
     */
    private String createId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人,与系统用户表user_info主键相关联
     */
    private String updateId;

    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName == null ? null : simpleName.trim();
    }

    public String getOrgCodeCert() {
        return orgCodeCert;
    }

    public void setOrgCodeCert(String orgCodeCert) {
        this.orgCodeCert = orgCodeCert == null ? null : orgCodeCert.trim();
    }

    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
    }

    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager == null ? null : customerManager.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCorpQuality() {
        return corpQuality;
    }

    public void setCorpQuality(String corpQuality) {
        this.corpQuality = corpQuality == null ? null : corpQuality.trim();
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

	public String getCorpQualityName() {
		return corpQualityName;
	}

	public void setCorpQualityName(String corpQualityName) {
		this.corpQualityName = corpQualityName;
	}
    
    
}