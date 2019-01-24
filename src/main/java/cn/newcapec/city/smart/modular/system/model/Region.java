package cn.newcapec.city.smart.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
@TableName("base_app_region")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.UUID)
    private String id;
    /**
     *区域名字
     */
    private String name;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 上级区域ID
     */
    private String pid;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 版本号
     */
    private String ver;
    /**
     * 状态（0正常 1删除 ）
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pid='" + pid + '\'' +
                ", description='" + description + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", ver='" + ver + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
