package cn.newcapec.city.smart.modular.system.controller;

import cn.newcapec.city.smart.core.common.annotion.BussinessLog;
import cn.newcapec.city.smart.core.common.annotion.Permission;
import cn.newcapec.city.smart.core.common.constant.Const;
import cn.newcapec.city.smart.core.common.constant.dictmap.RegionDict;
import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.common.exception.BizExceptionEnum;
import cn.newcapec.city.smart.core.core.base.controller.BaseController;
import cn.newcapec.city.smart.core.core.base.tips.Tip;
import cn.newcapec.city.smart.core.core.exception.GunsException;
import cn.newcapec.city.smart.core.core.node.ZTreeNode;
import cn.newcapec.city.smart.core.core.util.ToolUtil;
import cn.newcapec.city.smart.core.log.LogObjectHolder;
import cn.newcapec.city.smart.core.shiro.ShiroKit;
import cn.newcapec.city.smart.modular.system.model.Region;
import cn.newcapec.city.smart.modular.system.service.IRegionService;
import cn.newcapec.city.smart.modular.system.warpper.RegionWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Permission(Const.ADMIN_NAME)
    @RequestMapping("/region_edit/{id}")
    public String AreaUpdate(@PathVariable String id, Model model) {
        Region region = regionService.selectById(id);
        model.addAttribute("item", region);
        //如果有父级区域则查询父级区域名称
        if("0".equals(region.getPid())){
            model.addAttribute("pName","");
        }else{
            Region pRegion = regionService.selectById(region.getPid());
            model.addAttribute("pName",pRegion.getName());
        }
        LogObjectHolder.me().set(region);
        return PREFIX + "region_edit.html";
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectRegionTreeList")
    @ResponseBody
    public List<ZTreeNode> selectRegionTreeList() {
        List<ZTreeNode> regionTreeList = this.regionService.regionTreeList();
        return regionTreeList;
    }


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

    /**
     * 新增区域
     */
    @Permission(Const.ADMIN_NAME)
    @BussinessLog(value = "区域新增", key = "code,name", dict = RegionDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(Region region, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断是否存在该编号
       String existedRegionCode = ConstantFactory.me().getRegionNameByCode(region.getCode());
        if (ToolUtil.isNotEmpty(existedRegionCode)) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_REGION);
        }
        //判断父级编号是否为空，若为空则为省份直辖市，设置pid为0
        if(ToolUtil.isEmpty(region.getPid())){
            region.setPid("0");
        }
        region.setCreateBy(ShiroKit.getUser().getName());
        region.setCreateTime(new Date());
        region.setDelFlag(0);
        this.regionService.insert(region);
        return SUCCESS_TIP;
    }

    /**
     * 删除区域管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        System.out.println(id);
        //查询该区域
        Region region = regionService.selectById(id);
        region.setDelFlag(1);
        this.regionService.updateById(region);
        return SUCCESS_TIP;
    }

    /**
     * 修改区域管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Region region) {

        region.setUpdateBy(ShiroKit.getUser().getName());
        region.setUpdateTime(new Date());
        regionService.updateById(region);
        return SUCCESS_TIP;
    }

}
