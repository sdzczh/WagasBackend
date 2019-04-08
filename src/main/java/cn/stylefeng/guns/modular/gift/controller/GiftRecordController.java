package cn.stylefeng.guns.modular.gift.controller;

import cn.stylefeng.guns.modular.system.warpper.GiftRecordWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.GiftRecord;
import cn.stylefeng.guns.modular.gift.service.IGiftRecordService;

import java.util.List;
import java.util.Map;

/**
 * 礼物赠送记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 10:18:41
 */
@Controller
@RequestMapping("/giftRecord")
public class GiftRecordController extends BaseController {

    private String PREFIX = "/gift/giftRecord/";

    @Autowired
    private IGiftRecordService giftRecordService;

    /**
     * 跳转到礼物赠送记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "giftRecord.html";
    }

    /**
     * 跳转到添加礼物赠送记录
     */
    @RequestMapping("/giftRecord_add")
    public String giftRecordAdd() {
        return PREFIX + "giftRecord_add.html";
    }

    /**
     * 跳转到修改礼物赠送记录
     */
    @RequestMapping("/giftRecord_update/{giftRecordId}")
    public String giftRecordUpdate(@PathVariable Integer giftRecordId, Model model) {
        GiftRecord giftRecord = giftRecordService.selectById(giftRecordId);
        model.addAttribute("item",giftRecord);
        LogObjectHolder.me().set(giftRecord);
        return PREFIX + "giftRecord_edit.html";
    }

    /**
     * 获取礼物赠送记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String pushPhone, String recivePhone, String giftName, Integer state) {
        List<Map<String, Object>> list = giftRecordService.selectLists(pushPhone, recivePhone, giftName, state);
        return new GiftRecordWarpper(list).wrap();
    }

    /**
     * 新增礼物赠送记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GiftRecord giftRecord) {
        giftRecordService.insert(giftRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除礼物赠送记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer giftRecordId) {
        giftRecordService.deleteById(giftRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改礼物赠送记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GiftRecord giftRecord) {
        giftRecordService.updateById(giftRecord);
        return SUCCESS_TIP;
    }

    /**
     * 礼物赠送记录详情
     */
    @RequestMapping(value = "/detail/{giftRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("giftRecordId") Integer giftRecordId) {
        return giftRecordService.selectById(giftRecordId);
    }
}
