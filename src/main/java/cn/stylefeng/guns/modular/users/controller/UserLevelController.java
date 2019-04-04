package cn.stylefeng.guns.modular.users.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.UserLevel;
import cn.stylefeng.guns.modular.users.service.IUserLevelService;

/**
 * 会员等级管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 15:47:45
 */
@Controller
@RequestMapping("/userLevel")
public class UserLevelController extends BaseController {

    private String PREFIX = "/users/userLevel/";

    @Autowired
    private IUserLevelService userLevelService;

    /**
     * 跳转到会员等级管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userLevel.html";
    }

    /**
     * 跳转到添加会员等级管理
     */
    @RequestMapping("/userLevel_add")
    public String userLevelAdd() {
        return PREFIX + "userLevel_add.html";
    }

    /**
     * 跳转到修改会员等级管理
     */
    @RequestMapping("/userLevel_update/{userLevelId}")
    public String userLevelUpdate(@PathVariable Integer userLevelId, Model model) {
        UserLevel userLevel = userLevelService.selectById(userLevelId);
        model.addAttribute("item",userLevel);
        LogObjectHolder.me().set(userLevel);
        return PREFIX + "userLevel_edit.html";
    }

    /**
     * 获取会员等级管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return userLevelService.selectList(null);
    }

    /**
     * 新增会员等级管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserLevel userLevel) {
        userLevelService.insert(userLevel);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员等级管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userLevelId) {
        userLevelService.deleteById(userLevelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员等级管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserLevel userLevel) {
        userLevelService.updateById(userLevel);
        return SUCCESS_TIP;
    }

    /**
     * 会员等级管理详情
     */
    @RequestMapping(value = "/detail/{userLevelId}")
    @ResponseBody
    public Object detail(@PathVariable("userLevelId") Integer userLevelId) {
        return userLevelService.selectById(userLevelId);
    }
}
