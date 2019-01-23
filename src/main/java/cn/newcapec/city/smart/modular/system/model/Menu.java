package cn.newcapec.city.smart.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Setter
@Getter
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单父编号
     */
    private String pcode;
    /**
     * 当前菜单的所有父菜单编号
     */
    private String pcodes;
    /**
     * 菜单名称
     */
    @NotBlank
    private String name;
    /**
     * url地址
     */
    @NotBlank
    private String url;
    /**
     * 菜单排序号
     */
    private Integer num;
    /**
     * 菜单层级
     */
    private String levels;
    /**
     * 是否是菜单（1：是  0：不是）
     */
    private String ismenu;
    /**
     * 备注
     */
    private String tips;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    private String status;
    /**
     * 是否打开:    1:打开   0:不打开
     */
    private String isopen;
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
    private Integer ver;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", pcode='" + pcode + '\'' +
                ", pcodes='" + pcodes + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", num=" + num +
                ", levels='" + levels + '\'' +
                ", ismenu='" + ismenu + '\'' +
                ", tips='" + tips + '\'' +
                ", status='" + status + '\'' +
                ", isopen='" + isopen + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", ver=" + ver +
                '}';
    }
}
