package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class EstateFundFile implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * file名称
     */
    private String fileName;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * file类型
     */
    private String fileType;

    /**
     * file size
     */
    private Long fileSize;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 上传时间
     */
    private Date createDate;

    /**
     * 上传人
     */
    private String createId;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updateId;

    /**
     * 状态(1为正常，0为已删除)
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * estate_fund_file
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
     * file名称
     * @return file_name file名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * file名称
     * @param fileName file名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 真实名称
     * @return real_name 真实名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 真实名称
     * @param realName 真实名称
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * file类型
     * @return file_type file类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * file类型
     * @param fileType file类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * file size
     * @return file_size file size
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * file size
     * @param fileSize file size
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 文件路径
     * @return file_url 文件路径
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 文件路径
     * @param fileUrl 文件路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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

    /**
     * 上传人
     * @return create_id 上传人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 上传人
     * @param createId 上传人
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 修改日期
     * @return update_date 修改日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 修改日期
     * @param updateDate 修改日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 修改人
     * @return update_id 修改人
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 修改人
     * @param updateId 修改人
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 状态(1为正常，0为已删除)
     * @return status 状态(1为正常，0为已删除)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(1为正常，0为已删除)
     * @param status 状态(1为正常，0为已删除)
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}