package cn.newcapec.city.smart.modular.system.dao;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.model.Area;
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
public interface AreaMapper extends BaseMapper<Area> {

    List<Map<String, Object>> selectAreas(@Param("condition") String condition, @Param("areaCode") String areaCode);

    /**
     * @Description :获取区域菜单列表树
     * @param
     * @return
     */
    List<ZTreeNode> areaTreeList();

    /**
     * @Description :通过区域代码获取区域
     * @param
     * @return
     */
    Area selectOneByAreaCode(String code);

    /**
     * @Description :插入区域
     * @param
     * @return
     */
    Integer insertArea(Area area);

}
