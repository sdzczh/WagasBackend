package cn.stylefeng.guns.modular.activity.controller;

import cn.stylefeng.guns.modular.system.warpper.ActivityRecordWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ActivityRecord;
import cn.stylefeng.guns.modular.activity.service.IActivityRecordService;

import java.util.List;
import java.util.Map;

/**
 * 活动记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 17:37:05
 */
@Controller
@RequestMapping("/activityRecord")
public class ActivityRecordController extends BaseController {

    private String PREFIX = "/activity/activityRecord/";

    @Autowired
    private IActivityRecordService activityRecordService;

    /**
     * 跳转到活动记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activityRecord.html";
    }

    /**
     * 跳转到添加活动记录
     */
    @RequestMapping("/activityRecord_add")
    public String activityRecordAdd() {
        return PREFIX + "activityRecord_add.html";
    }

    /**
     * 跳转到修改活动记录
     */
    @RequestMapping("/activityRecord_update/{activityRecordId}")
    public String activityRecordUpdate(@PathVariable Integer activityRecordId, Model model) {
        ActivityRecord activityRecord = activityRecordService.selectById(activityRecordId);
        model.addAttribute("item",activityRecord);
        LogObjectHolder.me().set(activityRecord);
        return PREFIX + "activityRecord_edit.html";
    }

    /**
     * 获取活动记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String act_name, String phone) {
        List<Map<String, Object>> list = activityRecordService.selectLists(phone, act_name);
        return new ActivityRecordWarpper(list).wrap();
    }

    /**
     * 新增活动记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ActivityRecord activityRecord) {
        activityRecordService.insert(activityRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除活动记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activityRecordId) {
        activityRecordService.deleteById(activityRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改活动记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ActivityRecord activityRecord) {
        activityRecordService.updateById(activityRecord);
        return SUCCESS_TIP;
    }

    /**
     * 活动记录详情
     */
    @RequestMapping(value = "/detail/{activityRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("activityRecordId") Integer activityRecordId) {
        return activityRecordService.selectById(activityRecordId);
    }
}
