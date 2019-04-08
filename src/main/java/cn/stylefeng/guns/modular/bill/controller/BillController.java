package cn.stylefeng.guns.modular.bill.controller;

import cn.stylefeng.guns.core.common.constant.factory.PageFactory;
import cn.stylefeng.guns.core.common.page.PageInfoBT;
import cn.stylefeng.guns.modular.system.model.OperationLog;
import cn.stylefeng.guns.modular.system.warpper.BillWarpper;
import cn.stylefeng.guns.modular.system.warpper.LogWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Bill;
import cn.stylefeng.guns.modular.bill.service.IBillService;

import java.util.List;
import java.util.Map;

/**
 * 账单管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-08 15:58:39
 */
@Controller
@RequestMapping("/bill")
public class BillController extends BaseController {

    private String PREFIX = "/bill/bill/";

    @Autowired
    private IBillService billService;

    /**
     * 跳转到账单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bill.html";
    }

    /**
     * 跳转到添加账单管理
     */
    @RequestMapping("/bill_add")
    public String billAdd() {
        return PREFIX + "bill_add.html";
    }

    /**
     * 跳转到修改账单管理
     */
    @RequestMapping("/bill_update/{billId}")
    public String billUpdate(@PathVariable Integer billId, Model model) {
        Bill bill = billService.selectById(billId);
        model.addAttribute("item",bill);
        LogObjectHolder.me().set(bill);
        return PREFIX + "bill_edit.html";
    }

    /**
     * 获取账单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone, Integer type) {
        Page<Bill> page = new PageFactory<Bill>().defaultPage();
        List<Map<String, Object>> result = billService.selectLists(page, phone, type);
        page.setRecords(new BillWarpper(result).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增账单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Bill bill) {
        billService.insert(bill);
        return SUCCESS_TIP;
    }

    /**
     * 删除账单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer billId) {
        billService.deleteById(billId);
        return SUCCESS_TIP;
    }

    /**
     * 修改账单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Bill bill) {
        billService.updateById(bill);
        return SUCCESS_TIP;
    }

    /**
     * 账单管理详情
     */
    @RequestMapping(value = "/detail/{billId}")
    @ResponseBody
    public Object detail(@PathVariable("billId") Integer billId) {
        return billService.selectById(billId);
    }
}
