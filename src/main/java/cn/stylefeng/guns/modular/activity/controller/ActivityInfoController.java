package cn.stylefeng.guns.modular.activity.controller;

import cn.stylefeng.guns.modular.system.warpper.ActivityInfoWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ActivityInfo;
import cn.stylefeng.guns.modular.activity.service.IActivityInfoService;

import java.util.List;
import java.util.Map;

/**
 * 用户活动管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 17:25:23
 */
@Controller
@RequestMapping("/activityInfo")
public class ActivityInfoController extends BaseController {

    private String PREFIX = "/activity/activityInfo/";

    @Autowired
    private IActivityInfoService activityInfoService;

    /**
     * 跳转到用户活动管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activityInfo.html";
    }

    /**
     * 跳转到添加用户活动管理
     */
    @RequestMapping("/activityInfo_add")
    public String activityInfoAdd() {
        return PREFIX + "activityInfo_add.html";
    }

    /**
     * 跳转到修改用户活动管理
     */
    @RequestMapping("/activityInfo_update/{activityInfoId}")
    public String activityInfoUpdate(@PathVariable Integer activityInfoId, Model model) {
        ActivityInfo activityInfo = activityInfoService.selectById(activityInfoId);
        model.addAttribute("item",activityInfo);
        LogObjectHolder.me().set(activityInfo);
        return PREFIX + "activityInfo_edit.html";
    }

    /**
     * 获取用户活动管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String act_name, String phone) {
        List<Map<String, Object>> list = activityInfoService.selectLists(phone, act_name);
        return new ActivityInfoWarpper(list).wrap();
    }

    /**
     * 新增用户活动管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ActivityInfo activityInfo) {
        activityInfoService.insert(activityInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户活动管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activityInfoId) {
        activityInfoService.deleteById(activityInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户活动管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ActivityInfo activityInfo) {
        activityInfoService.updateById(activityInfo);
        return SUCCESS_TIP;
    }

    /**
     * 用户活动管理详情
     */
    @RequestMapping(value = "/detail/{activityInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("activityInfoId") Integer activityInfoId) {
        return activityInfoService.selectById(activityInfoId);
    }
}
