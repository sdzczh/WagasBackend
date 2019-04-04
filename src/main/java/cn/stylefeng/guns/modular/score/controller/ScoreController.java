package cn.stylefeng.guns.modular.score.controller;

import cn.stylefeng.guns.modular.system.warpper.ScoreWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Score;
import cn.stylefeng.guns.modular.score.service.IScoreService;

import java.util.List;
import java.util.Map;

/**
 * 会员积分管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-04 16:25:53
 */
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {

    private String PREFIX = "/score/score/";

    @Autowired
    private IScoreService scoreService;

    /**
     * 跳转到会员积分管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "score.html";
    }

    /**
     * 跳转到添加会员积分管理
     */
    @RequestMapping("/score_add")
    public String scoreAdd() {
        return PREFIX + "score_add.html";
    }

    /**
     * 跳转到修改会员积分管理
     */
    @RequestMapping("/score_update/{scoreId}")
    public String scoreUpdate(@PathVariable Integer scoreId, Model model) {
        Score score = scoreService.selectById(scoreId);
        model.addAttribute("item",score);
        LogObjectHolder.me().set(score);
        return PREFIX + "score_edit.html";
    }

    /**
     * 获取会员积分管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String phone) {
        List<Map<String, Object>> lists = scoreService.selectLists(phone);
        return new ScoreWarpper(lists).wrap();
    }

    /**
     * 新增会员积分管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Score score) {
        scoreService.insert(score);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员积分管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scoreId) {
        scoreService.deleteById(scoreId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员积分管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Score score) {
        scoreService.updateById(score);
        return SUCCESS_TIP;
    }

    /**
     * 会员积分管理详情
     */
    @RequestMapping(value = "/detail/{scoreId}")
    @ResponseBody
    public Object detail(@PathVariable("scoreId") Integer scoreId) {
        return scoreService.selectById(scoreId);
    }
}
