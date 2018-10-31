package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class PrjCompanyContract implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 企业项目关系表id
     */
    private String businessPrjInfoId;

    /**
     * file表id
     */
    private String fileId;

    /**
     * 合同名称
     */
    private String contractName;

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
     * prj_company_contract
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
     * 企业项目关系表id
     * @return business_prj_info_id 企业项目关系表id
     */
    public String getBusinessPrjInfoId() {
        return businessPrjInfoId;
    }

    /**
     * 企业项目关系表id
     * @param businessPrjInfoId 企业项目关系表id
     */
    public void setBusinessPrjInfoId(String businessPrjInfoId) {
        this.businessPrjInfoId = businessPrjInfoId == null ? null : businessPrjInfoId.trim();
    }

    /**
     * file表id
     * @return file_id file表id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * file表id
     * @param fileId file表id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 合同名称
     * @return contract_name 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 合同名称
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
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