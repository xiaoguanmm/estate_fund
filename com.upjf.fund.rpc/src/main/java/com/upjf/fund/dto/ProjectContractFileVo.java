package com.upjf.fund.dto;

import java.io.Serializable;

/**
 * 项目合同附件信息列表包装类
 * @author lixq
 * @date 2018年10月9日
 */
public class ProjectContractFileVo implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    /**
     * 项目附件关系信息主键
     */
    private String contractPid;
    
    /**
     * 合同名称
     */
    private String contractName;
    
    /**
     * 合同附件全名
     */
    private String contractFullName;
    
    /**
     * 上传时间
     */
    private String uploadTime;
    
    /**
     * 上传人
     */
    private String userName;
    
    /**
     * 文件路径
     */
    private String fileUrl;
    
    /**
     * 文件类型
     */
    private String fileType;

	public String getContractPid() {
		return contractPid;
	}

	public void setContractPid(String contractPid) {
		this.contractPid = contractPid;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractFullName() {
		return contractFullName;
	}

	public void setContractFullName(String contractFullName) {
		this.contractFullName = contractFullName;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
    
    
    
    
}