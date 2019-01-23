package cn.newcapec.city.smart.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author syf
 * @since 2019-01-17
 */
@TableName("sys_area")
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编号
     */
    private String areaCode;
    /**
     * 父级编号
     */
    private String parentCode;
    /**
     * 所有父级编号
     */
    private String parentCodes;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 区域类型
     */
    private String areaType;
    /**
     * 状态（0正常 1删除 2停用）
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected Serializable pkVal() {
        return this.areaCode;
    }

    @Override
    public String toString() {
        return "Area{" +
        "areaCode=" + areaCode +
        ", parentCode=" + parentCode +
        ", parentCodes=" + parentCodes +
        ", areaName=" + areaName +
        ", areaType=" + areaType +
        ", status=" + status +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", updateBy=" + updateBy +
        ", updateDate=" + updateDate +
        ", remarks=" + remarks +
        "}";
    }
}
