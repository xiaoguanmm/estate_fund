package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志表
 * 
 * @author wufujing
 * 
 */
public class LogInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * uuid
     */
    private String pid;
    /**
     * 操作用户ID
     */
    private String operatorId;
    /**
     * 操作模块名称
     */
    private String moduleName;
    /**
     * 操作模块action
     */
    private String uri;
    /**
     * 操作时间
     */
    private Date operateDate;
    /**
     * 用户登陆IP
     */
    private String ip;
    /**
     * 备注
     */
    private String remark;

    /**
     * 参数 以json格式保存
     */
    private String paramValue;

    /**
     * 操作用户name
     */
    private String operatorName;
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }
	/**
	 * @return operatorName
	 */
	
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * @param operatorName the operatorName to set
	 */
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

    
}
