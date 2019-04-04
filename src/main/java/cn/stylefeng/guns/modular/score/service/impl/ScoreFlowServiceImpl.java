package cn.stylefeng.guns.modular.score.service.impl;

import cn.stylefeng.guns.modular.system.model.ScoreFlow;
import cn.stylefeng.guns.modular.system.dao.ScoreFlowMapper;
import cn.stylefeng.guns.modular.score.service.IScoreFlowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
@Service
public class ScoreFlowServiceImpl extends ServiceImpl<ScoreFlowMapper, ScoreFlow> implements IScoreFlowService {

    @Autowired
    private ScoreFlowMapper scoreFlowMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return scoreFlowMapper.selectLists(phone);
    }
}
