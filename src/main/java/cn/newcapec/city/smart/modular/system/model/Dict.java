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
 * 字典表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Setter
@Getter
@TableName("sys_dict")
public class Dict extends Model<Dict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value="id", type= IdType.UUID)
	private String id;
	/**
	 * 排序
	 */
	private String num;
	/**
	 * 父级字典
	 */
	private String pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编码
	 */
	private String code;
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
		return "Dict{" +
				"id='" + id + '\'' +
				", num='" + num + '\'' +
				", pid='" + pid + '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", tips='" + tips + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateTime=" + updateTime +
				", ver=" + ver +
				'}';
	}
}
