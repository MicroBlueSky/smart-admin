package cn.newcapec.city.smart.modular.system.warpper;

import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 角色列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日10:59:02
 */
public class RoleWarpper extends BaseControllerWarpper {

    public RoleWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("pName", ConstantFactory.me().getSingleRoleName((String) map.get("PID")));
        map.put("deptName", ConstantFactory.me().getDeptName((String) map.get("DEPTID")));
    }

}
