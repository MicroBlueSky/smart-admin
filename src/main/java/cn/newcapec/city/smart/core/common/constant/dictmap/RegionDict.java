package cn.newcapec.city.smart.core.common.constant.dictmap;

import cn.newcapec.city.smart.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 地域的映射
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class RegionDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "主键");
        put("name", "区域名称");
        put("code", "区域编号");
        put("pid", "父级编号");
        put("description", "描述信息");
        put("createBy", "创建人");
        put("createTime", "创建时间");
        put("updateBy", "更新人");
        put("updateTime", "更新时间");
        put("ver", "版本号");
        put("delFlag", "删除状态");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("areaType", "getAreaType");
    }
}
