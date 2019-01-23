package cn.newcapec.city.smart.modular.system.service;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.model.Area;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author syf
 * @since 2019-01-17
 */
public interface IAreaService extends IService<Area> {

    /**
     * @Description :根据条件查询区域
     * @param
     * @return
     */
    List<Map<String, Object>> selectAreas(@Param("condition")String condition, @Param("areaCode")String areaCode);

    /**
     * @Description :获取区域菜单列表树
     * @param
     * @return
     */
    List<ZTreeNode> areaTreeList();

    /**
     * @Description :添加区域
     * @param
     * @return
     */
    boolean insertArea(Area area);

    /**
     * @Description :通过区域代码查询区域
     * @param
     * @return
     */
    Area selectByAreaCode(String code);
}
