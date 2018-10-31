package com.upjf.fund.dto;

import java.io.Serializable;

public class SysAreaInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 级别 1:省 2：市 3：区
     */
    private String level;

    /**
     * 电话号码区号
     */
    private String areaTelCode;

    
    

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getAreaTelCode() {
        return areaTelCode;
    }

    public void setAreaTelCode(String areaTelCode) {
        this.areaTelCode = areaTelCode == null ? null : areaTelCode.trim();
    }
}