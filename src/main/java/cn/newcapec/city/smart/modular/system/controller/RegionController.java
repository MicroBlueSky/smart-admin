package cn.newcapec.city.smart.modular.system.controller;

import cn.newcapec.city.smart.core.common.annotion.Permission;
import cn.newcapec.city.smart.core.common.constant.Const;
import cn.newcapec.city.smart.core.core.base.controller.BaseController;
import cn.newcapec.city.smart.core.log.LogObjectHolder;
import cn.newcapec.city.smart.modular.system.model.Region;
import cn.newcapec.city.smart.modular.system.service.IRegionService;
import cn.newcapec.city.smart.modular.system.warpper.RegionWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 区域管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-17 11:02:11
 */
@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {

    private String PREFIX = "/system/region/";

    @Autowired
    private IRegionService regionService;

    /**
     * 跳转到区域管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "region.html";
    }

    /**
     * 跳转到添加区域管理
     */
    @RequestMapping("/region_add")
    public String regionAdd() {
        return PREFIX + "region_add.html";
    }

    /**
     * 跳转到修改区域管理
     */
    //@Permission(value = "")
    @RequestMapping("/region_update/{regionId}")
    public String AreaUpdate(@PathVariable String regionId, Model model) {
        Region region = regionService.selectById(regionId);
        model.addAttribute("item", region);
        LogObjectHolder.me().set(region);
        return PREFIX + "region_edit.html";
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
//    @RequestMapping(value = "/selectAreaTreeList")
//    @ResponseBody
//    public List<ZTreeNode> selectMenuTreeList() {
//        List<ZTreeNode> areaTreeList = this.regionService.areaTreeList();
//        return areaTreeList;
//    }


    /**
     * 获取区域列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String code) {
        List<Map<String, Object>> regions = this.regionService.selectRegions(name,code);
        return new RegionWarpper(regions).warp();
    }
//
//    /**
//     * 新增区域管理
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "区域新增", key = "areaCode,areaName", dict = AreaDict.class)
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Tip add(Region region, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
//        }
//        //判断是否存在该编号
//       String existedAreaCode = ConstantFactory.me().getAreaNameByCode(region.getId());
//        if (ToolUtil.isNotEmpty(existedAreaCode)) {
//            throw new GunsException(BizExceptionEnum.EXISTED_THE_AREA);
//        }
//        //设置父级菜单编号
//        areaSetParentCode(region);
//
//        region.setCreateBy(ShiroKit.getUser().getId());
//        region.setCreateDate(new Date());
//        region.setStatus(AreaStatus.OK.getCode());
//        this.areaService.insertArea(region);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 删除区域管理
//     */
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam String areacode) {
//        System.out.println(areacode);
//        this.areaService.deleteById(areacode);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 修改区域管理
//     */
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(Region Region) {
//        areaService.updateById(Region);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 区域管理详情
//     */
//    @RequestMapping(value = "/detail/{AreaId}")
//    @ResponseBody
//    public Object detail(@PathVariable("AreaId") String AreaId) {
//        return areaService.selectById(AreaId);
//    }
//
//    /**
//     * 根据请求的父级区域编号设置parentCode和parentCodes
//     */
//    private void areaSetParentCode(@Valid Region region) {
//        if (ToolUtil.isEmpty(region.getParentCode())) {
//            region.setParentCode("0");
//            region.setParentCodes("0,");
//        } else {
//            Region parea = this.areaService.selectByAreaCode(region.getParentCode());
//            region.setParentCode(parea.getAreaCode());
//            //如果编号和父编号一致会导致无限递归
//            if (region.getAreaCode().equals(region.getParentCode())) {
//                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
//            }
//            region.setParentCodes(parea.getParentCodes() + parea.getAreaCode() + ",");
//        }
//    }
}
