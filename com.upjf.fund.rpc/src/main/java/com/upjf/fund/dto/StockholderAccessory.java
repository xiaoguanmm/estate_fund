package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class StockholderAccessory implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 股东表id
     */
    private String stockholderId;

    /**
     * file表id
     */
    private String fileId;

    /**
     * 上传时间
     */
    private Date createDate;
    
    /**
     * 上传人
     */
    private String createId;

    /**
     * stockholder_accessory
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
     * 股东表id
     * @return stockholder_id 股东表id
     */
    public String getStockholderId() {
        return stockholderId;
    }

    /**
     * 股东表id
     * @param stockholderId 股东表id
     */
    public void setStockholderId(String stockholderId) {
        this.stockholderId = stockholderId == null ? null : stockholderId.trim();
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
     * 上传时间
     * @return create_date 上传时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 上传时间
     * @param createDate 上传时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}
}