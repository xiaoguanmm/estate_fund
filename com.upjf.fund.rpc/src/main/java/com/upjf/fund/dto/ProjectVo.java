package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目管理信息包装类(用于项目管理列表检索及展示)
 * @author lixq
 * @date 2018年9月26日
 */
public class ProjectVo implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    /**
     * 项目主键
     */
    private String projectPid;
    
    /**
     * 项目编号
     */
    private String projectCode;
    
    /**
     * 项目名称
     */
    private String projectName;
    
    /**
     * 项目公司
     */
    private String projectCompany;
    
    /**
     * 项目地区全名
     */
    private String projectAddressFullName;
    
    /**
     * 项目种类中文名
     */
    private String projectCategory;
    
    /**
     * 土地获取方式中文名
     */
    private String landGetWay;
    
    /**
     * 总货值
     */
    private BigDecimal allPrice;
    
    /**
     * 项目最新进度时间
     */
    private String latestProgressTime;
    
    /**
     * 项目最新进度中文名
     */
    private String latestProgressName;
    
    /**
     * 项目最新进度id
     */
    private String projectProgress;
    
    /**
     * 省级编码
     */
    private String projectProvinceId;
    
    /**
     * 市级编码
     */
    private String projetCityId;
    
    /**
     * 区级编码
     */
    private String projectRegionId;
    
    /**
     * 项目种类id
     */
    private String projectCategoryId;
    
    /**
     * 土地获取方式id
     */
    private String landgetWayId;
    
    /**
     * 项目创建时间
     */
    private Date createDate;
    
    /**
     * 项目更新时间
     */
    private Date updateDate;

    
    
    
    
    
	public String getProjectPid() {
		return projectPid;
	}

	public void setProjectPid(String projectPid) {
		this.projectPid = projectPid;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCompany() {
		return projectCompany;
	}

	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}

	public String getProjectAddressFullName() {
		return projectAddressFullName;
	}

	public void setProjectAddressFullName(String projectAddressFullName) {
		this.projectAddressFullName = projectAddressFullName;
	}

	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getLandGetWay() {
		return landGetWay;
	}

	public void setLandGetWay(String landGetWay) {
		this.landGetWay = landGetWay;
	}

	public BigDecimal getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

	public String getLatestProgressTime() {
		return latestProgressTime;
	}

	public void setLatestProgressTime(String latestProgressTime) {
		this.latestProgressTime = latestProgressTime;
	}

	public String getLatestProgressName() {
		return latestProgressName;
	}

	public void setLatestProgressName(String latestProgressName) {
		this.latestProgressName = latestProgressName;
	}

	public String getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}

	public String getProjectProvinceId() {
		return projectProvinceId;
	}

	public void setProjectProvinceId(String projectProvinceId) {
		this.projectProvinceId = projectProvinceId;
	}

	public String getProjetCityId() {
		return projetCityId;
	}

	public void setProjetCityId(String projetCityId) {
		this.projetCityId = projetCityId;
	}

	public String getProjectRegionId() {
		return projectRegionId;
	}

	public void setProjectRegionId(String projectRegionId) {
		this.projectRegionId = projectRegionId;
	}

	public String getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(String projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public String getLandgetWayId() {
		return landgetWayId;
	}

	public void setLandgetWayId(String landgetWayId) {
		this.landgetWayId = landgetWayId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
    
}