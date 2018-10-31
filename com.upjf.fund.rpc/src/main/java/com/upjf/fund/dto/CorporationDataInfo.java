package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业资料扫描件实体类
 * @author lixq
 * @date 2018年10月15日
 */
public class CorporationDataInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    /**
     * 主键
     */
    private String pid;

    /**
     * 公司主键id
     */
    private String corpId;

    /**
     * 文件类型,与字典详情表dict_detail主键相关联
     */
    private String corDataType;

    /**
     * 证件类型名称
     */
    private String fileTypeName;
    
    /**
     * 文件路径
     */
    private String fileUrl;
    
    /**
     * file表id
     */
    private String fileId;

    /**
     * 是否有效:1是,0否
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private String updateId;

    
   

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public String getCorDataType() {
        return corDataType;
    }

    public void setCorDataType(String corDataType) {
        this.corDataType = corDataType == null ? null : corDataType.trim();
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName == null ? null : fileTypeName.trim();
    }
    
    
    public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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