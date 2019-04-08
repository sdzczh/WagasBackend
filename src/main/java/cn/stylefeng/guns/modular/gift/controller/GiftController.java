package cn.stylefeng.guns.modular.gift.controller;

import cn.stylefeng.guns.modular.system.warpper.GiftWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Gift;
import cn.stylefeng.guns.modular.gift.service.IGiftService;

import java.util.List;
import java.util.Map;

/**
 * 礼物管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 09:46:45
 */
@Controller
@RequestMapping("/gift")
public class GiftController extends BaseController {

    private String PREFIX = "/gift/gift/";

    @Autowired
    private IGiftService giftService;

    /**
     * 跳转到礼物管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "gift.html";
    }

    /**
     * 跳转到添加礼物管理
     */
    @RequestMapping("/gift_add")
    public String giftAdd() {
        return PREFIX + "gift_add.html";
    }

    /**
     * 跳转到修改礼物管理
     */
    @RequestMapping("/gift_update/{giftId}")
    public String giftUpdate(@PathVariable Integer giftId, Model model) {
        Gift gift = giftService.selectById(giftId);
        model.addAttribute("item",gift);
        LogObjectHolder.me().set(gift);
        return PREFIX + "gift_edit.html";
    }

    /**
     * 获取礼物管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String gift_name) {
        List<Map<String, Object>> list = giftService.selectLists(gift_name);
        return new GiftWarpper(list).wrap();
    }

    /**
     * 新增礼物管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Gift gift) {
        giftService.insert(gift);
        return SUCCESS_TIP;
    }

    /**
     * 删除礼物管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer giftId) {
        giftService.deleteById(giftId);
        return SUCCESS_TIP;
    }

    /**
     * 修改礼物管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Gift gift) {
        giftService.updateById(gift);
        return SUCCESS_TIP;
    }

    /**
     * 礼物管理详情
     */
    @RequestMapping(value = "/detail/{giftId}")
    @ResponseBody
    public Object detail(@PathVariable("giftId") Integer giftId) {
        return giftService.selectById(giftId);
    }
}
