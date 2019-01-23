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
 * 角色表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Setter
@Getter
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 序号
     */
	private Integer num;
    /**
     * 父角色id
     */
	private String pid;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 部门名称
     */
	private String deptid;
    /**
     * 提示
     */
	private String tips;
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
		return "Role{" +
				"id='" + id + '\'' +
				", num=" + num +
				", pid='" + pid + '\'' +
				", name='" + name + '\'' +
				", deptid='" + deptid + '\'' +
				", tips='" + tips + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime='" + createTime + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateTime='" + updateTime + '\'' +
				", ver=" + ver +
				'}';
	}
}
