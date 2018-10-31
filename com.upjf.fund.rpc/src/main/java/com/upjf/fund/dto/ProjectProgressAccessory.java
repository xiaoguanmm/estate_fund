package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目进度附件关系信息实体
 * @author lixq
 * @date 2018年9月30日
 */
public class ProjectProgressAccessory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键
     */
    private String pid;

    /**
     * 所属项目进度,与项目进度信息表project_progress主键相关联
     */
    private String prjProgressId;

    /**
     * 项目进度附件关联的文件,与file信息表estate_fund_file主键相关联
     */
    private String fileId;
    
    
    /**
     * 是否有效:0否,1是
     */
    private Integer status;

    /**
     * 上传时间
     */
    private Date createDate;

    /**
     * 创建人,与系统用户信息表user_info主键相关联
     */
    private String createId;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改人,与系统用户信息表user_info主键相关联
     */
    private String updateId;

   
    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPrjProgressId() {
        return prjProgressId;
    }

    public void setPrjProgressId(String prjProgressId) {
        this.prjProgressId = prjProgressId == null ? null : prjProgressId.trim();
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