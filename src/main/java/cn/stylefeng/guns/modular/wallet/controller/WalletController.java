package cn.stylefeng.guns.modular.wallet.controller;

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
import cn.stylefeng.guns.modular.system.model.Wallet;
import cn.stylefeng.guns.modular.wallet.service.IWalletService;

import java.util.List;
import java.util.Map;

/**
 * 会员钱包管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 11:30:15
 */
@Controller
@RequestMapping("/wallet")
public class WalletController extends BaseController {

    private String PREFIX = "/wallet/wallet/";

    @Autowired
    private IWalletService walletService;

    /**
     * 跳转到会员钱包管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wallet.html";
    }

    /**
     * 跳转到添加会员钱包管理
     */
    @RequestMapping("/wallet_add")
    public String walletAdd() {
        return PREFIX + "wallet_add.html";
    }

    /**
     * 跳转到修改会员钱包管理
     */
    @RequestMapping("/wallet_update/{walletId}")
    public String walletUpdate(@PathVariable Integer walletId, Model model) {
        Wallet wallet = walletService.selectById(walletId);
        model.addAttribute("item",wallet);
        LogObjectHolder.me().set(wallet);
        return PREFIX + "wallet_edit.html";
    }

    /**
     * 获取会员钱包管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        return walletService.selectLists(phone);
    }

    /**
     * 新增会员钱包管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Wallet wallet) {
        walletService.insert(wallet);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员钱包管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer walletId) {
        walletService.deleteById(walletId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员钱包管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Wallet wallet) {
        walletService.updateById(wallet);
        return SUCCESS_TIP;
    }

    /**
     * 会员钱包管理详情
     */
    @RequestMapping(value = "/detail/{walletId}")
    @ResponseBody
    public Object detail(@PathVariable("walletId") Integer walletId) {
        return walletService.selectById(walletId);
    }
}
