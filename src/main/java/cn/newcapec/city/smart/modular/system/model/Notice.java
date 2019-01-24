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
 * 通知表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Getter
@Setter
@TableName("base_app_message_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.UUID)
	private String id;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
	/**
	 * 描述
	 */
	private  String description;
	/**
	 * 行业类型
	 */
	private  String industryType;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 创建人
     */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 逻辑删除
	 */
	private String delFlag;
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
		return "Notice{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", description='" + description + '\'' +
				", industryType='" + industryType + '\'' +
				", createTime=" + createTime +
				", createBy='" + createBy + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateTime=" + updateTime +
				", delFlag='" + delFlag + '\'' +
				", ver=" + ver +
				'}';
	}
}
