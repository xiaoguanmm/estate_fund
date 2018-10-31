package com.upjf.fund.dto;


/**
 * 项目预算包装类
 * @author lixq
 * @date 2018年10月11日
 */
public class ProjectBudgetVo  extends ProjectBudget {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 项目预算附件关系信息主键
	 */
	private String budgetAccessoryPid;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	
	/**
	 * 文件路径
	 */
	private String fileUrl;
	
	
	/**
	 * 操作人名称
	 */
	private String operatorName;
	
	
	/**
	 * 操作时间字符串
	 */
	private String operatorTime;
	
	
	/**
	 * 预算时间字符串
	 */
	private String budgetDateStr;
	
	
	public String getBudgetAccessoryPid() {
		return budgetAccessoryPid;
	}
	
	public void setBudgetAccessoryPid(String budgetAccessoryPid) {
		this.budgetAccessoryPid = budgetAccessoryPid;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getBudgetDateStr() {
		return budgetDateStr;
	}

	public void setBudgetDateStr(String budgetDateStr) {
		this.budgetDateStr = budgetDateStr;
	}
	
	
	
}