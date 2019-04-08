package cn.stylefeng.guns.modular.activity.controller;

import cn.stylefeng.guns.modular.system.warpper.ActivityWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Activity;
import cn.stylefeng.guns.modular.activity.service.IActivityService;

import java.util.List;
import java.util.Map;

/**
 * 活动信息管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 16:59:25
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    private String PREFIX = "/activity/activity/";

    @Autowired
    private IActivityService activityService;

    /**
     * 跳转到活动信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activity.html";
    }

    /**
     * 跳转到添加活动信息管理
     */
    @RequestMapping("/activity_add")
    public String activityAdd() {
        return PREFIX + "activity_add.html";
    }

    /**
     * 跳转到修改活动信息管理
     */
    @RequestMapping("/activity_update/{activityId}")
    public String activityUpdate(@PathVariable Integer activityId, Model model) {
        Activity activity = activityService.selectById(activityId);
        model.addAttribute("item",activity);
        LogObjectHolder.me().set(activity);
        return PREFIX + "activity_edit.html";
    }

    /**
     * 获取活动信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String goods_name) {
        List<Map<String, Object>> list = activityService.selectLists(goods_name);
        return new ActivityWarpper(list).wrap();
    }

    /**
     * 新增活动信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Activity activity) {
        activityService.insert(activity);
        return SUCCESS_TIP;
    }

    /**
     * 删除活动信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activityId) {
        activityService.deleteById(activityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改活动信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Activity activity) {
        activityService.updateById(activity);
        return SUCCESS_TIP;
    }

    /**
     * 活动信息管理详情
     */
    @RequestMapping(value = "/detail/{activityId}")
    @ResponseBody
    public Object detail(@PathVariable("activityId") Integer activityId) {
        return activityService.selectById(activityId);
    }
}
