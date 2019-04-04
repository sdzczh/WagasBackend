package cn.stylefeng.guns.modular.wallet.controller;

import cn.stylefeng.guns.modular.system.warpper.WalletRecordWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Record;
import cn.stylefeng.guns.modular.wallet.service.IRecordService;

import java.util.List;
import java.util.Map;

/**
 * 钱包流水记录控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 14:44:55
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {

    private String PREFIX = "/wallet/record/";

    @Autowired
    private IRecordService recordService;

    /**
     * 跳转到钱包流水记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "record.html";
    }

    /**
     * 跳转到添加钱包流水记录
     */
    @RequestMapping("/record_add")
    public String recordAdd() {
        return PREFIX + "record_add.html";
    }

    /**
     * 跳转到修改钱包流水记录
     */
    @RequestMapping("/record_update/{recordId}")
    public String recordUpdate(@PathVariable Integer recordId, Model model) {
        Record record = recordService.selectById(recordId);
        model.addAttribute("item",record);
        LogObjectHolder.me().set(record);
        return PREFIX + "record_edit.html";
    }

    /**
     * 获取钱包流水记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        List<Map<String, Object>> lists = recordService.selectLists(phone);
        return new WalletRecordWarpper(lists).wrap();
    }

    /**
     * 新增钱包流水记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Record record) {
        recordService.insert(record);
        return SUCCESS_TIP;
    }

    /**
     * 删除钱包流水记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer recordId) {
        recordService.deleteById(recordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改钱包流水记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Record record) {
        recordService.updateById(record);
        return SUCCESS_TIP;
    }

    /**
     * 钱包流水记录详情
     */
    @RequestMapping(value = "/detail/{recordId}")
    @ResponseBody
    public Object detail(@PathVariable("recordId") Integer recordId) {
        return recordService.selectById(recordId);
    }
}
