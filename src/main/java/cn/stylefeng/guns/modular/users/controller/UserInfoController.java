package cn.stylefeng.guns.modular.users.controller;

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
import cn.stylefeng.guns.modular.system.model.UserInfo;
import cn.stylefeng.guns.modular.users.service.IUserInfoService;

/**
 * 会员信息管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 09:54:26
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    private String PREFIX = "/users/userInfo/";

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 跳转到会员信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userInfo.html";
    }

    /**
     * 跳转到添加会员信息管理
     */
    @RequestMapping("/userInfo_add")
    public String userInfoAdd() {
        return PREFIX + "userInfo_add.html";
    }

    /**
     * 跳转到修改会员信息管理
     */
    @RequestMapping("/userInfo_update/{userInfoId}")
    public String userInfoUpdate(@PathVariable Integer userInfoId, Model model) {
        UserInfo userInfo = userInfoService.selectById(userInfoId);
        model.addAttribute("item",userInfo);
        LogObjectHolder.me().set(userInfo);
        return PREFIX + "userInfo_edit.html";
    }

    /**
     * 获取会员信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.like("phone", phone);
        return userInfoService.selectList(entityWrapper);
    }

    /**
     * 新增会员信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserInfo userInfo) {
        userInfoService.insert(userInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userInfoId) {
        userInfoService.deleteById(userInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserInfo userInfo) {
        userInfoService.updateById(userInfo);
        return SUCCESS_TIP;
    }

    /**
     * 会员信息管理详情
     */
    @RequestMapping(value = "/detail/{userInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("userInfoId") Integer userInfoId) {
        return userInfoService.selectById(userInfoId);
    }
}
