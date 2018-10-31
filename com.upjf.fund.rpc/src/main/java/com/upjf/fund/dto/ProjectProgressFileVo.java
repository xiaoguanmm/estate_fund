package com.upjf.fund.dto;

import java.io.Serializable;

/**
 * 项目进度附件信息列表包装类
 * @author lixq
 * @date 2018年10月9日
 */
public class ProjectProgressFileVo implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    /**
     * 项目进度状态名称
     */
    private String progressStatusName;
    
    /**
     * 进度附件名称
     */
    private String progressFileName;
    
    /**
     * 备注
     */
    private String progressRemark;
    
    /**
     * 进度文件类型
     */
    private String progressFileType;
    
    /**
     * 进度操作时间
     */
    private String progressUpdateTime;
    
    /**
     * 更新人名称
     */
    private String operatorName;
    
    /**
     * 文件路径
     */
    private String progressFileUrl;
    
    /**
     * 进度状态记录主键(进度状态有文件上传时,此属性为项目进度附件表主键,无文件上传时,为项目进度主键)
     */
    private String progressFilePid;

    
    
    
	public String getProgressStatusName() {
		return progressStatusName;
	}

	public void setProgressStatusName(String progressStatusName) {
		this.progressStatusName = progressStatusName;
	}

	public String getProgressFileName() {
		return progressFileName;
	}

	public void setProgressFileName(String progressFileName) {
		this.progressFileName = progressFileName;
	}

	public String getProgressRemark() {
		return progressRemark;
	}

	public void setProgressRemark(String progressRemark) {
		this.progressRemark = progressRemark;
	}

	public String getProgressFileType() {
		return progressFileType;
	}

	public void setProgressFileType(String progressFileType) {
		this.progressFileType = progressFileType;
	}


	public String getProgressUpdateTime() {
		return progressUpdateTime;
	}

	public void setProgressUpdateTime(String progressUpdateTime) {
		this.progressUpdateTime = progressUpdateTime;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getProgressFileUrl() {
		return progressFileUrl;
	}

	public void setProgressFileUrl(String progressFileUrl) {
		this.progressFileUrl = progressFileUrl;
	}

	public String getProgressFilePid() {
		return progressFilePid;
	}

	public void setProgressFilePid(String progressFilePid) {
		this.progressFilePid = progressFilePid;
	}
    
    
    
}