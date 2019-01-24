package cn.newcapec.city.smart.modular.system.warpper;

import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 区域管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class RegionWarpper extends BaseControllerWarpper {

    public RegionWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("NAMECODE","("+map.get("CODE")+")"+map.get("NAME"));
       // map.put("statusName", ConstantFactory.me().getAreaStatusName((String) map.get("STATUS")));
    }

}
