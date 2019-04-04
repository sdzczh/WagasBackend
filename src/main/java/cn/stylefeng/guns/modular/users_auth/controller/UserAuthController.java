package cn.stylefeng.guns.modular.users_auth.controller;

import cn.stylefeng.guns.modular.system.warpper.UserAuthWarpper;
import cn.stylefeng.guns.modular.system.warpper.UserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.UserAuth;
import cn.stylefeng.guns.modular.users_auth.service.IUserAuthService;

import java.util.List;
import java.util.Map;

/**
 * 第三方授权管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 10:05:19
 */
@Controller
@RequestMapping("/userAuth")
public class UserAuthController extends BaseController {

    private String PREFIX = "/users_auth/userAuth/";

    @Autowired
    private IUserAuthService userAuthService;

    /**
     * 跳转到第三方授权管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userAuth.html";
    }

    /**
     * 跳转到添加第三方授权管理
     */
    @RequestMapping("/userAuth_add")
    public String userAuthAdd() {
        return PREFIX + "userAuth_add.html";
    }

    /**
     * 跳转到修改第三方授权管理
     */
    @RequestMapping("/userAuth_update/{userAuthId}")
    public String userAuthUpdate(@PathVariable Integer userAuthId, Model model) {
        UserAuth userAuth = userAuthService.selectById(userAuthId);
        model.addAttribute("item",userAuth);
        LogObjectHolder.me().set(userAuth);
        return PREFIX + "userAuth_edit.html";
    }

    /**
     * 获取第三方授权管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        List<Map<String, Object>> lists = userAuthService.selectLists(phone);
        return new UserAuthWarpper(lists).wrap();
    }

    /**
     * 新增第三方授权管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserAuth userAuth) {
        userAuthService.insert(userAuth);
        return SUCCESS_TIP;
    }

    /**
     * 删除第三方授权管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userAuthId) {
        userAuthService.deleteById(userAuthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改第三方授权管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserAuth userAuth) {
        userAuthService.updateById(userAuth);
        return SUCCESS_TIP;
    }

    /**
     * 第三方授权管理详情
     */
    @RequestMapping(value = "/detail/{userAuthId}")
    @ResponseBody
    public Object detail(@PathVariable("userAuthId") Integer userAuthId) {
        return userAuthService.selectById(userAuthId);
    }
}
