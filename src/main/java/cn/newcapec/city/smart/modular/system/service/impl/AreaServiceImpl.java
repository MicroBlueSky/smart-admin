package cn.newcapec.city.smart.modular.system.service.impl;

import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.modular.system.model.Area;
import cn.newcapec.city.smart.modular.system.dao.AreaMapper;
import cn.newcapec.city.smart.modular.system.service.IAreaService;
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
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Map<String, Object>> selectAreas(String condition, String areaCode) {
        return areaMapper.selectAreas(condition,areaCode);
    }

    @Override
    public List<ZTreeNode> areaTreeList() {
        return baseMapper.areaTreeList();
    }

    @Override
    public boolean insertArea(Area area) {
        Integer integer = areaMapper.insertArea(area);
        if(integer > 0){
            return true;
        }
        return false;
    }

    @Override
    public Area selectByAreaCode(String code) {
        return areaMapper.selectOneByAreaCode(code);
    }


}
