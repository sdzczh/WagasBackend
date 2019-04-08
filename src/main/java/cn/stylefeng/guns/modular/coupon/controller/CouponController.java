package cn.stylefeng.guns.modular.coupon.controller;

import cn.stylefeng.guns.modular.coupon.service.ICouponInfoService;
import cn.stylefeng.guns.modular.system.model.CouponInfo;
import cn.stylefeng.guns.modular.system.warpper.CouponWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Coupon;
import cn.stylefeng.guns.modular.coupon.service.ICouponService;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 优惠券信息控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 11:33:35
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    private String PREFIX = "/coupon/coupon/";

    @Autowired
    private ICouponService couponService;
    @Autowired
    private ICouponInfoService couponInfoService;

    /**
     * 跳转到优惠券信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "coupon.html";
    }

    /**
     * 跳转到添加优惠券信息
     */
    @RequestMapping("/coupon_add")
    public String couponAdd() {
        return PREFIX + "coupon_add.html";
    }

    /**
     * 跳转到修改优惠券信息
     */
    @RequestMapping("/coupon_update/{couponId}")
    public String couponUpdate(@PathVariable Integer couponId, Model model) {
        Coupon coupon = couponService.selectById(couponId);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("coupon_id", couponId.toString());
        CouponInfo couponInfo = couponInfoService.selectOne(entityWrapper);
        model.addAttribute("item",coupon);
        model.addAttribute("couponInfo",couponInfo);
        LogObjectHolder.me().set(coupon);
        LogObjectHolder.me().set(couponInfo);
        return PREFIX + "coupon_edit.html";
    }

    /**
     * 获取优惠券信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String title) {
        List<Map<String, Object>> list = couponService.selectLists(title);
        return new CouponWarpper(list).wrap();
    }

    /**
     * 新增优惠券信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Coupon coupon, CouponInfo couponInfo, Integer coinInfoId, String title, String desc, String rules, String showImgUrl, String infoImgUrl) {
        couponService.insert(coupon);
        couponInfo.setId(coinInfoId);
        couponInfo.setTitle(title);
        couponInfo.setDesc(desc);
        couponInfo.setInfoImgUrl(infoImgUrl);
        couponInfo.setRules(rules);
        couponInfo.setShowImgUrl(showImgUrl);
        couponInfo.setCouponId(coupon.getId());
        couponInfoService.insert(couponInfo);
        return SUCCESS_TIP;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    /**
     * 删除优惠券信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer couponId) {
        couponService.deleteById(couponId);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("coupon_id", couponId);
        CouponInfo couponInfo = couponInfoService.selectOne(entityWrapper);
        couponInfoService.deleteById(couponInfo.getId());
        return SUCCESS_TIP;
    }

    /**
     * 修改优惠券信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Coupon coupon, CouponInfo couponInfo, String title, String desc, String rules, String showImgUrl, String infoImgUrl) {
        couponService.updateById(coupon);
        couponInfo.setTitle(title);
        couponInfo.setDesc(desc);
        couponInfo.setInfoImgUrl(infoImgUrl);
        couponInfo.setRules(rules);
        couponInfo.setShowImgUrl(showImgUrl);
        couponInfo.setCouponId(coupon.getId());
        couponInfoService.updateById(couponInfo);
        return SUCCESS_TIP;
    }

    /**
     * 优惠券信息详情
     */
    @RequestMapping(value = "/detail/{couponId}")
    @ResponseBody
    public Object detail(@PathVariable("couponId") Integer couponId) {
        return couponService.selectById(couponId);
    }
}
