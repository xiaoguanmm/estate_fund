package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class BusinessPrjInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键
     */
    private String pid;

    /**
     * 项目公司名称(相当于别名)
     */
    private String prjCorpName;

    /**
     * 企业表id
     */
    private String corporationInfoId;

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
     * 是否有效:0否,1是
     */
    private String status;
    

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
     * 项目公司名称(相当于别名)
     * @return prj_corp_name 项目公司名称(相当于别名)
     */
    public String getPrjCorpName() {
        return prjCorpName;
    }

    /**
     * 项目公司名称(相当于别名)
     * @param prjCorpName 项目公司名称(相当于别名)
     */
    public void setPrjCorpName(String prjCorpName) {
        this.prjCorpName = prjCorpName == null ? null : prjCorpName.trim();
    }

    /**
     * 企业表id
     * @return corporation_info_id 企业表id
     */
    public String getCorporationInfoId() {
        return corporationInfoId;
    }

    /**
     * 企业表id
     * @param corporationInfoId 企业表id
     */
    public void setCorporationInfoId(String corporationInfoId) {
        this.corporationInfoId = corporationInfoId == null ? null : corporationInfoId.trim();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}