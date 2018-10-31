package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class RoleResourceOperator  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String pid;

    private String roleId;

    private String resourceOperatorId;

    private String createId;

    private Date createDate;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceOperatorId() {
        return resourceOperatorId;
    }

    public void setResourceOperatorId(String resourceOperatorId) {
        this.resourceOperatorId = resourceOperatorId == null ? null : resourceOperatorId.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}