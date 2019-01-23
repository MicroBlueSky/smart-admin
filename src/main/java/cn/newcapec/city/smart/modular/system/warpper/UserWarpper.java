package cn.newcapec.city.smart.modular.system.warpper;

import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWarpper extends BaseControllerWarpper {

    public UserWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((String) map.get("SEX")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("ROLEID")));
        map.put("deptName", ConstantFactory.me().getDeptName((String) map.get("DEPTID")));
        map.put("statusName", ConstantFactory.me().getStatusName((String) map.get("STATUS")));
    }

}
