package cn.newcapec.city.smart.modular.system.service;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.model.Region;
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
public interface IRegionService extends IService<Region> {

    /**
     * @Description :根据条件查询菜单
     * @param
     * @return
     */
    List<Map<String, Object>> selectRegions(@Param("code") String condition, @Param("code") String code);
}
