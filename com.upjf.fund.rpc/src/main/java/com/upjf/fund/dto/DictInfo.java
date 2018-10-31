package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class DictInfo implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字典码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0:删除,1:正常）
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
    
    private DictDetail[] dictDetails;

    /**
     * dict_info
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
     * 字段名称
     * @return name 字段名称
     */
    public String getName() {
        return name;
    }

    /**
     * 字段名称
     * @param name 字段名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 字典码
     * @return code 字典码
     */
    public String getCode() {
        return code;
    }

    /**
     * 字典码
     * @param code 字典码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态（0:删除,1:正常）
     * @return status 状态（0:删除,1:正常）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态（0:删除,1:正常）
     * @param status 状态（0:删除,1:正常）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public DictDetail[] getDictDetails() {
		return dictDetails;
	}

	public void setDictDetails(DictDetail[] dictDetails) {
		this.dictDetails = dictDetails;
	}
    
}