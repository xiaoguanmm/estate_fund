package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class InvestSubjectAccessory implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 投资主体（投资人）id
     */
    private String subjectId;

    /**
     * file表id
     */
    private String fileId;
    
    /**
     * 附件类型(1为投资主体附件，2为投资人附件)
     */
    private String investAccessoryType;

    /**
     * 上传日期
     */
    private Date createDate;
    
    /**
     * 上传人id
     */
    private String createId;
    
    /**
     * 上传日期
     */
    private Date updateDate;
    
    /**
     * 修改人id
     */
    private String updateId;

    /**
     * invest_subject_accessory
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
     * 投资主体（投资人）id
     * @return subject_id 投资主体（投资人）id
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 投资主体（投资人）id
     * @param subjectId 投资主体（投资人）id
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
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
     * 上传日期
     * @return create_date 上传日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 上传日期
     * @param createDate 上传日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getInvestAccessoryType() {
		return investAccessoryType;
	}

	public void setInvestAccessoryType(String investAccessoryType) {
		this.investAccessoryType = investAccessoryType;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
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
		this.updateId = updateId;
	}
}