package cn.stylefeng.guns.modular.score.controller;

import cn.stylefeng.guns.modular.system.warpper.ScoreFlowWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ScoreFlow;
import cn.stylefeng.guns.modular.score.service.IScoreFlowService;

import java.util.List;
import java.util.Map;

/**
 * 积分流水控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 16:55:38
 */
@Controller
@RequestMapping("/scoreFlow")
public class ScoreFlowController extends BaseController {

    private String PREFIX = "/score/scoreFlow/";

    @Autowired
    private IScoreFlowService scoreFlowService;

    /**
     * 跳转到积分流水首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "scoreFlow.html";
    }

    /**
     * 跳转到添加积分流水
     */
    @RequestMapping("/scoreFlow_add")
    public String scoreFlowAdd() {
        return PREFIX + "scoreFlow_add.html";
    }

    /**
     * 跳转到修改积分流水
     */
    @RequestMapping("/scoreFlow_update/{scoreFlowId}")
    public String scoreFlowUpdate(@PathVariable Integer scoreFlowId, Model model) {
        ScoreFlow scoreFlow = scoreFlowService.selectById(scoreFlowId);
        model.addAttribute("item",scoreFlow);
        LogObjectHolder.me().set(scoreFlow);
        return PREFIX + "scoreFlow_edit.html";
    }

    /**
     * 获取积分流水列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        List<Map<String, Object>> lists = scoreFlowService.selectLists(phone);
        return new ScoreFlowWarpper(lists).wrap();
    }

    /**
     * 新增积分流水
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ScoreFlow scoreFlow) {
        scoreFlowService.insert(scoreFlow);
        return SUCCESS_TIP;
    }

    /**
     * 删除积分流水
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scoreFlowId) {
        scoreFlowService.deleteById(scoreFlowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改积分流水
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ScoreFlow scoreFlow) {
        scoreFlowService.updateById(scoreFlow);
        return SUCCESS_TIP;
    }

    /**
     * 积分流水详情
     */
    @RequestMapping(value = "/detail/{scoreFlowId}")
    @ResponseBody
    public Object detail(@PathVariable("scoreFlowId") Integer scoreFlowId) {
        return scoreFlowService.selectById(scoreFlowId);
    }
}
