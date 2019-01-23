package cn.newcapec.city.smart.core.common.constant.dictmap;

import cn.newcapec.city.smart.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 地域的映射
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class AreaDict extends AbstractDictMap {

    @Override
    public void init() {
        put("areaCode", "区域编号");
        put("parentCode", "父级编号");
        put("parentCodes", "所有父级编号");
        put("treeSort", "本级排序号");
        put("treeSorts", "所有级别排序号");
        put("treeLeaf", "是否最末级");
        put("treeLevel", "层次级别");
        put("treeNames", "全节点名");
        put("areaName", "区域名称");
        put("areaType", "区域类型");
        put("status", "状态");
        put("remarks", "备注信息");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("areaType", "getAreaType");
    }
}
