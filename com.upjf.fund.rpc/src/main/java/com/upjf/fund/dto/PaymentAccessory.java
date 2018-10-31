package com.upjf.fund.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentAccessory implements Serializable {
    /**
     * 主键id
     */
    private String pid;

    /**
     * 收付款表id
     */
    private String paymentId;

    /**
     * 附件类型，0为付款凭证，1为收款凭证
     */
    private String accessoryType;

    /**
     * file表id
     */
    private String fileId;

    /**
     * 上传日期
     */
    private Date createDate;

    /**
     * payment_accessory
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
     * 收付款表id
     * @return payment_id 收付款表id
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * 收付款表id
     * @param paymentId 收付款表id
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    /**
     * 附件类型，0为付款凭证，1为收款凭证
     * @return accessory_type 附件类型，0为付款凭证，1为收款凭证
     */
    public String getAccessoryType() {
        return accessoryType;
    }

    /**
     * 附件类型，0为付款凭证，1为收款凭证
     * @param accessoryType 附件类型，0为付款凭证，1为收款凭证
     */
    public void setAccessoryType(String accessoryType) {
        this.accessoryType = accessoryType == null ? null : accessoryType.trim();
    }

    /**
     * file表id
     * @return file_id file表id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * file表id
     * @param fileId file表id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 上传日期
     * @return create_date 上传日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 上传日期
     * @param createDate 上传日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}