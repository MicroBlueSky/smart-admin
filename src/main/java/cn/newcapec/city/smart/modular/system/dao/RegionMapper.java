package cn.newcapec.city.smart.modular.system.dao;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.model.Region;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author syf
 * @since 2019-01-17
 */
public interface RegionMapper extends BaseMapper<Region> {
    /**
     * 根据条件查询菜单
     * @return
     * @date 2019年1月24日 下午2:30:00
     */
    List<Map<String, Object>> selectRegions(@Param("name") String name, @Param("code") String code);

    /**
     * 获取区域列表树
     *
     * @return
     * @date 2019年1月25日
     */
    List<ZTreeNode> regionTreeList();

    /**
     * 通过区域代码获取区域名称
     *
     * @return
     * @date 2019年1月25日
     */
    Region selectRegionByCode(String code);

    /**
     * @Description :syf
     * @return
     * @CreateDate:     2019/1/28 9:58
     */
    Region selectRegionByIdAndCode(String id, String code);
}
