package cn.newcapec.city.smart.modular.system.controller;

import cn.newcapec.city.smart.core.common.annotion.BussinessLog;
import cn.newcapec.city.smart.core.common.constant.dictmap.NoticeMap;
import cn.newcapec.city.smart.core.common.constant.factory.ConstantFactory;
import cn.newcapec.city.smart.core.common.exception.BizExceptionEnum;
import cn.newcapec.city.smart.core.core.base.controller.BaseController;
import cn.newcapec.city.smart.core.core.exception.GunsException;
import cn.newcapec.city.smart.core.core.util.ToolUtil;
import cn.newcapec.city.smart.core.log.LogObjectHolder;
import cn.newcapec.city.smart.core.shiro.ShiroKit;
import cn.newcapec.city.smart.modular.system.model.Notice;
import cn.newcapec.city.smart.modular.system.service.INoticeService;
import cn.newcapec.city.smart.modular.system.warpper.NoticeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable String noticeId, Model model) {
        Notice notice = this.noticeService.selectById(noticeId);
        model.addAttribute("notice",notice);
        LogObjectHolder.me().set(notice);
        return PREFIX + "notice_edit.html";
    }

    /**
     * 跳转到内容详情页
     */
    @RequestMapping("/notice_detail/{noticeId}")
    public String noticeDetail(@PathVariable String noticeId, Model model) {
        Notice notice = this.noticeService.selectById(noticeId);
        model.addAttribute("content",notice.getContent());
        return PREFIX + "notice_detail.jsp";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> notices = noticeService.list(null);
        
        for (int i = 0; i < notices.size(); i++) {
        	Clob clob =(Clob)notices.get(i).get("CONTENT");
            Reader instream;
			try {
				instream = clob.getCharacterStream();
				BufferedReader br = new BufferedReader(instream);
				String str=br.readLine();
				StringBuffer sb=new StringBuffer();
				while (str!=null) {
					sb.append(str);
					str=br.readLine();
				}
				String content=sb.toString();
				notices.get(i).put("content",content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       

        super.setAttr("noticeList",notices);
        return "/blackboard.html";
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.noticeService.list(condition);
        return super.warpObject(new NoticeWrapper(list));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增通知",key = "title",dict = NoticeMap.class)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreateBy(ShiroKit.getUser().getId());
        notice.setCreateTime(new Date());
        notice.insert();
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知",key = "noticeId",dict = NoticeMap.class)
    public Object delete(@RequestParam String noticeId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.noticeService.deleteById(noticeId);

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改通知",key = "title",dict = NoticeMap.class)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.selectById(notice.getId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.setUpdateBy(ShiroKit.getUser().getId());
        old.setUpdateTime(new Date());
        old.updateById();
        return SUCCESS_TIP;
    }

}
