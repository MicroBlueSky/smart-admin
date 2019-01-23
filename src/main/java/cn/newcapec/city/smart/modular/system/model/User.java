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
 * 管理员表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Setter
@Getter
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 头像
     */
	private String avatar;
    /**
     * 账号
     */
	private String account;
    /**
     * 密码
     */
	private String password;
    /**
     * md5密码盐
     */
	private String salt;
    /**
     * 名字
     */
	private String name;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 性别（1：男 2：女）
     */
	private Integer sex;
    /**
     * 电子邮件
     */
	private String email;
    /**
     * 电话
     */
	private String phone;
    /**
     * 角色id
     */
	private String roleid;
    /**
     * 部门id
     */
	private String deptid;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
	private String status;
	/**
	 * 创建人
	 */
	private String createBy;
    /**
     * 创建时间
     */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String updateBy;
	/**
	 * 创建时间
	 */
	private Date updateTime;
    /**
     * 保留字段
     */
	private Integer ver;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", avatar='" + avatar + '\'' +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", name='" + name + '\'' +
				", birthday=" + birthday +
				", sex=" + sex +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", roleid='" + roleid + '\'' +
				", deptid='" + deptid + '\'' +
				", status='" + status + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateTime=" + updateTime +
				", ver='" + ver + '\'' +
				'}';
	}
}
