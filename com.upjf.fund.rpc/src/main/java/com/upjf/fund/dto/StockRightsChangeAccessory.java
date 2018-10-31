package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class StockRightsChangeAccessory implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 股权变更表id
     */
    private String stockRightsChangeId;

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
     * stock_rights_change_accessory
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
     * 股权变更表id
     * @return stock_rights_change_id 股权变更表id
     */
    public String getStockRightsChangeId() {
        return stockRightsChangeId;
    }

    /**
     * 股权变更表id
     * @param stockRightsChangeId 股权变更表id
     */
    public void setStockRightsChangeId(String stockRightsChangeId) {
        this.stockRightsChangeId = stockRightsChangeId == null ? null : stockRightsChangeId.trim();
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