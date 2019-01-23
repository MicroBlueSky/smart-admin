package cn.newcapec.city.smart.modular.system.controller;

import cn.newcapec.city.smart.core.common.annotion.BussinessLog;
import cn.newcapec.city.smart.core.common.annotion.Permission;
import cn.newcapec.city.smart.core.common.constant.Const;
import cn.newcapec.city.smart.core.common.constant.dictmap.AreaDict;
import cn.newcapec.city.smart.core.common.constant.dictmap.MenuDict;
import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.common.constant.state.AreaStatus;
import cn.newcapec.city.smart.core.common.constant.state.MenuStatus;
import cn.newcapec.city.smart.core.common.exception.BizExceptionEnum;
import cn.newcapec.city.smart.core.core.base.controller.BaseController;
import cn.newcapec.city.smart.core.core.base.tips.Tip;
import cn.newcapec.city.smart.core.core.exception.GunsException;
import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.core.core.util.ToolUtil;
import cn.newcapec.city.smart.core.log.LogObjectHolder;
import cn.newcapec.city.smart.core.shiro.ShiroKit;
import cn.newcapec.city.smart.modular.system.model.Area;
import cn.newcapec.city.smart.modular.system.service.IAreaService;
import cn.newcapec.city.smart.modular.system.warpper.AreaWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 区域管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-17 11:02:11
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    private String PREFIX = "/system/area/";

    @Autowired
    private IAreaService areaService;

    /**
     * 跳转到区域管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "area.html";
    }

    /**
     * 跳转到添加区域管理
     */
    @RequestMapping("/Area_add")
    public String AreaAdd() {
        return PREFIX + "sysArea_add.html";
    }

    /**
     * 跳转到修改区域管理
     */
    //@Permission(value = "")
    @RequestMapping("/Area_update/{AreaId}")
    public String AreaUpdate(@PathVariable String AreaId, Model model) {
        System.out.println("=========================="+AreaId);
        Area Area = areaService.selectById(AreaId);
        model.addAttribute("item",Area);
        LogObjectHolder.me().set(Area);
        return PREFIX + "sysArea_edit.html";
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectAreaTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> areaTreeList = this.areaService.areaTreeList();
        return areaTreeList;
    }


    /**
     * 获取区域列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String areaName, @RequestParam(required = false) String areaCode) {
        List<Map<String, Object>> areas = this.areaService.selectAreas(areaName, areaCode);
        return new AreaWarpper(areas).warp();
    }

    /**
     * 新增区域管理
     */
    @Permission(Const.ADMIN_NAME)
    @BussinessLog(value = "区域新增", key = "areaCode,areaName", dict = AreaDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(Area area,  BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断是否存在该编号
       String existedAreaCode = ConstantFactory.me().getAreaNameByCode(area.getAreaCode());
        if (ToolUtil.isNotEmpty(existedAreaCode)) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_AREA);
        }
        //设置父级菜单编号
        areaSetParentCode(area);

        area.setCreateBy(ShiroKit.getUser().getId());
        area.setCreateDate(new Date());
        area.setStatus(AreaStatus.OK.getCode());
        this.areaService.insertArea(area);
        return SUCCESS_TIP;
    }

    /**
     * 删除区域管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String areacode) {
        System.out.println(areacode);
        this.areaService.deleteById(areacode);
        return SUCCESS_TIP;
    }

    /**
     * 修改区域管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Area Area) {
        areaService.updateById(Area);
        return SUCCESS_TIP;
    }

    /**
     * 区域管理详情
     */
    @RequestMapping(value = "/detail/{AreaId}")
    @ResponseBody
    public Object detail(@PathVariable("AreaId") String AreaId) {
        return areaService.selectById(AreaId);
    }

    /**
     * 根据请求的父级区域编号设置parentCode和parentCodes
     */
    private void areaSetParentCode(@Valid Area area) {
        if (ToolUtil.isEmpty(area.getParentCode())) {
            area.setParentCode("0");
            area.setParentCodes("0,");
        } else {
            Area parea = this.areaService.selectByAreaCode(area.getParentCode());
            area.setParentCode(parea.getAreaCode());
            //如果编号和父编号一致会导致无限递归
            if (area.getAreaCode().equals(area.getParentCode())) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            area.setParentCodes(parea.getParentCodes() + parea.getAreaCode() + ",");
        }
    }
}
