package com.upjf.fund.dto;

import java.io.Serializable;

public class DictDetail implements Serializable {
    /**
     * 
     */
    private String pid;

    /**
     * 字典编码(主表ID)
     */
    private String dictInfoId;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典详情名称
     */
    private String valueDes;

    /**
     * dict_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return pid 
     */
    public String getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 字典编码(主表ID)
     * @return dict_info_id 字典编码(主表ID)
     */
    public String getDictInfoId() {
        return dictInfoId;
    }

    /**
     * 字典编码(主表ID)
     * @param dictInfoId 字典编码(主表ID)
     */
    public void setDictInfoId(String dictInfoId) {
        this.dictInfoId = dictInfoId == null ? null : dictInfoId.trim();
    }

    /**
     * 字典值
     * @return value 字典值
     */
    public String getValue() {
        return value;
    }

    /**
     * 字典值
     * @param value 字典值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 字典详情名称
     * @return value_des 字典详情名称
     */
    public String getValueDes() {
        return valueDes;
    }

    /**
     * 字典详情名称
     * @param valueDes 字典详情名称
     */
    public void setValueDes(String valueDes) {
        this.valueDes = valueDes == null ? null : valueDes.trim();
    }
}