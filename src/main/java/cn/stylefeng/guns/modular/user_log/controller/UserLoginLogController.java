package cn.stylefeng.guns.modular.user_log.controller;

import cn.stylefeng.guns.modular.system.warpper.UserAuthWarpper;
import cn.stylefeng.guns.modular.system.warpper.UserLogWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.UserLoginLog;
import cn.stylefeng.guns.modular.user_log.service.IUserLoginLogService;

import java.util.List;
import java.util.Map;

/**
 * 会员登录日志控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 11:09:09
 */
@Controller
@RequestMapping("/userLoginLog")
public class UserLoginLogController extends BaseController {

    private String PREFIX = "/user_log/userLoginLog/";

    @Autowired
    private IUserLoginLogService userLoginLogService;

    /**
     * 跳转到会员登录日志首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userLoginLog.html";
    }

    /**
     * 跳转到添加会员登录日志
     */
    @RequestMapping("/userLoginLog_add")
    public String userLoginLogAdd() {
        return PREFIX + "userLoginLog_add.html";
    }

    /**
     * 跳转到修改会员登录日志
     */
    @RequestMapping("/userLoginLog_update/{userLoginLogId}")
    public String userLoginLogUpdate(@PathVariable Integer userLoginLogId, Model model) {
        UserLoginLog userLoginLog = userLoginLogService.selectById(userLoginLogId);
        model.addAttribute("item",userLoginLog);
        LogObjectHolder.me().set(userLoginLog);
        return PREFIX + "userLoginLog_edit.html";
    }

    /**
     * 获取会员登录日志列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        List<Map<String, Object>> lists = userLoginLogService.selectLists(phone);
        return new UserLogWarpper(lists).wrap();
    }

    /**
     * 新增会员登录日志
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserLoginLog userLoginLog) {
        userLoginLogService.insert(userLoginLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员登录日志
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userLoginLogId) {
        userLoginLogService.deleteById(userLoginLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员登录日志
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserLoginLog userLoginLog) {
        userLoginLogService.updateById(userLoginLog);
        return SUCCESS_TIP;
    }

    /**
     * 会员登录日志详情
     */
    @RequestMapping(value = "/detail/{userLoginLogId}")
    @ResponseBody
    public Object detail(@PathVariable("userLoginLogId") Integer userLoginLogId) {
        return userLoginLogService.selectById(userLoginLogId);
    }
}
