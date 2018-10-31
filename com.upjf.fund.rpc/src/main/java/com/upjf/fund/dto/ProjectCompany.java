package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectCompany implements Serializable {
    /**
     * 主键
     */
    private String pid;

    /**
     * 项目公司名称
     */
    private String name;

    /**
     * 名称简称
     */
    private String simpleName;

    /**
     * 组织机构代码证
     */
    private BigDecimal orgCodeCert;

    /**
     * 营业执照号码
     */
    private BigDecimal businessLicenceCode;

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
     * 公司性质
     */
    private String corpQuality;

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
     * project_company
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return pid 主键
     */
    public String getPid() {
        return pid;
    }

    /**
     * 主键
     * @param pid 主键
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 项目公司名称
     * @return name 项目公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 项目公司名称
     * @param name 项目公司名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 名称简称
     * @return simple_name 名称简称
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * 名称简称
     * @param simpleName 名称简称
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName == null ? null : simpleName.trim();
    }

    /**
     * 组织机构代码证
     * @return org_code_cert 组织机构代码证
     */
    public BigDecimal getOrgCodeCert() {
        return orgCodeCert;
    }

    /**
     * 组织机构代码证
     * @param orgCodeCert 组织机构代码证
     */
    public void setOrgCodeCert(BigDecimal orgCodeCert) {
        this.orgCodeCert = orgCodeCert;
    }

    /**
     * 营业执照号码
     * @return business_licence_code 营业执照号码
     */
    public BigDecimal getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    /**
     * 营业执照号码
     * @param businessLicenceCode 营业执照号码
     */
    public void setBusinessLicenceCode(BigDecimal businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode;
    }

    /**
     * 法人代表
     * @return legal_representative 法人代表
     */
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * 法人代表
     * @param legalRepresentative 法人代表
     */
    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
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
     * 客户经理
     * @return customer_manager 客户经理
     */
    public String getCustomerManager() {
        return customerManager;
    }

    /**
     * 客户经理
     * @param customerManager 客户经理
     */
    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager == null ? null : customerManager.trim();
    }

    /**
     * 联系电话
     * @return phone 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 公司性质
     * @return corp_quality 公司性质
     */
    public String getCorpQuality() {
        return corpQuality;
    }

    /**
     * 公司性质
     * @param corpQuality 公司性质
     */
    public void setCorpQuality(String corpQuality) {
        this.corpQuality = corpQuality == null ? null : corpQuality.trim();
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
}