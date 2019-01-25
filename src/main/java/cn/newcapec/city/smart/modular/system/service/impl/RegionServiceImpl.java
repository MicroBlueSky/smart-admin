package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.dao.RegionMapper;
import cn.newcapec.city.smart.modular.system.model.Region;
import cn.newcapec.city.smart.modular.system.service.IRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author syf
 * @since 2019-01-17
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

    @Resource
    private RegionMapper regionMapper;


    @Override
    public List<Map<String, Object>> selectRegions(String name, String code) {
        return regionMapper.selectRegions(name,code);
    }

    @Override
    public List<ZTreeNode> regionTreeList() {
        return regionMapper.regionTreeList();
    }
}
