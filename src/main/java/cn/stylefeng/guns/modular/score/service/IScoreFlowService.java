package cn.stylefeng.guns.modular.score.service;

import cn.stylefeng.guns.modular.system.model.ScoreFlow;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
public interface IScoreFlowService extends IService<ScoreFlow> {

    List<Map<String, Object>> selectLists(String phone);
}
