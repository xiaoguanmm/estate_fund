package com.upjf.fund.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目基本信息实体类
 * @author lixq
 * @date 2018年9月26日
 */
public class ProjectInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    /**
     * 主键id
     */
    private String pid;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目公司,与企业项目关系表business_prj_info主键相关联
     */
    private String businessPrjInfoId;
    
    /**
     * 项目公司中文名
     */
    private String projectCompanyName;

    /**
     * 楼盘名称
     */
    private String buildingName;

    /**
     * 开发商,与企业信息表corporation_info主键相关联
     */
    private String developersId;

    /**
     * 项目所属省份,与地区表sys_area_info主键area_code相关联
     */
    private String projectProvice;

    /**
     * 项目所属市,与地区表sys_area_info主键area_code相关联
     */
    private String projectCity;

    /**
     * 项目所属区,与地区表sys_area_info主键area_code相关联
     */
    private String projectRegion;

    /**
     * 项目详细地址
     */
    private String projectDetail;

    /**
     * 项目种类,与字典详情信息表dict_detail主键相关联
     */
    private String projectCategory;

    /**
     * 土地获取方式,与字典详情信息表dict_detail主键相关联
     */
    private String landGetWay;

    /**
     * 项目用地性质
     */
    private String projectLandQuality;

    /**
     * 项目占地面积
     */
    private BigDecimal projectOccupationArea;

    /**
     * 项目建设用地面积
     */
    private BigDecimal projectUserArea;

    /**
     * 项目容积率
     */
    private BigDecimal projectCubageRate;

    /**
     * 项目总面积
     */
    private BigDecimal projectArea;

    /**
     * 土地获取成本
     */
    private BigDecimal projectAreaCost;

    /**
     * 楼面地价
     */
    private BigDecimal priceOfPerFloor;

    /**
     * 计容楼面地价
     */
    private BigDecimal cubagePerFloor;

    /**
     * 计容建面
     */
    private BigDecimal cubageBuildingArea;

    /**
     * 可售总面积
     */
    private BigDecimal saleAllArea;

    /**
     * 计容可售建面
     */
    private BigDecimal cubageSaleArea;

    /**
     * 总货价
     */
    private BigDecimal allPrice;

    /**
     * 项目总投入
     */
    private BigDecimal projectAllPutInto;

    /**
     * 项目期限
     */
    private Date projectTerm;

    /**
     * 预期开工时间
     */
    private Date expectStartWorkDate;

    /**
     * 预期开盘时间
     */
    private Date expectOpeningDate;

    /**
     * 项目预期收益
     */
    private BigDecimal expectEarnings;

    /**
     * 预期项目收益率
     */
    private BigDecimal expectEarningsRate;

    /**
     * 更新范围单元面积
     */
    private BigDecimal updateRangePerArea;

    /**
     * 可开发建设用地
     */
    private BigDecimal developBuildArea;

    /**
     * 拟拆除建筑面积
     */
    private BigDecimal prepareDismantleArea;

    /**
     * 容积比率
     */
    private BigDecimal cubageRate;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 预期每平方米售价
     */
    private BigDecimal expectPricePerArea;

    /**
     * 预期完成收地日期
     */
    private Date expectResumptionDate;

    /**
     * 项目备注
     */
    private String remark;

    /**
     * 是否有效:0否,1是
     */
    private Integer status;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人,与系统用户信息表user_info主键相关联
     */
    private String createId;

    /**
     * 修改日期
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getBusinessPrjInfoId() {
        return businessPrjInfoId;
    }

    public void setBusinessPrjInfoId(String businessPrjInfoId) {
        this.businessPrjInfoId = businessPrjInfoId == null ? null : businessPrjInfoId.trim();
    }
    
    public String getProjectCompanyName() {
		return projectCompanyName;
	}

	public void setProjectCompanyName(String projectCompanyName) {
		this.projectCompanyName = projectCompanyName;
	}

	public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getDevelopersId() {
        return developersId;
    }

    public void setDevelopersId(String developersId) {
        this.developersId = developersId == null ? null : developersId.trim();
    }

    public String getProjectProvice() {
        return projectProvice;
    }

    public void setProjectProvice(String projectProvice) {
        this.projectProvice = projectProvice == null ? null : projectProvice.trim();
    }

    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity == null ? null : projectCity.trim();
    }

    public String getProjectRegion() {
        return projectRegion;
    }

    public void setProjectRegion(String projectRegion) {
        this.projectRegion = projectRegion == null ? null : projectRegion.trim();
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail == null ? null : projectDetail.trim();
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory == null ? null : projectCategory.trim();
    }

    public String getLandGetWay() {
        return landGetWay;
    }

    public void setLandGetWay(String landGetWay) {
        this.landGetWay = landGetWay == null ? null : landGetWay.trim();
    }

    public String getProjectLandQuality() {
        return projectLandQuality;
    }

    public void setProjectLandQuality(String projectLandQuality) {
        this.projectLandQuality = projectLandQuality == null ? null : projectLandQuality.trim();
    }

    public BigDecimal getProjectOccupationArea() {
        return projectOccupationArea;
    }

    public void setProjectOccupationArea(BigDecimal projectOccupationArea) {
        this.projectOccupationArea = projectOccupationArea;
    }

    public BigDecimal getProjectUserArea() {
        return projectUserArea;
    }

    public void setProjectUserArea(BigDecimal projectUserArea) {
        this.projectUserArea = projectUserArea;
    }

    public BigDecimal getProjectCubageRate() {
        return projectCubageRate;
    }

    public void setProjectCubageRate(BigDecimal projectCubageRate) {
        this.projectCubageRate = projectCubageRate;
    }

    public BigDecimal getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(BigDecimal projectArea) {
        this.projectArea = projectArea;
    }

    public BigDecimal getProjectAreaCost() {
        return projectAreaCost;
    }

    public void setProjectAreaCost(BigDecimal projectAreaCost) {
        this.projectAreaCost = projectAreaCost;
    }

    public BigDecimal getPriceOfPerFloor() {
        return priceOfPerFloor;
    }

    public void setPriceOfPerFloor(BigDecimal priceOfPerFloor) {
        this.priceOfPerFloor = priceOfPerFloor;
    }

    public BigDecimal getCubagePerFloor() {
        return cubagePerFloor;
    }

    public void setCubagePerFloor(BigDecimal cubagePerFloor) {
        this.cubagePerFloor = cubagePerFloor;
    }

    public BigDecimal getCubageBuildingArea() {
        return cubageBuildingArea;
    }

    public void setCubageBuildingArea(BigDecimal cubageBuildingArea) {
        this.cubageBuildingArea = cubageBuildingArea;
    }

    public BigDecimal getSaleAllArea() {
        return saleAllArea;
    }

    public void setSaleAllArea(BigDecimal saleAllArea) {
        this.saleAllArea = saleAllArea;
    }

    public BigDecimal getCubageSaleArea() {
        return cubageSaleArea;
    }

    public void setCubageSaleArea(BigDecimal cubageSaleArea) {
        this.cubageSaleArea = cubageSaleArea;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public BigDecimal getProjectAllPutInto() {
        return projectAllPutInto;
    }

    public void setProjectAllPutInto(BigDecimal projectAllPutInto) {
        this.projectAllPutInto = projectAllPutInto;
    }

    public Date getProjectTerm() {
        return projectTerm;
    }

    public void setProjectTerm(Date projectTerm) {
        this.projectTerm = projectTerm;
    }
    
    public Date getExpectStartWorkDate() {
        return expectStartWorkDate;
    }

    public void setExpectStartWorkDate(Date expectStartWorkDate) {
        this.expectStartWorkDate = expectStartWorkDate;
    }

    public Date getExpectOpeningDate() {
        return expectOpeningDate;
    }

    public void setExpectOpeningDate(Date expectOpeningDate) {
        this.expectOpeningDate = expectOpeningDate;
    }

    public BigDecimal getExpectEarnings() {
        return expectEarnings;
    }

    public void setExpectEarnings(BigDecimal expectEarnings) {
        this.expectEarnings = expectEarnings;
    }

    public BigDecimal getExpectEarningsRate() {
        return expectEarningsRate;
    }

    public void setExpectEarningsRate(BigDecimal expectEarningsRate) {
        this.expectEarningsRate = expectEarningsRate;
    }

    public BigDecimal getUpdateRangePerArea() {
        return updateRangePerArea;
    }

    public void setUpdateRangePerArea(BigDecimal updateRangePerArea) {
        this.updateRangePerArea = updateRangePerArea;
    }

    public BigDecimal getDevelopBuildArea() {
        return developBuildArea;
    }

    public void setDevelopBuildArea(BigDecimal developBuildArea) {
        this.developBuildArea = developBuildArea;
    }

    public BigDecimal getPrepareDismantleArea() {
        return prepareDismantleArea;
    }

    public void setPrepareDismantleArea(BigDecimal prepareDismantleArea) {
        this.prepareDismantleArea = prepareDismantleArea;
    }

    public BigDecimal getCubageRate() {
        return cubageRate;
    }

    public void setCubageRate(BigDecimal cubageRate) {
        this.cubageRate = cubageRate;
    }

    public BigDecimal getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(BigDecimal buildArea) {
        this.buildArea = buildArea;
    }

    public BigDecimal getExpectPricePerArea() {
        return expectPricePerArea;
    }

    public void setExpectPricePerArea(BigDecimal expectPricePerArea) {
        this.expectPricePerArea = expectPricePerArea;
    }

    public Date getExpectResumptionDate() {
        return expectResumptionDate;
    }

    public void setExpectResumptionDate(Date expectResumptionDate) {
        this.expectResumptionDate = expectResumptionDate;
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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