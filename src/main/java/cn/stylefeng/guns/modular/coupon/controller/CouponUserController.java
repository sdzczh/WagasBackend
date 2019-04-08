package cn.stylefeng.guns.modular.coupon.controller;

import cn.stylefeng.guns.modular.system.warpper.CouponUserWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.CouponUser;
import cn.stylefeng.guns.modular.coupon.service.ICouponUserService;

import java.util.List;
import java.util.Map;

/**
 * 优惠券持有管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 15:19:27
 */
@Controller
@RequestMapping("/couponUser")
public class CouponUserController extends BaseController {

    private String PREFIX = "/coupon/couponUser/";

    @Autowired
    private ICouponUserService couponUserService;

    /**
     * 跳转到优惠券持有管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "couponUser.html";
    }

    /**
     * 跳转到添加优惠券持有管理
     */
    @RequestMapping("/couponUser_add")
    public String couponUserAdd() {
        return PREFIX + "couponUser_add.html";
    }

    /**
     * 跳转到修改优惠券持有管理
     */
    @RequestMapping("/couponUser_update/{couponUserId}")
    public String couponUserUpdate(@PathVariable Integer couponUserId, Model model) {
        CouponUser couponUser = couponUserService.selectById(couponUserId);
        model.addAttribute("item",couponUser);
        LogObjectHolder.me().set(couponUser);
        return PREFIX + "couponUser_edit.html";
    }

    /**
     * 获取优惠券持有管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone, String title) {
        List<Map<String, Object>> list = couponUserService.selectLists(phone, title);
        return new CouponUserWarpper(list).wrap();
    }

    /**
     * 新增优惠券持有管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CouponUser couponUser) {
        couponUserService.insert(couponUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除优惠券持有管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer couponUserId) {
        couponUserService.deleteById(couponUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改优惠券持有管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CouponUser couponUser) {
        couponUserService.updateById(couponUser);
        return SUCCESS_TIP;
    }

    /**
     * 优惠券持有管理详情
     */
    @RequestMapping(value = "/detail/{couponUserId}")
    @ResponseBody
    public Object detail(@PathVariable("couponUserId") Integer couponUserId) {
        return couponUserService.selectById(couponUserId);
    }
}
